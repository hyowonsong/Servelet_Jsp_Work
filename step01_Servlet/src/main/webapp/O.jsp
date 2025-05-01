<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>이름: <%= request.getParameter("userName") %></p>
<p>O형은 천방지축!</p>
<a href="bloodForm.html">돌아가기</a>
</body>
</html>