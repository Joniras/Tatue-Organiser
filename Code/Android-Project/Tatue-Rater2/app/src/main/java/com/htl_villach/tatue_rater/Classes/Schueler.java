package com.htl_villach.tatue_rater.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Schueler {
    public int s_id;
    public String vorname;
    public String nachname;
    public String klasse;
    public Boolean guide;
    public List<GuideRating> guiderating = new ArrayList<GuideRating>();

    public Schueler() {
    }

    public Schueler(int s_id, String vorname, String nachname, String klasse, Boolean guide) {
        this.s_id = s_id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.klasse = klasse;
        this.guide = guide;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public Boolean getGuide() {
        return guide;
    }

    public void setGuide(Boolean guide) {
        this.guide = guide;
    }

    public List<GuideRating> getGuiderating() {
        return guiderating;
    }

    public void setGuiderating(List<GuideRating> guiderating) {
        this.guiderating = guiderating;
    }
    public void addRatingToSchueler(GuideRating _Rating)
    {
        guiderating.add(_Rating);
    }

    public float getFreundlichkeit()
    {
        float avg_freundlichkeit = 0;
        int count = 0;

        if (guiderating != null)
        {
            for (GuideRating gr : guiderating)
            {
                avg_freundlichkeit += gr.getFreundlichkeit();
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

        if (guiderating != null)
        {
            for (GuideRating gr : guiderating)
            {
                avg_kompetenz += gr.getKompetenz();
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


    @Override
    public String toString() {
        return "Schueler{" +
                "s_id=" + s_id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", klasse='" + klasse + '\'' +
                ", guide=" + guide +
                ", guiderating=" + guiderating +
                '}';
    }
}
