//package com.fastcampus.ch3;
//
//import java.util.*;
//
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.*;
//import org.springframework.web.context.support.*;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//@Controller
//public class HomeController {
//    @Autowired
//    WebApplicationContext servletAC;    // Servlet AC는 Controller에서 @Autowired에서 주입받을 수 있음
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home(HttpServletRequest request) {
//        // 서블릿에서는 request.getServletContext()지만, 컨트롤러는 HttpServlet을 상속받지 않아서 아래와 같이 해야함.
//        ServletContext sc = request.getSession().getServletContext();    // ApplicationContextFacade
//        WebApplicationContext rootAC = WebApplicationContextUtils.getWebApplicationContext(sc);    // Root AC 얻기
//
//        if (rootAC != null) {
//            System.out.println("webApplicationContext = " + rootAC);
//            System.out.println("servletAC = " + servletAC);
//
//            System.out.println("rootAC.getBeanDefinitionNames() = " + Arrays.toString(rootAC.getBeanDefinitionNames()));
//            System.out.println("servletAC.getBeanDefinitionNames() = " + Arrays.toString(servletAC.getBeanDefinitionNames()));
//
//            System.out.println("rootAC.getBeanDefinitionCount() = " + rootAC.getBeanDefinitionCount());
//            System.out.println("servletAC.getBeanDefinitionCount() = " + servletAC.getBeanDefinitionCount());
//
//            // servletAC를 주입받으면 rootAC를 request를 통하지 않고도 얻을 수 있음
//            System.out.println("(servletAC.getParent() == rootAC) = " + (servletAC.getParent() == rootAC));
////        WebApplicationContext rootAC = (WebApplicationContext) servletAC.getParent();   // Root AC 얻기
//        }
//        return "home";
//    }
//}