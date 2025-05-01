package web.mvc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * 회원관리 - 이제 handleRequest 버렸으니까 각각 구현(달라지는 것은 없음)
 */
public class UserController implements Controller {
	
	/**
	 * 로그인 기능
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		System.out.println("UserController의 login 호출....");
		// 로그인 기능.... 진행
		
		// 로그인 성공하면 세션에 정보 저장
		HttpSession session = request.getSession();
		session.setAttribute("sessionMessage", "로그인에 성공하셨습니다.");  // ${sessionMessage}
		
		return new ModelAndView("user/login.jsp", true);     // 현재 true 이면 Redirect
	}
	
	/**
	 * 회원 정보 수정 
	 */
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("UserController의 login 호출....");
		
		// 회원정보 수정 완료 후....
		request.setAttribute("message", "수정이 완료되었습니다.");
		
		return new ModelAndView("user/update.jsp");
	}
}










