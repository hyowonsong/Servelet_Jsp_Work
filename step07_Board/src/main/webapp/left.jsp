<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Left Section</title>
</head>
<body>
  <%
    String username = (String) session.getAttribute("username");
    if (username == null) username = "woni";
    Date loginTime = (Date) session.getAttribute("loginTime");
    if (loginTime == null) loginTime = new Date();
  %>
  <p><%= username %>님 로그인 중</p>
  <p>접속시간: <%= loginTime.toString() %></p>
  
  <!-- target="_parent" 이렇게 하면 로그아웃하고 index.jsp로 간다.  -->
  <p><a href="LogoutServlet" target="_parent">로그아웃</a></p> 
</body>
</html>