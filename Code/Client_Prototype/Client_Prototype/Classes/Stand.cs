using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Shapes;

namespace Client_Prototype
{
    public class Stand
    {
        public int ST_ID { get; set; }
        public String STName { get; set; }
        public String Info { get; set; }
        public Shape Shape { get; set; }
        public List<StandRating> standratings = new List<StandRating>();

        public Stand(int _ID, String _Name, String _Info, Shape _Shape)
        {
            ST_ID = _ID;
            STName = _Name;
            Info = _Info;
            Shape = _Shape;
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
            return  this.STName; 
        }

        public void resetRatings()
        {
            standratings = null;
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
                    avg_freundlichkeit += st.SR_Freundlichkeit;
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
                    avg_kompetenz += st.SR_Kompetenz;
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
                    avg_Aufbau += st.SR_Aufbau;
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
