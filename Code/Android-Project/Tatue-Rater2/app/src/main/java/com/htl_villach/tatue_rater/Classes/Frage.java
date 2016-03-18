package com.htl_villach.tatue_rater.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Frage implements Serializable {
    public int f_id;
    public String text;
    public List<Antwort> antworten = new ArrayList<Antwort>();

    public Frage() {
    }

    public Frage(int f_id, String text) {
        this.f_id = f_id;
        this.text = text;
    }

    public void addAntwort(Antwort ant)
    {
        this.antworten.add(ant);
    }

    @Override
    public String toString() {
        return "Frage{" +
                "text='" + text + '\'' +
                '}';
    }

    public Antwort getrichtigeAntwort(){
        Antwort retValue = null;

        int index = 0;
        while(retValue == null && index < this.antworten.size() ){
            if(antworten.get(index).isright){
                retValue = antworten.get(index);
            }
            index++;
        }

        return retValue;
    }
}
