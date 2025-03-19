package com.sigorzav.singmate.user.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.user.dto.request.CheckDuplicateRequestDTO;
import com.sigorzav.singmate.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 중복 데이터 체크
     */
    @Override
    public ApiResponse<Object> checkDuplicate(CheckDuplicateRequestDTO checkDuplicateRequestDTO) {
        String type = checkDuplicateRequestDTO.getType();
        String value = checkDuplicateRequestDTO.getValue();

        boolean isDuplicate = switch (type) {
            case "EMAIL" -> userRepository.existsByEmail(value);
            case "NICKNAME" -> userRepository.existsByNickname(value);
            default -> throw new IllegalArgumentException("Invalid Type: " + type);
        };
        return new ApiResponse<>(isDuplicate);
    }
}
