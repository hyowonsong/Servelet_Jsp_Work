<%@page import="ex0416.el.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 표현언어 연습</title>
</head>
<body>
<h1>EL{Expression Language} = 표현언어</h1>
\${5} = ${5}<br>
\${5+2} = ${5+2}<br>
\${5*3} = ${5*3}<br>
\${5 lt 3} = ${5 lt 3}<br>
\${5 > 3} = ${5 > 3}<br>
\${"배고프다"} = ${"배고프다"}<br>
\${'되니?'} = ${'되니?'}<br>
\${5 gt 3 and 10 eq 10 } = ${5 gt 3 and 10 eq 10 }<br>
${5 > 3 ? "크다" : "작다"}<br>
${param.age >= 18 ? "성인":"미성년자"}<br> 

${not empty param.id ? param.id.concat("님") :"Guest"}<br>
<%-- 아래 라인은 EL에서는 문법 오류 발생하므로 주석 처리 --%>
<%-- ${not empty param.id ? param.id += "님" :"Guest"}<br> --%>

<%
	request.setAttribute("id", "song");
	session.setAttribute("name", "hyowon");
	session.setAttribute("addr", "오리역");
	application.setAttribute("addr", "서울");
	application.setAttribute("message", "EL학습중");
%>

<h3>scope 에 있는 정보 조회하기</h3>
아이디 : <%=request.getAttribute("id") %> / ${requestScope.id} / ${id}<br>
이름 : <%=session.getAttribute("name") %> / ${sessionScope.name} / ${name}<br>
주소(session) : <%=session.getAttribute("addr") %> / ${sessionScope.addr} / ${addr}<br>
주소(application) : <%=application.getAttribute("addr") %> / ${applicationScope.addr} / ${addr}<br>
메시지 : <%=application.getAttribute("message") %> / ${applicationScope.message} / ${message}<br>

<h3>객체 EL 접근하기</h3>
<jsp:useBean id="p" class="ex0416.el.Product">
</jsp:useBean>

상품코드: ${p.code}<br>
상품이름: ${p.name}<br>
상품수량: ${p.qty}<br>
상품가격: ${p.price}<br>
총금액: ${p.qty * p.price}<br>

</body>
</html>
