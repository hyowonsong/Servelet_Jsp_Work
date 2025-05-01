package web.mvc.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  각 요청을 위임받아 처리 해줄 공통의 메소드를 제공해주는 메서드(규격서)
 */
public interface Controller {
	
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
