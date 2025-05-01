package kosta.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kosta.dao.MemberDAO;
import kosta.dao.MemberDAOImpl;
import kosta.dto.MemberDTO;

@WebServlet(urlPatterns = {"/selectAll"}, loadOnStartup = 1)
public class SelectAllServlet extends HttpServlet {
	private static MemberDAO dao = new MemberDAOImpl();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 try {
			List<MemberDTO> list = dao.selectAll();
			request.setAttribute("list", list);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("memberSelect.jsp");
			dispatcher.forward(request, response); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
