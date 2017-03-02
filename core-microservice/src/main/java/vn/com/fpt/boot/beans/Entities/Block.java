package vn.com.fpt.boot.beans.Entities;

import javax.persistence.*;


@Entity
@Table(name = "block")
public class Block {
	@Id
	@SequenceGenerator(name = "block_seq", sequenceName = "block_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "block_seq")
	@Column(name = "blockid", updatable=false)
	private Long blockid;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "landid", updatable=false)
	private Land land;


	public Long getBlockid() {
		return blockid;
	}

	public void setBlockid(Long blockid) {
		this.blockid = blockid;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
	}
}
