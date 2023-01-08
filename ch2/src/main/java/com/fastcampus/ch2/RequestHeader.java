package com.fastcampus.ch2;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestHeader {
	@RequestMapping("/requestHeader")
	public void main(HttpServletRequest request) {

		Enumeration<?> e = request.getHeaderNames();	// 요청한 헤더의 Iterator

		while (e.hasMoreElements()) {	// 요청한 헤더를 모두 출력
			String name = (String)e.nextElement();
			System.out.println(name + ":" + request.getHeader(name));
		}
	}
}