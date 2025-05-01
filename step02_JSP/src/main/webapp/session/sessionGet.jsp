<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
세션정보 확인하기 <br>
이름 : <%=session.getAttribute("name") %><br>
취미 : <%=session.getAttribute("myHobbies") %><br>
나이 : <%=session.getAttribute("age") %><br>

</body>
</html>