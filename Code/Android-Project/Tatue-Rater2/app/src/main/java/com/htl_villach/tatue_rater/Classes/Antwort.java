package com.htl_villach.tatue_rater.Classes;

/**
 * Created by simon on 10.03.2016.
 */
public class Antwort {
    public int a_id;
    public String text;
    public Boolean isright;

    public Antwort() {
    }

    public Antwort(int a_id, String text, Boolean isright) {
        this.a_id = a_id;
        this.text = text;
        this.isright = isright;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsright() {
        return isright;
    }

    public void setIsright(Boolean isright) {
        this.isright = isright;
    }

    @Override
    public String toString() {
        return "Antwort{" +
                "a_id=" + a_id +
                ", text='" + text + '\'' +
                ", isright=" + isright +
                '}';
    }
}
