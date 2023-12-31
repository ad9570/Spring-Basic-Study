package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("unused")
// 1. 원격 호출 가능한 프로그램으로 등록
@Controller
public class Hello {
    int iv = 10;    // 인스턴스 변수
    static int cv = 20;    // 클래스 변수

    // 2. URL과 메서드를 연결
//	@RequestMapping("/hello")
    public void main1() {    // 인스턴스 메서드 : iv, cv 둘 다 사용 가능
        System.out.println("Hello");    // 브라우저에는 출력할 내용이 없지만 콘솔에 찍힘
        System.out.println(cv);    // 20
        System.out.println(iv);    // 10
    }

    //	@RequestMapping("/hello")
    public static void main2() {    // static 메서드 : cv만 사용 가능
        System.out.println("Hello - static");
        System.out.println(cv);    // 20
//		System.out.println(iv);	// 에러
    }

    @RequestMapping("/hello")
    private void main3() {
        System.out.println("Hello - private");
        System.out.println(cv);
        System.out.println(iv);
    }
}