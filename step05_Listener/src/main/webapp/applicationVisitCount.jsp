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


<%
	// 1. 맨 처음 조회
	Object obj = application.getAttribute("count");
	
	
	// 2. 없으면 -> 이 작업 리스너에서 한다. 
	/**
	if (obj == null){
		application.setAttribute("count", new AtomicInteger());
		obj = application.getAttribute("count");
	}**/
	
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

contextPath : ${pageContext.request.contextPath}/<%=application.getContextPath()%>/${path}

</body>
</html>


