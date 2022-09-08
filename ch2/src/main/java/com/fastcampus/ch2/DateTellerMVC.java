package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class DateTellerMVC {	// http://localhost/ch2/getDateMVC?year=2022&month=9&day=8

	@RequestMapping("/getDateMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
	public void main(int year, int month, int day, HttpServletResponse response) throws IOException {
	  // 1. 입력
//      String year = request.getParameter("year");
//      String month = request.getParameter("month");
//      String day = request.getParameter("day");

//      int yyyy = Integer.parseInt(year);
//      int mm = Integer.parseInt(month);
//      int dd = Integer.parseInt(day);

      // 2. 처리(작업)
      Calendar cal = Calendar.getInstance();
      cal.set(year, month - 1, day);

      int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:일요일, 2:월요일, ...
      char date = " �Ͽ�ȭ�������".charAt(dayOfWeek);

      // 3. 출력
      response.setContentType("text/html");    // 응답의 형식을 html로 지정
      response.setCharacterEncoding("utf-8");  // 응답의 인코딩을 utf-8로 지정
      
      PrintWriter out = response.getWriter();  // 브라우저로의 출력 스트림(out)을 얻는다.
      out.println("<html>");
      out.println("<head>");
      out.println("</head>");
      out.println("<body>");
      out.println(year + "�� " + month + "�� " + day + "���� ");
      out.println(date + "�����Դϴ�.");
      out.println("</body>");
      out.println("</html>");
      out.close();
	}
	
}
