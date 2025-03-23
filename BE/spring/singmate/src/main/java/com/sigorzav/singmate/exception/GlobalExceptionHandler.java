package com.sigorzav.singmate.exception;

import com.sigorzav.singmate.api.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException e) {
        final HttpStatus status = e.getStatus();

        log.warn("CustomException Occurred - status: {}, message: {}", status, e.getMessage());

        return ResponseEntity
                .status(status)
                .body(ApiResponse.fail(false, status, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception e) {
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        log.error("GenericException Occurred", e);

        return ResponseEntity
                .status(status)
                .body(ApiResponse.fail(false, status, status.getReasonPhrase()));
    }
}
