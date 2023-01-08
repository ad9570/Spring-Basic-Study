<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.fastcampus.ch2.*" %>
<%
	Person person = new Person();
	// person은 lv이지만 EL에는 lv 사용 불가능
	// 따라서 request객체에 setAttribute(Key, Value)를 사용해 저장
	request.setAttribute("person", person);
	request.setAttribute("name", "PMW");   
	request.setAttribute("list", new java.util.ArrayList<>());	
%>
<html>  
<head>   
	<title>EL</title>   
</head>
<body>
person.getCar().getColor()=<%=person.getCar().getColor()%> <br>
person.getCar().getColor()=${person.getCar().getColor()} <br>
<%-- lv는 setAttribute(K, V)로 request객체에 저장해야 아래처럼 사용 가능 --%>
person.getCar().getColor()=${person.car.color} <br>
<hr>
name=<%=request.getAttribute("name")%> <br>
<%-- 저장소 request에서 setAttribute(K, V)로 넘어온 값을 저장하는 Map 이름 = requestScope--%>
name=${requestScope.name} <br>
<%-- requestScope을 쓰지 않으면 좁은 저장소 순으로 검색 page->request->session->application --%>
name=${name} <br>
<hr>
id=<%=request.getParameter("id")%> <br>
<%-- EL에선 lv를 못쓰기 때문에 request 단독이 아닌 pageContext.request --%>
<%-- EL에선 null을 출력하지 않음 --%>
id=${pageContext.request.getParameter("id")} <br>
id=${param.id} <br>
<hr>
<%-- java와 반대로 문자열 "1"이 숫자 1로 바뀐다. JS와 같은 규칙 --%>
"1"+1 = ${"1"+1} <br>
"1"+="1" = ${"1"+="1"} <br>
"2">1 = ${"2">1} <br>
<hr>
null = ${null}<br>
<%-- 계산 시 null이 0으로 바뀐다. --%>
null+1 = ${null+1} <br>
null+null = ${null+null} <br>
"" + null = ${""+null} <br>   
""-1 = ${""-1} <br>
<hr>
<%-- empty : null 혹은 빈 컬렉션 or 배열일 때 true --%>
empty null=${empty null} <br>
empty list=${empty list} <br>
<hr>
null==0 = ${null==0} <br>
null eq 0 = ${null eq 0} <br>
<hr>
name == "PMW"=${name=="PMW"} <br>
name != "PMW"=${name!="PMW"} <br>
<%-- eq : equals, ne : not equals --%>
name eq "PMW"=${name eq "PMW"} <br>
name ne "PMW"=${name ne "PMW"} <br>
name.equals("PMW")=${name.equals("PMW")} <br>

</body>
</html>