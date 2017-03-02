package vn.com.fpt.boot.beans.dataaccesses;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.com.fpt.boot.beans.Entities.Sensor;


public interface SensorDAO extends JpaRepository<Sensor, Long> {

	@Query(value = "select * from Vegetable.sensor", nativeQuery = true)
	List<Sensor> getAll();
	
	@Query(value = "SELECT * FROM Vegetable.sensor ORDER BY sensorid DESC limit 1", nativeQuery = true)
	List<Sensor> getLastSensor();
	
	@Query(value = "SELECT * FROM Vegetable.sensor where sensorid=?1 limit 1", nativeQuery = true)
	List<Sensor> getSensorById(long sensorId);
	
	@Query(value = "Insert into Vegetable.sensor(blockid, createddate) values(?1,?2)", nativeQuery = true)
	void addSensor(long blockId, Date createdDate);
	
	@Query(value = "Update Vegetable.sensor set sensorid=?1, blockid=?1,createddate=?2" , nativeQuery = true)
	void updateSensor(long sensorId, long blockid, Date createdDate);
	
	@Query(value = "Delete from Vegetable.sensor where sensorid=?", nativeQuery = true)
	void deleteSensor(long blockid);

}
