package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class DateTeller {
    //	public static void main(String[] args) {
    @RequestMapping("/getDate")
    public void getDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 입력
//      String year = args[0];
//      String month = args[1];
//      String day = args[2];
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);

        // 2. 처리(작업)
        Calendar cal = Calendar.getInstance();
        cal.set(yyyy, mm - 1, dd);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);    // 1:일요일, 2:월요일, ...
        char date = " 일월화수목금토".charAt(dayOfWeek);

        // 3. 출력
//      System.out.println(year + "년 " + month + "월 " + day + "일은 ");
//      System.out.println(date + "요일입니다.");
        response.setContentType("text/html");    // 응답의 형식을 html로 지정
        response.setCharacterEncoding("utf-8");  // 응답의 인코딩을 utf-8로 지정

        PrintWriter out = response.getWriter();  // 브라우저로의 출력 스트림(out)을 얻는다.
        /*out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println(year + "년 " + month + "월 " + day + "일은 ");
        out.println(date + "요일입니다.");
        out.println("</body>");
        out.println("</html>");
        out.close();*/
        out.println(
                """
                <html>
                <head></head>
                <body>
                %s년 %s월 %s일은
                %s요일입니다.
                </body>
                </body>
                </html>
                """.formatted(year, month, day, date)
        );
    }
}