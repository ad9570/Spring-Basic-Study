<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>date</title>
</head>
<body>
<h1>year=<%=request.getParameter("year")%></h1>
<P>  ${year}년 ${month}월 ${day}일은 ${date}요일입니다. </P>
</body>
</html>