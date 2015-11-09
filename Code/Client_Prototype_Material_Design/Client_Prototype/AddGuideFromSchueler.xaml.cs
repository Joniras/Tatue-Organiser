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

namespace Client_Prototype
{
    /// <summary>
    /// Interaction logic for AddGuideFromSchueler.xaml
    /// </summary>
    public partial class AddGuideFromSchueler : Window
    {
        Window myParent;

        public AddGuideFromSchueler()
        {
            InitializeComponent();
            fillListGuides();
            fillComboSchueler();
        }

        public AddGuideFromSchueler(Window _parent)
        {
            InitializeComponent();
            myParent = _parent;
            fillListGuides();
            fillComboSchueler();
            gridGuides.IsReadOnly = true;
        }

        private void fillComboSchueler()
        {
            //TODO
            //Get Schueler where isGuide = false
            List<Schueler> content = new List<Schueler>();
            content.Add(new Schueler(1, "Hansi", "Jaeger", "5BHIFS", false));
            content.Add(new Schueler(1, "Markus", "Weber", "5BHIFS", false));
            content.Add(new Schueler(1, "Michael", "Delfser", "5BHIFS", false));
            cmbSchueler.SelectedItem = content.First();
            cmbSchueler.ItemsSource = content;
        }

        private void fillListGuides()
        {
            //TODO
            //Get Schueler where isGuide = true
            List<Schueler> content = new List<Schueler>();
            content.Add(new Schueler(1, "Jonas", "Schaltegger", "5BHIFS", true));
            content.Add(new Schueler(2, "Simon", "Schwantler", "5BHIFS", true));
            content.Add(new Schueler(3, "Henrik", "Csoere", "5BHIFS", true));
            content.Add(new Schueler(4, "Richard", "Neumann", "5AHIFS", true));
            content.Add(new Schueler(5, "Sandro", "Linder", "4AHIFS", true));
            gridGuides.ItemsSource = content;
        }

        private void btnAddGuide_Click(object sender, RoutedEventArgs e)
        {
            Schueler toAdd = ((Schueler)cmbSchueler.SelectedItem);
            toAdd.S_isGuide = true;
            //TODO
            //post new Schueler to Database
            //call fillComboSchueler
            //call fillListGuides
            lblMessage.Content = "Guide Added";
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }
    }
}
