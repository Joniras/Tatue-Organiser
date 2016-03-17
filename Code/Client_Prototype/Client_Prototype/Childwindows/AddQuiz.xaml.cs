using BSD_Client.Classes;
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
    /// Interaction logic for AddQuiz.xaml
    /// </summary>
    public partial class AddQuiz : Window
    {
        Window myParent;
        Abteilung abteilung;
        public BackgroundWorker bw_addQuiz = new BackgroundWorker();
        Quiz toAdd;
        private string Method;

        public AddQuiz(Window _parent, Abteilung _abteilung, Quiz _quiz)
        {
            InitializeComponent();
            myParent = _parent;
            abteilung = _abteilung;
            if(_quiz != null)
            {
                toAdd = _quiz;
                this.lvFragen.ItemsSource = _quiz.fragen;
                this.txtTitel.Text = _quiz.titel;
                this.Method = "PUT";
            }
            else
            {
                toAdd = new Quiz();
                this.Method = "POST";
            }
        }

        private void btnAddQuiz_Click(object sender, RoutedEventArgs e)
        {
            if(!txtTitel.Text.Equals(""))
            {
                toAdd.titel = txtTitel.Text;
                bw_addQuiz.DoWork += new DoWorkEventHandler(bw_DoWorkAddQuiz);
                bw_addQuiz.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_DoWorkCompletedAddQuiz);
                bw_addQuiz.RunWorkerAsync(toAdd);
                lblMessage.Content = "Quiz added";
            }
            else
            {
                lblMessage.Content = "Bitte Titel eingeben"; 
            }
            

        }

        private void bw_DoWorkCompletedAddQuiz(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((HttpStatusCode)e.Result != HttpStatusCode.OK)
            {
                MessageBox.Show("Quiz konnte nicht gespeichert werden\nStatus:" + ((HttpStatusCode)e.Result).ToString());
            }
            else
            {
                this.Close();
                myParent.Show();
            }
        }


        private void btnAddFrage_Click(object sender, RoutedEventArgs e)
        {
            Frage tmp = new Frage();
            tmp.text = this.txtFrage.Text;
            tmp.addAntwort(new Antwort(this.txtFalscheAntwort1.Text,false));
            tmp.addAntwort(new Antwort(this.txtFalscheAntwort2.Text,false));
            tmp.addAntwort(new Antwort(this.txtFalscheAntwort3.Text,false));
            tmp.addAntwort(new Antwort(this.txtRichtigeAntwort.Text,true));

            this.txtFalscheAntwort1.Text = "";
            this.txtFalscheAntwort2.Text = "";
            this.txtFalscheAntwort3.Text = "";
            this.txtRichtigeAntwort.Text = "";
            this.txtFrage.Text = "";
            toAdd.fragen.Add(tmp);
            this.lvFragen.ItemsSource = toAdd.fragen;
            Console.WriteLine("Fragen: " + this.toAdd.fragen.Count);


            
        }



        private void btnDeleteFrage_Click(object sender, RoutedEventArgs e)
        {
            if (this.lvFragen.SelectedItem != null)
            {
                this.toAdd.fragen.RemoveAt(this.lvFragen.SelectedIndex);
                this.lvFragen.Items.RemoveAt(this.lvFragen.SelectedIndex);
            }
        }

        private void bw_DoWorkAddQuiz(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/abteilungen/" + abteilung.ab_id+"/quiz")) as HttpWebRequest;
            req.Method = this.Method;

            req.ContentType = "application/json";
            req.Accept = "application/json";
            Quiz toadd = (Quiz)e.Argument;

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

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }



    }
}
