package vn.com.fpt.boot.beans.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vegetable")
public class Vegetable {
	@Id
	@SequenceGenerator(name = "vegetable_seq", sequenceName = "vegetable_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vegetable_seq")
	@Column(name = "vegetableid", updatable=false)
	private Long vegetableid;
	
	@Column(name = "vegetablename")
	private String vegetableName;

	public Long getVegetableid() {
		return vegetableid;
	}

	public void setVegetableid(Long vegetableid) {
		this.vegetableid = vegetableid;
	}

	public String getVegetableName() {
		return vegetableName;
	}

	public void setVegetableName(String vegetableName) {
		this.vegetableName = vegetableName;
	}
	@Column(name = "duration")
	private float duration;

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	
	

}
