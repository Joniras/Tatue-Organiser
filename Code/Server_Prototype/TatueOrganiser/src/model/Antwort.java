package model;

public class Antwort {
	
	private int a_id = 0;
	private String text = null;
	private boolean isRight = false;
	public Antwort(int a_id, String text, boolean isRight) {
		super();
		this.a_id = a_id;
		this.text = text;
		this.isRight = isRight;
	}
	public Antwort() {
		super();
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isRight() {
		return isRight;
	}
	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	
}
