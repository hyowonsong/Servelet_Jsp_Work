package ex0414.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import org.apache.tomcat.jakartaee.commons.lang3.ObjectUtils.Null;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	String dbId="jang";
	String dbPwd="1234";
	
	public LoginServlet() {
		System.out.println("LifeCycleServlet constructor call....");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost call...");
		
		// 폼으로 전송되는 폼데이터를 받기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		
		// userId.equals() 이렇게 하면 userId는 null 값일 수 있으니 NullPointerException 발생 가능
		if (dbId.equals(userId) && dbPwd.equals(userPwd)) {
			// 성공 - loginOk.jsp
			// 총 두가지의 방법이 존재한다. (1. redirect, 2. request)
			// 1. redirect 방법 - 아래와 같이 하면 root 페이지 바로 밑에서 실행하는 것 + 쿼리 스트링의 형태를 하면 멱등성 지킬 수 있음
			// URLEncoder 사용하면 가능
			response.sendRedirect("loginOk.jsp?userName=" + URLEncoder.encode(userName, "UTF-8"));      
			
			// 2. forward 방식 - 기존 request,response 가 유지된다.
			//request.getRequestDispatcher("loginOk.jsp").forward(request, response);
			
		} else {
			response.setContentType("text/html;charset=UTF-8");
			
			// 메시지 출력
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보를 다시 확인해주세요')");
			// out.println("location.href='login.html'");
			out.println("history.back()");
			out.println("</script>");
			
			// 실패! - login.html로 다시 이동
			// response.sendRedirect("login.html");
		}
		
		System.out.println("userId = " + userId);
		System.out.println("userPwd = " + userPwd);
		System.out.println("userName = " + userName);
	}	
}








