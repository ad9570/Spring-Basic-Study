package com.fastcampus.ch4.domain;

@SuppressWarnings("unused")
public class SearchOption {
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer offset = 0;
    private String keyword = "";
    private String field = "";

    public SearchOption() {
    }
    public SearchOption(Integer page, Integer pageSize, String keyword, String field) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.field = field;
        offset = (page - 1) * pageSize;
    }

    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "SearchOption{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", keyword='" + keyword + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}