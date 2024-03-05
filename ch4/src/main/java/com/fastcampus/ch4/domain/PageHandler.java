package com.fastcampus.ch4.domain;

@SuppressWarnings("unused")
public class PageHandler {
    private Integer totalCnt;   // 총 게시물 수
    private Integer pageSize;   // 한 페이지의 크기
    private Integer naviSize;   // 페이지 내비게이션의 크기
    private Integer totalPage;  // 전체 페이지 수
    private Integer page;       // 현재 페이지
    private Integer beginPage;  // 내비게이션의 첫번째 페이지
    private Integer endPage;    // 내비게이션의 마지막 페이지
    private Boolean showPrev;   // 이전 내비게이션 페이지로 이동 가능
    private Boolean showNext;   // 다음 내비게이션 페이지로 이동 가능

    public PageHandler() {
    }
    public PageHandler(Integer totalCnt, Integer pageSize, Integer page) {
        this.totalCnt = totalCnt;
        this.pageSize = pageSize;
        this.page = page;

        naviSize = 10;
        int currentNavi = (int) Math.ceil((float) page / naviSize); // 현재 내비 순번 : naviSize의 N배 초과 시 N+1번째

        totalPage = (int) Math.ceil((float) totalCnt / pageSize);   // pageSize의 배수에서 1이라도 초과될 경우 새로운 페이지
        beginPage = (currentNavi - 1) * naviSize + 1;               // 시작페이지 = (현재 내비 순번 - 1) * naviSize + 1 = 이전 내비의 마지막페이지 + 1
        endPage = Math.min(beginPage + naviSize - 1, totalPage);    // 원래 내비게이션의 끝(naviSize 기준), 혹은 전체의 마지막 페이지 중 작은 값

        showPrev = (beginPage != 1);
        showNext = (!endPage.equals(totalPage));
    }

    public PageHandler(Integer totalCnt, Integer page) {
        this(totalCnt, 10, page);
    }

    void printNavi() {
        StringBuilder sb = new StringBuilder(45);
        sb.append("page = ").append(page).append("\n");
        if (showPrev) {
            sb.append("◀◀ ");
        }
        for (int i = beginPage; i <= endPage; i++) {
            sb.append(i).append(" ");
        }
        if (showNext) {
            sb.append("▶▶");
        }
        System.out.println(sb);
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }
    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getNaviSize() {
        return naviSize;
    }
    public void setNaviSize(Integer naviSize) {
        this.naviSize = naviSize;
    }
    public Integer getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getBeginPage() {
        return beginPage;
    }
    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }
    public Integer getEndPage() {
        return endPage;
    }
    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }
    public Boolean getShowPrev() {
        return showPrev;
    }
    public void setShowPrev(Boolean showPrev) {
        this.showPrev = showPrev;
    }
    public Boolean getShowNext() {
        return showNext;
    }
    public void setShowNext(Boolean showNext) {
        this.showNext = showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}