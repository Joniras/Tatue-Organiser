using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BSD_Client.Classes
{
    public class Quiz
    {
        public int q_id { get; set; }
        public string titel { get; set; }
        public List<Frage> fragen = new List<Frage>();

        public Quiz()
        {

        }

        public Quiz(int _id, String _Titel)
        {
            q_id = _id;
            titel = _Titel;
        }

        public override String ToString()
        {
            return this.titel;
        }
    }
}
