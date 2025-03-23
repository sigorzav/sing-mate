package com.sigorzav.singmate.user.repository;

import com.sigorzav.singmate.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자 정보
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
