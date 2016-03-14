package model;

public class Antwort {
	
	private int a_id = 0;
	private String text = null;
	private boolean isright = false;
	public Antwort(int a_id, String text, boolean isRight) {
		super();
		this.a_id = a_id;
		this.text = text;
		this.isright = isRight;
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
	public boolean isIsright() {
		return isright;
	}
	public void setIsright(boolean isright) {
		this.isright = isright;
	}
}
