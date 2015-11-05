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
    /// Interaction logic for EditStand.xaml
    /// </summary>
    public partial class EditStand : Window
    {
        Stand stand;
        Window myParent;

        public EditStand(Stand _stand, Window _parent)
        {
            InitializeComponent();
            stand = _stand;
            myParent = _parent;
            txtName.Text = stand.ST_Name;
            txtInfo.Text = stand.ST_Info;
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            Stand toAdd = new Stand(1, txtName.Text, txtInfo.Text, null);
            //TODO
            //Post Stand
            lblMessage.Content = "Stand changed";
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Close();
            myParent.Show();
        }

        private void btnRatings_Click(object sender, RoutedEventArgs e)
        {
            //new Gui for Ratings
            StandRatingAdmin gra = new StandRatingAdmin(stand, this);
            gra.Show();
            this.Hide();
        }
    }
}
