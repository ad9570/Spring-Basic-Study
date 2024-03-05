<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginId" value="${pageContext.request.getSession(false) == null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${empty loginId ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<div style="text-align: center">
    <button type="button" id="writePost">글쓰기</button>
    <table border="1" style="width: 100%">
        <colgroup>
            <col width="4">
            <col width="45">
            <col width="15">
            <col width="30">
            <col width="5">
        </colgroup>
        <tbody>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>이름</th>
                <th>등록일</th>
                <th>조회수</th>
            </tr>
            <c:forEach var="boardDto" items="${boardList}">
                <tr>
                    <td>${boardDto.bno}</td>
                    <td><a href="<c:url value='/board/read?bno=${boardDto.bno}&page=${pageHandler.page}&pageSize=${pageHandler.pageSize}'/>">${boardDto.title}</a></td>
                    <td>${boardDto.writer}</td>
                    <td>${boardDto.regDate}</td>
                    <td>${boardDto.viewCnt}</td>
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
    <div>
        <c:if test="${navi.showPrev}">
            <a href="<c:url value='/board/list?page=${navi.beginPage - 1}&pageSize=${navi.pageSize}'/>">◀</a>
        </c:if>
        <c:forEach var="i" begin="${navi.beginPage}" end="${navi.endPage}">
            <a href="<c:url value='/board/list?page=${i}&pageSize=${navi.pageSize}'/>">${i}</a>
        </c:forEach>
        <c:if test="${navi.showNext}">
            <a href="<c:url value='/board/list?page=${navi.endPage + 1}&pageSize=${navi.pageSize}'/>">▶</a>
        </c:if>
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
            location.href = '<c:url value="/board/write?page=${pageHandler.page}&pageSize=${pageHandler.pageSize}"/>';
        });
    };
</script>
</body>
</html>