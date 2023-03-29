package com.imjcker.jrebel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 异常处理方法
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // 处理异常
        String message = "发生异常：" + e.getMessage();
        log.error(message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}

