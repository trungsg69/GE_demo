package vn.com.fpt.boot.beans.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "sensor")
public class Sensor {
	@Id
	@SequenceGenerator(name = "sensor_seq", sequenceName = "sensor_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_seq")
	@Column(name = "sensorid", updatable=false)
	private Long sensorId;
	
	@Column(name = "blockid")
	private Long blockId;
	
	@Column(name = "createddate")
	private Date createdDate;

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
