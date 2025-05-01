package ex0414.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet 작성법
 *  1) public 이어야 한다.
 *  2) HttpServlet 상속
 *  3) 필요한 메서드 재정의해서 기능 작성
 *  4) 생성 + mapping
 *  	- web.xml 설정 or @annotation 설정
 */
public class LifeCycleServlet extends HttpServlet {
	
	public LifeCycleServlet() {
		System.out.println("LifeCycleServlet constructor call....");
	}
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("LifeCycleServlet init call....");
	}
	
	// 서비스 오버라이딩 하지 않으면 doGet 이렇게 따로 오버라이딩 해줘야
	/*
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("LifeCycleServlet service call....");
	}*/


	/*
	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeCycleServlet doGet call....");
		
    	// HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
    	response.setContentType("text/html; charset=utf-8");
		
		// 브라우저에 출력
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.println("<head><title>Servlet 시작</title></head>");
		out.println("<body>");
		
		out.println("<script>");
		out.println("alert('밥먹고 싶어요')");
		out.println("</script>");
		
		out.println("<h1>Servlet 입니다</h1>");
		
		out.println("</body>");
		out.print("</html>");
	}*/

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LifeCycleServlet doPost call....");
	}

	
	@Override
	public void destroy() {
		System.out.println("LifeCycleServlet destroy call....");
	}
}
