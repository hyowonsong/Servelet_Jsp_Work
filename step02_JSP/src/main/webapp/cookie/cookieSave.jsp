<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Cookie 저장하기</h1>
<%
	//쿠키 생성 -> String 타입이라 객체는 저장 X
	Cookie co1 = new Cookie("id", "song");
	Cookie co2 = new Cookie("age", "20");

	//저장전에 옵션 설정!
	co1.setMaxAge(60*60*24); // 24시간(1일)
	co2.setMaxAge(60*60*365); // 1년
	
	co1.setPath("/"); //co1 쿠키는 모든 경로에서 접근 가능
	
	//클라이언트 쪽에 저장
	response.addCookie(co1);
	response.addCookie(co2);
%>

<a href =  "cookieGet.jsp">쿠키 확인</a>
</body>
</html>