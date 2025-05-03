<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.BoardDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/center.css">
</head>
<body>
  <h3 class="title-board">Board List</h3>
  <table class="table-board">
    <tr>
      <th>no</th>
      <th>subject</th>
      <th>content</th>
    </tr>
    <!-- 게시글 반복 출력 -->
    <c:if test="${not empty applicationScope.boardList}">
      <c:forEach items="${applicationScope.boardList}" var="board" varStatus="state">
        <tr>
          <td>${board.no}</td>
          <td>${board.subject}</td>
          <td>${board.content}</td>
        </tr>
      </c:forEach>
    </c:if>
  </table>
  
  <c:if test="${not empty sessionScope.sessionId}">
  	<form action="${rootPath}/board" method="post" >
	  <p>글 번호: <input type="text" id="no" name="no"></p>
	  <p>제 목: <input type="text" id="subject" name="subject"></p>
	  <p>내 용: <input type="text" id="content" name="content"></p>
	  <input type="submit" value="등록">
	  <input type="reset" value="취소">
    </form>
  </c:if>
</body>
</html>