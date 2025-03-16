package com.sigorzav.singmate.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private int statusCode;         // 상태 코드
    private String statusName;      // 상태 코드 이름
    private String message;         // 응답 메세지
    private T data;                 // 응답 데이터

    // 성공 응답 생성자
    public ApiResponse(T data) {
        this.statusCode = HttpStatus.OK.value();
        this.statusName = HttpStatus.OK.name();
        this.message = "Success";
        this.data = data;
    }

    // 실패 응답 생성자
    public ApiResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode.value();
        this.statusName = statusCode.name();
        this.message = message;
        this.data = null;
    }
}
