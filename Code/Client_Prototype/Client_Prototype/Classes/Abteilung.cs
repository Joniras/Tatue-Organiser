using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Client_Prototype
{
    public class Abteilung
    {
        public int ab_id { get; set; }
        public string ab_name { get; set; }
        public int ab_etage { get; set; }
        public List<Stand> ab_stands = new List<Stand>();

        public Abteilung()
        {
            
        }

        public Abteilung(int _ID, String _Name, int _Etage)
        {
            ab_id = _ID;
            ab_name = _Name;
            ab_etage = _Etage;
        }

        public void addStandToAbteilung(Stand _Stand)
        {
            ab_stands.Add(_Stand);
        }

        public override String ToString()
        {
            return "Name: "+this.ab_name+"; ID:"+this.ab_id+"; Etage: "+this.ab_etage+"; Stands:"+this.ab_stands;
        }

        
        
    }
}
