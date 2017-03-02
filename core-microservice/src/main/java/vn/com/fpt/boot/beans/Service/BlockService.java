package vn.com.fpt.boot.beans.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import vn.com.fpt.boot.beans.Entities.Block;
import vn.com.fpt.boot.beans.dataaccesses.BlockDAO;

import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class BlockService {
	static final Logger logger = LoggerFactory.getLogger(BlockService.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	BlockDAO blockDAO;
	public List<Block> getAll() {
		try {
			List<Block> users = blockDAO.getAll();
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}

	
	
	public boolean addBlock(long landid) {
		try {
			blockDAO.addBlock(landid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean updateBlock( long blockid, long plantid) {
		try {
			blockDAO.updateBlock(blockid, plantid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}

	public boolean deleteBlock(long id) {
		try {
			blockDAO.deleteBlock(id);;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return false;
	}
	public List<Block> findBlockByLandId(String userName, long landId) {
		try {
			List<Block> blocks = blockDAO.getAll();
			return blocks;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}
	public Block findBlockByBlockId(String userName, long blockId) {
		try {
			List<Block> blocks = blockDAO.getBlockById( blockId);
			Block block= null;
			if (blocks!=null) 
				block=blocks.get(0);
			return block;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return null;
	}
}
