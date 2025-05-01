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
        return electronicsDAO.selectAll();
    }

    @Override
    public List<Electronics> selectAll(int pageNo) throws SQLException {
        return electronicsDAO.getBoardList(pageNo);
    }

    @Override
    public void insert(Electronics electronics) throws SQLException {
        electronicsDAO.insert(electronics);
    }

    @Override
    public Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
        if (flag) {
        	if (electronicsDAO.increamentByReadnum(modelNum)==0) {
        		throw new SQLException("안된다 임마!");
        	}
        }
        return electronicsDAO.selectByModelNum(modelNum);
    }

    // 비밀번호가 일치하면 삭제 아니라면 삭제X
    
    @Override
    public void delete(String modelNum, String password, String saveDir) throws SQLException {
        Electronics electronics = electronicsDAO.selectByModelNum(modelNum);
        // 만약 null 이 아니면 
        if (electronics != null) {
            File file = new File(saveDir, electronics.getFname());
            // 만약 존재하면 
            if (file.exists()) {
                file.delete();
            }
        }
        electronicsDAO.delete(modelNum, password);
    }

    @Override
    public void update(Electronics electronics) throws SQLException {
        electronicsDAO.update(electronics);
    }
}