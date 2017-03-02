package vn.com.fpt.boot.beans.dataaccesses;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




import vn.com.fpt.boot.beans.Entities.Block;





public interface BlockDAO extends JpaRepository<Block, Long> {

	@Query(value = "select * from block b ", nativeQuery = true)
	List<Block> getAll();
	
	@Query(value = "select b from block b, users u, land l where u.username =?1 and b.landid = l.landid and l.userid = u.userid ", nativeQuery = true)
	List<Block> findBlockByLandId(String userName, long landId);
	
	@Query(value = "SELECT * FROM block ORDER BY blockid DESC limit 1", nativeQuery = true)
	List<Block> getLastBlock();
	
	@Query(value = "select * from block b where b.blockid = ?1 ", nativeQuery = true)
	List<Block> getBlockById(long blockid);
	
	@Query(value = "Insert into Vegetable.block(landid) values(?1)", nativeQuery = true)
	void addBlock(long landid);
	
	@Query(value = "Update Vegetable.block set blockid=?1,landid=?2" , nativeQuery = true)
	void updateBlock(long blockid, long landid);
	
	@Query(value = "Delete from Vegetable.block where blockid=?", nativeQuery = true)
	void deleteBlock(long blockid);

}
