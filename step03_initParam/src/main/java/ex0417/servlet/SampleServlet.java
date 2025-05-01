package ex0417.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
		urlPatterns = { "/sample" }, 
		initParams = { 
				@WebInitParam(name = "Info", value = "점심메뉴는?"), 
				@WebInitParam(name = "age", value = "20")
		}, loadOnStartup = 1)
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String info;
	private int age;

    public SampleServlet() {
        System.out.println("SampleServlet constructor call....");
    }

	public void init(ServletConfig config) throws ServletException {
		info = config.getInitParameter("Info");
		int age = Integer.parseInt(config.getInitParameter("age"));
		
		String id = config.getInitParameter("id");
		
		System.out.println("info = " + info);
		System.out.println("age = " + age);
		System.out.println("id = " + id);
		
		//context-param 정보 가져오기 - ServletContext 영역에 저장되어 있다.
		System.out.println("-----context-param입니다-----");
		ServletContext application= config.getServletContext();
		String message = application.getInitParameter("message");
		String contextConfig = application.getInitParameter("contextConfig");
		System.out.println("message = " + message);
		System.out.println("contextConfig = " + contextConfig);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
