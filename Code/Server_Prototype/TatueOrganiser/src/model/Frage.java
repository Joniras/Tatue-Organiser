package model;

import java.util.Vector;

public class Frage {

	private int f_id = 0;
	private Vector<Antwort> antworten = null;
	private String text = null;
	public Frage() {
		super();
	}
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_if) {
		this.f_id = f_if;
	}
	public Vector<Antwort> getAntworten() {
		return antworten;
	}
	public void setAntworten(Vector<Antwort> antworten) {
		this.antworten = antworten;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Frage [f_id=" + f_id + ", antworten=" + antworten + ", text="
				+ text + "]";
	}
	
}
