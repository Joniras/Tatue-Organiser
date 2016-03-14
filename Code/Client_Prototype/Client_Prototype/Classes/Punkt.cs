using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BSD_Client.Classes
{
    public class Punkt
    {

        public float x { get; set; }
        public float y { get; set; }

        public Punkt()
        {

        }

        public Punkt(float x, float y)
        {
            this.x = x;
            this.y = y;
        }

        public override String ToString()
        {
            return x + " " + y;
        }
    }
}
