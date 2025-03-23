package com.sigorzav.singmate.common.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {

    CHECK_DUPLICATE_INVALID_TYPE("잘못된 중복 체크 타입입니다"),
    CHECK_DUPLICATE_FAIL("중복 확인 중 오류가 발생했습니다."),

    SIGNUP_SUCCESS("회원가입이 성공했습니다."),
    SIGNUP_FAIL("회원가입에 실패했습니다. 다시 시도해주세요.");

    private final String msg;

    MessageEnum(String msg) {
        this.msg = msg;
    }
}
