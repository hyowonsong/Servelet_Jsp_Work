package kosta.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kosta.dao.MemberDAO;
import kosta.dao.MemberDAOImpl;
import kosta.dto.MemberDTO;

@WebServlet(urlPatterns = {"/readServlet"}, loadOnStartup = 1)
public class SelectServlet extends HttpServlet {
	private static MemberDAO dao = new MemberDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		String id = request.getParameter("id");
		MemberDTO member = dao.getSelectById(id);
		
		if (member == null) {
			request.setAttribute("errMsg", id + "에 해당하는 정보가 없습니다.");
		} else {
			request.setAttribute("memberDTO", member);
			url = "read.jsp";	
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response); 	
	}
}















