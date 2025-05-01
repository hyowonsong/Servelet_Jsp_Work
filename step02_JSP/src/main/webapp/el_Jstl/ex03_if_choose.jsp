<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>&lt;c:if> and &lt;c:choose></h3>
<!-- 만약, http://localhost/step02_JSP/el_Jstl/ex03_if_choose.jsp?age=10
	age의 값이 있다면 당신은 ~살 입니다. 출력 -->
<c:if test="${not empty param.age}">
	<h3>당신은 ${param.age}살 입니다.</h3>
</c:if>
	
<!-- 만약, http://localhost/step02_JSP/el_Jstl/ex03_if_choose.jsp?age=10
	age의 값이 18살보다 크면 "많은 이용부탁드립니다.-blue" -->	
<c:if test="${not empty param.age}">
	<c:choose>
		<c:when test="${param.age>=18}">
			<h3 style="color:#03a9f4">많은 이용 부탁드립니다.</h3>
		</c:when>
		<c:otherwise>
			<h3 style="color:#e91e63ab">그냥 나가요</h3>
		</c:otherwise>
	</c:choose>
</c:if>
	
</body>
</html>