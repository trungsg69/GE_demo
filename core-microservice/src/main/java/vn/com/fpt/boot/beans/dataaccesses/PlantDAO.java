package vn.com.fpt.boot.beans.dataaccesses;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.fpt.boot.beans.Entities.Plant;


public interface PlantDAO extends JpaRepository<Plant, Long> {

	@Query(value = "select * from plant p where 1=1 ", nativeQuery = true)
	List<Plant> getAll();
	
	@Query(value = "SELECT * FROM Vegetable.plant ORDER BY plantid DESC limit 1", nativeQuery = true)
	List<Plant> getLastPlant();
	@Query(value = "SELECT * FROM plant where blockid=?1 ORDER BY plantid DESC limit 1", nativeQuery = true)
	List<Plant> getPlantByBlockId(long blockId);
	@Query(value = "SELECT * FROM plant p ", nativeQuery = true)
	List<Plant> getPlantByLandId(long landId);
	
	@Query(value = "SELECT * FROM plant p where p.plantid=?1 limit 1", nativeQuery = true)
	List<Plant> getPlantById(long plantid);
	
	@Query(value = "Insert into Vegetable.plant(vegetableid ,plantid, starteddate) values(?1,?2,?3)", nativeQuery = true)
	void addPlant(long  vegetableid,long plantid, Date date);
	
	@Query(value = "Update Vegetable.plant set vegetableid=?1,plantid=?2,starteddate=?3", nativeQuery = true)
	void updatePlant( long vegetableid,long plantid, Date starteddate);
	
	@Query(value = "Delete from Vegetable.plant where plantid=?", nativeQuery = true)
	void deletePlant(long plantid);

}
