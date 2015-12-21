using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Net;
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

namespace BSD_Client
{
    /// <summary>
    /// Interaction logic for StandRatingAdmin.xaml
    /// </summary>
    public partial class StandRatingAdmin : Window
    {
        Stand currentStand;
        Window myParent;
        private BackgroundWorker bw_resetRating = new BackgroundWorker();

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
            lblavgFreundlichkeit.Content =   currentStand.getFreundlichkeit();
            lblavgKompetenz.Content =   currentStand.getKompetenz();
            lblavgAufbau.Content =  currentStand.getAufbau();
        }

        private void fillGridRatings()
        {
            //Get Ratings from Stand
            gridRatings.ItemsSource = currentStand.getAllRatings();
        }

        private void btnResetRatings_Click(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("Wirklich alle Ratings löschen?", "Achtung", MessageBoxButton.YesNo, MessageBoxImage.Warning);
            if (result == MessageBoxResult.Yes)
            {
                bw_resetRating.DoWork += new DoWorkEventHandler(bw_DoWorkResetRatings);
                bw_resetRating.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedResetRatings);
                bw_resetRating.RunWorkerAsync();

                currentStand.resetRatings();
                fillGridRatings();
                calcAvgRatings();
            }

        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Hide();
            myParent.Show();
        }


        private void bw_DoWorkResetRatings(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/staende/" + this.currentStand.st_id+"/ratings")) as HttpWebRequest;
            req.Method = "DELETE";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = resp.StatusCode;
            }
        }

        private void bw_RunWorkerCompletedResetRatings(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((HttpStatusCode)e.Result != HttpStatusCode.OK)
            {
                MessageBox.Show("Ratings konnten nicht gelöscht werden\nStatus:" + ((HttpStatusCode)e.Result).ToString());
            }
        }
    }
}
