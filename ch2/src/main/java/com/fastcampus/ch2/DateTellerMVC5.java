package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

//년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class DateTellerMVC5 {    // http://localhost/ch2/getDateMVC5?year=2022&month=9&day=15

    @ExceptionHandler(Exception.class)
    public String catcher(Exception ex) {
        ex.printStackTrace();
        return "dateError";
    }

    @RequestMapping("/getDateMVC5")
    // 0. 입력
//	public String main(@ModelAttribute("myDate") MyDate myDate, Model m) throws IOException {
    public String main(@ModelAttribute MyDate myDate, Model m) throws IOException {

        // 1. 유효성 검사
        if (!isValid(myDate)) {
            return "dateError";
        }

//		// 2. 처리(작업)
//		char date = getDate(myDate);
//		
//		// 3. 계산한 결과를 Model에 저장
//		m.addAttribute("myDate", myDate);
//		m.addAttribute("date", date);

        // 4. 출력
        return "date2";    // /WEB-INF/views/date2.jsp
    }

    private boolean isValid(MyDate myDate) {
        return isValid(myDate.getYear(), myDate.getMonth(), myDate.getDay());
    }

    private boolean isValid(int year, int month, int day) {
        if (year == -1 || month == -1 || day == -1)
            return false;
        return (1 <= month && month <= 12) && (1 <= day && day <= 31);
    }

    private @ModelAttribute("date") char getDate(MyDate myDate) {
        return getDate(myDate.getYear(), myDate.getMonth(), myDate.getDay());
    }

    private char getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);    // 1:일요일, 2:월요일, ...
        return " 일월화수목금토".charAt(dayOfWeek);
    }

}
