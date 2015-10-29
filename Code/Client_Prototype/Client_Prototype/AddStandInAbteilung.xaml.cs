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
    /// Interaktionslogik für AddStandInAbteilung.xaml
    /// </summary>
    public partial class AddStandInAbteilung : Window
    {
        private Point startPoint;
        private Rectangle rect;
        private int rect_index = -1;

        public AddStandInAbteilung()
        {
            InitializeComponent();
            btnResetCanvas.IsEnabled = false;
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
            Stand toAdd = new Stand(1, txtName.Text, txtInfo.Text, rect);
        }

        private void btnResetCanvas_Click(object sender, RoutedEventArgs e)
        {
            canvasDrawStand.IsEnabled = true;
            canvasDrawStand.Children.RemoveAt(rect_index);
            btnResetCanvas.IsEnabled = false;
        }
        
    }
}
