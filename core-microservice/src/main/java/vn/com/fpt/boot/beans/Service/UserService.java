package vn.com.fpt.boot.beans.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vn.com.fpt.boot.beans.Entities.Users;
import vn.com.fpt.boot.beans.dataaccesses.UserDAO;

import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class UserService {
	static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	UserDAO userDao;
	public List<Users> getAll() {
		try {
			List<Users> users = userDao.getAll();
			logger.debug("list users:" + users);
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	public Users getLastUser() {
		try {
			List<Users> users = userDao.getLastUser();
			Users user = null;
			logger.debug("get last alerts size:" + users.size());
			if (users != null) {
				user = userDao.getLastUser().get(0);
				logger.debug("get last user:" + user.getUserid());
			}
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}
	public Users getUserByUserName(String userName) {
		try {
			List<Users> users = userDao.getUserByName(userName);
			Users user = null;
			if (users != null) {
				user = userDao.getUserByName(userName).get(0);
				logger.debug("get user:" + user.getUserid());
			}
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	public boolean addUser(String userName, String password) {
		try {
			userDao.addUser(userName, password);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updateUser(long id, String userName, String password) {
		try {
			userDao.updateUser(id, userName, password, password);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean deleteUser(long id) {
		try {
			userDao.deleteUser(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}
}
