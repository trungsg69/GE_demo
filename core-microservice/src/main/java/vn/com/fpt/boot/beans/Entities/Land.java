package vn.com.fpt.boot.beans.Entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "land")
public class Land {
	@Id
	@SequenceGenerator(name = "land_seq", sequenceName = "land_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "land_seq")
	@Column(name = "landid", updatable=false)
	private Long landid;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "userid", updatable=false)
	private Users user;
	
	@Column(name ="createddate")
	private Date createdDate;

	public Long getLandid() {
		return landid;
	}

	public void setLandid(Long landid) {
		this.landid = landid;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

}
