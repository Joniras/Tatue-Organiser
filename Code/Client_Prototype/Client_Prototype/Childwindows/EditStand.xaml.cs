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
    /// Interaction logic for EditStand.xaml
    /// </summary>
    public partial class EditStand : Window
    {
        Stand stand;
        Window myParent;
        private BackgroundWorker bw_editStand = new BackgroundWorker();
        private BackgroundWorker bw_getSchueler = new BackgroundWorker();

        public EditStand(Stand _stand, Window _parent)
        {
            InitializeComponent();
            stand = _stand;
            myParent = _parent;
            txtName.Text = stand.stname;
            txtInfo.Text = stand.info;
            getSchuler();
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            Stand toAdd = new Stand(1, txtName.Text, txtInfo.Text, null);
            bw_editStand.DoWork += new DoWorkEventHandler(bw_DoWorkEditStand);
            bw_editStand.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedStand);
            stand.info = this.txtInfo.Text;
            stand.stname = this.txtName.Text;
            bw_editStand.RunWorkerAsync(stand);
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }


        private void bw_DoWorkEditStand(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/staende/"+this.stand.st_id)) as HttpWebRequest;
            req.Method = "PUT";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            Stand toadd = (Stand)e.Argument;

            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            String content = json_serializer.Serialize(toadd);

            req.ContentLength = content.Length;
            byte[] data = Encoding.ASCII.GetBytes(content);
            using (Stream stream = req.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = resp.StatusCode;
            }
        }

        private void bw_RunWorkerCompletedStand(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((HttpStatusCode)e.Result == HttpStatusCode.OK)
            {
                lblMessage.Content = "Stand changed";
                this.myParent.Show();
                this.Hide();
            }
            else
            {
                lblMessage.Content = "Stand not changed";
            }
        }

        private void getSchuler()
        {
            //TODO
            //Get all Stands from Abteilung
            bw_getSchueler.WorkerReportsProgress = false;
            bw_getSchueler.WorkerSupportsCancellation = false;
            bw_getSchueler.DoWork += new DoWorkEventHandler(bw_DoWorkSchueler);
            bw_getSchueler.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedSchueler);
            bw_getSchueler.RunWorkerAsync();
        }

        private void bw_DoWorkSchueler(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/schueler/")) as HttpWebRequest;
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

            Console.WriteLine(content.ToString());

            foreach (Schueler s in schueler)
            {
                Console.WriteLine(s.ToString());
                cmbSchueler.Items.Add(s);
            }

        }

    }
}
