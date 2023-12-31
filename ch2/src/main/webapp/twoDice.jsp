<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Random" %>
<%-- <%! 클래스 영역 %> --%>
<%!  
	int getRandomInt(int range) {
		return new Random().nextInt(range) + 1;
	}
%>
<%-- <%  메서드 영역 - service()의 내부 %> --%>
<%
	int idx1 = getRandomInt(6);
	int idx2 = getRandomInt(6);
%>
<html>
<head>
	<title>twoDice.jsp</title>
</head>
<body>
	<img src='${pageContext.request.contextPath}/img/dice<%=idx1%>.jpg' alt="dice1">
	<img src='${pageContext.request.contextPath}/img/dice<%=idx2%>.jpg' alt="dice2">
</body>
</html>