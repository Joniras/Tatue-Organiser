using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Client_Prototype
{
    /// <summary>
    /// Interaktionslogik für MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public enum HTTPMETHODS { GET, PUT, POST, DELETE };

        private BackgroundWorker bw_Abteilungen = new BackgroundWorker();
        private BackgroundWorker bw_Schueler = new BackgroundWorker();
        private BackgroundWorker bw_deleteSchueler = new BackgroundWorker();
        public MainWindow()
        {
            
            InitializeComponent();
            if (checkConnection())
            {
                addData();
                gridGuide.IsReadOnly = true;
                gridAbteilung.IsReadOnly = true;
            }
            else
            {
                FailedConnection fc = new FailedConnection();
                fc.Show();
                this.Close();
                
            }
        }

        private void addData()
        {
            /*
            List<Abteilung> content = new List<Abteilung>();
            content.Add(new Abteilung(1, "EDVO", 1));
            content.Add(new Abteilung(2, "Bautechnik", 2));
            gridAbteilung.ItemsSource = content;

            string url = "http://192.168.196.0:8080/TatueOrganiser/api/abteilungen";
            */
            //HttpGet(url, HTTPMETHODS.GET, null, new Del(useInformation));
            
            bw_Abteilungen.WorkerReportsProgress = false;
            bw_Abteilungen.WorkerSupportsCancellation = false;
            bw_Abteilungen.DoWork += new DoWorkEventHandler(bw_DoWorkAbteilung);
            bw_Abteilungen.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedAbteilung);
            bw_Abteilungen.RunWorkerAsync();

            
            bw_Schueler.WorkerReportsProgress = false;
            bw_Schueler.WorkerSupportsCancellation = false;
            bw_Schueler.DoWork += new DoWorkEventHandler(bw_DoWorkSchueler);
            bw_Schueler.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedSchueler);
            bw_Schueler.RunWorkerAsync();

            
        }

        private void btnEdit_Click(object sender, RoutedEventArgs e)
        {
            if ((Abteilung)gridAbteilung.SelectedItem != null)
            {
                Abteilung ab = (Abteilung)gridAbteilung.SelectedItem;
                EditAbteilung ea = new EditAbteilung(ab, this);
                lblMessage.Content = "Edit Abteilung";
                ea.Show();
                gridAbteilung.SelectedItem = null;
                this.Hide();
            }
            else if((Schueler)gridGuide.SelectedItem != null)
            {
                Schueler sc = (Schueler)gridGuide.SelectedItem;
                EditSchueler eg = new EditSchueler(sc, this);
                lblMessage.Content = "Edit Abteilung";
                eg.Show();
                gridGuide.SelectedItem = null;
                this.Hide();
            }

            else
            {
                lblMessage.Content = "Bitte auswahl treffen";
            }

        }

        private void btnAddSchueler_Click(object sender, RoutedEventArgs e)
        {
            lblMessage.Content = "Add Guide";
            AddSchueler eg = new AddSchueler(this);
            eg.Show();
            this.Hide();
    
            
        }

        private void btnTemp_Click(object sender, RoutedEventArgs e)
        {
            LoadingWindow lw = new LoadingWindow();
            lw.Show();
        }

        private void bw_DoWorkAbteilung(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/abteilungen")) as HttpWebRequest;
            req.Method = "GET";
            
            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader =  new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void bw_RunWorkerCompletedAbteilung(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            Abteilung[] abteilungen = (Abteilung[])json_serializer.Deserialize<Abteilung[]>((String)e.Result);
            List<Abteilung> content = new List<Abteilung>(abteilungen);
            Console.WriteLine((String)e.Result);
            Console.WriteLine(content[0].ToString());
            gridAbteilung.ItemsSource = abteilungen;

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
                Console.WriteLine("###################"+e.Result);

            }
        }

        private void bw_RunWorkerCompletedSchueler(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            Schueler[] schueler = (Schueler[])json_serializer.Deserialize<Schueler[]>((String)e.Result);
            List<Schueler> content = new List<Schueler>(schueler);
            gridGuide.ItemsSource = schueler;

        }

        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            if ((Schueler)gridGuide.SelectedItem != null)
            {
                bw_deleteSchueler.WorkerReportsProgress = false;
                bw_deleteSchueler.WorkerSupportsCancellation = false;
                bw_deleteSchueler.DoWork += new DoWorkEventHandler(bw_DoWorkDeleteSchueler);
                bw_deleteSchueler.RunWorkerAsync(((Schueler)gridGuide.SelectedItem).getId());
            }
        }

          private void bw_DoWorkDeleteSchueler(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/schueler/"+(int)e.Argument)) as HttpWebRequest;
            req.Method = "DELETE";
            
            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader =  new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private bool checkConnection()
        {
            var url = "http://192.168.196.0:8080/TatueOrganiser/ping";
            bool retVal = false;
            try
            {
                var myRequest = (HttpWebRequest)WebRequest.Create(url);

                var response = (HttpWebResponse)myRequest.GetResponse();

                if (response.StatusCode == HttpStatusCode.OK)
                {
                    retVal = true;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return retVal;
            }
            return retVal;
        }
        


    }
}
