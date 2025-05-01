package ex0414.servlet;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class LogFilter implements Filter {
	Log log = LogFactory.getLog(super.getClass());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request1 = (HttpServletRequest)request;
		String url = request1.getRequestURL().toString();
		
		log.debug(url);
		
		String ip = request.getRemoteAddr();
		String addr = request.getRemoteAddr();
		long start =  System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		long end =  System.currentTimeMillis();
		
		long duration = end-start;
		
		log.debug("ip = " + ip +" , " + "addr" + addr + " , " + "duration" + duration );
	}

}
