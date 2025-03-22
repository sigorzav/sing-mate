package com.sigorzav.singmate.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeResponse implements Serializable {

    private String code;        // 코드
    private String name;        // 코드명
}
