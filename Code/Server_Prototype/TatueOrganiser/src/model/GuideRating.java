package model;

public class GuideRating {
	private int gr_id = 0;
	private float freundlichkeit = 0;
	private float kompetenz = 0;
	public GuideRating() {
		super();
	}
	public int getGr_id() {
		return gr_id;
	}
	public void setGr_id(int gr_id) {
		this.gr_id = gr_id;
	}
	public float getFreundlichkeit() {
		return freundlichkeit;
	}
	public void setFreundlichkeit(float freundlichkeit) {
		this.freundlichkeit = freundlichkeit;
	}
	public float getKompetenz() {
		return kompetenz;
	}
	public void setKompetenz(float kompetenz) {
		this.kompetenz = kompetenz;
	}
	
}
