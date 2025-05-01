<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 반드시 해줘야 -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>&lt;c:out> and &lt;c:set></h3>
<!-- 지금 이것들은 변수를 선언한 것 -->
<c:set var="id" value="song" scope="session"/> <!-- session.setAttribute("id","jang")과 동일 -->
<c:set var="age" value="20"/>
<c:set var="addr" value="오리" scope="application"/>
<c:set var="name" value="hyowon"/>

<%
  String name = "hee";
%>

<h3>저장된 정보 출력</h3>
<!-- c:out 사용하면 스크립팅 공격(XSS)을 방지하고 null 값 처리도 쉽게 해주는 장점 -->
아이디 : ${id}/ ${sessionScope.id}/ <c:out value="${id}"/><br> 
나이 :  ${age}/ <c:out value="${age}"/><br>
주소 : ${addr} / ${applicationScope.addr}/ <c:out value="${addr}"/><br> <!-- 주소는 applicationScope에 저장했으니 같음 -->
이름 : ${name} <br>

<!-- 이렇게 escapeXml 속성을 사용해서 xss 공격을 방지(기본이 true) -->
<c:out value="<h3>쉬고싶다!</h3>"/>
<c:out value="<h3>쉬고싶다!</h3>" escapeXml="false"/>

<br>
<a href="ex02_result.jsp">확인하자</a>
</body>
</html>





