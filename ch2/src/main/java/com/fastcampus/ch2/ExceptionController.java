package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

	// @ExceptionHandler : try - catch의 catch 블록 역할
	@ExceptionHandler(Exception.class)	// Exception : 모든 예외의 조상
	public String catcher1(Exception e, Model m) {	// Model : 컨트롤러의 Model과 다른 객체
		System.out.println("local catcher1(Exception)이 처리");
		m.addAttribute("ex", e);
		return "error";					// 컨트롤러 메서드와 마찬가지로 view 이름 반환
	}
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception e, Model m) {
		System.out.println("local catcher2(NullPointerException, FileNotFoundException)가 처리");
		m.addAttribute("ex", e);
		return "error";
	}
	
	@RequestMapping("/ex1")
	public void main1(Model m) throws Exception {
		m.addAttribute("msg", "message from ExceptionController.main1()");
		throw new Exception("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex2")
	public void main2() throws Exception {
		throw new NullPointerException("NullPointerException이 발생했습니다.");
	}
	
	@RequestMapping("/ex3")
	public void main3() throws Exception {
		throw new FileNotFoundException("FileNotFoundException이 발생했습니다.");
	}
	
}
