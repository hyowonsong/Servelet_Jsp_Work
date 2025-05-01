package ex0414.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/login/loginOk.jsp")
public class AuthenticationCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(); 
		
		if (session.getAttribute("userId") == null) {
			request.setAttribute("errorMsg","인증하고 이용하세요.");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error/errorPage.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		chain.doFilter(request, response);
	}
}
