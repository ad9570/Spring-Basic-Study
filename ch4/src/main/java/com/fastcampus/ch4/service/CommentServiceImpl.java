package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.*;
import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    BoardDao boardDao;
    CommentDao commentDao;

    // @Autowired를 이용한 iv 주입 대신 생성자 주입도 사용 가능
    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }

    @Override
    public int getCmtCount(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.selectPost(bno);
        if (boardDto == null) {
            throw new Exception("게시글이 없습니다. : [" + bno + "]");
        }

        return commentDao.cmtCount(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeCmt(CommentDto commentDto) throws Exception {
        int rowCnt = commentDao.deleteCmt(commentDto);

        if (rowCnt == 0) {
            throw new Exception("댓글 삭제 실패 : [cno = " + commentDto.getCno() + "]");
        }

        Map<String, Integer> commentCnt = new HashMap<>();
        commentCnt.put("bno", commentDto.getBno());
        commentCnt.put("cnt", -1);
        rowCnt = boardDao.updateCommentCnt(commentCnt);

        if (rowCnt == 0) {
            throw new Exception("게시글 정보 조회 실패 : [bno = " + commentDto.getBno() + "]");
        }

        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int writeCmt(CommentDto commentDto) throws Exception {
        int rowCnt = commentDao.insertCmt(commentDto);

        if (rowCnt == 0) {
            throw new Exception("댓글 저장 실패");
        }

        Map<String, Integer> commentCnt = new HashMap<>();
        commentCnt.put("bno", commentDto.getBno());
        commentCnt.put("cnt", 1);
        rowCnt = boardDao.updateCommentCnt(commentCnt);

        if (rowCnt == 0) {
            throw new Exception("게시글 정보 조회 실패 : [bno = " + commentDto.getBno() + "]");
        }

        return rowCnt;
    }

    @Override
    public List<CommentDto> getCmtList(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.selectPost(bno);
        if (boardDto == null) {
            throw new Exception("게시글이 없습니다. : [" + bno + "]");
        }

        return commentDao.selectPostCmts(bno);
    }

    @Override
    public CommentDto readCmt(Integer cno) throws Exception {
        return commentDao.selectCmt(cno);
    }

    @Override
    public int modifyCmt(CommentDto commentDto) throws Exception {
        return commentDao.updateCmt(commentDto);
    }
}