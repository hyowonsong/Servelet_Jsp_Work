<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Left Section</title>
</head>
<body>
<c:choose>
  <c:when test="${not empty sessionScope.sessionId}">
     ${sessionId}님 로그인중<br>
     [접속시간 : ${creationTime}] <p>
     <a href="${rootPath}/logout">로그아웃</a>
  </c:when>
  <c:otherwise>
     <form method="post" action="${rootPath}/login"> <!-- servlet의 url-pattern 이 /login -->
		  ID : <input type="text" name="userId"  size="10"/><br/>
		  PWD : <input type="password" name="userPwd" size="10"/></br/>
		  <input type="submit" value="로그인" />
		  <input type="reset" value="취소" />
	</form>
  </c:otherwise>
</c:choose>
</body>
</html>