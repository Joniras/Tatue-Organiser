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
    /// Interaction logic for AlertAskWindow.xaml
    /// </summary>
    public partial class AlertAskWindow : Window
    {
        Window myParent;
        public AlertAskWindow(Window _parent)
        {
            myParent = _parent;
            InitializeComponent();
        }

        private void btnJa_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = true;
        }

        private void btnNein_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = false;
        }

        private void Window_Closed(object sender, EventArgs e)
        {
            this.Hide();
            myParent.Show();
        }
    }
}
