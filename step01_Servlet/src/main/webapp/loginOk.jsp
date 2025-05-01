<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> login OK page </h1>
	<!-- =은 자바 코드를 출력하겠다는 것. 하지만, request.getParameter 해도 null 이 나온다. forward 방식은 나옴 -->
	<h3> <%=request.getParameter("userName") %> 님 로그인에 성공하셨습니다. </h3>
</body>
</html>