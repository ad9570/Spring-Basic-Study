package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentDaoImplTest {
    @Autowired
    CommentDao commentDao;

    @Autowired
    BoardDao boardDao;

    private final static String WRITER = "asdf";
    private Integer bno = null;

    @Test
    public void countTest() throws Exception {
        getBno();
        int initCnt = commentDao.cmtCount(bno);

        int result = insertRandomId();
        int currCnt = commentDao.cmtCount(bno);
        assertEquals(initCnt + result, currCnt);
        syncCommentCnt(bno, 1);
    }

    @Test
    public void deleteTest() throws Exception {
        getBno();
        assertEquals(1, insertRandomId());
        syncCommentCnt(bno, 1);
        int initCnt = commentDao.cmtCount(bno);

        List<CommentDto> cmtList = commentDao.selectPostCmts(bno);
        assertFalse(cmtList.isEmpty());
        CommentDto commentDto = new CommentDto(0, bno, WRITER);
        assertEquals(0, commentDao.deleteCmt(commentDto));
        syncCommentCnt(bno, 0);

        commentDto.setCno(cmtList.get(0).getCno());
        commentDto.setWriter(WRITER + "222");
        assertEquals(0, commentDao.deleteCmt(commentDto));
        syncCommentCnt(bno, 0);

        commentDto.setWriter(WRITER);
        assertEquals(1, commentDao.deleteCmt(commentDto));
        syncCommentCnt(bno, -1);

        int currCnt = commentDao.cmtCount(bno);
        assertEquals(currCnt, initCnt - 1);
    }

    @Test
    public void insertTest() throws Exception {
        getBno();
        int initCnt = commentDao.cmtCount(bno);

        assertEquals(1, insertRandomId());
        int currCnt = commentDao.cmtCount(bno);
        assertEquals(currCnt, initCnt + 1);
        syncCommentCnt(bno, 1);

        assertEquals(1, insertRandomId());
        currCnt = commentDao.cmtCount(bno);
        assertEquals(currCnt, initCnt + 2);
        syncCommentCnt(bno, 1);
    }

    @Test
    public void selectAllTest() throws Exception {
        getBno();
        assertEquals(1, insertRandomId());
        syncCommentCnt(bno, 1);
        int initCnt = commentDao.cmtCount(bno);

        List<CommentDto> list = commentDao.selectPostCmts(bno);
        assertEquals(list.size(), initCnt);

        assertEquals(1, insertRandomId());
        syncCommentCnt(bno, 1);

        list = commentDao.selectPostCmts(bno);
        assertEquals(list.size(), initCnt + 1);
    }

    @Test
    public void selectTest() throws Exception {
        getBno();
        int id = (int) (Math.random() * 1000);
        CommentDto commentDto = new CommentDto(bno, 0, "comment" + id, WRITER);
        assertEquals(1, commentDao.insertCmt(commentDto));
        syncCommentCnt(bno, 1);

        List<CommentDto> cmtList = commentDao.selectPostCmts(bno);
        assertFalse(cmtList.isEmpty());

        CommentDto lastCmt = cmtList.get(cmtList.size() - 1);
        String comment = lastCmt.getComment();
        String writer = lastCmt.getWriter();
        assertEquals(comment, commentDto.getComment());
        assertEquals(writer, commentDto.getWriter());
    }

    @Test
    public void updateTest() throws Exception {
        getBno();
        int id = (int) (Math.random() * 1000);
        CommentDto commentDto = new CommentDto(bno, 0, "comment" + id, WRITER);
        assertEquals(1, commentDao.insertCmt(commentDto));
        syncCommentCnt(bno, 1);

        List<CommentDto> cmtList = commentDao.selectPostCmts(bno);
        assertFalse(cmtList.isEmpty());

        CommentDto lastCmt = cmtList.get(cmtList.size() - 1);
        commentDto.setCno(lastCmt.getCno());
        commentDto.setComment(lastCmt.getComment() + "update");
        assertEquals(1, commentDao.updateCmt(commentDto));

        lastCmt = commentDao.selectCmt(commentDto.getCno());
        assertEquals(lastCmt, commentDto);
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

    private int insertRandomId() throws Exception {
        int id = (int) (Math.random() * 1000);
        CommentDto commentDto = new CommentDto(bno, 0, "comment" + id, WRITER);
        return commentDao.insertCmt(commentDto);
    }

    private void syncCommentCnt(Integer bno, int cnt) throws Exception {
        Map<String, Integer> commentCnt = new HashMap<>();
        commentCnt.put("bno", bno);
        commentCnt.put("cnt", cnt);
        boardDao.updateCommentCnt(commentCnt);
    }
}