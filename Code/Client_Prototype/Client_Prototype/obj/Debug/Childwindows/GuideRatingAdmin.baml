   M S B A M L   `   `   `  ����E  ABSD_Client, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:BSD_Client
BSD_Client  5   	   !    BSD_Client.GuideRatingAdmin   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:BSD_Client     5   	   -   5          � Title$  Guide Ratings��5   	   $��668.207��6    $��968.379��61   .��5	      �  � ColumnDefinitions 5
   
   �� 5      $
��763* �6   �� 5      $
��198* �6   .+�5   
   &   System.Windows.Controls.DataGrid  -   5          Name$ gridRatings��5      $��10,10,10,251q�6(   $��2��6>   
�������6R   �� 5   
   -   5         ��Name$ lblFreundlichkeit��5      $��Freundlichkeit��6+   $��10,0,10,209q�6D   $��Bottom=�6Y   $��2��6t   �� 5   
   -   5        $ lblKompetenz��5      $��	Kompetenz��6&   $��10,0,10,178q�6:   $��Bottom=�6O   $��2��6j   �� 5   
   -   5         ��Name$ btnResetRatings��5      $��Zurücksetzen��6*   $��10,0,10,115q�6A   $��Bottom=�6V   
���  ��$��2��6�   �� 5   
   -   5        $ 
lblMessage��5      $	��...��6$   $��
10,0,10,11q�62   ���#fff176$��28��6[   $��Bottom=�6g   $��2��6�   �� 5   
   -   5        $ lblavgFreundlichkeit��5      $��0��6.   $��158,0,24,207q�6:   $��Bottom=�6P   $��1q�6k   ���	#FFD6D6D6$��2��6�   �� 5   
   -   5        $ lblavgKompetenz��5      $��0��6)   $��158,0,23,177q�65   $��Bottom=�6K   ���	#FFC9C9C9$��1q�6~   $��2��6�   5      5      