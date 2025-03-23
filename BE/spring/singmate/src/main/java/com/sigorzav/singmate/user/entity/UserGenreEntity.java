package com.sigorzav.singmate.user.entity;

import com.sigorzav.singmate.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sm_user_genre")
public class UserGenreEntity extends BaseEntity {

    // AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 사용자 시퀀스: 외래키 필드
    @Column(nullable = false)
    private Integer userSeq;

    // 장르 코드 시퀀스: 외래키 필드
    @Column(nullable = false)
    private Integer genreCodeSeq;

}
