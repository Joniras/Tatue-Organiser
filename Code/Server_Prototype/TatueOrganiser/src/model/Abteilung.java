package model;

import java.util.List;

public class Abteilung {

	private int ab_id;
	private String name;
	private int abEtage;
	private Quiz quiz;
	private List<Stand> staende = null;
	
	public Abteilung(){
		name = "default";
	} 

	public Abteilung(int ab_id, String name, int abEtage) {
		this.ab_id = ab_id;
		this.name = name;
		this.abEtage = abEtage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAb_id() {
		return ab_id;
	}

	public void setAb_id(int ab_id) {
		this.ab_id = ab_id;
	}

	public int getAbEtage() {
		return abEtage;
	}

	public void setAbEtage(int abEtage) {
		this.abEtage = abEtage;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Stand> getStaende() {
		return staende;
	}

	public void setStaende(List<Stand> staende) {
		this.staende = staende;
	}
	
	
}
