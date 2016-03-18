package com.htl_villach.tatue_rater.Classes;

public class StandRating {
    private int sr_id = 0;
    private float aufbau = 0;
    private float freundlichkeit = 0;
    private float kompetenz = 0;

    public StandRating(int sr_id, float aufbau, float freundlichkeit, float kompetenz) {
        this.sr_id = sr_id;
        this.aufbau = aufbau;
        this.freundlichkeit = freundlichkeit;
        this.kompetenz = kompetenz;
    }

    public int getSr_id() {
        return sr_id;
    }
    public void setSr_id(int sr_id) {
        this.sr_id = sr_id;
    }
    public float getAufbau() {
        return aufbau;
    }
    public void setAufbau(float aufbau) {
        this.aufbau = aufbau;
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
    public StandRating() {
        super();
    }
    @Override
    public String toString() {
        return "StandRating [sr_id=" + sr_id + ", aufbau=" + aufbau
                + ", freundlichkeit=" + freundlichkeit + ", kompetenz="
                + kompetenz + "]";
    }

}
