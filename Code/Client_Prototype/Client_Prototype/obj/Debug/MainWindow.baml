   M S B A M L   `   `   `  ����  
BSD_Client    BSD_Client.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     6F   -   5          �Name$  Tatue_Mainwindow��5   �    � Title$ Tatue-Organiser��5   	   $��596.207��6!   $��976.379��62   ���	#FF3F51B5.��5      �  � ColumnDefinitions 5   
   �� 5      $
��647* �6   �� 5	      $
��321* �6   .+�5   
   �� $��0,0,0,69q�6   $��2��6'   .@�5      �� $��Abteilung-Editor��6   $��-2,-2,-241,0q�60   ��5      �� 5       �� EndPoint$	 0,1&�6*    �� 
StartPoint$	 0,0&�69   .��5      �  � Color$ 	#FFF0F0F0��6'    � Offset$ 0Q�69   � 5      $ White��6'   $ 1Q�65   5      .��5      �� .+�5      �� $��Alle Abteilungen:��6   
�������68   &   System.Windows.Controls.DataGrid  5      -   5          Name$ gridAbteilung��5      $��0,0,-1,0q�66      
IsReadOnly . 6H   5      5      �� 5      $��Schueler-Editor��6   $��241,-2,-485,0q�6/   .��5      �� .+�5        -   5        $ 	gridGuide��5       . 62   �� 5      -   5        	 ��Name$	 btnAddGuide��5      $��Schueler hinzufügen��62   6P   �� 5      -   5        $	 btnGettGuideStats��5      $��Statistik anzeigen��68   
���   �$��0,10,0,0q�6�   5      5       5#      �� 5$   
   -   5        
 ��Name$
 
lblMessage��5$      $!��Ladet Datensatz herunter...��6$   ���	#FFFBFFB4$��28��6a   $��Bottom=�6m   $��2��6�   �� 5%   
   -   5        $	 btnEdit��5%      
����  �$��
Bearbeiten��64   $��0,0,473.567,33q�6I   $��Right=�6w   $	��163��6�   $��30��6�   $��Bottom=�6�   �� 5&   
   -   5        $	 btnTemp��5&      $��Ladebildschirm testen��6"   $��Right=�6B   $��0,0,110.567,33q�6^   $	��190��6v   $��30��6�   $��Bottom=�6�   �� 5'   
   -	   5        $	 	btnDelete��5'      
���  ��$��Löschen��65   $��Right=�6G   $��0,0,305.567,33q�6c   $	��163��6{   $��30��6�   $��Bottom=�6�   � 5(   
   -
   5         �Name$ progressLoading��5(      $
��Left=�6/   $��32��6J   $��
10,464,0,0q�6V   $	��Top=�6j   $	��948��6�   $��2��6�   5+      5,      