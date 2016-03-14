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
    /// Interaktionslogik für AddStandInAbteilung.xaml
    /// </summary>
    public partial class AddStandInAbteilung : Window
    {
        private Point startPoint;
        private BackgroundWorker bw_addStand = new BackgroundWorker();
        private Rectangle rect;
        Abteilung abteilung;
        private int rect_index = -1;
        Window myParent;
        private Rechteck rechteck;

        public AddStandInAbteilung(Window _parent, Abteilung _abteilung)
        {
            InitializeComponent();
            abteilung = _abteilung;
            //TODO
            //Call draw Abteilung
            myParent = _parent;
            btnResetCanvas.IsEnabled = false;
            Console.WriteLine("-------" + abteilung.ToString());
            drawAbteilung();


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



            canvasDrawStand.Children.Add(shape);

            Console.WriteLine("##Painted-------- " + toPaint.ToString());


        }


        private void canvasDrawStand_MouseDown(object sender, MouseButtonEventArgs e)
        {
            startPoint = e.GetPosition(canvasDrawStand);
            rect = new Rectangle
            {
                Stroke = Brushes.LightBlue,
                StrokeThickness = 2
            };
            Canvas.SetLeft(rect, startPoint.X);
            Canvas.SetTop(rect, startPoint.X);
            rect_index = canvasDrawStand.Children.Add(rect);
        }

        private void canvasDrawStand_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.LeftButton == MouseButtonState.Released || rect == null)
                return;

            var pos = e.GetPosition(canvasDrawStand);

            var x = Math.Min(pos.X, startPoint.X);
            var y = Math.Min(pos.Y, startPoint.Y);

            var w = Math.Max(pos.X, startPoint.X) - x;
            var h = Math.Max(pos.Y, startPoint.Y) - y;

            rect.Width = w;
            rect.Height = h;
            this.rechteck = new Rechteck(new Punkt((float)x,(float) y), new Punkt((float)(x + w),(float) (y + h)));
            Console.WriteLine("new Rechteck(P1( " + x + " " + y + ") , P2( " + (x + w) + " " + (y + h) + " )");
            Canvas.SetLeft(rect, x);
            Canvas.SetTop(rect, y);
        }

        private void canvasDrawStand_MouseUp(object sender, MouseButtonEventArgs e)
        {
            rect = null;
            canvasDrawStand.IsEnabled = false;
            btnResetCanvas.IsEnabled = true;
        }

        private void btnAddStand_Click(object sender, RoutedEventArgs e)
        {

            Stand toAdd = new Stand(1, txtName.Text, txtInfo.Text, rechteck);
            bw_addStand.DoWork += new DoWorkEventHandler(bw_DoWorkAddSStand);
            bw_addStand.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw_RunWorkerCompletedStand);
            bw_addStand.RunWorkerAsync(toAdd);
        }

        private void bw_DoWorkAddSStand(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            HttpWebRequest req = WebRequest.Create(new Uri(MainWindow.URL + "/api/abteilungen/" + abteilung.ab_id + "/staende")) as HttpWebRequest;
            req.Method = "POST";

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
                e.Result = reader.ReadToEnd();
            }
        }

        private void btnResetCanvas_Click(object sender, RoutedEventArgs e)
        {
            canvasDrawStand.IsEnabled = true;
            canvasDrawStand.Children.RemoveAt(rect_index);
            btnResetCanvas.IsEnabled = false;
        }

        private void bw_RunWorkerCompletedStand(object sender, RunWorkerCompletedEventArgs e)
        {
            this.Close();
            myParent.Show();
        }
    }
}
