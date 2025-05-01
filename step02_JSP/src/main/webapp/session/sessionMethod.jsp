<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> HttpSession - session 관련 메서드 알아보기 </h1>
<h3>
session.getId() = <%=session.getId() %><br>
session.isNew() = <%=session.isNew() %><br>
session.getMaxInactiveInterval() = <%=session.getMaxInactiveInterval() %><br>
session.getCreationTime() = <%=session.getCreationTime() %><br>   <!-- 처음 접속한 시간 -->
session.getLastAccessedTime()  = <%=session.getLastAccessedTime() %><br> <!-- 마지막 접속한 시간 -->

세션에 정보 저장하기 <br>
<% 
	session.setAttribute("name", "효원");
	session.setAttribute("myHobbies", new String[]{"잠자기", "영화보기"}); // 객체도 저장가능
	session.setAttribute("age", 20);
%>

<!-- 출력하니까 %= -->
세션정보 확인하기 <br>
이름 : <%=session.getAttribute("name") %><br>
취미 : <%=session.getAttribute("myHobbies") %><br>
나이 : <%=session.getAttribute("age") %><br>

<a href="sessionGet.jsp">session 확인하러가자.</a>

</h3>
</body>
</html>