
package web.mvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(urlPatterns = "/front", loadOnStartup = 1)
@MultipartConfig( //어노테이션을 통해  서블릿이 파일 업로드 기능을 할 수 있도록 웹 컨테이너에 지시
        maxFileSize = 1024 * 1024 * 5, //5M - 한 번에 업로드 할 수 있는 파일 크기 제한
        maxRequestSize = 1024 * 1024 * 50 //50M -전체 요청의 크기 제한. 기본값은 무제한 
)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Controller> map;
	private Map<String, Class<?>> classMap;
	
	@Override
	public void init() throws ServletException {
		System.out.println("DispatcherServlet init....");
		// 보통 config.getServletContext() 하는데 여기서는 super 했음
		ServletContext application = super.getServletContext();
		map = (Map<String,Controller>)application.getAttribute("map");
		classMap = (Map<String, Class<?>>) application.getAttribute("classMap");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// key 받기
		String key = request.getParameter("key");
		// method 받기
		String methodName = request.getParameter("methodName");
		
		if (key==null || key.equals("")) {
			key="elec";
		}
		
		if (methodName==null || methodName.equals("")) {
			methodName="select";
		}
		
		
		
		System.out.println("여기오니?" + key + "methodName = " + methodName);
		
		// 이제 key 랑 method 
		
		
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
		
		
		
		// 공통의 메서드 호출하고 받아서 이동 
		
		// 1. handleRequest를 사용
		// ModelAndView mv = con.handleRequest(request, response);
		
		// 2. handleRequest를 사용했었는데 변경
		// action.properties를 사용하는 2차 버전
		Controller con = map.get(key);
		Class<?> className = classMap.get(key);
		try {
			// className 안에서 methodName 을 찾는다. 
			Method method = className.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 아까 찾은 메서드를 실제 객체(con)에 대해서 request, response를 넣어 실행
			ModelAndView mv = (ModelAndView) method.invoke(con, request, response);
			
			// redirect 가 true 라면 redirect 로 이동
			if (mv.isRedirect()) {
				response.sendRedirect(mv.getViewName());
			} else {   // forward 방식으로 이동
				request.getRequestDispatcher(mv.getViewName()).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}







