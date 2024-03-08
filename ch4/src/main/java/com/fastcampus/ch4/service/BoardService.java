package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchOption;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;

    int removePost(BoardDto boardDto) throws Exception;

    int writePost(BoardDto boardDto) throws Exception;

    List<BoardDto> getBoardList() throws Exception;

    BoardDto readPost(Integer bno) throws Exception;

    List<BoardDto> getPage(Map<String, Integer> map) throws Exception;

    int modifyPost(BoardDto boardDto) throws Exception;

    int getSearchResultCnt(SearchOption searchOption) throws Exception;

    List<BoardDto> getSearchResultPage(SearchOption searchOption) throws Exception;
}
