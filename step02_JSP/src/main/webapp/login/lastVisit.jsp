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
    boolean hasCookie = false;
    String outputMessage = "";
    long currentTime = System.currentTimeMillis();
    Cookie lastDateCookie = null;

    // 1. 쿠키 존재 여부 체크
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("lastDate".equals(cookie.getName())) {
                hasCookie = true;
                lastDateCookie = cookie;
                break;
            }
        }
    }

    // 2. 쿠키 없으면 현재 날짜로 쿠키 생성
    if (!hasCookie) {
        Cookie newCookie = new Cookie("lastDate", String.valueOf(currentTime));
        newCookie.setMaxAge(60 * 60 * 24 * 365); // 1년 유지
        response.addCookie(newCookie);
        outputMessage = "첫 방문입니다!";
    } else {
        // 3. 쿠키 있으면 이전 방문 날짜 가져오기
        outputMessage = "마지막 방문: " + new Date(Long.parseLong(lastDateCookie.getValue()));

        // 4. 현재 시간으로 쿠키 값 업데이트
        lastDateCookie.setValue(String.valueOf(currentTime));
        lastDateCookie.setMaxAge(60 * 60 * 24 * 365); // 1년 유지
        response.addCookie(lastDateCookie);
    }
%>

    <h2><%= outputMessage %></h2>
    <p>현재 시간: <%= new Date(currentTime) %></p>

</body>
</html>