package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController2 {
	
	@RequestMapping("/ex4")
	public void main4() throws Exception {
		throw new Exception("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex5")
	public void main5() throws Exception {
		throw new NullPointerException("NullPointerException이 발생했습니다.");
	}
	
	@RequestMapping("/ex6")
	public void main6() throws Exception {
		throw new FileNotFoundException("FileNotFoundException이 발생했습니다.");
	}
	
}
