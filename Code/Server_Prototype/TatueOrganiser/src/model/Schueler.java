package model;

import java.util.Vector;

public class Schueler {
	private int s_id = 0;
	private String vorname = null;
	private String nachname = null;
	private String klasse = null;
	private boolean isGuide = false;
	private Vector<GuideRating> guiderating = null;
	
	public Schueler() {
		super();
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
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
	public String getKlasse() {
		return klasse;
	}
	public void setKlasse(String klasse) {
		this.klasse = klasse;
	}
	public boolean isGuide() {
		return isGuide;
	}
	public void setGuide(boolean isGuide) {
		this.isGuide = isGuide;
	}
	public Vector<GuideRating> getGuiderating() {
		return guiderating;
	}
	public void setGuideRating(Vector<GuideRating> guiderating) {
		this.guiderating = guiderating;
	}
	@Override
	public String toString() {
		return "Schueler [s_id=" + s_id + ", vorname=" + vorname
				+ ", nachname=" + nachname + ", klasse=" + klasse
				+ ", isGuide=" + isGuide + ", guiderating=" + guiderating + "]";
	}
}
