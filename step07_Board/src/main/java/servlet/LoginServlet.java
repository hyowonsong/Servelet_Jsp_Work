package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/login" , loadOnStartup = 1 
, initParams = {
		@WebInitParam(name ="dbId" , value = "song") ,
		@WebInitParam(name ="dbPwd", value = "1234")
		}
)
public class LoginServlet extends HttpServlet {
	// dbPost 에서도 접근하게 하기 위해 전역 변수로 선언
	String dbId, dbPwd;

	 public LoginServlet() {
		 System.out.println("LoginServlet 생성됨.....");
	 }
	 
	 @Override
	public void init(ServletConfig config) throws ServletException {
		//init-param전달되는 값 받기
		 dbId = config.getInitParameter("dbId");
		 dbPwd = config.getInitParameter("dbPwd");
		 System.out.println("LoginServlet init....");
	}
	 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//넘어오는 값 2개를 받는다.
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		PrintWriter out = response.getWriter();
		
		if(dbId.equals(userId) && dbPwd.equals(userPwd)) {
			//세션에 정보를 저장
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", userId);
			session.setAttribute("creationTime", new Date().toLocaleString());
			
			//response.sendRedirect("index.jsp");
			
			out.println("<script>");
			out.println("top.location.href='index.jsp'");
			out.println("</script>");
			
		}else {
			request.setAttribute("errMsg", "정보를 다시 확인해주세요.");//뷰에서 ${errMsg}
			//이동
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		}
	}
}