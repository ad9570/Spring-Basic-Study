package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model, PageHandler ph) {
        if (!loginCheck(request)) {
            return "redirect:/login/login?toURL=" + request.getRequestURL();
        }

        try {
            // posts
            Map<String, Integer> pageInfo = new HashMap<>();
            pageInfo.put("offset", (ph.getPage() - 1) * ph.getPageSize());
            pageInfo.put("pageSize", ph.getPageSize());

            List<BoardDto> boardList = boardService.getPage(pageInfo);
            model.addAttribute("boardList", boardList);

            // navigator
            int totalCnt = boardService.getCount();
            PageHandler navi = new PageHandler(totalCnt, ph.getPageSize(), ph.getPage());
            model.addAttribute("navi", navi);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }

        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}