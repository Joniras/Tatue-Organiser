﻿using System;
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
using Client_Prototype.Properties;
using System.ComponentModel;
using System.Net;
using System.IO;
using System.Web.Script.Serialization;

namespace Client_Prototype
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

        public EditAbteilung(Abteilung _ab, Window _parent)
        {
            InitializeComponent();
            abteilung = _ab;
            myParent = _parent;
            lblTitle.Content = abteilung.ab_name + " bearbeiten";
            //drawTestLine();
            //drawAbteilung();
            addTestData();

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
            throw new NotImplementedException();
            //Draw Abteilung from current Abteilung
            //select all Stands from Abteilung
            //Draw to canvasPlan
        }

        private void addTestData()
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

            HttpWebRequest req = WebRequest.Create(new Uri("http://192.168.196.0:8080/TatueOrganiser/api/abteilungen/" + abteilung.ab_id + "/staende")) as HttpWebRequest;
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

            Console.WriteLine(content.ToString());

            foreach (Stand s in staende)
            {
                Console.WriteLine(s.ToString());
                listViewStaende.Items.Add(s);
            }
            
        }

        private void btnEdit_Click(object sender, RoutedEventArgs e)
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
            AddQuiz aq = new AddQuiz(this, abteilung);
            aq.Show();
            this.Hide();
        }
    }
}
