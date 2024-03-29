package com.fastcampus.ch2;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestParamTest {
    @ExceptionHandler(Exception.class)
    public String catcher(Exception ex) {    // 예외 처리
        System.out.println(ex.getMessage());
        return "dateError";
    }

    @RequestMapping("/requestParam")
    public String main1(HttpServletRequest request) {
        String year = request.getParameter("year");
//		http://localhost/ch2/requestParam         ---->> year=null
//		http://localhost/ch2/requestParam?year=   ---->> year=""
//		http://localhost/ch2/requestParam?year    ---->> year=""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam2")
//	public String main2(@RequestParam(name="year", required=false) String year) {   // 아래와 동일
    public String main2(String year) {
//		http://localhost/ch2/requestParam2         ---->> year=null
//		http://localhost/ch2/requestParam2?year    ---->> year=""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam3")
//	public String main3(@RequestParam(name="year", required=true) String year) {   // 아래와 동일
    public String main3(@RequestParam String year) {
//		http://localhost/ch2/requestParam3         ---->> year=null   400 Bad Request. required=true라서
//		http://localhost/ch2/requestParam3?year    ---->> year=""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam4")
    public String main4(@RequestParam(required = false) String year) {
//		http://localhost/ch2/requestParam4         ---->> year=null
//		http://localhost/ch2/requestParam4?year    ---->> year=""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam5")
    public String main5(@RequestParam(required = false, defaultValue = "2022") String year) {
//		http://localhost/ch2/requestParam5         ---->> year=2022
//		http://localhost/ch2/requestParam5?year    ---->> year=2022
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

// =======================================================================

    @RequestMapping("/requestParam6")
    public String main6(int year) {
//		http://localhost/ch2/requestParam6        ---->> 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
//		http://localhost/ch2/requestParam6?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam7")
    public String main7(@RequestParam int year) {
//		http://localhost/ch2/requestParam7        ---->> 400 Bad Request, Required int parameter 'year' is not present
//		http://localhost/ch2/requestParam7?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam8")
    public String main8(@RequestParam(required = false) int year) {
        //	http://localhost/ch2/requestParam8        ---->> 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
        //	http://localhost/ch2/requestParam8?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam9")
    public String main9(@RequestParam int year) {
        //	http://localhost/ch2/requestParam9        ---->> 400 Bad Request, Required int parameter 'year' is not present
        //	http://localhost/ch2/requestParam9?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam10")
    public String main10(@RequestParam(defaultValue = "2022") int year) {
        //	http://localhost/ch2/requestParam10        ---->> year=2022
        //	http://localhost/ch2/requestParam10?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }

    @RequestMapping("/requestParam11")
    public String main11(@RequestParam(required = false, defaultValue = "2022") int year) {
//		http://localhost/ch2/requestParam11        ---->> year=2022
//		http://localhost/ch2/requestParam11?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: "" 
        System.out.printf("[%s]year = [%s]%n", new Date(), year);
        return "date";
    }
}