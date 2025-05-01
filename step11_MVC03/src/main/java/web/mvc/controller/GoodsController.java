package web.mvc.controller;

import java.io.IOException;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoodsController implements Controller {
	
	/**
	 *  전체 상품 조회 - jsp 에서 list 라고 해놔서 list 로 함
	 */
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("GoodsController list call....");
		
		// 전체 조회 후에 결과 저장해서 뷰로 이동
		request.setAttribute("goodsList", Arrays.asList("새우깡","감자깡","고구마깡"));
		return new ModelAndView("goods/list.jsp");    // 상태 안 주면 forward
	}
	
	/**
	 * 상품등록
	 */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("GoodsController insert call....");
		
		// 등록 완료 후....
		
		return new ModelAndView("goods/registerOk.jsp", true);
	}
}
