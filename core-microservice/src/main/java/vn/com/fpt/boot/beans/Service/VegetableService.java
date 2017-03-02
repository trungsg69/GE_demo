package vn.com.fpt.boot.beans.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vn.com.fpt.boot.beans.Entities.Vegetable;
import vn.com.fpt.boot.beans.dataaccesses.VegetableDAO;

import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class VegetableService {
	static final Logger logger = LoggerFactory.getLogger(VegetableService.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	VegetableDAO vegetableDao;
	public List<Vegetable> getAll() {
		try {
			List<Vegetable> users = vegetableDao.getAll();
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	public Vegetable getLastUser() {
		try {
			List<Vegetable> users = vegetableDao.getLastVegetable();
			Vegetable user = null;
			logger.debug("get last alerts size:" + users.size());
			if (users != null) {
				user = vegetableDao.getLastVegetable().get(0);

			}
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}
	public Vegetable getVegetableByName(String userName) {
		try {
			List<Vegetable> users = vegetableDao.getVegetableByName(userName);
			Vegetable user = null;			
			if (users != null) {
				user = vegetableDao.getVegetableByName(userName).get(0);
				
			}
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	public boolean addUser(String vegetablename, float duration) {
		try {
			vegetableDao.addVegetable(vegetablename, duration);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updateVegetable( long vegetableId, String vegetablename, float duration) {
		try {
			vegetableDao.updateVegetable(vegetableId, vegetablename, duration);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean deleteVegetable(int vegetableId) {
		try {
			vegetableDao.deleteVegetable(vegetableId);;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}
}
