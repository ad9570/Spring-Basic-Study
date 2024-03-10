package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.dao.CommentDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.CommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentServiceImplTest {
    @Autowired
    CommentService commentService;

    @Autowired
    CommentDao commentDao;

    @Autowired
    BoardDao boardDao;

    private final static String BOARD_WRITER = "asdf";
    private final static String COMMENT_WRITER = "qwer";
    private final static String OTHER_WRITER = "zxcv";
    private Integer bno = null;

    @Test
    public void removeTest() throws Exception {
        assertEquals(1, insertPostRandomId());
        getBno();
        System.out.println("bno = " + bno);

        BoardDto lastPost = boardDao.selectPost(bno);
        assertEquals(0, lastPost.getCommentCnt());

        assertEquals(1, insertCmtRandomId(bno));
        lastPost = boardDao.selectPost(bno);
        int currCmtCnt = lastPost.getCommentCnt();
        assertEquals(1, currCmtCnt);

        Integer cno = commentDao.selectPostCmts(bno).get(0).getCno();

        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
        removeTxTest(cno, bno, OTHER_WRITER, currCmtCnt);

        removeTxTest(cno, 0, COMMENT_WRITER, currCmtCnt);

        removeTxTest(cno, bno, COMMENT_WRITER, currCmtCnt);
    }

    @Test
    public void writeTest() throws Exception {
        assertEquals(1, insertPostRandomId());
        getBno();
        System.out.println("bno = " + bno);

        BoardDto lastPost = boardDao.selectPost(bno);
        int currPostCmtCnt = lastPost.getCommentCnt();
        assertEquals(0, currPostCmtCnt);

        int currCmtCnt = commentDao.cmtCount(bno);
        assertEquals(0, currCmtCnt);

        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
        writeTxTest(0, currPostCmtCnt, currCmtCnt);

        writeTxTest(bno, currPostCmtCnt, currCmtCnt);
    }

    private void getBno() throws Exception {
        if (bno != null) {
            return;
        }

        List<BoardDto> postList = boardDao.selectAll();
        if (postList.isEmpty()) {
            return;
        }

        BoardDto lastPost = postList.get(0);
        bno = lastPost.getBno();
    }

    private int insertCmtRandomId(Integer bno) throws Exception {
        int id = (int) (Math.random() * 1000);
        CommentDto commentDto = new CommentDto(bno, 0, "comment" + id, COMMENT_WRITER);
        return commentService.writeCmt(commentDto);
    }

    private int insertPostRandomId() throws Exception {
        int id = (int) (Math.random() * 1000);
        BoardDto boardDto = new BoardDto("no title" + id, "no content" + id, BOARD_WRITER);
        return boardDao.insertPost(boardDto);
    }

    private void removeTxTest(Integer cno, Integer bno, String writer, int currCmtCnt) throws Exception {
        BoardDto lastPost;
        try {
            commentService.removeCmt(new CommentDto(cno, bno, writer));
            lastPost = boardDao.selectPost(this.bno);
            assertEquals(lastPost.getCommentCnt(), currCmtCnt - 1);
            assertNull(commentDao.selectCmt(cno));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lastPost = boardDao.selectPost(this.bno);
            assertEquals(lastPost.getCommentCnt(), currCmtCnt);
            assertNotNull(commentDao.selectCmt(cno));
        }
    }

    private void writeTxTest(Integer bno, int currPostCmtCnt, int currCmtCnt) throws Exception {
        BoardDto lastPost;
        try {
            insertCmtRandomId(bno);
            lastPost = boardDao.selectPost(this.bno);
            assertEquals(lastPost.getCommentCnt(), currPostCmtCnt + 1);
            assertEquals(commentDao.cmtCount(this.bno), currCmtCnt + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            lastPost = boardDao.selectPost(this.bno);
            assertEquals(lastPost.getCommentCnt(), currPostCmtCnt);
            assertEquals(commentDao.cmtCount(this.bno), currCmtCnt);
        }
    }
}