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

namespace Client_Prototype
{
    /// <summary>
    /// Interaction logic for AddGuideFromSchueler.xaml
    /// </summary>
    public partial class AddGuideFromSchueler : Window
    {
        Window myParent;
        private BackgroundWorker bw_Schueler = new BackgroundWorker();
        private BackgroundWorker bw_Guides = new BackgroundWorker();

        public AddGuideFromSchueler()
        {
            InitializeComponent();
            fillData();
        }

        public AddGuideFromSchueler(Window _parent)
        {
            InitializeComponent();
            myParent = _parent;
            fillData();
        }

        private void fillData()
        {
            bw_Schueler.WorkerReportsProgress = false;
            bw_Schueler.WorkerSupportsCancellation = false;
            bw_Schueler.DoWork += new DoWorkEventHandler(bw_DoWorkSchueler);
            bw_Schueler.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedSchueler);
            bw_Schueler.RunWorkerAsync();


            bw_Guides.WorkerReportsProgress = false;
            bw_Guides.WorkerSupportsCancellation = false;
            bw_Guides.DoWork += new DoWorkEventHandler(bw_DoWorkGuide);
            bw_Guides.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedGuide);
            bw_Guides.RunWorkerAsync();
        }



        private void btnAddGuide_Click(object sender, RoutedEventArgs e)
        {
            /*
            Schueler toAdd = ((Schueler)cmbSchueler.SelectedItem);
            toAdd.S_isGuide = true;
            //TODO
            //post new Schueler to Database
            //call fillComboSchueler
            //call fillListGuides
            lblMessage.Content = "Guide Added";
            */
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }

        private void bw_DoWorkSchueler(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/schueler")) as HttpWebRequest;
            req.Method = "GET";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void bw_RunWorkerCompletedSchueler(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            Schueler[] schueler = (Schueler[])json_serializer.Deserialize<Schueler[]>((String)e.Result);
            List<Schueler> content = new List<Schueler>(schueler);
            Console.WriteLine((String)e.Result);
            Console.WriteLine(content[0].ToString());
            cmbSchueler.ItemsSource = schueler;
        }

        private void bw_DoWorkGuide(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/guides")) as HttpWebRequest;
            req.Method = "GET";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void bw_RunWorkerCompletedGuide(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            Schueler[] guides = (Schueler[])json_serializer.Deserialize<Schueler[]>((String)e.Result);
            List<Schueler> content = new List<Schueler>(guides);
            Console.WriteLine((String)e.Result);
            Console.WriteLine(content[0].ToString());
            gridGuides.ItemsSource = guides;
        }
    }
}
