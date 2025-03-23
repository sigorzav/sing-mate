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
    public ApiResponse(T data, String message) {
        this.statusCode = HttpStatus.OK.value();
        this.statusName = HttpStatus.OK.name();
        this.message = message;
        this.data = data;
    }

    // 실패 응답 생성자
    public ApiResponse(T data, HttpStatus statusCode, String message) {
        this.statusCode = statusCode.value();
        this.statusName = statusCode.name();
        this.message = message;
        this.data = data;
    }


    // --------------------------------------------------
    // ✅ 정적 메서드 (Static Factory)
    // --------------------------------------------------
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "Success");
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static <T> ApiResponse<T> fail(HttpStatus status, String message) {
        return new ApiResponse<>(null, status, message);
    }

    public static <T> ApiResponse<T> fail(T data, HttpStatus status, String message) {
        return new ApiResponse<>(data, status, message);
    }

    public static <T> ApiResponse<T> noContent(String message) {
        return new ApiResponse<>(null, HttpStatus.NO_CONTENT, message);
    }
}
