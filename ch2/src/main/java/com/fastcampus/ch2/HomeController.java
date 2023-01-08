package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller		// 1. 원격 호출 가능한 프로그램으로 등록
public class HomeController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)	// 2. URL과 메서드 연결(맵핑, mapping)
	public String home(HttpServletRequest request, Model model) {
		// getSession(true) : 세션이 없을 시 자동 생성(디폴트)
		// getSession(false) : 세션이 없어도 생성하지 않음
//		boolean sessionNotExists = request.getSession(false) == null;
//		boolean sessionIdNotExists = true;
//		if (!sessionNotExists)
//			sessionIdNotExists = request.getSession(false).getAttribute("id") == null;
//		model.addAttribute("login", sessionIdNotExists ? "Login" : "Logout");
//		model.addAttribute("loginLink", sessionIdNotExists ? "/login/login" : "/login/logout");
		return "index";
	}
	
}