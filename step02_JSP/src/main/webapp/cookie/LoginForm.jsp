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
	// 쿠키에 저장된 아이디 있다면 id의 value에 기억된 id 놓기
	String saveId = "";
	Cookie[] cookies = request.getCookies();
	if (cookies != null){
		for (Cookie co : cookies){
			String name = co.getName();
			if (name.equals("saveId")){  // new Cookie("saveId", 값)과 비교
				saveId = co.getValue();
				break;
			} 
		}
	}
%>


<h1>회원인증 Page</h1>
<form method="post" action="<%=application.getContextPath()%>/login">
<!-- value = "<%=saveId%> 는 쿠키 때문에 -->
  ID : <input type="text" name="userId" value = "<%=saveId%>" /><br/>
  PWD : <input type="password" name="userPwd" /></br/>
  NAME : <input type="text" name="userName" /></br/>
  
  <input type="submit" value="로그인" />
</form>
</body>
</html>