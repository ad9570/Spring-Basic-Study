package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto selectPost(Integer bno) throws Exception;

    int totCount() throws Exception;

    int deleteForAdmin(Integer bno) throws Exception;

    int deleteMyPost(BoardDto boardDto) throws Exception;

    int insertPost(BoardDto boardDto) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    List<BoardDto> selectPage(Map<String, Integer> pageInfo) throws Exception;

    int updatePost(BoardDto boardDto) throws Exception;

    int updateCommentCnt(Map<String, Integer> commentCnt) throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;
}
