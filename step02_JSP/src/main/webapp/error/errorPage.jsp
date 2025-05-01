<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>
에러메시지 : ${requestScope.errorMsg}<br>
<a href="${pageContext.request.contextPath}/login/LoginForm.jsp">로그인폼이동</a>
</h3>
</body>
</html>