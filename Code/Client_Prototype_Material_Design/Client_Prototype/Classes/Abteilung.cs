using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Client_Prototype
{
    public class Abteilung
    {
        public int AB_ID { get; set; }
        public string AB_Name { get; set; }
        public int AB_Etage { get; set; }
        public List<Stand> AB_Stands = new List<Stand>();


        public Abteilung(int _ID, String _Name, int _Etage)
        {
            AB_ID = _ID;
            AB_Name = _Name;
            AB_Etage = _Etage;
        }

        public void addStandToAbteilung(Stand _Stand)
        {
            AB_Stands.Add(_Stand);
        }

        public override String ToString()
        {
            return this.AB_Name;
        }

        
        
    }
}
