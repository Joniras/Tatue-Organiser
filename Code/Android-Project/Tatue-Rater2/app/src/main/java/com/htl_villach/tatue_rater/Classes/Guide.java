package com.htl_villach.tatue_rater.Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 10.03.2016.
 */
public class Guide extends Schueler{
    public int G_ID;
    public List<GuideRating> G_allRatings = new ArrayList<GuideRating>();

    public Guide() {
    }

    public Guide(int _GID, int _SID, String _Vorname, String _Nachname, String _Klasse) {
        super(_SID, _Vorname, _Nachname, _Klasse, true);
        G_ID = _GID;
    }
}
