package model;

import java.util.Vector;

public class Stand {
	private int st_id = 0;
	private String stname = null;
	private String info = null;
	private Vector<StandRating> standratings = null;
	private Vector<Schueler> standschueler = null;
	private Rechteck shape = null;
	
	public Stand(int st_id, String stName, String info, Vector<StandRating> sr) {
		super();
		this.st_id = st_id;
		this.stname = stName;
		this.info = info;
		this.standratings = sr;
	}
	
	public Stand(){
		super();
	}
	
	public int getSt_id() {
		return st_id;
	}
	public void setSt_id(int st_id) {
		this.st_id = st_id;
	}
		public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public Vector<StandRating> getStandratings() {
		return standratings;
	}

	public void setStandratings(Vector<StandRating> standratings) {
		this.standratings = standratings;
	}

	public Vector<Schueler> getStandschueler() {
		return standschueler;
	}

	public void setStandschueler(Vector<Schueler> standschueler) {
		this.standschueler = standschueler;
	}

	public Rechteck getShape() {
		return shape;
	}

	public void setShape(Rechteck shape) {
		this.shape = shape;
	}
	
}
