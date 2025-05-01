<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> ServletContext - application </h3>
<h3>
	<!-- 현재 진짜 realPath 어디인지 알려줌 -->
	application.getRealPath("/")  = <%=application.getRealPath("/") %> <br>
	<!-- 이 프로젝트의 ContextPath 경로 -->
	application.getContextPath()  = <%=application.getContextPath() %> <br>
	<!-- dynamic web version 말하는거 -->
	application.getMajorVersion()  = <%=application.getMajorVersion() %> <br>
	
	<%
		//application에 정보 저장해보자 - 서버를 중지 시키기 전에는 다 나온다.(방문자 수 같은거)
		application.setAttribute("message", "곧 쉬어요~");
		application.setAttribute("menus", Arrays.asList("짜장","짬뽕","탕수육"));
		
		//session에 정보 저장해보자
		session.setAttribute("id", "8253jang");
		
		//request에 정보 저장해보자 -> 다른 페이지로 가면 사라지겠지만 forward를 사용하면 기억함
		request.setAttribute("addr", "오리역");
	%>
	
	메시지 : <%=application.getAttribute("message") %><br>
	메뉴 : <%=application.getAttribute("menus") %><br>
	
	아이디 : <%=session.getAttribute("id") %><br>
	주소 : <%=request.getAttribute("addr") %><br>
	
	<a href = "applicationGet.jsp">잘 되는지 확인합시당</a>
</h3>

</body>
</html>