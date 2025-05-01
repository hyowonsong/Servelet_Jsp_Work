<%@page import="java.io.PrintWriter"%>
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

<%
// 맨 처음 조회
if (session.getAttribute("userId") != null){
	String username = (String) session.getAttribute("userId");
%>

	<h2><%= username %> 님은 로그인 중입니다.</h2>
	<img src="img/Character.JPG" alt="사진"/><br>
	<button id="logoutBtn">로그아웃</button>

<%
} else {
%>

<script>
alert("로그인 후 이용해주세요.");
location.href = "LoginForm.jsp";  
</script>

<%
}
%>

<script>
document.getElementById("logoutBtn").addEventListener("click", function () {
    if (confirm('정말 로그아웃 하시겠습니까?')) {
    	location.href = '<%=request.getContextPath()%>/logout';
    }
});
</script>

</body>
</html>