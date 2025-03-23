package com.sigorzav.singmate.common.enums;

import lombok.Getter;

@Getter
public enum CommonCodeEnum {

    SONG_GENRE("SONG_GENRE"),
    USER_ACCOUNT_STATUS("USER_ACCOUNT_STATUS");

    private final String key;

    CommonCodeEnum(String key) {
        this.key = key;
    }
}
