package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice	// 모든 패키지의 컨트롤러 예외 처리 담당
@ControllerAdvice("com.fastcampus.ch2")    // 특정 패키지의 컨트롤러 예외 처리 담당
public class GlobalCatcher {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)    // 200 -> 500
    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher2(Exception e) {
        System.out.println("global catcher(NullPointerException, FileNotFoundException)가 처리");
        return "error2";
    }

}
