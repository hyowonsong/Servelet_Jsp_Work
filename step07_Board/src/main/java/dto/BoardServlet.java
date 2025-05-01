package dto;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
  private List<BoardDTO> boardList;

  @Override
  public void init() throws ServletException {
    ServletContext context = getServletContext();
    boardList = (List<BoardDTO>) context.getAttribute("boardList");
    if (boardList == null) {
      boardList = new ArrayList<>();
      boardList.add(new BoardDTO(1, "servlet", "servlet 공부"));
      boardList.add(new BoardDTO(2, "spring", "프레임워크"));
      boardList.add(new BoardDTO(3, "HTML", "프론트 기초"));
      boardList.add(new BoardDTO(4, "jsp", "jsp 공부"));
      context.setAttribute("boardList", boardList);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int no = Integer.parseInt(request.getParameter("no"));
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");

    boardList.add(new BoardDTO(no, subject, content));
    response.sendRedirect("index.jsp");
  }
}