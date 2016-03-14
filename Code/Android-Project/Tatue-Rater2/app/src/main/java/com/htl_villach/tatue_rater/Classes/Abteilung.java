package com.htl_villach.tatue_rater.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Abteilung {
    public int ab_id;
    public String ab_name;
    public int ab_etage;
    public List<Stand> ab_staende = new ArrayList<Stand>();
    public Polygon shap;
    public Quiz ab_quiz;

    public Abteilung() {
    }

    public Abteilung(int ab_id, String ab_name, int ab_etage) {
        this.ab_id = ab_id;
        this.ab_name = ab_name;
        this.ab_etage = ab_etage;
    }

    public int getAb_id() {
        return ab_id;
    }

    public void setAb_id(int ab_id) {
        this.ab_id = ab_id;
    }

    public String getAb_name() {
        return ab_name;
    }

    public void setAb_name(String ab_name) {
        this.ab_name = ab_name;
    }

    public int getAb_etage() {
        return ab_etage;
    }

    public void setAb_etage(int ab_etage) {
        this.ab_etage = ab_etage;
    }

    public List<Stand> getAb_stande() {
        return ab_staende;
    }

    public void setAb_stande(List<Stand> ab_stande) {
        this.ab_staende = ab_stande;
    }

    public Polygon getShap() {
        return shap;
    }

    public void setShap(Polygon shap) {
        this.shap = shap;
    }

    public Quiz getAb_quiz() {
        return ab_quiz;
    }

    public void setAb_quiz(Quiz ab_quiz) {
        this.ab_quiz = ab_quiz;
    }

    @Override
    public String toString() {
        return "Abteilung{" +
                "ab_id=" + ab_id +
                ", ab_name='" + ab_name + '\'' +
                ", ab_etage=" + ab_etage +
                ", ab_stande=" + ab_staende +
                ", shap=" + shap +
                ", ab_quiz=" + ab_quiz +
                '}';
    }
}
