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
using Client_Prototype.Properties;

namespace Client_Prototype
{
    /// <summary>
    /// Interaktionslogik für EditAbteilung.xaml
    /// </summary>
    public partial class EditAbteilung : Window
    {
        Abteilung abteilung;
        Window myParent;

        public EditAbteilung(Abteilung _ab, Window _parent)
        {
            InitializeComponent();
            abteilung = _ab;
            myParent = _parent;
            lblTitle.Content = abteilung.AB_Name + " bearbeiten";
            //drawTestLine();
            //drawAbteilung();
            addTestData();

        }

        private void btnAddStand_Click(object sender, RoutedEventArgs e)
        {

            AddStandInAbteilung asin = new AddStandInAbteilung(this);
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
            //Draw to canvasStandplan
        }

        private void addTestData()
        {
            listViewStaende.Items.Add(new Stand(1, "SAP", "Funny SAP Things", null));
            listViewStaende.Items.Add(new Stand(2, "ABAP", "Funny ABAP Things", null));
            listViewStaende.Items.Add(new Stand(3, "POS", "Funny POS Things", null));
            Stand ratingS = new Stand(3, "Test", "Funny Test Things", null);
            ratingS.addRatingToStand(new StandRating(1, 1, 1, 1));
            listViewStaende.Items.Add(ratingS);
        }

        private void Window_Closed_1(object sender, EventArgs e)
        {
            this.Hide();
            myParent.Show();
        }

        private void btnEdit_Click(object sender, RoutedEventArgs e)
        {
            if(listViewStaende.SelectedItem != null)
            {
                StandRatingAdmin gra = new StandRatingAdmin(((Stand)listViewStaende.SelectedItem), this);
                gra.Show();
                this.Hide();
            }
            else
            {
                lblMessage.Content = "Stand auswählen";
            }

        }
    }
}
