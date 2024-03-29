package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    public int getCount() throws Exception {
        return boardDao.totCount();
    }

    @Override
    public int removePost(BoardDto boardDto) throws Exception {
        return boardDao.deleteMyPost(boardDto);
    }

    @Override
    public int writePost(BoardDto boardDto) throws Exception {
        return boardDao.insertPost(boardDto);
    }

    @Override
    public List<BoardDto> getBoardList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public BoardDto readPost(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.selectPost(bno);
        boardDao.increaseViewCnt(bno);

        return boardDto;
    }

    @Override
    public List<BoardDto> getPage(Map<String, Integer> map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public int modifyPost(BoardDto boardDto) throws Exception {
        return boardDao.updatePost(boardDto);
    }

    @Override
    public int getSearchResultCnt(SearchOption searchOption) throws Exception {
        return boardDao.searchPostCnt(searchOption);
    }

    @Override
    public List<BoardDto> getSearchResultPage(SearchOption searchOption) throws Exception {
        return boardDao.searchPostList(searchOption);
    }
}
