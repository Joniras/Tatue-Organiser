namespace BSD_Client
{
    public class StandRating
    {
        public int SR_ID { get; set; }
        public int SR_Aufbau { get; set; }
        public int SR_Freundlichkeit { get; set; }
        public int SR_Kompetenz { get; set; }

        public StandRating(int _ID, int _Aufbau, int _Freundlichkeit, int _Kompetenz)
        {
            SR_ID = _ID;
            SR_Aufbau = _Aufbau;
            SR_Freundlichkeit = _Freundlichkeit;
            SR_Kompetenz = _Kompetenz;
        }
    }
}