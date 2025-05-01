package web.mvc.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderController implements Controller {
	
	/**
	 * 주문 등록
	 */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("OrderController의 insert 호출....");
		
		request.setAttribute("message", "주문이 성공적으로 등록되었습니다.");
		return new ModelAndView("order/registerOk.jsp",true);
	}
	
	/**
	 * 주문 조회
	 */
	public ModelAndView selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("OrderController의 select 호출....");
		
		request.setAttribute("orderList", Arrays.asList("잠만보1","잠만보2","잠만보3"));
		return new ModelAndView("order/list.jsp");
	}
	
}
