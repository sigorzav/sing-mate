package com.sigorzav.singmate.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonCodeDTO {

    private Integer codeSeq;    // 코드 시퀀스 
    private String division;    // 구분
    private String codeGroup;   // 코드그룹
    private String code;        // 코드
    private String name;        // 코드명
}
