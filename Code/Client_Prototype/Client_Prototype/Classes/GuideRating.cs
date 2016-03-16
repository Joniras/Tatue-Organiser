namespace BSD_Client
{
    public class GuideRating
    {
        public int GR_ID { get; set; }
        public float freundlichkeit { get; set; }
        public float kompetenz { get; set; }

        public GuideRating()
        {

        }

        public GuideRating(int _ID, float _Freundlichkeit, float _Kompetenz)
        {
            GR_ID = _ID;
            freundlichkeit = _Freundlichkeit;
            kompetenz = _Kompetenz;
        }
    }
}