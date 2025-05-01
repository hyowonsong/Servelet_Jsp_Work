<%@page import="java.util.concurrent.atomic.AtomicInteger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	// 1. 방문자수 기존값을 조회한다. - application 저장된 
	Object obj = application.getAttribute("count");

	//2. 만약 null이면 기본 0으로 count를 저장한다.
	if (obj == null){
		// null 이면 담는다
		application.setAttribute("count", 0);
		obj = application.getAttribute("count");
	}
	
	// 여기 위치에서 무조건 count 있다.
	int count = (int)obj;
	// 3. 새로운 세션이면 count ++ 
	if (session.isNew()){
		count ++;
		// ++한 application을 담아준다.
		application.setAttribute("count", count);
	}
--%>

<%
	// 1. 맨 처음 조회
	Object obj = application.getAttribute("count");
	// 2. 없으면 
	if (obj == null){
		application.setAttribute("count", new AtomicInteger());
		obj = application.getAttribute("count");
	}
	
	// AtomicInteger 를 사용하면 자동으로 동기화처리가 된다.
	AtomicInteger at = (AtomicInteger)obj;
	int count = at.get();
	
	// 3. 새로운 세션이면 count ++ 
	if (session.isNew()){
		// 자동으로 incrementAndGet 사용
		count = at.incrementAndGet();
	}
%>

<h2>전체 방문자수 : <%=count %>명</h2>
</body>
</html>


