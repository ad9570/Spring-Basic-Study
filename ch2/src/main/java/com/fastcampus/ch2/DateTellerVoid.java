package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class DateTellerVoid {	// http://localhost/ch2/getDateMVC?year=2022&month=9&day=8

	@RequestMapping("/getDateVoid")
	// 0. 입력
	public void main(int year, int month, int day, Model model) throws IOException {

		// 1. 처리(작업)
		char date = getDate(year, month, day);
		
		// 2. 계산한 결과를 Model에 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("date", date);
	}

	private char getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:일요일, 2:월요일, ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}
	
}
