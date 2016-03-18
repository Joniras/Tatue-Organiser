package model;

import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;

public class Quiz {
	private int q_id = 0;
	private String titel = null;
	private Vector<Frage> fragen = null;
	@XmlElement(name = "gewinnspieldaten")
	private Vector<GewinnspielDaten> gewinnspieldaten = null;

	public Quiz() {
		super();
	}
	public int getQ_id() {
		return q_id;
	}
	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public Vector<Frage> getFragen() {
		return fragen;
	}
	public void setFragen(Vector<Frage> fragen) {
		this.fragen = fragen;
	}
	public Vector<GewinnspielDaten> getGewinnspieldaten() {
		return gewinnspieldaten;
	}
	public void setGewinnspieldaten(Vector<GewinnspielDaten> gd) {
		this.gewinnspieldaten = gd;
	}
	@Override
	public String toString() {
		return "Quiz [q_id=" + q_id + ", titel=" + titel + ", fragen=" + fragen
				+ ", gewinnspieldaten=" + gewinnspieldaten + "]";
	}
}
