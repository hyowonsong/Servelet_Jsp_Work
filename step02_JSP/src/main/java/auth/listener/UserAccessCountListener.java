package auth.listener;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener
public class UserAccessCountListener implements HttpSessionListener, HttpSessionActivationListener, ServletContextListener {
	ServletContext application;
	
	public UserAccessCountListener() {
		System.out.println("UserAccessCountListener 생성됨..");
	}
 
	/**
	 *  서버가 start 될때 loginCount를 초기화한다. 
	 */
    public void contextInitialized(ServletContextEvent e)  { 
         application = e.getServletContext();
         application.setAttribute("loginCount", new AtomicInteger());
    }
    
    // session 생성을 여기서 하면은 안된다. 
    /**@Override
    public void sessionCreated(HttpSessionEvent se) {
    	// TODO Auto-generated method stub
    	HttpSessionListener.super.sessionCreated(se);
    }**/

    /**
     *  session.invalidate(); or session-timeout 이 될 때 호출된다.
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    	AtomicInteger at = (AtomicInteger)application.getAttribute("loginCount");
    	int loginCount = at.decrementAndGet();
    	System.out.println("감소 후 loginCount = " + loginCount);
    }
}









