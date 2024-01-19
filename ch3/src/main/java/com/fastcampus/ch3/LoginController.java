package com.fastcampus.ch3;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) {
        // id와 pwd를 확인
        if (!loginCheck(id, pwd)) {
            // 일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", StandardCharsets.UTF_8);

            return "redirect:/login/login?msg=" + msg;
        }

        // id와 pwd가 일치하면, 세션 객체를 얻어오기
        HttpSession session = request.getSession();
        // 세션 객체에 id를 저장
        session.setAttribute("id", id);

        // 쿠키를 생성
        Cookie cookie = new Cookie("id", id);

        // 저장된 아이디가 없을 경우 기존 쿠키를 삭제
        if (!rememberId) {
            cookie.setMaxAge(0);
        }

        // 응답에 저장
        response.addCookie(cookie);
        // 3. 홈으로 이동
        return "redirect:" + StringUtils.defaultIfEmpty(toURL, "/");
    }

    private boolean loginCheck(String id, String pwd) {
        User user = userDao.selectUser(id);

        return user != null && user.getPwd().equals(pwd);
    }
}