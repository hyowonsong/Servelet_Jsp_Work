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
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("UserController의 login 호출....");
		// 로그인 기능.... 진행
		try {
			UserDTO userDTO = new UserDTO(
		            request.getParameter("userId"),
		            request.getParameter("pwd")
		    );
		    UserDTO loginUser = userService.loginCheck(userDTO);
			
			// 로그인 성공하면 세션에 정보 저장
			HttpSession session = request.getSession();
			session.setAttribute("user", loginUser);
			session.setAttribute("loginUser", loginUser);
			
		    return new ModelAndView("index.jsp", true); 
		} catch (SQLException | AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            return new ModelAndView("error.jsp", false);
        }
	}	
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserController의 logout 호출....");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return new ModelAndView("index.jsp", true); 
    }
}
