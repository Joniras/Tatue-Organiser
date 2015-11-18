package model;

import java.util.List;

public class Stand {
	private int st_id = 0;
	private String stName = null;
	private String info = null;
	private List<StandRating> sr = null;
	public int getSt_id() {
		return st_id;
	}
	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}
	public String getStName() {
		return stName;
	}
	public void setStName(String stName) {
		this.stName = stName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<StandRating> getSr() {
		return sr;
	}
	public void setSr(List<StandRating> sr) {
		this.sr = sr;
	}
	public Stand(int st_id, String stName, String info, List<StandRating> sr) {
		super();
		this.st_id = st_id;
		this.stName = stName;
		this.info = info;
		this.sr = sr;
	}
}
