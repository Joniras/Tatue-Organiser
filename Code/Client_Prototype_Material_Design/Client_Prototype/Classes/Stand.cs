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
        public String ST_Name { get; set; }
        public String ST_Info { get; set; }
        public Shape ST_Shape { get; set; }
        public List<StandRating> ST_allRatings = new List<StandRating>();

        public Stand(int _ID, String _Name, String _Info, Shape _Shape)
        {
            ST_ID = _ID;
            ST_Name = _Name;
            ST_Info = _Info;
            ST_Shape = _Shape;
        }

        public void addRatingToStand(StandRating _Rating)
        {
            ST_allRatings.Add(_Rating);
        }

        public override String ToString()
        {
            return this.ST_Name; 
        }

        public void resetRatings()
        {
            ST_allRatings = null;
        }

        public List<StandRating> getAllRatings()
        {
            return ST_allRatings;
        }

        public float getFreundlichkeit()
        {
            float avg_freundlichkeit = 0;
            int count = 0;

            if (ST_allRatings != null)
            {
                foreach (StandRating st in ST_allRatings)
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

            if (ST_allRatings != null)
            {
                foreach (StandRating st in ST_allRatings)
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

            if (ST_allRatings != null)
            {
                foreach (StandRating st in ST_allRatings)
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
