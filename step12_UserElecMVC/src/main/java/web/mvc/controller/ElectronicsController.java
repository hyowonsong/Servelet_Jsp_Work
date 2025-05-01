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
    public ModelAndView select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Electronics> list = electronicService.selectAll();
            request.setAttribute("list", list);
        } catch (SQLException e) {
            e.printStackTrace();      
        }
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
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String saveDir = request.getServletContext().getRealPath("/upload");
        int maxSize = 1024 * 1024 * 10; // 10MB 제한
        File dir = new File(saveDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            // Part API를 사용해 파라미터와 파일 처리
            String modelNum = request.getParameter("model_num");
            String modelName = request.getParameter("model_name");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            String password = request.getParameter("password");

            // 입력값 검증
            if (modelNum == null || modelNum.equals("") ||
                modelName == null || modelName.equals("") ||
                priceStr == null || priceStr.equals("") ||
                description == null || description.equals("") ||
                password == null || password.equals("")) {
                request.setAttribute("errorMessage", "모든 필수 입력값을 채워주세요.");
                return new ModelAndView("elec/write.jsp"); 
            }

            int price = Integer.parseInt(priceStr);

            String fname = null;
            int fsize = 0;
            Part filePart = request.getPart("file");
            if (filePart != null && filePart.getSize() > 0) {
                fname = filePart.getSubmittedFileName();
                fsize = (int) filePart.getSize();
                String uniqueFileName = System.currentTimeMillis() + "_" + fname;
                filePart.write(saveDir + File.separator + uniqueFileName);
                fname = uniqueFileName;
            }

            Electronics electronics = new Electronics(modelNum, modelName, price, description, password, null, 0, fname, fsize);
            electronicService.insert(electronics);

            return new ModelAndView("front?key=elec&methodName=select", true); 
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "가격은 숫자 형식이어야 합니다.");
            return new ModelAndView("elec/write.jsp"); 
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "상품 등록에 실패했습니다.");
            return new ModelAndView("elec/write.jsp"); 
        }
    }

    /**
     * 게시물 수정 폼으로 이동
     */
    public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelNum = request.getParameter("modelNum");

        try {
            Electronics elec = electronicService.selectByModelnum(modelNum, false);
            request.setAttribute("elec", elec);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "상품 정보를 조회할 수 없습니다.");
        }

        return new ModelAndView("elec/update.jsp"); 
    }

    /**
     * 게시물 수정
     */
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelNum = request.getParameter("modelNum");
        String modelName = request.getParameter("modelName");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String password = request.getParameter("password");

        // 입력값 검증
        if (modelNum == null || modelNum.equals("") ||
            modelName == null || modelName.equals("") ||
            priceStr == null || priceStr.equals("") ||
            description == null || description.equals("") ||
            password == null || password.equals("")) {
            request.setAttribute("errorMessage", "모든 필수 입력값을 채워주세요.");
            return new ModelAndView("elec/update.jsp"); 
        }

        try {
            int price = Integer.parseInt(priceStr);
            Electronics electronics = new Electronics(modelNum, modelName, price, description, password);
            electronicService.update(electronics);
            return new ModelAndView("front?key=elec&methodName=selectByModelNum&modelNum=" + electronics.getModelNum(), true);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "상품 수정에 실패했습니다.");
            return new ModelAndView("elec/update.jsp", false);
        }
    }

    /**
     * 게시물 삭제
     */
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String modelNum = request.getParameter("modelNum");
        String password = request.getParameter("password");
      
        String saveDir = request.getServletContext().getRealPath("/upload");
        try {
            electronicService.delete(modelNum, password, saveDir);
            return new ModelAndView("front?key=elec&methodName=select", true);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "상품 삭제에 실패했습니다.");
            return new ModelAndView("elec/read.jsp", false);
        }
    }
}
