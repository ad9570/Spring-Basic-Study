package com.fastcampus.ch4.domain;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
public class BoardDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private int commentCnt;
    private Date regDate;
    private Date updDate;

    public BoardDto() {
    }
    public BoardDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public BoardDto(Integer bno, String writer) {
        this.bno = bno;
        this.writer = writer;
    }

    public Integer getBno() {
        return bno;
    }
    public void setBno(Integer bno) {
        this.bno = bno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public int getViewCnt() {
        return viewCnt;
    }
    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
    public int getCommentCnt() {
        return commentCnt;
    }
    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }
    public Date getRegDate() {
        return regDate;
    }
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public Date getUpdDate() {
        return updDate;
    }
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(writer, boardDto.writer);
    }
    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, writer);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", viewCnt=" + viewCnt +
                ", commentCnt=" + commentCnt +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}