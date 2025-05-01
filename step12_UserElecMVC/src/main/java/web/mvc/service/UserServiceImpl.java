package web.mvc.service;

import java.sql.SQLException;

import web.mvc.dao.UserDAO;
import web.mvc.dao.UserDAOImpl;
import web.mvc.dto.UserDTO;
import web.mvc.exception.AuthenticationException;

public class UserServiceImpl implements UserService{
	private UserDAO userDao = new UserDAOImpl();

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException, AuthenticationException {
		 UserDTO loginUser = userDao.loginCheck(userDTO);
		 if (loginUser == null) {
			 throw new AuthenticationException("로그인이 잘못되었습니다.");
		 } else {
			 return loginUser;
		 }
	}
}


