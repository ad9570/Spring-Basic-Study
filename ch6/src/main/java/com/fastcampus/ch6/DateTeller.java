package com.fastcampus.ch6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Calendar;

// 년월일을 입력 시 요일을 반환하는 원격 프로그램
@Controller
public class DateTeller {
    @RequestMapping("/getDate")
    public void getDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 입력
        // 1-1. 전송받은 데이터
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        // 1-2. 입력 텍스트를 정수로 변환
        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);

        // 2. 작업
        // 2-1. 유효성 체크
        if (isInvalid(yyyy, mm, dd)) {
            response.setCharacterEncoding("ms949");
            PrintWriter out = response.getWriter();
            out.println(
                    """
                    <html>
                    <head></head>
                    <body>
                    <h1>ERROR</h1>
                    </body>
                    </html>
                    """
            );
            return;
        }

        // 2-2. 요일 계산
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(yyyy, mm - 1, dd);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  // 1 = 일요일, ... , 7 = 토요일
        String date = "일월화수목금토".charAt(dayOfWeek - 1) + "요일";

        // 3. 출력 - 브라우저에 전송
        response.setCharacterEncoding("ms949");
        PrintWriter out = response.getWriter();
        out.println(
                """
                <html>
                <head></head>
                <body>
                <h1>%s년 %s월 %s일 : %s</h1>
                </body>
                </html>
                """.formatted(year, month, day, date)
        );
    }

    @RequestMapping("/getDateMVC")
    public String getDateMVC(int year, int month, int day, Model model) {
        // 1. 입력 ↑
        // 1-1. 전송받은 데이터를 Reflection API로 같은 이름의 파라미터와 자동 맵핑
        // 1-2. 입력 텍스트를 정수로 변환

        // 2. 작업
        // 2-1. 유효성 체크
        if (isInvalid(year, month, day)) {
            return "dateError";
        }

        // 2-1. 요일 계산
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  // 1 = 일요일, ... , 7 = 토요일
        String date = "일월화수목금토".charAt(dayOfWeek - 1) + "요일";

        // 2-2. 작업한 결과를 모델에 저장 - Dispatcher Servlet에서 생성한 객체를 참조
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("date", date);

        // 3. 출력 - 브라우저에 전송 ↓
        // View의 이름을 반환 : /resources/templates/date.html
        return "date";
    }

    @RequestMapping("/date")
    public void date(int year, int month, int day, Model model) {
        // 1. 입력 ↑
        // 1-1. 전송받은 데이터를 Reflection API로 같은 이름의 파라미터와 자동 맵핑
        // 1-2. 입력 텍스트를 정수로 변환

        // 2. 작업
        // 2-1. View 이름이 URL과 같아야 하므로 dateError 반환 불가능
        if (isInvalid(year, month, day)) {
            model.addAttribute("year", year);
            model.addAttribute("month", month);
            model.addAttribute("day", day);
            model.addAttribute("date", "ERROR");
            return;
        }

        // 2-2. 요일 계산
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  // 1 = 일요일, ... , 7 = 토요일
        String date = "일월화수목금토".charAt(dayOfWeek - 1) + "요일";

        // 2-3. 작업한 결과를 모델에 저장 - Dispatcher Servlet에서 생성한 객체를 참조
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("date", date);

        // 3. 출력 - 브라우저에 전송
        // Mapping된 URL과 이름이 일치하는 View의 이름을 반환 : /resources/templates/date.html
    }

    @RequestMapping("/getDateMaV")
    public ModelAndView getDateMaV(int year, int month, int day) {
        // 1. 입력 ↑
        // 1-1. 전송받은 데이터를 Reflection API로 같은 이름의 파라미터와 자동 맵핑
        // 1-2. 입력 텍스트를 정수로 변환

        // 2. 작업
        // 2-1. 모델 객체 생성
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dateError");

        // 2-2. 유효성 체크
        if (isInvalid(year, month, day)) {
            return modelAndView;
        }

        // 2-3. 요일 계산
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);  // 1 = 일요일, ... , 7 = 토요일
        String date = "일월화수목금토".charAt(dayOfWeek - 1) + "요일";

        // 2-4. 작업한 결과를 모델에 저장
        modelAndView.addObject("year", year);
        modelAndView.addObject("month", month);
        modelAndView.addObject("day", day);
        modelAndView.addObject("date", date);

        // 3. 출력 - 브라우저에 전송 ↓
        // Model 객체와 View의 이름을 반환 : /resources/templates/date.html
        modelAndView.setViewName("date");
        return modelAndView;
    }

    private boolean isInvalid(int year, int month, int day) {
        if (day < 1) {
            return true;
        }

        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return day > 31;
            }
            case 4, 6 ,9, 11 -> {
                return day > 30;
            }
            case 2 -> {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    return day > 29;
                } else {
                    return day > 28;
                }
            }
            default -> {
                return true;
            }
        }
    }
}