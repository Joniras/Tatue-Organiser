using System;
using System.Collections.Generic;

namespace Client_Prototype
{
    class Guide : Schueler
    {
        public int G_ID { get; set; }
        public List<GuideRating> G_allRatings = new List<GuideRating>();

        public Guide(int _GID, int _SID, String _Vorname, String _Nachname, String _Klasse) : base(_SID, _Vorname, _Nachname, _Klasse)
        {
            G_ID = _GID;
        }
    }
}
