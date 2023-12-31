package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String list(HttpServletRequest request) {
        // 로그인을 안했으면 로그인 화면으로
        if (!logincheck(request)) {
            return "redirect:/login/login?toURL=" + request.getRequestURL();
        }

        // 로그인을 했으면 게시판 화면으로
        return "boardList";
    }

    private boolean logincheck(HttpServletRequest request) {
        // 1. 세션을 얻음
        HttpSession session = request.getSession();

        // 2. 세션에 id가 있는지 확인하여 있으면 true, 없으면 false 반환
        return session.getAttribute("id") != null;
    }
}
