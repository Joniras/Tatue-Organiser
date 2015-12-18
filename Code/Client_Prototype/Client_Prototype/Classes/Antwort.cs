using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Client_Prototype.Classes
{
    class Antwort
    {
        public int a_id { get; set; }
        public string text { get; set; }
        public bool isright { get; set; }
        public List<Antwort> antworten = new List<Antwort>();

        public Antwort()
        {

        }

        public Antwort(int _id, String _text, bool _isright)
        {
            a_id = _id;
            text = _text;
            isright = _isright;
        }
    }




}
