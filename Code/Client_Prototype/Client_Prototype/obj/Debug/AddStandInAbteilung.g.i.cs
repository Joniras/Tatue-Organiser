﻿#pragma checksum "..\..\AddStandInAbteilung.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "5754EE31E7AF3EC87C580D09F308BB95"
//------------------------------------------------------------------------------
// <auto-generated>
//     Dieser Code wurde von einem Tool generiert.
//     Laufzeitversion:4.0.30319.42000
//
//     Änderungen an dieser Datei können falsches Verhalten verursachen und gehen verloren, wenn
//     der Code erneut generiert wird.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace Client_Prototype {
    
    
    /// <summary>
    /// AddStandInAbteilung
    /// </summary>
    public partial class AddStandInAbteilung : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 6 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Label Info;
        
        #line default
        #line hidden
        
        
        #line 7 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Label txtName1;
        
        #line default
        #line hidden
        
        
        #line 8 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Label lblTitle;
        
        #line default
        #line hidden
        
        
        #line 9 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txtName;
        
        #line default
        #line hidden
        
        
        #line 10 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txtInfo;
        
        #line default
        #line hidden
        
        
        #line 11 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Canvas canvasDrawStand;
        
        #line default
        #line hidden
        
        
        #line 12 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnAddStand;
        
        #line default
        #line hidden
        
        
        #line 13 "..\..\AddStandInAbteilung.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btnResetCanvas;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/Client_Prototype;component/addstandinabteilung.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\AddStandInAbteilung.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            
            #line 4 "..\..\AddStandInAbteilung.xaml"
            ((Client_Prototype.AddStandInAbteilung)(target)).Closed += new System.EventHandler(this.Window_Closed);
            
            #line default
            #line hidden
            return;
            case 2:
            this.Info = ((System.Windows.Controls.Label)(target));
            return;
            case 3:
            this.txtName1 = ((System.Windows.Controls.Label)(target));
            return;
            case 4:
            this.lblTitle = ((System.Windows.Controls.Label)(target));
            return;
            case 5:
            this.txtName = ((System.Windows.Controls.TextBox)(target));
            return;
            case 6:
            this.txtInfo = ((System.Windows.Controls.TextBox)(target));
            return;
            case 7:
            this.canvasDrawStand = ((System.Windows.Controls.Canvas)(target));
            
            #line 11 "..\..\AddStandInAbteilung.xaml"
            this.canvasDrawStand.MouseDown += new System.Windows.Input.MouseButtonEventHandler(this.canvasDrawStand_MouseDown);
            
            #line default
            #line hidden
            
            #line 11 "..\..\AddStandInAbteilung.xaml"
            this.canvasDrawStand.MouseMove += new System.Windows.Input.MouseEventHandler(this.canvasDrawStand_MouseMove);
            
            #line default
            #line hidden
            
            #line 11 "..\..\AddStandInAbteilung.xaml"
            this.canvasDrawStand.MouseUp += new System.Windows.Input.MouseButtonEventHandler(this.canvasDrawStand_MouseUp);
            
            #line default
            #line hidden
            return;
            case 8:
            this.btnAddStand = ((System.Windows.Controls.Button)(target));
            
            #line 12 "..\..\AddStandInAbteilung.xaml"
            this.btnAddStand.Click += new System.Windows.RoutedEventHandler(this.btnAddStand_Click);
            
            #line default
            #line hidden
            return;
            case 9:
            this.btnResetCanvas = ((System.Windows.Controls.Button)(target));
            
            #line 13 "..\..\AddStandInAbteilung.xaml"
            this.btnResetCanvas.Click += new System.Windows.RoutedEventHandler(this.btnResetCanvas_Click);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

