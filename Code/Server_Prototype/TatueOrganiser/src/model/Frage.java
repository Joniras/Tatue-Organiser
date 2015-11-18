package model;

import java.util.List;

public class Frage {

	private int f_if = 0;
	private List<Antwort> antworten = null;
	private String text = null;
	public Frage() {
		super();
	}
	public int getF_if() {
		return f_if;
	}
	public void setF_if(int f_if) {
		this.f_if = f_if;
	}
	public List<Antwort> getAntworten() {
		return antworten;
	}
	public void setAntworten(List<Antwort> antworten) {
		this.antworten = antworten;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
