package com.fastcampus.ch4.domain;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
public class CommentDto {
    private Integer cno;
    private Integer bno;
    private Integer parentCno;
    private String comment;
    private String writer;
    private Date regDate;
    private Date updDate;

    public CommentDto() {
    }
    public CommentDto(Integer bno, Integer parentCno, String comment, String writer) {
        this.bno = bno;
        this.parentCno = parentCno;
        this.comment = comment;
        this.writer = writer;
    }
    public CommentDto(Integer cno, Integer bno, String writer) {
        this.cno = cno;
        this.bno = bno;
        this.writer = writer;
    }

    public Integer getCno() {
        return cno;
    }
    public void setCno(Integer cno) {
        this.cno = cno;
    }
    public Integer getBno() {
        return bno;
    }
    public void setBno(Integer bno) {
        this.bno = bno;
    }
    public Integer getParentCno() {
        return parentCno;
    }
    public void setParentCno(Integer parentCno) {
        this.parentCno = parentCno;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
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
        CommentDto that = (CommentDto) o;
        return Objects.equals(cno, that.cno) && Objects.equals(bno, that.bno) && Objects.equals(parentCno, that.parentCno) && Objects.equals(comment, that.comment) && Objects.equals(writer, that.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, bno, parentCno, comment, writer);
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "cno=" + cno +
                ", bno=" + bno +
                ", parentCno=" + parentCno +
                ", comment='" + comment + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}