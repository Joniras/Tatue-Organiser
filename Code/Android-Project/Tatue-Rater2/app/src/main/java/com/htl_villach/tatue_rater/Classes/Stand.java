package com.htl_villach.tatue_rater.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Stand {
    public int st_id;
    public String stname;
    public String info;
    public List<StandRating> standratings = new ArrayList<StandRating>();
    public Rechteck shape;
    public List<Schueler> standschueler = new ArrayList<Schueler>();

    public Stand() {
    }

    public Stand(int st_id, String stname, String info, Rechteck shape) {
        this.st_id = st_id;
        this.stname = stname;
        this.info = info;
        this.shape = shape;
    }

    public int getSt_id() {
        return st_id;
    }

    public void setSt_id(int st_id) {
        this.st_id = st_id;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<StandRating> getStandratings() {
        return standratings;
    }

    public void setStandratings(List<StandRating> standratings) {
        this.standratings = standratings;
    }

    public Rechteck getShape() {
        return shape;
    }

    public void setShape(Rechteck shape) {
        this.shape = shape;
    }

    public List<Schueler> getStandschueler() {
        return standschueler;
    }

    public void setStandschueler(List<Schueler> standschueler) {
        this.standschueler = standschueler;
    }

    @Override
    public String toString() {
        return stname;
    }

    public void addRatingToStand(StandRating _Rating)
    {
        standratings.add(_Rating);
    }

    public void resetRatings()
    {
        standratings.clear();
    }

    public float getFreundlichkeit()
    {
        float avg_freundlichkeit = 0;
        int count = 0;

        if (standratings != null)
        {
            for (StandRating st : standratings)
            {
                avg_freundlichkeit += st.getSR_Freundlichkeit();
                count++;
            }
            avg_freundlichkeit = avg_freundlichkeit / count;
        }
        else
        {
            avg_freundlichkeit = -1;
        }

        return avg_freundlichkeit;
    }

    public float getKompetenz()
    {
        float avg_kompetenz = 0;
        int count = 0;

        if (standratings != null)
        {
            for (StandRating st : standratings)
            {
                avg_kompetenz += st.getSR_Kompetenz();
                count++;
            }
            avg_kompetenz = avg_kompetenz / count;
        }
        else
        {
            avg_kompetenz = -1;
        }


        return avg_kompetenz;
    }

    public float getAufbau()
    {
        float avg_Aufbau = 0;
        int count = 0;

        if (standratings != null)
        {
            for (StandRating st : standratings)
            {
                avg_Aufbau += st.getSR_Aufbau();
                count++;
            }
            avg_Aufbau = avg_Aufbau / count;
        }
        else
        {
            avg_Aufbau = -1;
        }


        return avg_Aufbau;
    }


}
