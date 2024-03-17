package com.fastcampus.ch6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// 년월일을 입력 시 요일을 반환하는 원격 프로그램
@RestController
public class DateTeller {
    @RequestMapping("/getDate")
    public String getDate(HttpServletRequest request) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        System.out.println("year = " + year);
        System.out.println("month = " + month);
        System.out.println("day = " + day);

        return null;
    }
}