package com.htl_villach.tatue_rater.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Quiz {
    public int q_id;
    public String titel;
    public List<Frage> fragen = new ArrayList<Frage>();

    public Quiz() {
    }

    public Quiz(int q_id, String titel) {
        this.q_id = q_id;
        this.titel = titel;
    }

    public void addFrage(Frage _f) {
        fragen.add(_f);
    }

    @Override
    public String toString() {
        return titel;
    }
}
