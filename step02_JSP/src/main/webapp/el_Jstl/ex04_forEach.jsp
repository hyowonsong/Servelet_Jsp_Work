<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@tagliburi="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tagliburi="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>&lt;c:forEach> Test</h3>

<!-- 객체 생성 -->
<jsp:useBean id="bean" class = "ex0416.el.ForEachBean"></jsp:useBean>

${bean.names}<br>
${bean.menus}<br>


<!-- 이름은 checkbox 출력 -->
<fieldset>
	<legend>이름 선택</legend>
	<c:forEach items ="${bean.names}" var="name">
		<input type="checkbox" name="name" value="${name}">${name}
	</c:forEach>
</fieldset>

<!-- 메뉴 select 박스 출력 -->
<fieldset>
	<legend>select 박스</legend>
	<select>
		<option value="" selected disabled>선택</option>
		<!-- 배열을 돌릴 때는 items + get 메서드 있어서 호출 가능-->
		<c:forEach items ="${bean.menus}" var="menu" varStatus="state">
			<option value="${state.index}">${menu}</option>
		</c:forEach>
	</select>
</fieldset>


<!-- boardList는 table 출력-->
<fieldset>
	<legend>boardList</legend>
	<table style = "width:500px; border-collapse:collapse;">
		<th style ="border:1px solid">순서</th>
		<th style ="border:1px solid">번호</th>
		<th style ="border:1px solid">제목</th>
		<th style ="border:1px solid">내용</th>
		<c:forEach items = "${bean.boardList}" var="board" varStatus="state">
			<tr>
				<td style ="border:1px solid; text-align:center">${state.count}</td>
				<td style ="border:1px solid; text-align:center">${board.no}</td>
				<td style ="border:1px solid; text-align:center">${board.subject}</td>
				<td style ="border:1px solid; text-align:center">${board.content}</td>
			</tr>
		</c:forEach>
	</table>
</fieldset>

<!-- map는 radio 박스 출력-->
<fieldset>
	<legend>나라선택</legend>
	<c:forEach items="${bean.map}" var="nation">
		<input type="radio" name="nation" value="${nation.key}">${nation.value}
	</c:forEach>
</fieldset>

<fieldset>
	<legend>fmt</legend>
		가격 : 256400000 / <fmt:formatNumber value="256400000"/>원 <br>
		날짜 : <%=new Date()%> / <fmt:formatDate value="<%=new Date()%>" pattern="yyyy-MM-dd HH:mm:ss" />
</fieldset>


</body>
</html>












