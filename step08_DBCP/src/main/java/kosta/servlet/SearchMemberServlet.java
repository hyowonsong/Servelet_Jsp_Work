package kosta.servlet;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kosta.dao.MemberDAO;
import kosta.dao.MemberDAOImpl;
import kosta.dto.MemberDTO;


@WebServlet("/search")
public class SearchMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	MemberDAO dao = new MemberDAOImpl();
    	
    	// 요청 파라미터 받아오기
        String keyField = request.getParameter("keyField");
        String keyWord = request.getParameter("keyWord");   

        // 검색 메서드 호출
        List<MemberDTO> list = dao.findBykeyFieldWord(keyField, keyWord);

        // 검색 결과 저장
        request.setAttribute("list", list);

        request.getRequestDispatcher("memberSelect.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}