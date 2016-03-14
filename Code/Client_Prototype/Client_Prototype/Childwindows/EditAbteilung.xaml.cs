using System;
using System.Collections.Generic;
using System.Linq;
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
using BSD_Client.Properties;
using System.ComponentModel;
using System.Net;
using System.IO;
using System.Web.Script.Serialization;

namespace BSD_Client
{
    /// <summary>
    /// Interaktionslogik für EditAbteilung.xaml
    /// </summary>
    public partial class EditAbteilung : Window
    {
        Abteilung abteilung;
        Window myParent;
        public enum HTTPMETHODS { GET, PUT, POST, DELETE };

        private BackgroundWorker bw_Staende = new BackgroundWorker();
        private BackgroundWorker bw_DeleteStand = new BackgroundWorker();

        public EditAbteilung(Abteilung _ab, Window _parent)
        {
            InitializeComponent();
            abteilung = _ab;
            myParent = _parent;
            lblTitle.Content = abteilung.ab_name + " bearbeiten";
            if (_ab.ab_quiz != null && _ab.ab_quiz.titel != "" && _ab.ab_quiz.titel != null)
            {
                lblQuiz.Content = _ab.ab_quiz.titel;
                btnAddQuiz.Visibility = System.Windows.Visibility.Hidden;
            }
            else
            {
                btnEditQuiz.Visibility = System.Windows.Visibility.Visible;
            }

            //drawTestLine();
            //drawAbteilung();
            //getStaende();

        }

        private void btnAddStand_Click(object sender, RoutedEventArgs e)
        {

            AddStandInAbteilung asin = new AddStandInAbteilung(this, abteilung);
            asin.Show();
            this.Hide();
        }

        private void drawTestLine()
        {
            Line myLine = new Line();
            myLine.Stroke = System.Windows.Media.Brushes.LightSteelBlue;
            myLine.X1 = 1;
            myLine.X2 = 50;
            myLine.Y1 = 1;
            myLine.Y2 = 50;
            myLine.HorizontalAlignment = HorizontalAlignment.Left;
            myLine.VerticalAlignment = VerticalAlignment.Center;
            myLine.StrokeThickness = 2;
            canvasStandplan.Children.Add(myLine);
        }


        private void drawAbteilung()
        {
            foreach (Stand item in abteilung.ab_stande)
            {
                Paint_Stand(item);
            }
        }

        private void Paint_Stand(Stand toPaint)
        {

            System.Windows.Shapes.Polygon shape = new System.Windows.Shapes.Polygon();

            PointCollection points = new PointCollection();
            points.Add(new Point(toPaint.shape.a.x, toPaint.shape.a.y)); //Left Top
            points.Add(new Point(toPaint.shape.b.x, toPaint.shape.a.y)); //Right Top
            points.Add(new Point(toPaint.shape.b.x, toPaint.shape.b.y)); //Right Bottom
            points.Add(new Point(toPaint.shape.a.x, toPaint.shape.b.y)); //Left Bottom

            shape.Points = points;
            shape.Stroke = Brushes.Black;
            //shape.Fill = Brushes.Green;
            shape.StrokeThickness = 1;
            //shape.HorizontalAlignment = HorizontalAlignment.Left;
            //shape.VerticalAlignment = VerticalAlignment.Center;



            canvasStandplan.Children.Add(shape);

            Console.WriteLine("##Painted-------- " + toPaint.ToString());


        }

        private void getStaende()
        {
            //TODO
            //Get all Stands from Abteilung
            bw_Staende.WorkerReportsProgress = false;
            bw_Staende.WorkerSupportsCancellation = false;
            bw_Staende.DoWork += new DoWorkEventHandler(bw_DoWorkStaende);
            bw_Staende.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedStaende);
            bw_Staende.RunWorkerAsync();
        }

        private void bw_DoWorkStaende(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/abteilungen/" + abteilung.ab_id + "/staende")) as HttpWebRequest;
            req.Method = "GET";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = reader.ReadToEnd();
            }
        }

        private void bw_RunWorkerCompletedStaende(object sender, RunWorkerCompletedEventArgs e)
        {
            JavaScriptSerializer json_serializer = new JavaScriptSerializer();
            Stand[] staende = (Stand[])json_serializer.Deserialize<Stand[]>((String)e.Result);
            
            List<Stand> content = new List<Stand>(staende);
            abteilung.ab_stande = content;
            Console.WriteLine("Staende: " + content.ToString());

            drawAbteilung();
            listViewStaende.ItemsSource = staende;
            
        }

        private void btnEditStand_Click(object sender, RoutedEventArgs e)
        {
            if(listViewStaende.SelectedItem != null)
            {
                EditStand es = new EditStand(((Stand)listViewStaende.SelectedItem), this);
                es.Show();
                this.Hide();
            }
            else
            {
                lblMessage.Content = "Stand auswählen";
            }

        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Hide();
            myParent.Show();
        }

        private void btnAddQuiz_Click(object sender, RoutedEventArgs e)
        {
            AddQuiz aq = new AddQuiz(this, abteilung, null);
            aq.Show();
            this.Hide();
        }

        private void btnDeleteStand_Click(object sender, RoutedEventArgs e)
        {
            bw_DeleteStand.DoWork += new DoWorkEventHandler(bw_DoWorkDeleteStand);
            bw_DeleteStand.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedDeleteStand);
            bw_DeleteStand.RunWorkerAsync(((Stand)listViewStaende.SelectedItem).st_id);
        }

        private void btnRemoveQuiz_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnEditQuiz_Click(object sender, RoutedEventArgs e)
        {
            AddQuiz aq = new AddQuiz(this, abteilung, this.abteilung.ab_quiz);
            aq.Show();
            this.Hide();
        }



        private void bw_DoWorkDeleteStand(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/staende/" + Convert.ToInt32(e.Argument))) as HttpWebRequest;
            req.Method = "DELETE";

            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader = new StreamReader(resp.GetResponseStream());
                e.Result = resp.StatusCode;
            }

        }

        private void bw_RunWorkerCompletedDeleteStand(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((HttpStatusCode)e.Result == HttpStatusCode.OK)
            {
                lblMessage.Content = "Stand gelöscht";
                listViewStaende.ItemsSource = null;
                canvasStandplan.Children.Clear();
                getStaende();
            }
            else
            {
                lblMessage.Content = "Stand nicht gelöscht (Status: " + (HttpStatusCode)e.Result+")";
            }
        }

        private void Window_Activated(object sender, EventArgs e)
        {
            listViewStaende.ItemsSource = null;
            
            canvasStandplan.Children.Clear();
            getStaende();
        }
    }
}
