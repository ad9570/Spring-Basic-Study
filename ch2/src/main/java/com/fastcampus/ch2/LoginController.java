package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response) throws Exception {
//	public String login(String id, String pwd, String rememberId, HttpServletResponse response) throws Exception {
		System.out.println(rememberId);	// "on" : <input type="checkbox"> 체크시 기본 value="on"
										// 체크가 안되어있다면 null
										// 다른 값을 받고싶다면 <input>의 value 속성을 직접 입력
										// Spring에서 boolean 타입으로 자동 형변환 가능
										// 체크시 true / 미체크시 false
		// 아이디, 비밀번호 체크
		if (!loginCheck(id, pwd)) {
			String msg = URLEncoder.encode("아이디 또는 비밀번호가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg=" + msg;
		}
		
		// rememberId 값에 따라 쿠키 생성 or 삭제
		Cookie cookie = new Cookie("id", "asdf");
		if (rememberId) {
			cookie.setMaxAge(86400);
		} else {
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		// 문자열.equals(null)은 가능하지만 null.equals(문자열)은 불가능
		return "asdf".equals(id) && "1234".equals(pwd);
	}

}
