package web.mvc.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 전송된 데이터 받기
		System.out.println("DeleteController call..");
		
		// 얘는 forward 방식으로 이동한다
		return new ModelAndView("index.jsp",true);
	}
}




























