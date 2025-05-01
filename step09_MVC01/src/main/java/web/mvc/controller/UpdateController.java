package web.mvc.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 검색 기능 구현
		System.out.println("UpdateController call..");
		
		// 1. request 로 전송된 데이터가 있다면 받기
		
		// 2. service 호출 -> dao 호출
		// 3. 결과를 받아서 영속성에 저장하고 뷰페이지 이동;
		request.setAttribute("message", "수정되었습니다.");
		
		// 얘는 forward 방식으로 이동한다
		return new ModelAndView("updateResult.jsp");
	}
}




























