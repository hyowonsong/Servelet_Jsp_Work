<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <title>마지막 방문 시간</title>
</head>
<body>
<%
    // 변수 선언
    boolean state = false;
    String outputMessage = "";
    long currentTime = System.currentTimeMillis();
    Cookie lastDateCookie = null;

    // 1. 쿠키 존재 여부 체크
    Cookie[] cookies = request.getCookies();
    
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("lastDateCookie")) {
            	state = true;
                lastDateCookie = cookie;
                break;
            }
        }
    }

    // 2. 쿠키 없으면 현재 날짜로 쿠키 생성
    if (!state) {
    	lastDateCookie = new Cookie("lastDateCookie", currentTime+"");
    	lastDateCookie.setMaxAge(60 * 60 * 24 * 365); // 1년 유지
        response.addCookie(lastDateCookie);
        outputMessage = "당신은 처음 방문하셨습니다.!";
    } else {
        // 3. 쿠키 있으면 이전 방문 날짜 가져오기
        outputMessage = "이전 마지막 방문: " + lastDateCookie.getValue();

        // 4. 현재 시간으로 쿠키 값 업데이트
        lastDateCookie.setMaxAge(60 * 60 * 24 * 365); // 1년 유지
        lastDateCookie.setPath("/");
        lastDateCookie.setValue(currentTime + "");
        response.addCookie(lastDateCookie);
    }
%>

    <h2><%= outputMessage %></h2>
    <p>현재 시간: <%= new Date(currentTime) %></p>

</body>
</html>