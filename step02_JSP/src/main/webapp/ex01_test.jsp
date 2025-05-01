<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Have a nice day! - 반가워요</h1>
	<%
		// 여기 안의 now, name은 지역변수. service 메소드 안에 있기 때문. 지역변수이기 때문에 초기화를 하지 않으면 오류가 발생한다.
		// String name; 이런식으로 하면 안된다. 따라서 만약 전역변수 만들고 싶으면 ! 사용한다.
		Calendar now = Calendar.getInstance();
		// java
		String name = "hyowon";
		// jsp에서는 자주 사용하는 9개의 객체 변수를 미리 해놓음
		out.print("<h3>" + name + "</h3>");
		out.print("<h4>" + now + "</h4>");
	%>
	
	<h4>
	메소드 호출해보자 = <%=test(name)%>
	</h4>
	
	<%!
		// !(느낌표) 사용하면 선언문(전역변수 or 메소드 선언 가능)
		String name;
		String addr = "서울";
		
		public String test(String name){
			System.out.println(name + "출력됩니다.");
			this.name = name;
			return name + "님 주소는 " + addr;
		}
	%>
</body>
</html>