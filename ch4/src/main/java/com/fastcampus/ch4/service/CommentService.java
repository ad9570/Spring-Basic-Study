package com.fastcampus.ch4.service;

import com.fastcampus.ch4.domain.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unused")
public interface CommentService {
    int getCmtCount(Integer bno) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int removeCmt(CommentDto commentDto) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int writeCmt(CommentDto commentDto) throws Exception;

    List<CommentDto> getCmtList(Integer bno) throws Exception;

    CommentDto readCmt(Integer cno) throws Exception;

    int modifyCmt(CommentDto commentDto) throws Exception;
}
