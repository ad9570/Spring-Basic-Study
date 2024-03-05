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
    <h2 id="pageTitle">게시물 ${mode eq 'write' ? '쓰기' : '읽기'}</h2>
    <form action="" id="boardForm">
        <input type="${mode eq 'write' ? 'hidden' : 'text'}" name="bno" value="${boardDto.bno}" readonly />
        <input type="text" name="title" value="${boardDto.title}" <c:if test="${mode eq 'read'}">readonly</c:if> />
        <textarea name="content" cols="30" rows="10" <c:if test="${mode eq 'read'}">readonly</c:if>>${boardDto.content}</textarea>
        <input type="hidden" name="page" value="${param.page}" />
        <input type="hidden" name="pageSize" value="${param.pageSize}" />
        <c:choose>
            <c:when test="${mode eq 'write'}">
                <button type="button" id="writeBtn" class="btn">등록</button>
            </c:when>
            <c:when test="${mode eq 'read' and sessionScope.id eq boardDto.writer}">
                <button type="button" id="modifyBtn" class="btn">수정</button>
                <button type="button" id="removeBtn" class="btn">삭제</button>
            </c:when>
        </c:choose>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    let resultMsg = '${resultMsg}';
    if (resultMsg === 'wrtFail') {
        alert('등록 실패');
    } else if (resultMsg === 'uptFail') {
        alert('수정 실패');
    }

    window.onload = function () {
        document.getElementById('listBtn').addEventListener('click', function () {
            location.href = '<c:url value="/board/list?page=${param.page}&pageSize=${param.pageSize}"/>';
        });

        document.getElementById('removeBtn').addEventListener('click', function () {
            if (confirm('삭제하시겠습니까?')) {
                let form = document.getElementById('boardForm');
                form.setAttribute('action', '<c:url value="/board/remove"/>');
                form.setAttribute('method', 'POST');
                form.submit();
            }
        });

        document.getElementById('writeBtn').addEventListener('click', function () {
            let form = document.getElementById('boardForm');
            form.setAttribute('action', '<c:url value="/board/write"/>');
            form.setAttribute('method', 'POST');
            form.submit();
        });

        document.getElementById('modifyBtn').addEventListener('click', function () {
            const title = document.getElementsByName('title').item(0);
            const content = document.getElementsByName('content').item(0);
            const isReadOnly = title.hasAttribute('readonly');

            // 읽기 모드일 경우 수정 모드로 변경
            if (isReadOnly) {
                title.removeAttribute('readonly');
                content.removeAttribute('readonly');
                document.getElementById('modifyBtn').innerText = '저장';
                document.getElementById('pageTitle').innerText = '게시물 수정하기';
            }
            // 수정 모드일 경우 수정 내용을 서버로 전송
            else {
                let form = document.getElementById('boardForm');
                form.setAttribute('action', '<c:url value="/board/modify" />');
                form.setAttribute('method', 'POST');
                form.submit();
            }
        });
    };
</script>
</body>
</html>