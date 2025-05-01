package ex0417.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener // 생성 <listener-class> 동일
public class SessionListener implements HttpSessionListener {
  
    public SessionListener() {
    	System.out.println("SessionListener 생성자 호출");
    }


    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("SessionListener sessionCreated call");
    }

    /**
     * 브라우저 창을 닫는다고 서버가 종료되는 것은 아니다.
     * sessionDestroyed 되는 경우
     * 1) session.invalidate() 호출했을 때
     * 2) session timeout 이 되었을 때 호출된다.
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("SessionListener sessionDestroyed call");
    }
}
