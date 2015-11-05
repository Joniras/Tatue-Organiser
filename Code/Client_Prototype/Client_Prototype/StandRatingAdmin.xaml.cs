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
    /// Interaction logic for StandRatingAdmin.xaml
    /// </summary>
    public partial class StandRatingAdmin : Window
    {
        Stand currentStand;
        Window myParent;

        public StandRatingAdmin(Stand _currentS, Window _parent)
        {
            InitializeComponent();
            currentStand = _currentS;
            myParent = _parent;
            fillGridRatings();
            calcAvgRatings();
            gridRatings.IsReadOnly = true;
        }

        private void calcAvgRatings()
        {
            lblFreundlichkeit.Content = "Freundlichkeit: " + currentStand.getFreundlichkeit();
            lblKompetenz.Content = "Kompetenz: " + currentStand.getKompetenz();
            lblAufbau.Content = "Aufbau: " + currentStand.getAufbau();
        }

        private void fillGridRatings()
        {
            //Get Ratings from Stand
            gridRatings.ItemsSource = currentStand.getAllRatings();
            lblMessage.Content = "List Filled";
        }

        private void btnResetRatings_Click(object sender, RoutedEventArgs e)
        {
            AlertAskWindow aaw = new AlertAskWindow(this);

            if (aaw.ShowDialog() == true)
            {
                //Delete Ratings from Stand
                currentStand.resetRatings();
                fillGridRatings();
                calcAvgRatings();
                lblMessage.Content = "Ratings Deleted";
            }

        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Hide();
            myParent.Show();
        }
    }
}
