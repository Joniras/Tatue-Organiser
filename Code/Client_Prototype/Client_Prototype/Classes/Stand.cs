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

    }
}
