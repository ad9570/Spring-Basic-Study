<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${pageContext.request.getSession(false) == null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${empty loginId ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${empty loginId ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fastcampus</title>
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>"/>
<link rel="stylesheet" href="<c:url value='/css/board-dtl.css'/>"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<c:choose>
    <c:when test="${mode eq 'write'}">
        <c:set var="wrtieShow" value=""/>
        <c:set var="wrtieNewShow" value="none"/>
    </c:when>
    <c:otherwise>
        <c:set var="wrtieShow" value="none"/>
        <c:set var="wrtieNewShow" value=""/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${mode eq 'read' and sessionScope.id eq boardDto.writer}">
        <c:set var="canUpdate" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="canUpdate" value="none"/>
    </c:otherwise>
</c:choose>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<div class="container">
    <div style="text-align: center">
        <h2 class="writing-header" id="pageTitle">게시물 ${mode eq 'write' ? '쓰기' : '읽기'}</h2>
        <form action="" class="frm" id="boardForm">
            <input type="hidden" name="bno" value="${boardDto.bno}" readonly/>
            <input type="hidden" name="page" value="${param.page}"/>
            <input type="hidden" name="pageSize" value="${param.pageSize}"/>

            <input type="text" name="title" value="<c:out value='${boardDto.title}'/>" <c:if test="${mode eq 'read'}">readonly</c:if>/>
            <br/>
            <textarea name="content" rows="20" <c:if test="${mode eq 'read'}">readonly</c:if>><c:out value="${boardDto.content}"/></textarea>
            <br/>

            <button type="button" class="btn btn-write" id="writeBtn" style="display: ${wrtieShow};"><i class="fa fa-pencil"></i> 등록</button>
            <button type="button" class="btn btn-write" id="writeNewBtn" style="display: ${wrtieNewShow};"><i class="fa fa-pencil"></i> 글쓰기</button>
            <button type="button" class="btn btn-modify" id="modifyBtn" style="display: ${canUpdate};"><i class="fa fa-edit"></i> 수정</button>
            <button type="button" class="btn btn-remove" id="removeBtn" style="display: ${canUpdate};"><i class="fa fa-trash"></i> 삭제</button>
            <button type="button" class="btn btn-list" id="listBtn"><i class="fa fa-bars"></i> 목록</button>
        </form>
    </div>
</div>
<script>
    let resultMsg = '${resultMsg}';
    if (resultMsg === 'wrtFail') {
        alert('등록 실패');
    } else if (resultMsg === 'uptFail') {
        alert('수정 실패');
    }

    window.onload = function () {
        document.getElementById('listBtn').addEventListener('click', () => location.href = '<c:url value="/board/list${searchCondition.queryString}"/>');

        document.getElementById('removeBtn').addEventListener('click', () => {
            if (!confirm('삭제하시겠습니까?')) {
                return;
            }

            let form = document.getElementById('boardForm');
            form.setAttribute('action', '<c:url value="/board/remove"/>');
            form.setAttribute('method', 'POST');
            form.submit();
        });

        document.getElementById('writeNewBtn').addEventListener('click', () => location.href="<c:url value='/board/write${searchCondition.queryString}'/>");

        document.getElementById('writeBtn').addEventListener('click', () => {
            let form = document.getElementById('boardForm');
            form.setAttribute('action', '<c:url value="/board/write"/>');
            form.setAttribute('method', 'POST');

            if (formCheck()) {
                form.submit();
            }
        });

        document.getElementById('modifyBtn').addEventListener('click', () => {
            alert();
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

                if (formCheck()) {
                    form.submit();
                }
            }
        });
    };

    const formCheck = function () {
        let form = document.getElementById('boardForm');

        if (!form.title.value) {
            alert('제목을 입력해 주세요.');
            form.title.focus();
            return false;
        }
        else if (!form.content.value) {
            alert('내용을 입력해 주세요.');
            form.content.focus();
            return false;
        }

        return true;
    }
</script>
</body>
</html>