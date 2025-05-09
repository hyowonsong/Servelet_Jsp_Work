package web.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.mvc.dto.UserDTO;
import web.mvc.exception.AuthenticationException;
import web.mvc.service.UserService;
import web.mvc.service.UserServiceImpl;

public class UserController implements Controller {
	UserService userService = new UserServiceImpl(); 
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserController의 login 호출....");
		//두개의 전송되는 값을 받는다.
		String userId =request.getParameter("userId");
		String pwd =request.getParameter("pwd");
		
		//서비스 호출 
		UserDTO dbDTO = userService.loginCheck( new UserDTO(userId, pwd) );
		
		//로그인성공하면 세션에 정보를 저장 - ${loginUser} / ${loginName}
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", dbDTO);//뷰에서 ${loginUser.userId}
		
		session.setAttribute("loginName", dbDTO.getName());

		//index.jsp이동 - redirect방식
		
		return new ModelAndView("index.jsp", true);
	}	
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//모든 세션의정보를 삭제한다.
		request.getSession().invalidate();
		
		return new ModelAndView("index.jsp", true);
    }
}
