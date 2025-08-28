package com.fbwoals.shop;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(Exception e) {
        // e.getMessage() → RuntimeException 생성자에 넣은 메시지 ex)"음수 안됩니다"
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
