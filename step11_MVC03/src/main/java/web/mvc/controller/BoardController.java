package web.mvc.controller;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardController implements Controller {
	
	/**
	 * 게시판 list
	 */
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("BoardController list call....");
		// 서비스 호출하고 결과 받아서 저장하고 이동한다. 
		request.setAttribute("message", "게시판 조회 완료~~");
		
		return new ModelAndView("board/list.jsp");   
	}
	
	/**
	 * 상세보기
	 */
	public ModelAndView read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("BoardController read call....");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/read.jsp");
		mv.setRedirect(true);
		
		return mv;  
	}
}










