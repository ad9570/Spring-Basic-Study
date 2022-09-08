<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>getDateVoid</title>
</head>
<body>
<P>  ${year}년 ${month}월 ${day}일은 ${date}요일입니다. </P>
<h4>컨트롤러 메서드의 리턴타입을 void로 하면 맵핑된 URL에 의해 view의 이름이 결정</h4>
</body>
</html>
