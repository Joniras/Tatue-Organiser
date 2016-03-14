package com.htl_villach.tatue_rater.Classes;

/**
 * Created by simon on 10.03.2016.
 */
public class StandRating {
    public int SR_ID;
    public int SR_Aufbau;
    public int SR_Freundlichkeit;
    public int SR_Kompetenz;

    public StandRating() {
    }

    public StandRating(int SR_ID, int SR_Aufbau, int SR_Freundlichkeit, int SR_Kompetenz) {
        this.SR_ID = SR_ID;
        this.SR_Aufbau = SR_Aufbau;
        this.SR_Freundlichkeit = SR_Freundlichkeit;
        this.SR_Kompetenz = SR_Kompetenz;
    }

    public int getSR_ID() {
        return SR_ID;
    }

    public void setSR_ID(int SR_ID) {
        this.SR_ID = SR_ID;
    }

    public int getSR_Aufbau() {
        return SR_Aufbau;
    }

    public void setSR_Aufbau(int SR_Aufbau) {
        this.SR_Aufbau = SR_Aufbau;
    }

    public int getSR_Freundlichkeit() {
        return SR_Freundlichkeit;
    }

    public void setSR_Freundlichkeit(int SR_Freundlichkeit) {
        this.SR_Freundlichkeit = SR_Freundlichkeit;
    }

    public int getSR_Kompetenz() {
        return SR_Kompetenz;
    }

    public void setSR_Kompetenz(int SR_Kompetenz) {
        this.SR_Kompetenz = SR_Kompetenz;
    }

    @Override
    public String toString() {
        return "StandRating{" +
                "SR_ID=" + SR_ID +
                ", SR_Aufbau=" + SR_Aufbau +
                ", SR_Freundlichkeit=" + SR_Freundlichkeit +
                ", SR_Kompetenz=" + SR_Kompetenz +
                '}';
    }
}
