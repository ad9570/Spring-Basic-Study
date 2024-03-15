package com.fastcampus.ch4.domain;

import org.springframework.web.util.UriComponentsBuilder;

@SuppressWarnings("all")
public class SearchOption {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String keyword = "";
    private String field = "";

    public SearchOption() {
    }
    public SearchOption(Integer page, Integer pageSize, String keyword, String field) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.field = field;
    }
    public SearchOption(Integer page) {
        this(page, 10, "", "");
    }

    // iv 없이 getter만 존재해도 EL, mybatis에서 사용 가능
    public String getQueryString() {
        return getQueryString(page);
    }
    public String getQueryString(Integer page) {
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("keyword", keyword)
                .queryParam("field", field)
                .build().toString();
    }
    public Integer getOffset() {
        return (page - 1) * pageSize;
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
                ", keyword='" + keyword + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}