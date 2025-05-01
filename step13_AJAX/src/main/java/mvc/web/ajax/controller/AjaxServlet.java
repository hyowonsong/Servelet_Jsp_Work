package mvc.web.ajax.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajaxCheck")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기 오나??");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println("여기 오니 = " + id + " , " + name);
		
		// 응답을 한다고 가정
		PrintWriter out = response.getWriter();
		out.println("Welcome 환영합니다.");  
		out.println("<br>");
		out.println("환영합니다 =" + name + " , " + id);
	}
}
