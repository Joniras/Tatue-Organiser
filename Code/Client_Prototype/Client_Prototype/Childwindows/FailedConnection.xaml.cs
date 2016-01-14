using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
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

namespace BSD_Client
{
    /// <summary>
    /// Interaction logic for FailedConnection.xaml
    /// </summary>
    public partial class FailedConnection : Window
    {
        int count = 0;
        Window myParent;
        public FailedConnection(Window _parent)
        {
            InitializeComponent();
            myParent = _parent;
        }

        private void btn_Reconnect_Click(object sender, RoutedEventArgs e)
        {
            if(checkConnection())
            {
                myParent.Show();
                this.Close();
            }
            else
            {
                count++;
                lbl_Text.Content = count;
            }

        }

        private bool checkConnection()
        {
            var url = MainWindow.URL + "/ping";
            bool retVal = false;
            try
            {
                var myRequest = (HttpWebRequest)WebRequest.Create(url);

                var response = (HttpWebResponse)myRequest.GetResponse();

                if (response.StatusCode == HttpStatusCode.OK)
                {
                    retVal = true;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return retVal;
            }
            return retVal;
        }
    }
}
