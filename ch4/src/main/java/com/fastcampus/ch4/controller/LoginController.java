package com.fastcampus.ch4.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastcampus.ch4.dao.*;
import com.fastcampus.ch4.domain.*;
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
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId, HttpServletRequest request, HttpServletResponse response) {
        if (!loginCheck(id, pwd)) {
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", StandardCharsets.UTF_8);

            return "redirect:/login/login?msg=" + msg;
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        Cookie cookie = new Cookie("id", id);
        if (!rememberId) {
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);

        toURL = StringUtils.defaultIfEmpty(toURL, "/");

        return "redirect:" + toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        User user;

        try {
            user = userDao.selectUser(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return user != null && user.getPwd().equals(pwd);
    }
}