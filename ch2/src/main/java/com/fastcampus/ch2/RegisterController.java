package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
	
	// yyyy-MM-dd 형태의 문자열을 Date타입으로 변환
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService conversionService = binder.getConversionService();
		System.out.println(conversionService);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "birth", new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
	}

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
	public String save(User user, BindingResult br, Model m) throws UnsupportedEncodingException {
		// BindingResult br : '바로 앞'의 객체인 User user를 바인딩. '위치에 주의'해야함.
		System.out.println("BindingResult = " + br);
		
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
