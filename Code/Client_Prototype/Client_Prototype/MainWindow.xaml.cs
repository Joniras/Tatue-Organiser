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
            
        }

        private void addTestDataToGuide()
        {
            List<Guide> content = new List<Guide>();
            content.Add(new Guide(1, 1, "Jonas", "Schaltegger", "5BHIFS"));
            content.Add(new Guide(2, 2, "Simon", "Schwantler", "5BHIFS"));
            content.Add(new Guide(3, 3, "Henrik", "Csoere", "5BHIFS"));
            content.Add(new Guide(4, 4, "Richard", "Neumann", "5AHIFS"));
            content.Add(new Guide(5, 5, "Sandro", "Linder", "4AHIFS"));
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
                EditAbteilung ea = new EditAbteilung(ab);
                lblMessage.Content = "Edit Abteilung";
                ea.Show();
                this.Hide();
            }
            else if ((Guide)gridGuide.SelectedItem != null)
            {
                Guide gi = (Guide)gridGuide.SelectedItem;
                lblMessage.Content = "Edit Guide";
                /*
                EditGuide eg = new EditGuide(gi);
                eg.Show();
                this.Hide();
                */
            }
            else
            {
                lblMessage.Content = "Bitte auswahl treffen";
            }

        }
    }
}
