package model;

import java.util.Vector;

public class Polygon {
	private Vector<Punkt> punkte = null;
	
	public Polygon(){
		super();
		punkte = new Vector<Punkt>();
	}

	public Vector<Punkt> getPunkte() {
		return punkte;
	}

	public void setPunkte(Vector<Punkt> punkte) {
		this.punkte = punkte;
	}
	
	
}
