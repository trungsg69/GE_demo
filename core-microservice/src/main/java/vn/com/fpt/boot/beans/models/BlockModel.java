package vn.com.fpt.boot.beans.models;

import java.util.Date;

public class BlockModel {
	private long blockid;
	private String blocklabel;
	private String vegetablename;
	private Date starteddate;
	private int humidity;
	public long getBlockid() {
		return blockid;
	}
	public void setBlockid(long blockid) {
		this.blockid = blockid;
	}
	public String getBlocklabel() {
		return blocklabel;
	}
	public void setBlocklabel(String blocklabel) {
		this.blocklabel = blocklabel;
	}
	public String getVegetablename() {
		return vegetablename;
	}
	public void setVegetablename(String vegetablename) {
		this.vegetablename = vegetablename;
	}
	public Date getStarteddate() {
		return starteddate;
	}
	public void setStarteddate(Date starteddate) {
		this.starteddate = starteddate;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public BlockModel(){};
	public BlockModel(long blockId, String vegetablename, Date starteddate){
		this.blockid=blockid;
		this.vegetablename=vegetablename;
		this.starteddate=starteddate;
		this.blocklabel="1x1";
		this.humidity=100;
	};
	

}
