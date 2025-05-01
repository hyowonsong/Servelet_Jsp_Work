package web.mvc.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartController implements Controller {
	
	/**
	 * 장바구니 등록
	 */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("CartController의 insert 호출....");
		
		return new ModelAndView("cart/registerOk.jsp",true);
	}
	
	/**
	 * 장바구니 조회
	 */
	public ModelAndView select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("CartController의 select 호출....");
		
		request.setAttribute("cartsList", Arrays.asList("새우깡1","감자깡1","고구마깡1"));
		return new ModelAndView("cart/list.jsp");
	}
}
