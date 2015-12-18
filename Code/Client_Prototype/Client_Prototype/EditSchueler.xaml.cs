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
    /// Interaction logic for EditGuide.xaml
    /// </summary>
    public partial class EditSchueler : Window
    {
        Schueler schueler;
        Window myParent;
        private BackgroundWorker bw_editSchueler = new BackgroundWorker();

        public EditSchueler(Schueler _schueler, Window _parent)
        {
            InitializeComponent();
            schueler = _schueler;
            myParent = _parent;
            txtVorname.Text = schueler.vorname;
            txtNachname.Text = schueler.nachname;
            txtKlasse.Text = schueler.klasse;
            checkBoxIsGuide.IsChecked = schueler.guide;

            if (!schueler.guide)
            {
                btnRatings.IsEnabled = false;
            }
        }

        private void btnSave_Click(object sender, RoutedEventArgs e){
        
            //TODO
            //Post Schueler to database

            Schueler toAdd = new Schueler(schueler.s_id, txtVorname.Text, txtNachname.Text, txtKlasse.Text, ((checkBoxIsGuide.IsChecked.HasValue) ? (bool)checkBoxIsGuide.IsChecked : false));
            bw_editSchueler.DoWork += new DoWorkEventHandler(bw_DoWorkAddSchueler);
            bw_editSchueler.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedSchueler);
            bw_editSchueler.RunWorkerAsync(toAdd);

            lblMessage.Content = "Schueler changed";
        }

        private void bw_RunWorkerCompletedSchueler(object sender, RunWorkerCompletedEventArgs e)
        {
            this.Close();
            myParent.Show();
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

        private void bw_DoWorkAddSchueler(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/schueler/")) as HttpWebRequest;
            req.Method = "PUT";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            Schueler toadd = (Schueler)e.Argument;

            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            String content = json_serializer.Serialize(toadd);

            req.ContentLength = content.Length;
            byte[] data = Encoding.ASCII.GetBytes(content);
            using (Stream stream = req.GetRequestStream())
            {
                stream.Write(data, 0, data.Length);
            }

            Console.WriteLine("###################");
            Console.WriteLine(content);
            Console.WriteLine("###################");
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }
    }
}
