package model;

import java.util.Vector;

public class Abteilung {

	private int ab_id;
	private String ab_name;
	private int ab_etage;
	private Quiz ab_quiz;
	private Vector<Stand> ab_staende = null;
	private Polygon shape = null;
	
	public Abteilung(){
		ab_name = "default";
	} 

	public Abteilung(int ab_id, String name, int abEtage) {
		this.ab_id = ab_id;
		this.ab_name = name;
		this.ab_etage = abEtage;
	}
	
	public int getAb_id() {
		return ab_id;
	}

	public void setAb_id(int ab_id) {
		this.ab_id = ab_id;
	}

	public String getAb_name() {
		return ab_name;
	}

	public void setAb_name(String ab_name) {
		this.ab_name = ab_name;
	}

	public int getAb_etage() {
		return ab_etage;
	}

	public void setAb_etage(int ab_etage) {
		this.ab_etage = ab_etage;
	}

	public Quiz getAb_quiz() {
		return ab_quiz;
	}

	public void setAb_quiz(Quiz ab_quiz) {
		this.ab_quiz = ab_quiz;
	}

	public Vector<Stand> getAb_staende() {
		return ab_staende;
	}

	public void setAb_staende(Vector<Stand> ab_staende) {
		this.ab_staende = ab_staende;
	}

	public Polygon getShape() {
		return shape;
	}

	public void setShape(Polygon shape) {
		this.shape = shape;
	}
	
}
