package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;

@WebServlet(urlPatterns = "/board", loadOnStartup = 1)
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<BoardDTO> boardList;
  
    public BoardServlet() {
		System.out.println("BoardServlet 생성자 call....");
    }


	/**
	 * 게시물 3개 초기화 해서 ServletContext영역에 저장 - ServletConfig 매개변수 사용
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// ServletContext 객체를 얻음
		ServletContext application = config.getServletContext();
		// 리스너에서 저장된 boardList를 가져온다
		boardList = (List<BoardDTO>) application.getAttribute("boardList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자가 입력한 게시글 정보 받기
		String no = request.getParameter("no");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		// 새로운 게시글 객체 생성
		BoardDTO board = new BoardDTO(Integer.parseInt(no), subject, content);

		// 기존 boardList에 새로운 게시글 추가
		boardList.add(board); // 새로운 게시글을 기존 boardList에 추가

		// 변경된 boardList를 다시 ServletContext에 저장하여 다른 서블릿이나 JSP에서도 사용 가능하게 함
		getServletContext().setAttribute("boardList", boardList);

		// 추가 후 "center.jsp"로 리다이렉트
		response.sendRedirect("center.jsp");
	}
}