package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logout" , loadOnStartup = 1) 
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모든 세션의 정보 삭제
		HttpSession session = req.getSession();
		session.invalidate();//모든세션정보 무효화
		
		PrintWriter out= resp.getWriter();
		out.println("<script>");
		out.println("top.location.href='index.jsp';");
		out.println("</script>");
	}
}