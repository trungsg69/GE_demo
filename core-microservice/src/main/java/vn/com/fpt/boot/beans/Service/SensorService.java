package vn.com.fpt.boot.beans.Service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vn.com.fpt.boot.beans.Entities.Sensor;
import vn.com.fpt.boot.beans.dataaccesses.SensorDAO;

import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class SensorService {
	static final Logger logger = LoggerFactory.getLogger(SensorService.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	SensorDAO sensorDAO ;
	public List<Sensor> getAll() {
		try {
			List<Sensor> users = sensorDAO.getAll();
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	
	
	public boolean addSensor(long blockId, Date createdDate) {
		try {
			sensorDAO.addSensor(blockId, createdDate);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updateSensor( long sensorId, long blockid, Date createdDate) {
		try {
			sensorDAO.updateSensor(sensorId, blockid, createdDate);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean deleteSensor(long id) {
		try {
			sensorDAO.deleteSensor(id);;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}
}
