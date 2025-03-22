package com.sigorzav.singmate.common.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeCache implements Serializable {

    private String code;        // 코드
    private String name;        // 코드명
    private Integer codeSeq;    // 코드 시퀀스
}
