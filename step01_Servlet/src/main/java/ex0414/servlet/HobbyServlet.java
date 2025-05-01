package ex0414.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HobbyServlet extends HttpServlet {
	
	public HobbyServlet() {
		System.out.println("LifeCycleServlet constructor call....");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼으로 전송되는 폼데이터를 받기
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		String userName = request.getParameter("name");
		String userAge = request.getParameter("age");
		String[] userHobby = request.getParameterValues("hobby");
	
		response.setContentType("text/html;charset=UTF-8");
		
		// 메시지 출력
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("id = " + userId + "<br>");
		out.println("pw = " + userPwd + "<br>");
		out.println("name = " + userName + "<br>");
		out.println("age = " + userAge + "<br>");
		for (String s : userHobby) {
		    out.println("hobby = " + s + "<br>");
		}
		out.println("</body>");
		out.println("</html>");
		
		System.out.println("userId = " + userId);
		System.out.println("userPwd = " + userPwd);
		System.out.println("userName = " + userName);
		System.out.println("userAge = " + userAge);
		System.out.println("userHobby = " + userHobby);
	}
}
