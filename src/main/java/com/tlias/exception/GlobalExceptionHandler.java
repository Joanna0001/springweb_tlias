package com.tlias.exception;

import com.tlias.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)  // 捕获所有异常
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("Error");
    }
}
