package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice	// 모든 패키지의 컨트롤러 예외 처리 담당
@ControllerAdvice("com.fastcampus.ch2")	// 특정 패키지의 컨트롤러 예외 처리 담당
public class GlobalCatcher {
	
	@ExceptionHandler(Exception.class)
	public String catcher1(Exception e, Model m) {
		System.out.println("global catcher1(Exception)이 처리");
		m.addAttribute("ex", e);
		return "error";
	}
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception e, Model m) {
		System.out.println("global catcher2(NullPointerException, FileNotFoundException)가 처리");
		m.addAttribute("ex", e);
		return "error";
	}

}
