package web.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import web.mvc.dto.Electronics;
import web.mvc.service.ElectronicsService;
import web.mvc.service.ElectronicsServiceImpl;

public class ElectronicsController implements Controller {
    ElectronicsService electronicService = new ElectronicsServiceImpl();

    /**
     * 상품 리스트 조회
     */
    public ModelAndView select(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	String pageNo = request.getParameter("pageNo");
    	
    	if(pageNo==null || pageNo.equals("")) {
			  pageNo="1";
		  }
    	
    	
        List<Electronics> list = electronicService.selectAll( Integer.parseInt(pageNo) );
        request.setAttribute("list", list);
        request.setAttribute("pageNo", pageNo); //뷰에서 ${pageNo}
        
        return new ModelAndView("elec/list.jsp"); 
    }
    
    /**
     * 게시물 자세히 보기 (상세 조회)
     */
    public ModelAndView selectByModelNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    	String modelNum = request.getParameter("modelNum");

        try {
            Electronics elec = electronicService.selectByModelnum(modelNum, true); 
            request.setAttribute("elec", elec);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "상품 상세 정보를 조회할 수 없습니다.");
        }

        return new ModelAndView("elec/read.jsp");
    }
    
    /**
     * 게시물 등록 (파일 업로드 포함)
     */
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//전송된 데이터 받기 
		String modelNum = request.getParameter("model_num");
		String modelName = request.getParameter("model_name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String password = request.getParameter("password");
		
		Electronics elec = 
			new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		Part part = request.getPart("file");//Servlet 3.0버전부터 제공되는 Part API를 이용한 방법인데, getPart() 메서드
		
		String fname = part.getSubmittedFileName(); //전송된 파일이름정보
		System.out.println("fname = " + fname);
		
		if(part!=null) {
			String fileName = this.getFilename(part);
			
			fileName = fileName.replace("/", "").replace("..", "").replace("\\", "");
			
			System.out.println("fileName = " + fileName);
			
			String saveDir = request.getServletContext().getRealPath("/save"); 
			//String saveDir = "C:\\Edu\\save"; 
			
			if (fileName!=null && !fileName.equals("")) {
	            part.write( saveDir + "/"+ fileName);//서버폴더에 파일 저장=업로드
	            
	            elec.setFname(fileName);
	            elec.setFsize( (int)part.getSize() );
	        }
		}
		
		electronicService.insert(elec); // P R G-멱등성

	   return new ModelAndView("front", true);//key=elec&methodName=select 기본으로 설정된다.	
	}
    
    /**
	 * 전송된 파일정보에서 파일이름만 추출해 내는 과정 
	 * */
	private String getFilename(Part part) {
        String headerContent = part.getHeader("content-disposition");
        
        //contentDisp의 결과 form-data; name="fileName"; filename="추가한 파일 이름"
        System.out.println(headerContent);
        
        String[] split = headerContent.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
            	System.out.println("temp = " + temp);
            	System.out.println("temp.indexOf(=) = " + temp.indexOf("=") );
            	
                return temp.substring( temp.indexOf("=") + 2 ,  temp.length() - 1);
            }
        }
        return null;
    }

    /**
     * 게시물 수정 폼으로 이동
     */
    public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String modelNum = request.getParameter("modelNum");
        String pageNo  = request.getParameter("pageNo");
		
		Electronics elec = electronicService.selectByModelnum(modelNum, false);
		request.setAttribute("elec", elec);//update.jsp에서 ${elec}
		request.setAttribute("pageNo", pageNo);
		
		return new ModelAndView("elec/update.jsp");//forward방식
	}

    /**
     * 게시물 수정
     */
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//수정할 정보 5개 받기
		String modelNum = request.getParameter("modelNum");
		String modelName = request.getParameter("modelName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String password = request.getParameter("password");
		
		String pageNo = request.getParameter("pageNo");
		
		Electronics electronics = new Electronics(modelNum, modelName, 
				              Integer.parseInt(price), description, password);
		
		electronicService.update( electronics );
		
		//수정이 완료가 된후....
		ModelAndView mv = new ModelAndView();
		mv.setViewName("front?key=elec&methodName=selectByModelNum&modelNum="+modelNum+"&flag=1&pageNo="+pageNo);
	    mv.setRedirect(true);
		return mv;
	}

    /**
     * 게시물 삭제
     */
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String modelNum = request.getParameter("modelNum");
        String password = request.getParameter("password");
      
        //첨부된 파일을 삭제하는 경우라면 삭제가 되었을때 폴더에서 파일도 삭제한다. - 이 기능을 service한다.
  		String saveDir = request.getServletContext().getRealPath("/save");
  		
  		electronicService.delete(modelNum, password , saveDir);
  		
  		return new ModelAndView("front", true);
    }
}
