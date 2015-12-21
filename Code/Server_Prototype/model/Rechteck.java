package model;

public class Rechteck {
	private Punkt a = null;
	private Punkt b = null;
	
	public Rechteck(){
		super();
		a = new Punkt();
		b = new Punkt();
	}

	public Punkt getA() {
		return a;
	}

	public void setA(Punkt a) {
		this.a = a;
	}

	public Punkt getB() {
		return b;
	}

	public void setB(Punkt b) {
		this.b = b;
	}
}
