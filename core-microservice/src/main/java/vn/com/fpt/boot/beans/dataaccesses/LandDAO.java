package vn.com.fpt.boot.beans.dataaccesses;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import vn.com.fpt.boot.beans.Entities.Land;


public interface LandDAO extends JpaRepository<Land, Long> {

	@Query(value = "select * from land ", nativeQuery = true)
	List<Land> getAll();
	
	@Query(value = "select l from land l where 1=1 ", nativeQuery = true)
	List<Land> findByUserName(String userName);
	
	@Query(value = "SELECT * FROM Vegetable.land ORDER BY landid DESC limit 1", nativeQuery = true)
	List<Land> getLastLand();
	@Query(value = "SELECT * FROM Vegetable.land where userid=? ORDER BY landid DESC limit 1", nativeQuery = true)
	List<Land> getLandByUserId(long  userId);
	
	@Query(value = "SELECT * FROM land l where l.landid=?1 limit 1", nativeQuery = true)
	List<Land> getLandById(long landid);
	
	@Query(value = "Insert into Vegetable.land(userId,createddate) values(?1,?2)", nativeQuery = true)
	void addLand(long  userId,Date date);
	
	@Query(value = "Update Vegetable.land set userid=?1,createddate=?2", nativeQuery = true)
	void updateLand(long landid,long userid,Date date);
	
	@Query(value = "Delete from Vegetable.land where landid=?", nativeQuery = true)
	void deleteLand(long landid);

}
