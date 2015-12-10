using System;
using System.Collections.Generic;
using System.ComponentModel;
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
        private BackgroundWorker bw_Guides = new BackgroundWorker();
        public MainWindow()
        {
            InitializeComponent();
            addData();
            gridGuide.IsReadOnly = true;
            gridAbteilung.IsReadOnly = true;

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

            
            bw_Guides.WorkerReportsProgress = false;
            bw_Guides.WorkerSupportsCancellation = false;
            bw_Guides.DoWork += new DoWorkEventHandler(bw_DoWorkGuide);
            bw_Guides.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedGuide);
            bw_Guides.RunWorkerAsync();
            



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
                EditGuide eg = new EditGuide(sc, this);
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

        private void btnAddGuide_Click(object sender, RoutedEventArgs e)
        {
            lblMessage.Content = "Add Guide";
            AddGuideFromSchueler eg = new AddGuideFromSchueler(this);
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
            Schueler[] schueler = (Schueler[])json_serializer.Deserialize<Schueler[]>((String)e.Result);
            List<Schueler> content = new List<Schueler>(schueler);
            Console.WriteLine((String)e.Result);
            Console.WriteLine(content[0].ToString());
            gridGuide.ItemsSource = schueler;
        }


    }
}
