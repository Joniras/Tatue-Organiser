using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Shapes;
using BSD_Client.Classes;

namespace BSD_Client
{
    public class Stand
    {
        public int st_id { get; set; }
        public String stname { get; set; }
        public String info { get; set; }
        public List<StandRating> standratings = new List<StandRating>();
        public Rechteck shape { get; set; }
        public List<Schueler> standschueler = new List<Schueler>();


        public Stand(int _ID, String _Name, String _Info, Rechteck _Shape)
        {
            st_id = _ID;
            stname = _Name;
            info = _Info;
            shape = _Shape;
        }

        public Stand()
        {

        }

        public void addRatingToStand(StandRating _Rating)
        {
            standratings.Add(_Rating);
        }

        public override String ToString()
        {
            return  this.stname + " " + shape; 
        }

        public void resetRatings()
        {
            standratings.Clear();
        }

        public List<StandRating> getAllRatings()
        {
            return standratings;
        }

        public float getFreundlichkeit()
        {
            float avg_freundlichkeit = 0;
            int count = 0;

            if (standratings != null)
            {
                foreach (StandRating st in standratings)
                {
                    avg_freundlichkeit += st.freundlichkeit;
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
                foreach (StandRating st in standratings)
                {
                    avg_kompetenz += st.kompetenz;
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
                foreach (StandRating st in standratings)
                {
                    avg_Aufbau += st.aufbau;
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
}
