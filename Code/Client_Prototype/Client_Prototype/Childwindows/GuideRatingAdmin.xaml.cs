using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
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
    /// Interaction logic for GuideRatingAdmin.xaml
    /// </summary>
    public partial class GuideRatingAdmin : Window
    {
        Window myParent;
        Schueler currentSchueler;
        private BackgroundWorker bw_getRatings = new BackgroundWorker();
        public GuideRatingAdmin(Schueler _currentS, Window _parent)
        {
            InitializeComponent();
            currentSchueler = _currentS;
            myParent = _parent;
            
            gridRatings.IsReadOnly = true;

        }

        private void calcAvgRatings()
        {
            lblavgFreundlichkeit.Content = currentSchueler.getFreundlichkeit();
            lblavgKompetenz.Content = currentSchueler.getKompetenz();
        }

        private void fillGridRatings()
        {
            //Get Ratings form currentSchueler
            gridRatings.ItemsSource = currentSchueler.getAllRatings();
            lblMessage.Content = "List Filled";
        }

        private void btnResetRatings_Click(object sender, RoutedEventArgs e)
        {
            //delete Ratings from Schueler
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

        private void bw_DoWorkRatings(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/guides/" + currentSchueler.s_id + "/ratings")) as HttpWebRequest;
            req.Method = "GET";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void bw_RunWorkerCompletedRatings(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            GuideRating[] guideR = (GuideRating[])json_serializer.Deserialize<GuideRating[]>((String)e.Result);
            List<GuideRating> content = new List<GuideRating>(guideR);
            currentSchueler.guiderating = content;
            Console.WriteLine("Staende: " + content.ToString());

            gridRatings.ItemsSource = content;
            lblMessage.Content = content.Count + " Ratings Loaded";
            if (gridRatings.Items.Count == 0)
            {
                lblMessage.Content = "Dieser Guide hat keine Ratings";
                btnResetRatings.IsEnabled = false;
            }
            else
            {
                calcAvgRatings();
            }

        }

        private void getRatings()
        {
            bw_getRatings.WorkerReportsProgress = false;
            bw_getRatings.WorkerSupportsCancellation = false;
            bw_getRatings.DoWork += new DoWorkEventHandler(bw_DoWorkRatings);
            bw_getRatings.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedRatings);
            bw_getRatings.RunWorkerAsync();
        }

        private void Window_Activated(object sender, EventArgs e)
        {
            getRatings();
  
        }
    }
}
