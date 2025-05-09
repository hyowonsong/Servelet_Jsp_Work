package web.mvc.service;

import web.mvc.dao.ElectronicsDAO;
import web.mvc.dao.ElectronicsDAOImpl;
import web.mvc.dto.Electronics;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class ElectronicsServiceImpl implements ElectronicsService {
    private ElectronicsDAO electronicsDAO = new ElectronicsDAOImpl();

    @Override
    public List<Electronics> selectAll() throws SQLException {
    	List<Electronics>  list = electronicsDAO.selectAll();
		return list;
    }

    @Override
    public List<Electronics> selectAll(int pageNo) throws SQLException {
    	List<Electronics>  list = electronicsDAO.getBoardList(pageNo);//페이징처리하는 dao호출
		
		return list;
    }

    @Override
    public void insert(Electronics electronics) throws SQLException {
    	int result = electronicsDAO.insert(electronics);
		if(result==0)throw new SQLException("등록되지 않았습니다.");
    }

    @Override
    public Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
        if (flag) {
        	if (electronicsDAO.increamentByReadnum(modelNum)==0) {
        		throw new SQLException("조회수 증가 오류로 검색할수 없습니다.");
        	}
        }
        Electronics electronics = electronicsDAO.selectByModelNum(modelNum);
		if(electronics == null)
			throw new SQLException("상세보기에 오류가 발생했습니다.");
		
		return electronics;
    }

    // 비밀번호가 일치하면 삭제 아니라면 삭제X
    
    @Override
    public void delete(String modelNum, String password, String saveDir) throws SQLException {
    	Electronics dbElec = electronicsDAO.selectByModelNum(modelNum);
    	
		if(dbElec == null) {
			throw new SQLException("모델번호 오류로 삭제 할수없습니다.");
			
		}else if(!dbElec.getPassword().equals(password)) {
			throw new SQLException("비밀번호를 다시 확인해주세요.-삭제실패");
		}
		
		if(electronicsDAO.delete(modelNum, password) == 0) {
			throw new SQLException("삭제되지 않았습니다.");
		}
		 
		 //삭제가 되었을대 첨부파일이 있는 경우는 save폴더에서 파일도 삭제한다.
		if(dbElec.getFname()!=null) {
			String fileName = saveDir+"/" + dbElec.getFname();
			new File(fileName).delete();
		}
    }

    @Override
    public void update(Electronics electronics) throws SQLException {
    	//전달된 비번과 DB에저장된 비번이 일치하는지 먼저 체크한다.
		Electronics dbElec = electronicsDAO.selectByModelNum(electronics.getModelNum());
		
		if(dbElec == null) {
			throw new SQLException("모델번호 오류로 수정할수 없습니다.");
			
		}else if(!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new SQLException("비밀번호를 다시 확인해주세요.-수정실패");
		}
		
		if(electronicsDAO.update(electronics) ==0) {
			throw new SQLException("수정되지않았습니다.^^");
		}
    }
}