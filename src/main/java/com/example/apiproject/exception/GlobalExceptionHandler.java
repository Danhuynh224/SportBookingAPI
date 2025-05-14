package com.example.apiproject.exception;

import com.example.apiproject.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        // Ghi log nếu cần
        return new ResponseEntity<>(
                ApiResponse.error(500, "Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(400, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ApiResponse<?>> handleForbidden(HttpClientErrorException.Forbidden ex) {
        return  new ResponseEntity<>(
                ApiResponse.error(403, ex.getMessage()),HttpStatus.FORBIDDEN
        );
    }
}
