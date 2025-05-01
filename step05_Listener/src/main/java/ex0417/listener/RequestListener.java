package ex0417.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;

@WebListener
public class RequestListener implements HttpSessionActivationListener {

    public RequestListener() {
        System.out.println("RequestListener 생성자 call");
    }

    public void sessionDidActivate(HttpSessionEvent se)  { 
         System.out.println("requestInitialized call..");
    }

    public void sessionWillPassivate(HttpSessionEvent se)  { 
         System.out.println("requestDestroyed call...");
    }
}
