package com.htl_villach.tatue_rater.Classes;

import java.io.Serializable;

public class GewinnspielDaten implements Serializable {
	private int gd_id = 0;
	private int score = 0;
	private String vorname = null;
	private String nachname = null;
	private String email = null;
	private String telefon = null;
	public int getGd_id() {
		return gd_id;
	}
	public void setGd_id(int gd_id) {
		this.gd_id = gd_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public GewinnspielDaten() {
		super();
	}
}
