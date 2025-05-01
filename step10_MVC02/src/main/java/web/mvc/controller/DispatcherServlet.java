package web.mvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/front", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Controller> map;
	
	@Override
	public void init() throws ServletException {
		System.out.println("DispatcherServlet init....");
		// 보통 config.getServletContext() 하는데 여기서는 super 했음
		ServletContext application = super.getServletContext();
		map = (Map<String,Controller>)application.getAttribute("map");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// key 받기
		String key = request.getParameter("key");
		
		System.out.println("여기오니?" + key);
		
		// Controller 
		
		/*
		// 1. action.properties를 사용하면 이것들 필요 없음
		Controller con = null;
		if ("insert".equals(key)) {
			// 등록 기능
			con = new InsertController();
		} else if ("select".equals(key)) {
			// 검색 기능
			con = new SelectController();
		} else if ("update".equals(key)) {
			// 수정 기능
			con = new UpdateController();
		} else if ("delete".equals(key)) {
			// 삭제 기능
			con = new DeleteController();
		}
		*/
		
		// 1. action.properties를 사용하는 2차 버전
		Controller con = map.get(key);
		
		// 공통의 메서드 호출하고 받아서 이동
		ModelAndView mv = con.handleRequest(request, response);
		
		// redirect 가 true 라면 redirect 로 이동
		if (mv.isRedirect()) {
			response.sendRedirect(mv.getViewName());
		} else {   // forward 방식으로 이동
			request.getRequestDispatcher(mv.getViewName()).forward(request, response);
		}
	}
}







