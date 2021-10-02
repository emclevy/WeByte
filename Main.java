package softwareProject;
import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
public class Main {
	
	
	
		
		public static void main(String[] args) {
	
			//Splash Screen
			JWindow screen = new JWindow();
			screen.getContentPane().add(new JLabel(new ImageIcon("logo.jpg")), SwingConstants.CENTER);
			screen.setBounds(0, 0, 1366, 869);
			screen.setVisible(true);
			try {
			    Thread.sleep(3000);
			} catch (InterruptedException e) {
			    
			}
			screen.setVisible(false);
			//JTextArea enterNames = new JTextArea(4, 20);
			
			//enterNames.setBackground(Color.GREEN);
			String[] colNames = {"ID", "NAME"};
			String[][] rowData = {{"1111","testName"},{"123","testName2"}};
			//JTable enterGreen = new JTable(rowData, colNames);
			//JTable enterRed = new JTable(20, 2);
			
			DefaultTableModel enter1Green = new DefaultTableModel(rowData, colNames);
			DefaultTableModel enter1Red = new DefaultTableModel(rowData, colNames);
			
			JTable enterGreen = new JTable(enter1Green);
			JTable enterRed = new JTable(enter1Red);
			
			//enterGreen.setBackground(Color.GREEN);
			enterRed.setBackground(Color.RED);
			//enterGreen.setTableHeader(new JTableHeader());
			enterGreen.setFillsViewportHeight(true);
			enterGreen.setAlignmentX(0);
			enterGreen.setAlignmentY(0);
			JPanel nameScreen = new JPanel();
			nameScreen.add(enterGreen);
			nameScreen.add(enterRed);
			JFrame frame = new JFrame();
			frame.add(nameScreen);
			frame.setSize(1366,869);
			frame.setVisible(true);
			/*enterGreen.getModel().addTableModelListener(new TableModelListener()
					{
						public void tableChanged(TableModelEvent e)
						{
							
						}
					});*/
	}

			
}
