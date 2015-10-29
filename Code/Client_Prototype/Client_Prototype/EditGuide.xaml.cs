using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Client_Prototype
{
    /// <summary>
    /// Interaction logic for EditGuide.xaml
    /// </summary>
    public partial class EditGuide : Window
    {
        Schueler schueler;


        public EditGuide(Schueler _schueler)
        {
            InitializeComponent();
            schueler = _schueler;
            txtVorname.Text = schueler.S_Vorname;
            txtNachname.Text = schueler.S_Nachname;
            txtKlasse.Text = schueler.S_Klasse;
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            Schueler toAdd = new Schueler(1, txtVorname.Text, txtNachname.Text, txtKlasse.Text, schueler.S_isGuide);
            lblMessage.Content = "SChueler changed";
        }
    }
}
