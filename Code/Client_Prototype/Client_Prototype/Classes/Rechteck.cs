﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

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
    }
}
