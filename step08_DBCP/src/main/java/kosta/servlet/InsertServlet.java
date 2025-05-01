package kosta.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kosta.dao.MemberDAO;
import kosta.dao.MemberDAOImpl;
import kosta.dto.MemberDTO;

@WebServlet(urlPatterns = {"/insert"}, loadOnStartup = 1)
public class InsertServlet extends HttpServlet {
	private static MemberDAO dao = new MemberDAOImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 미리 세팅
		// 1. 가장 자주 사용하는 것을 url 로 미리 정의
		String url = "error.jsp";
	    String errMsg = "등록하지 않았습니다.";
	    // 2. 특별히 말하지 않으면 forward 로 간다는 것(default 가 false)
	    boolean isRedircet = false;
		
		// Servlet 에는 이거 전부 다 그냥 받아야함.
		String id = request.getParameter("id");
	    String pwd = request.getParameter("pwd");
	    String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String joinDate = request.getParameter("joinDate");
		
		// 3. 위에서 정의한 값들에 대한 변경을 여기서 하면 된다.
		if (id==null || id.equals("") || pwd ==null || pwd.equals("")||
			name==null || name.equals("") || age == 0 ||	
			phone==null || phone.equals("") || addr ==null || addr.equals("") ) {
			errMsg = "입력값들의 정보가 충분하지 않습니다.";
		} 
		else {
			if (dao.getSelectById(id) != null) {
				errMsg = id +"는 이미 사용중입니다.";
			} else {
				MemberDTO memberDTO = new MemberDTO(id,pwd,name,age,phone,addr,joinDate);
				
				if(dao.insert(memberDTO) > 0) {
					url = "selectAll";
					isRedircet = true;
				}
			}
		}
		
		// 4. 이러나 저러나 결국 이동!
		// 오류가 생기면 redirect 를 사용한다. 
		if(isRedircet) {
			response.sendRedirect(url);
		} else {
			request.setAttribute("errMsg", errMsg);
			RequestDispatcher dispatcher=request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response); 	
		}
	}
}
