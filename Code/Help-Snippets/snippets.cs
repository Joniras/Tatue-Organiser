//Snippet to Paint an Object in a Canvas
        //type 1 point; 2 line or curve; 3 polygon; 4 collection; 5 Multipoint; 6 Multiline; 7 Multipolygon
        private void Paint_Object(String cobject,string list,SolidColorBrush color)
        {
            List<Point> points = this.database.getPoints(cobject,list);
            Shape a = new Polygon();
            PointCollection b = new PointCollection(points);
            spatialType type;
            if (list != "punkt")
            {
                type = this.database.getType(cobject, list);
            }
            else
            {
                type = spatialType.POINT;
            }
            switch (type)
            {
                case spatialType.CIRCLE:
                    a = new Ellipse();
                    Point p1 = b[0];
                    Point p2 = b[1];
                    Point p3 = b[2];
                //12.5 Lösung
                    a.Fill = Brushes.LightBlue;

                    Point center = getCenterOfCircle(p1, p2, p3);
                    double radius = Math.Abs(Math.Sqrt(Math.Pow(p2.X - center.X, 2) + Math.Pow(p2.Y - center.Y, 2))) * 2;
                    a.Height = radius;
                    a.Width = radius;
                    double left = center.X - (a.Width / 2);
                    double top = center.Y - (a.Height / 2) -1;
                    a.Margin = new Thickness(left, top, 0, 0);
                    break;
                case spatialType.LINE:
                    a = new Line();
                    ((Line)a).X1 = b[0].X;
                    ((Line)a).Y1 = b[0].Y;
                    ((Line)a).X2 = b[1].X;
                    ((Line)a).Y2 = b[1].Y;
                    break;
                case spatialType.POLYGON: 
                    ((Polygon)a).Points = b;
                    a.Fill = Brushes.LightBlue;
                    break;
                case spatialType.RECTANGLE:
                    b.Insert(1,new Point(b[1].X,b[0].Y));
                    b.Add(new Point(b[0].X, b[2].Y));

                    a.Fill = Brushes.LightBlue;
                    ((Polygon)a).Points = b;
                    break;
                case spatialType.POINT:
                    a = new Ellipse();
                    a.Margin = new Thickness(b[0].X-1,b[0].Y-1,0,0);
                    a.Height = 2;
                    a.Width = 2;
                    a.Fill = Brushes.LightBlue;
                    break;
            }

            a.ToolTip = cobject;

            a.Stroke = Brushes.Black;
            a.StrokeThickness = 0.5;

            a.VerticalAlignment = System.Windows.VerticalAlignment.Center;
            a.HorizontalAlignment = System.Windows.HorizontalAlignment.Center;
            
            canvas.Children.Add(a);
        }



//eventhandler for clicked canvas
        private void btnInsertUnfall_Click(object sender, RoutedEventArgs e)
                {
                    this.msgLabel.Content = "";
                    double x = double.Parse(this.txtBoxX.Text);
                    double y = double.Parse(this.txtBoxY.Text);

                    this.database.insertUnfall(x, y);
                }



