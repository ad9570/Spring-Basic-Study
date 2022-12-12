<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>id=${param.id}</h1>
<h1>pwd=${param.pwd}</h1>
<h1>name=${param.name}</h1>
<h1>hobby=${param.hobby}</h1>
<h1>birth=${param.birth}</h1>
<h1>join=${param.join}</h1>
<h1>sns=${param.sns}</h1>
<hr>
<h1>sns=${paramValues.sns}</h1>
<h1>sns=${paramValues.sns[0]}</h1>
<h1>sns=${paramValues.sns[1]}</h1>
<h1>sns=${paramValues.sns[2]}</h1>
<hr>
<h1>user.id=${user.id}</h1>
<h1>user.pwd=${user.pwd}</h1>
<h1>user.name=${user.name}</h1>
<h1>user.hobby[0]=${user.hobby[0]}</h1>
<h1>user.hobby[1]=${user.hobby[1]}</h1>
<h1>user.hobby[2]=${user.hobby[2]}</h1>
<h1>user.birth=${user.birth}</h1>
<h1>user.join=${user.join}</h1>
<h1>user.sns=${user.sns}</h1>
</body>
</html>