package ex0417.listener;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {
	
	public AppListener() {
		System.out.println("AppListener 생성자 호출");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent e) {
		System.out.println("AppListener contextInitialized(ServletContextEvent sce) call..");
		// 서버가 시작될때(프로젝트가 배포될때 해야할 작업)
		ServletContext application = e.getServletContext();
		
		application.setAttribute("count", new AtomicInteger());    // jsp에서 ${applicationScope.count}
		application.setAttribute("path", application.getContextPath());    // jsp에서 ${path} 하면 이전 처럼 길게 쓸 필요 없다.
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("AppListener contextDestroyed(ServletContextEvent sce) call..");
	}
}
