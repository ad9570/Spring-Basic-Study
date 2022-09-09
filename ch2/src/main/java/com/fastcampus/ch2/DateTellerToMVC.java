package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class DateTellerToMVC {	// http://localhost/ch2/getDateToMVC?year=2022&month=9&day=8

	@RequestMapping("/getDateToMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	// 0. 입력
	public String main(int year, int month, int day, Model m) throws IOException {
//      String year = request.getParameter("year");
//      String month = request.getParameter("month");
//      String day = request.getParameter("day");

//      int yyyy = Integer.parseInt(year);
//      int mm = Integer.parseInt(month);
//      int dd = Integer.parseInt(day);
		
		// 1. 유효성 검사
		if (!isValid(year, month, day))
			return "dateError";

		// 2. 처리(작업)
		char date = getDate(year, month, day);
		
		// 3. 계산한 결과를 Model에 저장
		m.addAttribute("year", year);
		m.addAttribute("month", month);
		m.addAttribute("day", day);
		m.addAttribute("date", date);

		// 4. 출력
		return "date";	// /WEB-INF/views/date.jsp
//      response.setContentType("text/html");    // 응답의 형식을 html로 지정
//      response.setCharacterEncoding("utf-8");  // 응답의 인코딩을 utf-8로 지정
//      
//      PrintWriter out = response.getWriter();  // 브라우저로의 출력 스트림(out)을 얻는다.
//      out.println("<html>");
//      out.println("<head>");
//      out.println("</head>");
//      out.println("<body>");
//      out.println(year + "년 " + month + "월 " + day + "일은 ");
//      out.println(date + "요일입니다.");
//      out.println("</body>");
//      out.println("</html>");
//      out.close();
	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

	private char getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:일요일, 2:월요일, ...
		return " 일월화수목금토".charAt(dayOfWeek);
	}
	
}
