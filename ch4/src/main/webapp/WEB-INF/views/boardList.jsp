<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginId" value="${pageContext.request.getSession(false) == null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${empty loginId ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fastcampus</title>
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>"/>
<link rel="stylesheet" href="<c:url value='/css/board-list.css'/>"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/test'/>">REST</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<div style="text-align: center">
    <div class="board-container">
        <div class="search-container">
            <form action="<c:url value="/board/list"/>" class="search-form" method="get">
                <select class="search-option" name="field">
                    <option value="A" ${searchOption.field eq 'A' || searchOption.field eq '' ? 'selected' : ''}>제목+내용</option>
                    <option value="T" ${searchOption.field eq 'T' ? 'selected' : ''}>제목만</option>
                    <option value="W" ${searchOption.field eq 'W' ? 'selected' : ''}>작성자</option>
                </select>

                <input type="text" name="keyword" class="search-input" value="${searchOption.keyword}" placeholder="검색어를 입력해주세요"/>
                <input type="submit" class="search-button" value="검색"/>
            </form>
            <button id="writePost" class="btn-write"><i class="fa fa-pencil"></i> 글쓰기</button>
        </div>

        <table>
            <tbody>
                <tr>
                    <th class="no">번호</th>
                    <th class="title">제목</th>
                    <th class="writer">이름</th>
                    <th class="regdate">등록일</th>
                    <th class="viewcnt">조회수</th>
                </tr>
                <c:forEach var="boardDto" items="${boardList}">
                    <tr>
                        <td class="no">${boardDto.bno}</td>
                        <td class="title"><a href="<c:url value="/board/read${searchOption.queryString}&bno=${boardDto.bno}"/>"><c:out value="${boardDto.title}"/></a></td>
                        <td class="writer">${boardDto.writer}</td>
                        <c:choose>
                            <c:when test="${boardDto.regDate.time >= startOfToday}">
                                <td class="regdate"><fmt:formatDate value="${boardDto.regDate}" pattern="HH:mm" type="time"/></td>
                            </c:when>
                            <c:otherwise>
                                <td class="regdate"><fmt:formatDate value="${boardDto.regDate}" pattern="yyyy-MM-dd" type="date"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td class="viewcnt">${boardDto.viewCnt}</td>
                    </tr>
                </c:forEach>
                <c:if test="${!empty errorMsg}">
                    <tr>
                        <td colspan="5" style="color: red">SYSTEM ERROR!! : ${errorMsg}</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <br/>
        <div class="paging-container">
            <div class="paging">
                <c:choose>
                <c:when test="${navi.totalCnt == null || navi.totalCnt == 0}">
                    <div> 게시물이 없습니다. </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${navi.showPrev}">
                        <a href="<c:url value='/board/list${navi.searchOption.getQueryString(navi.beginPage - 1)}'/>" class="page">◀</a>
                    </c:if>
                    <c:forEach var="i" begin="${navi.beginPage}" end="${navi.endPage}">
                        <a href="<c:url value='/board/list${navi.searchOption.getQueryString(i)}'/>" class="page ${i eq navi.searchOption.page? 'paging-active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${navi.showNext}">
                        <a href="<c:url value='/board/list${navi.searchOption.getQueryString(navi.endPage + 1)}'/>" class="page">▶</a>
                    </c:if>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<script>
    let resultMsg = '${resultMsg}';
    if (resultMsg === 'delSuccess') {
        alert('삭제 성공');
    } else if (resultMsg === 'delFail') {
        alert('삭제 실패');
    } else if (resultMsg === 'wrtSuccess') {
        alert('등록 성공');
    } else if (resultMsg === 'uptSuccess') {
        alert('수정 성공');
    }

    window.onload = function () {
        document.getElementById('writePost').addEventListener('click', function () {
            location.href = '<c:url value="/board/write?page=${searchCondition.queryString}"/>';
        });
    };
</script>
</body>
</html>