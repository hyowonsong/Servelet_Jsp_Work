<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String addr = "서울시 강남구";
%>

<jsp:include page = "header.jsp"/>
<h1>요기는 register.jsp입니다.</h1>
<jsp:include page = "footer.jsp">
	<jsp:param value="<%=addr %>" name="addr"/>
	<jsp:param value="hyowon" name="name"/>
</jsp:include>

