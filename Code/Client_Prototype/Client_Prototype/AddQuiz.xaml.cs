using Client_Prototype.Classes;
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
    /// Interaction logic for AddQuiz.xaml
    /// </summary>
    public partial class AddQuiz : Window
    {
        Window myParent;
        Abteilung abteilung;
        private BackgroundWorker bw_addQuiz = new BackgroundWorker();
        private BackgroundWorker bw_addFrage = new BackgroundWorker();
        private BackgroundWorker bw_updateQuiz = new BackgroundWorker();

        public AddQuiz(Window _parent, Abteilung _abteilung)
        {
            InitializeComponent();
            myParent = _parent;
            abteilung = _abteilung;
            updateQuizzes();
        }

        private void btnAddQuiz_Click(object sender, RoutedEventArgs e)
        {
            if(!txtTitel.Text.Equals(""))
            {
                Quiz toAdd = new Quiz(1, txtTitel.Text);
                bw_addQuiz.DoWork += new DoWorkEventHandler(bw_DoWorkAddQuiz);
                bw_addQuiz.RunWorkerAsync(toAdd);
                lblMessage.Content = "Quiz added";
                updateQuizzes();
            }
            else
            {
                lblMessage.Content = "Bitte Titel eingeben"; 
            }
            

        }

        private void updateQuizzes()
        {
            lvQuizes.Items.Clear();
            bw_updateQuiz.WorkerReportsProgress = false;
            bw_updateQuiz.WorkerSupportsCancellation = false;
            bw_updateQuiz.DoWork += new DoWorkEventHandler(bw_DoWorkUpdateQuizes);
            bw_updateQuiz.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerUpdateCompletedQuizes);
            bw_updateQuiz.RunWorkerAsync();
        }

        private void btnAddFrage_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnDeleteQuiz_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnDeleteFrage_Click(object sender, RoutedEventArgs e)
        {

        }

        private void bw_DoWorkAddQuiz(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/quizzes/?ab_id=" + abteilung.ab_id)) as HttpWebRequest;
            req.Method = "POST";

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
            Console.WriteLine("###################");
            Console.WriteLine(content);
            Console.WriteLine("###################");
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }

        private void bw_DoWorkUpdateQuizes(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/quizzes/?ab_id=" + abteilung.ab_id)) as HttpWebRequest;
            req.Method = "GET";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void bw_RunWorkerUpdateCompletedQuizes(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            Quiz[] quizes = (Quiz[])json_serializer.Deserialize<Quiz[]>((String)e.Result);

            List<Quiz> content = new List<Quiz>(quizes);

            Console.WriteLine(content.ToString());

            if (!(quizes.Length == 0))
            {
                lvQuizes.Items.Clear();
                foreach (Quiz q in quizes)
                {
                    Console.WriteLine(q.ToString());
                    lvQuizes.Items.Add(q);
                }
            }
            else
            {
                lblMessage.Content = "No Quizzes";
            }

        }
    }
}
