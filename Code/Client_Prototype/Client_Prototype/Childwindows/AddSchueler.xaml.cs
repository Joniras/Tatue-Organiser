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
    /// Interaction logic for AddGuideFromSchueler.xaml
    /// </summary>
    public partial class AddSchueler : Window
    {
        Window myParent;
        private BackgroundWorker bw_addSchueler = new BackgroundWorker();


        public AddSchueler()
        {
            InitializeComponent();
        }

        public AddSchueler(Window _parent)
        {
            InitializeComponent();
            myParent = _parent;
        }


        private void bw_RunWorkerCompletedSchueler(object sender, RunWorkerCompletedEventArgs e)
        {
            this.Close();
            myParent.Show();
        }



        private void btnSave_Click(object sender, RoutedEventArgs e)
        {

            Schueler toAdd = new Schueler(1, txtVorname.Text, txtNachname.Text, txtKlasse.Text, ((checkBoxIsGuide.IsChecked.HasValue)?(bool)checkBoxIsGuide.IsChecked:false));
            bw_addSchueler.DoWork += new DoWorkEventHandler(bw_DoWorkAddSchueler);
            bw_addSchueler.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedSchueler);
            bw_addSchueler.RunWorkerAsync(toAdd);

            
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }

        private void bw_DoWorkAddSchueler(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/schueler/")) as HttpWebRequest;
            req.Method = "POST";

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
