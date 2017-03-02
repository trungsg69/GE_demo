package vn.com.fpt.boot.beans.dataaccesses;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.fpt.boot.beans.Entities.Vegetable;





public interface VegetableDAO extends JpaRepository<Vegetable, Long> {

	@Query(value = "select * from vegetable", nativeQuery = true)
	List<Vegetable> getAll();
	
	@Query(value = "SELECT * FROM Vegetable.vegetable ORDER BY vegetableid DESC limit 1", nativeQuery = true)
	List<Vegetable> getLastVegetable();
	@Query(value = "SELECT * FROM Vegetable.vegetable  where vegetablename = ?1", nativeQuery = true)
	List<Vegetable> getVegetableByName(String vegetableName);
	
	@Query(value = "SELECT * FROM Vegetable.vegetable where vegetableid=?1 limit 1", nativeQuery = true)
	List<Vegetable> getVegetableById(long vegetableid);
	
	@Query(value = "Insert into Vegetable.vegetable(vegetablename,duration) values(?1,?2)", nativeQuery = true)
	void addVegetable(String vegetablename,float duration);
	
	@Query(value = "Update Vegetable.vegetable set vegetablename=?1,duration=?2" , nativeQuery = true)
	void updateVegetable(long vegetableId, String vegetablename,float duration);
	
	@Query(value = "Delete from Vegetable.vegetable where vegetableid=?", nativeQuery = true)
	void deleteVegetable(long vegetableid);

}
