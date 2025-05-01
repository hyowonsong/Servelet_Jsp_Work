<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page = "header.jsp"/>
<%
	String addr = "서울시 강남구";
%>
<h1>요기는 index.jsp입니다.</h1>

<!-- footer.jsp 쪽으로 addr, name은 전달 -->
<jsp:include page = "footer.jsp">
	<jsp:param value="<%=addr %>" name="addr"/>
	<jsp:param value="hyowon" name="name"/>
</jsp:include>
</body>
</html>