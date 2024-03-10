package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private SqlSession session;

    private final static String namespace = "com.fastcampus.ch4.dao.CommentMapper.";

    @Override
    public int cmtCount(Integer bno) throws Exception {
        return session.selectOne(namespace + "cmtCount", bno);
    }

    @Override
    public int deleteCmt(CommentDto commentDto) throws Exception {
        return session.delete(namespace + "deleteCmt", commentDto);
    }

    @Override
    public int insertCmt(CommentDto commentDto) throws Exception {
        return session.insert(namespace + "insertCmt", commentDto);
    }

    @Override
    public List<CommentDto> selectPostCmts(Integer bno) throws Exception {
        return session.selectList(namespace + "selectPostCmts", bno);
    }

    @Override
    public CommentDto selectCmt(Integer cno) throws Exception {
        return session.selectOne(namespace + "selectCmt", cno);
    }

    @Override
    public int updateCmt(CommentDto commentDto) throws Exception {
        return session.update(namespace + "updateCmt", commentDto);
    }
}