package vn.com.fpt.boot.beans.Service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vn.com.fpt.boot.beans.Entities.Land;
import vn.com.fpt.boot.beans.dataaccesses.LandDAO;

import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class LandService {
	static final Logger logger = LoggerFactory.getLogger(LandService.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	LandDAO landDAO;
	public List<Land> getAll() {
		try {
			List<Land> users = landDAO.getAll();
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	
	
	public boolean addLand(long landid, Date createdDate) {
		try {
			landDAO.addLand(landid,createdDate);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updateLand( long landid, long userid, Date createdDate) {
		try {
			landDAO.updateLand(landid, userid, createdDate);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean deleteLand(long id) {
		try {
			landDAO.deleteLand(id);;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}
	public List<Land> findByUserName(String userName){
		try {
			List<Land> users = landDAO.findByUserName(userName);
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
		
	}
	public Land findById(Long id){
		try {
			List<Land> lands = landDAO.getLandById(id);
			Land land= null;
			if(lands!=null)
				land=lands.get(0);
			return land;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;

	}
}
