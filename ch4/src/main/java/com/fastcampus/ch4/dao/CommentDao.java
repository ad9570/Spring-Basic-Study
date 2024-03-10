package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    int cmtCount(Integer bno) throws Exception;

    int deleteCmt(CommentDto commentDto) throws Exception;

    int insertCmt(CommentDto commentDto) throws Exception;

    List<CommentDto> selectPostCmts(Integer bno) throws Exception;

    CommentDto selectCmt(Integer cno) throws Exception;

    int updateCmt(CommentDto commentDto) throws Exception;
}
