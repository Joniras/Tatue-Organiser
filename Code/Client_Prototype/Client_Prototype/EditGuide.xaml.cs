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
        Window myParent;


        public EditGuide(Schueler _schueler, Window _parent)
        {
            InitializeComponent();
            schueler = _schueler;
            myParent = _parent;
            txtVorname.Text = schueler.Vorname;
            txtNachname.Text = schueler.Nachname;
            txtKlasse.Text = schueler.Klasse;
           // checkBoxIsGuide.IsChecked = schueler.S_isGuide;
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            Schueler toAdd = new Schueler(1, txtVorname.Text, txtNachname.Text, txtKlasse.Text, schueler.isGuide);
            //TODO
            //Post Schueler to database
            lblMessage.Content = "SChueler changed";
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }

        private void btnRatings_Click(object sender, RoutedEventArgs e)
        {
            //new Gui for Ratings
            GuideRatingAdmin gra = new GuideRatingAdmin(schueler, this);
            gra.Show();
            this.Hide();
        }
    }
}
