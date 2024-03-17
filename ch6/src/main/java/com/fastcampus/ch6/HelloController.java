package com.fastcampus.ch6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. 원격 프로그램으로 등록
@RestController
public class HelloController {
    // 2. URL과 메소드를 연경
    @RequestMapping("/hello")
    public String hello() {   // 원격 프로그램
        return "Hello";
    }
}