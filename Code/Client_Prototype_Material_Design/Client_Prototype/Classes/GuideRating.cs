namespace Client_Prototype
{
    public class GuideRating
    {
        public int GR_ID { get; set; }
        public int GR_Freundlichkeit { get; set; }
        public int GR_Kompetenz { get; set; }

        public GuideRating(int _ID, int _Freundlichkeit, int _Kompetenz)
        {
            GR_ID = _ID;
            GR_Freundlichkeit = _Freundlichkeit;
            GR_Kompetenz = _Kompetenz;
        }
    }
}