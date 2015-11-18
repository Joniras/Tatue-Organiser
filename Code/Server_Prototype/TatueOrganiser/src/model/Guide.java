package model;

public class Guide extends Schueler{
	private int g_id = 0;
	private GuideRating guideRating = null;
	public Guide() {
		super();
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public GuideRating getGuideRating() {
		return guideRating;
	}
	public void setGuideRating(GuideRating guideRating) {
		this.guideRating = guideRating;
	}
	
}
