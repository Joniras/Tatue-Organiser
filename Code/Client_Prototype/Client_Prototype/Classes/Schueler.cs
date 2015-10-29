using System;
using System.Collections.Generic;

namespace Client_Prototype
{
    public class Schueler
    {
        public int S_ID { get; set; }
        public String S_Vorname { get; set; }
        public String S_Nachname { get; set; }
        public String S_Klasse { get; set; }
        public Boolean S_isGuide { get; set; }
        public List<GuideRating> S_allRatings = new List<GuideRating>();

        public Schueler(int _ID, String _Vorname, String _Nachname, String _Klasse, Boolean _isGuide)
        {
            S_ID = _ID;
            S_Vorname = _Vorname;
            S_Nachname = _Nachname;
            S_Klasse = _Klasse;
            S_isGuide = _isGuide;
        }

        public void addRatingToSchueler(GuideRating _Rating)
        {
            S_allRatings.Add(_Rating);
        }

        public override String ToString()
        {
            return this.S_Nachname + " " + S_Vorname + ", " + S_Klasse;
        }

    }
}