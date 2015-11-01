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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Client_Prototype
{
    /// <summary>
    /// Interaktionslogik für MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            addTestDataToAbteilung();
            addTestDataToGuide();
            gridGuide.IsReadOnly = true;
            gridAbteilung.IsReadOnly = true;

        }

        private void addTestDataToGuide()
        {
            List<Schueler> content = new List<Schueler>();
            content.Add(new Schueler(1, "Jonas", "Schaltegger", "5BHIFS", true));
            content.Add(new Schueler(2, "Simon", "Schwantler", "5BHIFS", true));
            content.Add(new Schueler(3, "Henrik", "Csoere", "5BHIFS", true));
            content.Add(new Schueler(4, "Richard", "Neumann", "5AHIFS", true));
            content.Add(new Schueler(5, "Sandro", "Linder", "4AHIFS", true));
            gridGuide.ItemsSource = content;
        }

        private void addTestDataToAbteilung()
        {
            List<Abteilung> content = new List<Abteilung>();
            content.Add(new Abteilung(1, "EDVO", 1));
            content.Add(new Abteilung(2, "Bautechnik", 2));
            gridAbteilung.ItemsSource = content;
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
            Schueler gi = (Schueler)gridGuide.SelectedItem;
            lblMessage.Content = "Edit Guide";
            AddGuideFromSchueler eg = new AddGuideFromSchueler(gi, this);
            eg.Show();
            this.Hide();
    
            
        }
    }
}
