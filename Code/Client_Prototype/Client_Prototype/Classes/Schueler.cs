using System;
using System.Collections.Generic;

namespace Client_Prototype
{
    public class Schueler
    {
        public int s_id { get; set; }
        public String vorname { get; set; }
        public String nachname { get; set; }
        public String klasse { get; set; }
        public bool guide { get; set; }
        public List<GuideRating> guiderating = new List<GuideRating>();

        public Schueler()
        {

        }

        public Schueler(int _ID, String _Vorname, String _Nachname, String _Klasse, bool _isGuide)
        {
            s_id = _ID;
            vorname = _Vorname;
            nachname = _Nachname;
            klasse = _Klasse;
            guide = _isGuide;
        }

        public void addRatingToSchueler(GuideRating _Rating)
        {
            guiderating.Add(_Rating);
        }

        public List<GuideRating> getAllRatings()
        {
            return guiderating;
        }

        public override String ToString()
        {
            return this.nachname + " " + this.vorname + ", " + this.klasse+", isGuide: "+this.guide;
        }

        public void resetRatings()
        {
            guiderating = null;
        }

        public float getFreundlichkeit()
        {
            float avg_freundlichkeit = 0;
            int count = 0;

            if (guiderating != null)
            {
                foreach (GuideRating gr in guiderating)
                {
                    avg_freundlichkeit += gr.GR_Freundlichkeit;
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
                foreach (GuideRating gr in guiderating)
                {
                    avg_kompetenz += gr.GR_Kompetenz;
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

        public int getId()
        {
            return this.s_id;
        }
    }
}