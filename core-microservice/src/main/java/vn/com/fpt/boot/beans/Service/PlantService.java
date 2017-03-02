package vn.com.fpt.boot.beans.Service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vn.com.fpt.boot.beans.Entities.Plant;
import vn.com.fpt.boot.beans.dataaccesses.PlantDAO;

import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class PlantService {
	static final Logger logger = LoggerFactory.getLogger(PlantService.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	PlantDAO plantDAO;
	public List<Plant> getAll() {
		try {
			List<Plant> users = plantDAO.getAll();
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	
	
	public boolean addPlant(long vegetableid, long plantid, Date date) {
		try {
			plantDAO.addPlant(vegetableid, plantid, date);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updatePlant( long vegetableid, long plantid, Date date) {
		try {
			plantDAO.updatePlant(vegetableid, plantid, date);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean deletePlant(long id) {
		try {
			plantDAO.deletePlant(id);;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}
	public List<Plant> getPlantByLandId(long landId){
		try {
			List<Plant> plants = plantDAO.getAll();;
			return plants;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}
	public Plant findById(long plantId){
		try {
			List<Plant> plants = plantDAO.getPlantById(plantId);
			Plant plant=null;
			if(plants!=null)
				plant=plants.get(0);
			return plant;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}
}
