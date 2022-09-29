package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

//	@RequestMapping(value = "/register/add", method = RequestMethod.GET)
//	@GetMapping("/register/add")	// Spring 4.3+
	
	// view와 연결해주는 것 외의 역할이 없는 컨트롤러 메서드는 view-controller로 대체할 수 있다.
//	@RequestMapping(value = "/register/add", method = {RequestMethod.GET, RequestMethod.POST})
	@RequestMapping("/register/add")
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping(value = "/register/save", method = RequestMethod.POST)
	@PostMapping("/register/save")	// Spring 4.3+
	public String save(User user, Model m) throws UnsupportedEncodingException {
		// 1. 유효성 검사
		if (isValid(user).equals("r")) {
			String msg = URLEncoder.encode("회원 가입 정보가 올바르지 않습니다.", "utf-8");
//			m.addAttribute("msg", msg);
//			return "redirect:/register/add";	// URL 재작성(rewriting)
			// 위와 같이 model을 이용한 코드 작성 시
			// 스프링이 자동으로 아래와 같은 쿼리스트링을 이용한 코드로 바꿔서 처리
			// /register/save의 model은 /register/add로 redirect 된 이후 쓸수 없게 되기 때문
			return "redirect:/register/add?msg="+msg;
		} else if (isValid(user).equals("f")) {
			return "forward:/register/add";
		}
		
		// 2. DB에 신규회원 정보를 저장
		
		
		return "registerInfo";
	}

	private String isValid(User user) {
		if (user.getId().equals("redirect")) {
			return "r";
		} else if (user.getId().equals("forward")) {
			return "f";
		}
		
		return "";
	}

}
