<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>여기는 header.jsp입니다.</h3>
<!-- 표현식 : %= 하면 값이 나온다. -->
현재날짜 : <%=new Date().toLocaleString() %>	

