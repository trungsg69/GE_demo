package vn.com.fpt.boot.beans.Entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "plant")
public class Plant {
	@Id
	@SequenceGenerator(name = "plant_seq", sequenceName = "plant_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plant_seq")
	@Column(name = "plantid", updatable=false)
	private Long plantid;


	@Column(name = "vegetableid", updatable=false)
	private long vegetableid;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "blockid", updatable=false)
	private Block block;
	
	@Column(name = "starteddate")
	private Date startedDate;


	public Long getPlantid() {
		return plantid;
	}

	public void setPlantid(Long plantid) {
		this.plantid = plantid;
	}

	public long getVegetableid() {
		return vegetableid;
	}

	public void setVegetableid(long vegetableid) {
		this.vegetableid = vegetableid;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
}
