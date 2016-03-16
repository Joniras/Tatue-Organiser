using System;

namespace BSD_Client
{
    public class StandRating
    {
        public int SR_ID { get; set; }
        public float aufbau { get; set; }
        public float freundlichkeit { get; set; }
        public float kompetenz { get; set; }

        public StandRating()
        {

        }

        public StandRating(int _ID, float _Aufbau, float _Freundlichkeit, float _Kompetenz)
        {
            SR_ID = _ID;
            aufbau = _Aufbau;
            freundlichkeit = _Freundlichkeit;
            kompetenz = _Kompetenz;
        }

        public override String ToString()
        {
            return "Aufbau: " + aufbau + " Freundlichkeit: " + freundlichkeit + " Kompetenz: " + kompetenz;
        }
    }
}