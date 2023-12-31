package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
@ResponseStatus(HttpStatus.BAD_REQUEST)    // 500 -> 400
class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyException(String msg) {
        super(msg);
    }

    public MyException() {
        this("");
    }
}

@SuppressWarnings("unused")
class MappingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MappingException(String msg) {
        super(msg);
    }

    public MappingException() {
        this("");
    }
}

@Controller
public class ExceptionController2 {
    @RequestMapping("/ex4")
    public void main4() throws Exception {
        throw new Exception("예외가 발생했습니다.");
    }

    @RequestMapping("/ex5")
    public void main5() throws NullPointerException {
        throw new NullPointerException("NullPointerException이 발생했습니다.");
    }

    @RequestMapping("/ex6")
    public void main6() throws FileNotFoundException {
        throw new FileNotFoundException("FileNotFoundException이 발생했습니다.");
    }

    @RequestMapping("/ex7")
    public void main7() throws RuntimeException {
        throw new MyException("사용자 정의 예외가 발생했습니다.");
    }

    @RequestMapping("/ex8")
    public void main8() throws RuntimeException {
        throw new MappingException("사용자 정의 예외가 발생했습니다.");
    }
}