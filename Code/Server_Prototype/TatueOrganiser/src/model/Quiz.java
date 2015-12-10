package model;

import java.util.List;

public class Quiz {
	private int q_id = 0;
	private String titel = null;
	private List<Frage> fragen = null;
	private List<GewinnspielDaten> gd = null;

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
	public List<Frage> getFragen() {
		return fragen;
	}
	public void setFragen(List<Frage> fragen) {
		this.fragen = fragen;
	}
	public List<GewinnspielDaten> getGd() {
		return gd;
	}
	public void setGd(List<GewinnspielDaten> gd) {
		this.gd = gd;
	}
}