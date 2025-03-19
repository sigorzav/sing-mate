package com.sigorzav.singmate.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sm_user")
public class UserEntity {

    // AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 이메일: 고유값
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    // 닉네임: 고유값
    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    // 생년월일: DATE 형식과 매핑
    @Column(nullable = false, length = 8)
    private String birthDate;

    // 성별
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Gender gender;

    // 프로필 이미지 시퀀스: 외래키 필드 (nullable)
    @Column
    private Integer profileImageSeq;

    // 계정 상태 코드
    @Column(nullable = false)
    private Integer accountStatusCode;

    // 생성 일자 (업데이트 불가)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 수정 일자
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // EnumType: Gender 타입 정의
    public enum Gender {
        M, // 남성
        F  // 여성
    }

}
