package com.fastcampus.ch6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Calendar;

// 년월일을 입력 시 요일을 반환하는 원격 프로그램
@RestController
public class DateTeller {
    @RequestMapping("/getDate")
    public void getDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 입력 - 전송받은 데이터
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        // 2. 작업
        // 2-1. 입력 텍스트를 정수로 변환
        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);
        // 2-2. 요일 계산
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(yyyy, mm - 1, dd);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  // 1 = 일요일, ... , 7 = 토요일
        char date = "일월화수목금토".charAt(dayOfWeek - 1);

        // 3. 출력 - 브라우저에 전송
        response.setCharacterEncoding("ms949");
        PrintWriter out = response.getWriter();
        /*out.println("<html>");
        out.println("<head></head>");
        out.println("<body>");
        out.println("year = " + year);
        out.println("month = " + month);
        out.println("day = " + day);
        out.println("date = " + date);
        out.println("</body>");
        out.println("</html>");*/
        out.println(
                """
                <html>
                <head></head>
                <body>
                year = %s <br/>
                month = %s <br/>
                day = %s <br/>
                date = %s
                </body>
                </html>
                """.formatted(year, month, day, date)
        );
    }
}