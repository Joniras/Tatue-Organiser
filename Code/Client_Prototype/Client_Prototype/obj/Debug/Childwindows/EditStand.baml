   M S B A M L   `   `   `  ����E  ABSD_Client, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:BSD_Client
BSD_Client  5   	       BSD_Client.EditStand   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:BSD_Client     5   	   -   5          � Title$  Stand bearbeiten��5   	   $��333.995��6"   $	��608��63   .��5	      �  � ColumnDefinitions 5
   
   �� 5      $	��51* �6   �� 5      $	��49* �6   .+�5   
   �� -   5         ��Name$ lblName��5      $
��Name��6!   $
��Left=�60   $��	10,10,0,0q�6K   $	��Top=�6^   ���	#FF515151$	��284��6�   �� 5   
   -   5        $ lblInfo��5      $
��Info��6!   $
��Left=�60   $��	10,69,0,0q�6K   $	��Top=�6^   ���	#FF4D4D4D$	��286��6�   $��24��6�   �� 5   
   -   5         ��Name$ txtName��5      $��23��6#   $��	10,41,0,0q�6/    �� TextWrapping$
 Wrap=�6B   $	��Top=�6V   $
��Left=�6n   $	��286��6�   �� 5   
   -   5        $ txtInfo��5      $��
10,98,0,41q�6#   $
 Wrap=�67   $
��Left=�6K   $	��286��6f   �� 5   
   -   5         ��Name$ btnSave��5      $��	Speichern��6"   
��� � �$��10,0,149,41q�6I   $��28��6t    J� RenderTransformOrigin$ 
4.413,1.65&�6�   $��1��6�   $��Bottom=�6�   �� 5   
   -   5        $ 
lblMessage��5      $	��...��6$   $��
10,0,10,10q�62   ���#fff176$��2��6[   $��26��6o   $��Bottom=�6{   �� 5   
   -   5         ��Name$ cmbSchueler��5      $��
10,11,10,0q�6(   $	��Top=�6<   $��1��6T   �� 5   
   -	   5         ��Name$ listViewSchueler��5      $��10,122,10,78q�6-   $��1��6C   	 �� View	 5      �� 5      .P�5      �� 5      5      �� 5   
   -
   5        $ btnAddSchueler��5      $��Schüler Hinzufügen��6)   $��1��6F   $
��Left=�6V   $��	10,48,0,0q�6q   $	��Top=�6�   $	��173��6�   6�   �� 5   
   -   5        $ btnDeleteSchueler��5      $��Schüler entfernen��6,   $��1��6H   $
��Left=�6X   $��	10,85,0,0q�6s   $	��Top=�6�   $	��173��6�   $ 0.187,-0.094&�6�   6�   5      5       