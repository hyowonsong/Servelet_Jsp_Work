<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>context-param 정보 가져오기</h3>
	메시지 : <%=application.getInitParameter("message") %><br>
	contextConfig : <%=application.getInitParameter("contextConfig") %>
</body>
</html>