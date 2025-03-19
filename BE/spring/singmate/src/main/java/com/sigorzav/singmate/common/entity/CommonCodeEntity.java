package com.sigorzav.singmate.common.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sm_common_code")
public class CommonCodeEntity {

    // AUTO_INCREMENT
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_seq")
    private Integer codeSeq;

    // 구분
    @Column(name = "division", nullable = false, length = 50)
    private String division;

    // 코드 그룹
    @Column(name = "code_group", nullable = false, length = 50)
    private String codeGroup;

    // 코드
    @Column(name = "code", nullable = false, length = 50, unique = true)
    private String code;

    // 코드명
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // 정렬 순서
    @Column(name = "sort_order", nullable = false, columnDefinition = "int default 0")
    private Integer sortOrder;

    // 사용 여부
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive;

    // 생성일자
    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default current_timestamp")
    private LocalDateTime createdAt;

    // 수정일자
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;
}
