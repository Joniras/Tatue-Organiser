using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BSD_Client.Classes
{
    public class Antwort
    {
        public int a_id { get; set; }
        public string text { get; set; }
        public bool isright { get; set; }

        public Antwort()
        {

        }

        public Antwort(int _id, String _text, bool _isright)
        {
            a_id = _id;
            text = _text;
            isright = _isright;
        }

        public Antwort(String text, bool _isRight)
        {
            this.text = text;
            this.isright = _isRight;
        }

    }




}
