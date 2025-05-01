package kosta.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kosta.dao.MemberDAO;
import kosta.dao.MemberDAOImpl;

@WebServlet(urlPatterns = {"/delete"}, loadOnStartup = 1)
public class DeleteServlet extends HttpServlet {
	private static MemberDAO dao = new MemberDAOImpl();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        int result = dao.delete(id);

        if (result > 0) {
            // 삭제 성공
        	response.sendRedirect(request.getContextPath() + "/selectAll");
        } else {
            // 삭제 실패
        	request.setAttribute("errMsg", "회원 삭제에 실패했습니다.");
        	request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
