package com.htl_villach.tatue_rater.Classes;

/**
 * Created by simon on 10.03.2016.
 */
public class GuideRating {
    public int GR_ID;
    public int GR_Freundlichkeit;
    public int GR_Kompetenz;

    public GuideRating() {
    }

    public GuideRating(int GR_ID, int GR_Freundlichkeit, int GR_Kompetenz) {
        this.GR_ID = GR_ID;
        this.GR_Freundlichkeit = GR_Freundlichkeit;
        this.GR_Kompetenz = GR_Kompetenz;
    }

    public int getGR_ID() {
        return GR_ID;
    }

    public void setGR_ID(int GR_ID) {
        this.GR_ID = GR_ID;
    }

    public int getGR_Freundlichkeit() {
        return GR_Freundlichkeit;
    }

    public void setGR_Freundlichkeit(int GR_Freundlichkeit) {
        this.GR_Freundlichkeit = GR_Freundlichkeit;
    }

    public int getGR_Kompetenz() {
        return GR_Kompetenz;
    }

    public void setGR_Kompetenz(int GR_Kompetenz) {
        this.GR_Kompetenz = GR_Kompetenz;
    }

    @Override
    public String toString() {
        return "GuideRating{" +
                "GR_ID=" + GR_ID +
                ", GR_Freundlichkeit=" + GR_Freundlichkeit +
                ", GR_Kompetenz=" + GR_Kompetenz +
                '}';
    }
}
