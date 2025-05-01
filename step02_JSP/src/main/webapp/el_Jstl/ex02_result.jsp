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

<h3>저장된 정보 출력</h3>
<!-- c:out 사용하면 스크립팅 공격(XSS)을 방지하고 null 값 처리도 쉽게 해주는 장점 -->
아이디 : ${id}/ ${sessionScope.id}/ <c:out value="${id}"/><br> 
나이 :  ${age}/ <c:out value="${age}"/><br>
주소 : ${addr} / ${applicationScope.addr}/ <c:out value="${addr}"/><br> <!-- 주소는 applicationScope에 저장했으니 같음 -->

</body>
</html>