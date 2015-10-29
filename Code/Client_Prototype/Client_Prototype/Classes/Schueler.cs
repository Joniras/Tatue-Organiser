using System;

namespace Client_Prototype
{
    class Schueler
    {
        public int S_ID { get; set; }
        public String S_Vorname { get; set; }
        public String S_Nachname { get; set; }
        public String S_Klasse { get; set; }

        public Schueler(int _ID, String _Vorname, String _Nachname, String _Klasse)
        {
            S_ID = _ID;
            S_Vorname = _Vorname;
            S_Nachname = _Nachname;
            S_Klasse = _Klasse;
        }

    }
}