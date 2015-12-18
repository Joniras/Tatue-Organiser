using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client_Prototype.Classes
{
    class Frage
    {
        public int f_id { get; set; }
        public string text { get; set; }
        public List<Antwort> antworten = new List<Antwort>();

        public Frage()
        {

        }

        public Frage(int _id, string _Text)
        {
            f_id = _id;
            text = _Text;
        }
    }
}
