package ex0414.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Calendar;

import org.apache.tomcat.jakartaee.commons.lang3.ObjectUtils.Null;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

/*
 * Servlet 등록(생성) 방법 2가지
 * 1) web.xml
 * 2) @annotation - 클래스위에 @WebServlet 
 */
@WebServlet(urlPatterns = "/login", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	String dbId="hyowon";
	String dbPwd="1234";
	
	public LoginServlet() {
		System.out.println("하이");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼으로 전송되는 폼데이터를 받기
		String userId = request.getParameter("userId");
		String userPwd =request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		
		HttpSession session = request.getSession(); 
		
		// userId.equals() 이렇게 하면 userId는 null 값일 수 있으니 NullPointerException 발생 가능
		if (dbId.equals(userId) && dbPwd.equals(userPwd)) {
			session.setAttribute("userId", userId);
			session.setAttribute("userPwd", userPwd);
			
			Calendar cal = Calendar.getInstance();
			int y = cal.get(Calendar.YEAR);
			int m = cal.get(Calendar.MONTH);
			int d = cal.get(Calendar.DATE);
			int h = cal.get(Calendar.HOUR);
			int min = cal.get(Calendar.MINUTE);
			int s = cal.get(Calendar.SECOND);
			
			StringBuilder sb = new StringBuilder();
			sb.append(y + "년 ");
			sb.append(m + "월 ");
			sb.append(d + "일 ");
			sb.append(h + "시 ");
			sb.append(min + "분 ");
			sb.append(s + "초 ");
			
			session.setAttribute("creationTime", sb.toString());
			
			// ContextPath() 맞추기
			ServletContext application = request.getServletContext();
			String contextPath = application.getContextPath();
			
			// 로그인된 id 를 Cookie 에 저장하자!
			Cookie cookie = new Cookie("saveId", userId);
			cookie.setMaxAge(60*60*24*365);
			cookie.setPath("/");        // 전체적으로 접근할 수 있도록 해준다.
			response.addCookie(cookie); // 이렇게 하면 로그아웃을 해도 로그인한 id 를 저장해준다. 
			
			response.sendRedirect(contextPath + "/login/loginOk.jsp?userName=" + URLEncoder.encode(userName, "UTF-8"));
		} else {
			response.setContentType("text/html;charset=UTF-8");
			
			// 메시지 출력
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보를 다시 확인해주세요')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		System.out.println("userId = " + userId);
		System.out.println("userPwd = " + userPwd);
		System.out.println("userName = " + userName);
	}	
}








