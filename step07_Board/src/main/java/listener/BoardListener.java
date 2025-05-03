package listener;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import dto.BoardDTO;

@WebListener
public class BoardListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		// 게시글 데이터를 담을 리스트 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		// 더미 게시글 데이터를 리스트에 추가
		boardList.add(new BoardDTO(1, "servlet", "servlet 공부"));
		boardList.add(new BoardDTO(2, "spring", "프레임워크"));
		boardList.add(new BoardDTO(3, "HTML", "프론트 기초"));
		boardList.add(new BoardDTO(4, "jsp", "jsp 공부"));

		// ServletContext는 애플리케이션 전역에서 데이터를 공유할 수 있는 저장소
		ServletContext application = e.getServletContext();

		// 생성한 게시글 리스트를 "boardList"라는 이름으로 애플리케이션 전역에 저장
		application.setAttribute("boardList", boardList);

		// 루트 경로(ContextPath)를 "rootPath"라는 이름으로 전역에 저장
		// 뷰(JSP)에서 ${rootPath}로 참조 가능 (예: <a href="${rootPath}/index.jsp">)
		application.setAttribute("rootPath", application.getContextPath());

		// 콘솔에 초기화된 게시글 리스트를 출력 (디버깅용)
		System.out.println("boardList = " + boardList);
	}
}
