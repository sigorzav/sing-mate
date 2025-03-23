package com.sigorzav.singmate.user.repository;

import com.sigorzav.singmate.user.entity.UserGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자 선호 장르
 */
public interface UserGenreRepository extends JpaRepository<UserGenreEntity, Long> {

}
