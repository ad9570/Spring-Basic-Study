<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>date2</title>
</head>
<body>
<h1>year=<%=request.getParameter("year")%></h1>
<P>  ${param.year}년 ${param.month}월 ${param.day}일은 ${date}요일입니다. </P>
</body>
</html>
