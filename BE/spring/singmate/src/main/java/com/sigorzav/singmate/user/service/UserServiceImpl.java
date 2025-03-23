package com.sigorzav.singmate.user.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.api.service.RedisService;
import com.sigorzav.singmate.common.cache.CommonCodeCache;
import com.sigorzav.singmate.common.enums.CommonCodeEnum;
import com.sigorzav.singmate.common.enums.MessageEnum;
import com.sigorzav.singmate.common.enums.UserStatusEnum;
import com.sigorzav.singmate.exception.CustomException;
import com.sigorzav.singmate.user.dto.request.CheckDuplicateRequestDTO;
import com.sigorzav.singmate.user.dto.request.SignUpRequestDTO;
import com.sigorzav.singmate.user.entity.UserEntity;
import com.sigorzav.singmate.user.entity.UserGenreEntity;
import com.sigorzav.singmate.user.repository.UserGenreRepository;
import com.sigorzav.singmate.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final RedisService redisService;
    private final UserRepository userRepository;
    private final UserGenreRepository userGenreRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * ✅ 사용자 중복 데이터 체크
     */
    @Override
    public ApiResponse<Object> checkDuplicate(CheckDuplicateRequestDTO checkDuplicateRequestDTO) {
        String type = checkDuplicateRequestDTO.getType();
        String value = checkDuplicateRequestDTO.getValue();

        try {
            boolean isDuplicate = switch (type) {
                case "EMAIL" -> userRepository.existsByEmail(value);
                case "NICKNAME" -> userRepository.existsByNickname(value);
                default -> throw new CustomException(
                        MessageEnum.CHECK_DUPLICATE_INVALID_TYPE.getMsg() + ": " + type,
                        HttpStatus.BAD_REQUEST
                );
            };
            return ApiResponse.success(isDuplicate);
        } catch (Exception e) {
            log.error("checkDuplicate error", e);
            throw new CustomException(MessageEnum.CHECK_DUPLICATE_FAIL.getMsg(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * ✅ 회원가입
     */
    @Override
    public ApiResponse<Object> signUp(SignUpRequestDTO signUpRequestDTO) {
        try {
            // [사용자 정보 저장]
            UserEntity userEntity = modelMapper.map(signUpRequestDTO, UserEntity.class);

            // 비밀번호 암호화
            String encryptedPassword = passwordEncoder.encode(signUpRequestDTO.getPassword());
            userEntity.setPassword(encryptedPassword);

            // 계정 상태 코드
            int accountStatusCodeSeq = redisService.getCommonCodeFromRedis(CommonCodeEnum.USER_ACCOUNT_STATUS, UserStatusEnum.ACTIVE.name())
                    .map(CommonCodeCache::getCodeSeq)
                    .orElseThrow(() -> new IllegalStateException("UserStatusEnum.ACTIVE.name() not found"));
            userEntity.setAccountStatusCodeSeq(accountStatusCodeSeq);
            UserEntity savedEntity = userRepository.save(userEntity);
            int userSeq = savedEntity.getUserSeq();

            // [사용자 선호 장르 저장]
            List<UserGenreEntity> userGenreEntityList = new ArrayList<>();
            for (String genreCode : signUpRequestDTO.getGenres()) {
                redisService.getCommonCodeFromRedis(CommonCodeEnum.SONG_GENRE, genreCode)
                        .ifPresent(code -> {
                            UserGenreEntity entity = new UserGenreEntity();
                            entity.setUserSeq(userSeq);
                            entity.setGenreCodeSeq(code.getCodeSeq());
                            userGenreEntityList.add(entity);
                        });
            }
            userGenreRepository.saveAll(userGenreEntityList);
            return ApiResponse.success(true, MessageEnum.SIGNUP_SUCCESS.getMsg());
        } catch (Exception e) {
            log.error("signUp error", e);
            throw new CustomException(MessageEnum.SIGNUP_FAIL.getMsg(), HttpStatus.BAD_REQUEST);
        }
    }

}
