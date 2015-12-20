package model;

import java.util.Vector;

public class Stand {
	private int st_id = 0;
	private String stName = null;
	private String info = null;
	private Vector<StandRating> standratings = null;
	private Vector<Schueler> standschueler = null;
	
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
	public Vector<StandRating> getSr() {
		return standratings;
	}
	public void setSr(Vector<StandRating> sr) {
		this.standratings = sr;
	}
	public Stand(int st_id, String stName, String info, Vector<StandRating> sr) {
		super();
		this.st_id = st_id;
		this.stName = stName;
		this.info = info;
		this.standratings = sr;
	}
	public Stand(){
		super();
	}
	public Vector<Schueler> getStandSchueler() {
		return standschueler;
	}
	public void setStandSchueler(Vector<Schueler> standSchueler) {
		this.standschueler = standSchueler;
	}
}
