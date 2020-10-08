package com.hyp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @SuppressWarnings("rawtypes")
    public ResponseEntity exceptionHandler() {
        return ResponseEntity.ok("It is ok.");
    }
}
