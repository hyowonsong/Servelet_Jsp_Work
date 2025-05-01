package web.mvc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("InsertController 호출");
		
		// 전달 받기
		
		// vo or dto 만들고
		// service 쪽으로 vo 를 전달해서 등록완료 후 결과를 받는다.
		
		
		return new ModelAndView("index.jsp",true);
	}
}
