package ex0414.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BloodServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("name");
		String userBlood = request.getParameter("blood");
		
		response.sendRedirect(userBlood + ".jsp?userName=" + URLEncoder.encode(userName, "UTF-8"));      
		
		System.out.println("userName = " + userName);
		System.out.println("userBlood = " + userBlood);
	}
}
