package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

//년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class DateTellerMVC {    // http://localhost/ch2/getDateMVC?year=2022&month=9&day=8

    @RequestMapping("/getDateMVC")
    // 0. 입력
    public String main(int year, int month, int day, Model m) throws IOException {
//	public ModelAndView main(int year, int month, int day) throws IOException {
//		ModelAndView mv = new ModelAndView();

        // 1. 유효성 검사
        if (!isValid(year, month, day)) {
            return "dateError";
//			mv.setViewName("dateError");
//			return mv;
        }

        // 2. 처리(작업)
        char date = getDate(year, month, day);

        // 3. 계산한 결과를 Model에 저장
        m.addAttribute("year", year);
        m.addAttribute("month", month);
        m.addAttribute("day", day);
        m.addAttribute("date", date);
//		mv.addObject("year", year);
//		mv.addObject("month", month);
//		mv.addObject("day", day);
//		mv.addObject("date", date);

        // 4. 출력
        return "date";    // /WEB-INF/views/date.jsp
//		mv.setViewName("date");
//		return mv;
    }

    private boolean isValid(int year, int month, int day) {
        if (year == -1 || month == -1 || day == -1)
            return false;
        return (1 <= month && month <= 12) && (1 <= day && day <= 31);
    }

    private char getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);    // 1:일요일, 2:월요일, ...
        return " 일월화수목금토".charAt(dayOfWeek);
    }

}
