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
    /// Interaction logic for GuideRatingAdmin.xaml
    /// </summary>
    public partial class GuideRatingAdmin : Window
    {
        Window myParent;
        Schueler currentSchueler;
        public GuideRatingAdmin(Schueler _currentS, Window _parent)
        {
            InitializeComponent();
            currentSchueler = _currentS;
            myParent = _parent;
            fillGridRatings();
            calcAvgRatings();
            gridRatings.IsReadOnly = true;

        }

        private void calcAvgRatings()
        {
            lblFreundlichkeit.Content = "Freundlichkeit: " + currentSchueler.getFreundlichkeit();
            lblKompetenz.Content = "Kompetenz: " + currentSchueler.getKompetenz();
        }

        private void fillGridRatings()
        {
            gridRatings.ItemsSource = currentSchueler.getAllRatings();
            lblMessage.Content = "List Filled";
        }

        private void btnResetRatings_Click(object sender, RoutedEventArgs e)
        {
            currentSchueler.resetRatings();
            fillGridRatings();
            calcAvgRatings();
            lblMessage.Content = "Ratings Deleted";
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }
    }
}
