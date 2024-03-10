package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        try {
            List<CommentDto> list = commentService.getCmtList(bno);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<String> remove(@RequestBody CommentDto commentDto, HttpSession session) {
        try {
            commentDto.setWriter((String) session.getAttribute("id"));
            int result = commentService.writeCmt(commentDto);
            if (result == 0) {
                throw new Exception("writeFail");
            }
            return new ResponseEntity<>("writeSuccess", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/comments/{cno}")    // /comments/4
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
        try {
            commentDto.setWriter((String) session.getAttribute("id"));
            commentDto.setCno(cno);
            int result = commentService.modifyCmt(commentDto);
            if (result == 0) {
                throw new Exception("modifyFail");
            }
            return new ResponseEntity<>("modifySuccess", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/comments/{cno}")   // /comments/4?bno=250
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
        try {
            int result = commentService.removeCmt(new CommentDto(cno, bno, (String) session.getAttribute("id")));
            if (result == 0) {
                throw new Exception("deleteFail");
            }
            return new ResponseEntity<>("deleteSuccess", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
