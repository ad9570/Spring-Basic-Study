package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String list(HttpServletRequest request, Model model, PageHandler pageHandler) {
        if (!loginCheck(request)) {
            return "redirect:/login/login?toURL=" + request.getRequestURL();
        }

        try {
            // page
            int currPage = pageHandler.getPage() == null ? 1 : pageHandler.getPage();
            int pageSize = pageHandler.getPageSize() == null ? 10 : pageHandler.getPageSize();
            int totalCnt = boardService.getCount();
            pageHandler.setPage(Math.max(1, currPage));
            pageHandler.setPage(Math.min(totalCnt, currPage));
            int destPage = pageHandler.getPage();

            // posts
            Map<String, Integer> pageInfo = new HashMap<>();
            pageInfo.put("offset", (destPage - 1) * pageSize);
            pageInfo.put("pageSize", pageSize);

            List<BoardDto> boardList = boardService.getPage(pageInfo);
            model.addAttribute("boardList", boardList);

            // navigator
            PageHandler navi = new PageHandler(totalCnt, pageSize, destPage);
            model.addAttribute("navi", navi);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }

        return "boardList";
    }

    @GetMapping("/read")
    public String read(Integer bno, Model model) {
        try {
            BoardDto boardDto = boardService.readPost(bno);
            model.addAttribute(boardDto);   // Key값 생략 시 타입의 첫글자를 소문자로 한 "boardDto"가 자동으로 키값으로 지정
            model.addAttribute("mode", "read");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "boardDtl";
    }

    @PostMapping("/remove")
    public String remove(BoardDto boardDto, PageHandler pageHandler, HttpSession session, RedirectAttributes attributes) {
        try {
            String writer = (String) session.getAttribute("id");
            boardDto.setWriter(writer);
            int result = boardService.removePost(boardDto);

            // redirectAttributes.addFlashAttribute를 이용해 1회만 사용하고 사라질 redirect parameter 세팅 가능
            if (result == 1) {
                attributes.addFlashAttribute("resultMsg", "delSuccess");
            } else {
                throw new Exception("Not your post.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            attributes.addFlashAttribute("resultMsg", "delFail");
        }

        Integer page = pageHandler.getPage();
        Integer pageSize = pageHandler.getPageSize();

        return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("mode", "write");

        return "boardDtl";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model model, HttpSession session, RedirectAttributes attributes) {
        try {
            String writer = (String) session.getAttribute("id");
            boardDto.setWriter(writer);
            int result = boardService.writePost(boardDto);

            if (result == 1) {
                attributes.addFlashAttribute("resultMsg", "wrtSuccess");
            } else {
                throw new Exception("Save failed.");
            }

            return "redirect:/board/list";
        } catch (Exception e) {
            model.addAttribute("resultMsg", "wrtFail");
            model.addAttribute("mode", "write");

            return "boardDtl";
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, PageHandler pageHandler, Model model, HttpSession session, RedirectAttributes attributes) {
        try {
            String writer = (String) session.getAttribute("id");
            boardDto.setWriter(writer);
            int result = boardService.modifyPost(boardDto);

            if (result == 1) {
                attributes.addFlashAttribute("resultMsg", "uptSuccess");
            } else {
                throw new Exception("Save failed.");
            }

            Integer page = pageHandler.getPage();
            Integer pageSize = pageHandler.getPageSize();

            return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
        } catch (Exception e) {
            model.addAttribute("resultMsg", "uptFail");
            model.addAttribute("mode", "read");

            return "boardDtl";
        }
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id") != null;
    }
}