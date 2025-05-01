<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>쿠키 정보</h3>
<%
	//쿠키정보조회
	Cookie[] cookies = request.getCookies();
	if (cookies == null){
		out.println("쿠키정보가 없습니다-JSESSIONID 저장하겠습니다.");
	} else {
		out.println("쿠키 개수 = " + cookies.length + "<br>");
		
		for (Cookie co : cookies){
			String name = co.getName();
			String value = co.getValue();
			
			out.println(name +"=" + value + "<br>");
		}
	}
%>
</body>
</html>