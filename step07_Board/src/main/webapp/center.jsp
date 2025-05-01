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
      <c:forEach var="board" items="${applicationScope.boardList}">
        <tr>
          <td>${board.no}</td>
          <td>${board.subject}</td>
          <td>${board.content}</td>
        </tr>
      </c:forEach>
    </c:if>
  </table>
  <p>글 번호: <input type="text" id="no" name="no"></p>
  <p>제 목: <input type="text" id="subject" name="subject"></p>
  <p>내 용: <input type="text" id="content" name="content"></p>
  <button onclick="registerPost()">등록하기</button>
  <script>
    function registerPost() {
      const no = document.getElementById('no').value;
      const subject = document.getElementById('subject').value;
      const content = document.getElementById('content').value;
      // 이렇게 해야 한다고 함.. 잘 모르겠음..
      window.parent.location.href = "BoardServlet?no=" + no + "&subject=" + encodeURIComponent(subject) + "&content=" + encodeURIComponent(content);
    }
  </script>
</body>
</html>