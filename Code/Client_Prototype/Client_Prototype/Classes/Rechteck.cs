using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;

namespace BSD_Client.Classes
{
    public class Rechteck
    {

        public Punkt a { get; set; }
        public Punkt b { get; set; }

        public Rechteck()
        {

        }

        public Rechteck(Punkt a, Punkt b)
        {
            this.a = a;
            this.b = b;
        }

        public List<Punkt> getPoints()
        {
            List<Punkt> retValue = new List<Punkt>();
            retValue.Add(a);
            retValue.Add(b);
            return retValue;
        }

        public override String ToString()
        {
            return a + " " + b;
        }
    }
}
