package com.htl_villach.tatue_rater.Classes;

import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Polygon {
    public List<Punkt> punkte;

    public Polygon()
    {

    }

    public List<Punkt> getPunkte() {
        return punkte;
    }

    public void setPunkte(List<Punkt> punkte) {
        this.punkte = punkte;
    }
}
