package web.mvc.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import web.mvc.controller.Controller;


/**
 * 서버가 start 될 때 필요한 각 Controller 를 미리 생성해서 map 저장하고
 * application 영역에 저장한다. - 모든 영역에서 사용할 수 있도록
 */
@WebListener
public class HandlerMappingListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event)  { 
    	// actionMapping.properties 파일 로딩
    	ResourceBundle rb = ResourceBundle.getBundle("actionMapping"); //resources/actionMapping.properties
    	
    	Map<String, Controller> map = new HashMap<String, Controller>();
    	
    	// 반복문 안에서 key 와 value 분리해서
    	try {
	    	for (String key :rb.keySet()) {
	    		String value = rb.getString(key);
	    		System.out.println(key + "=" +value);
	    		
	    		// String 은 value 를 Reflection API 를 적용해서 객체로 만든다.
	    		Class<?> className =  Class.forName(value);
	    	
	    		// 생성 - value 를 객체로 만들고 
	    		Controller con = (Controller) className.getDeclaredConstructor().newInstance();
	    		
	    		System.out.println(key + "" + value + " , con = " + con);
	    		// map 에 저장한다.
	    		map.put(key, con);
	    	}
    	} catch (Exception e) {
			e.printStackTrace();
		} // for 문 끝


    	// 마지막에 map을 application 영역에 저장한다.
    	ServletContext application = event.getServletContext();
    	application.setAttribute("map", map);
    	application.setAttribute("path", application.getContextPath()); // ${path}
    	
    }
}





















