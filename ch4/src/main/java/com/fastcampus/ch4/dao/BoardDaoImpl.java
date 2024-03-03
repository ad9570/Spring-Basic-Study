package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    SqlSession session;

    private final String namespace = "com.fastcampus.ch4.dao.BoardMapper.";

    @Override
    public BoardDto selectPost(Integer bno) throws Exception {
        return session.selectOne(namespace + "selectPost", bno);
    }

    @Override
    public int totCount() throws Exception {
        return session.selectOne(namespace + "totCount");
    }

    @Override
    public int deleteForAdmin(Integer bno) throws Exception {
        return session.delete(namespace + "deleteForAdmin", bno);
    }

    @Override
    public int deleteMyPost(BoardDto boardDto) throws Exception {
        return session.delete(namespace + "deleteMyPost", boardDto);
    }

    @Override
    public int insertPost(BoardDto boardDto) throws Exception {
        return session.insert(namespace + "insertPost", boardDto);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public List<BoardDto> selectPage(Map<String, Integer> pageInfo) throws Exception {
        return session.selectList(namespace + "selectPage", pageInfo);
    }

    @Override
    public int updatePost(BoardDto boardDto) throws Exception {
        return session.update(namespace + "updatePost", boardDto);
    }

    @Override
    public int updateCommentCnt(Map<String, Integer> commentCnt) throws Exception {
        return session.update(namespace + "updateCommentCnt", commentCnt);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace + "increaseViewCnt", bno);
    }
}