package com.webyte.lasertag;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LasertagApplication {

	//private final String url = "jdbc:postgresql://localhost:5432/webytedb?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	//private final String url = "jdbc:postgresql://william.page.jackson@gmail.com:24mAXsECURITY$@ec2-52-206-193-199.compute-1.amazonaws.com:5432/d35s4n1ppn57md";
	private final String url = "jdbc:postgresql://fqiirartuvhkpw:a4f3a07b561a4e6e989b738b35a665c341d6c37631e7f0909a4d45bdc2746db9@ec2-52-206-193-199.compute-1.amazonaws.com:5432/d35s4n1ppn57md";
	private final String user = "william.page.jackson@gmail.com";
	private final String password = "24mAXsECURITY$";

	public static void main(String[] args) {
		//Splash Screen
		JWindow screen = new JWindow();
		screen.getContentPane().add(new JLabel(new ImageIcon("src/main/java/com/webyte/lasertag/logo.jpg")), SwingConstants.CENTER);
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
		String[][] rowData = {{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},
		{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};
		//JTable enterGreen = new JTable(rowData, colNames);
		//JTable enterRed = new JTable(20, 2);
		
		DefaultTableModel enter1Green = new DefaultTableModel(rowData, colNames);
		DefaultTableModel enter1Red = new DefaultTableModel(rowData, colNames);
		
		JTable enterGreen = new JTable(enter1Green);
		JTable enterRed = new JTable(enter1Red);
		
		enterGreen.setBackground(Color.GREEN);
		enterRed.setBackground(Color.RED);
		enterGreen.setTableHeader(new JTableHeader());
		enterGreen.setFillsViewportHeight(true);
		enterGreen.setAlignmentX(0);
		enterGreen.setAlignmentY(0);
		JPanel nameScreen = new JPanel();
		nameScreen.add(enterGreen);
		nameScreen.add(enterRed);
		JFrame frame = new JFrame();
		frame.add(nameScreen);
		frame.setSize(1366,869);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		enterGreen.getModel().addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e)
			{
				LasertagApplication lasertagApplication = new LasertagApplication();
				String str = lasertagApplication.findNameById(1);
				System.out.println(str);
			}
		});

		SpringApplication.run(LasertagApplication.class, args);
	}

	// private static Connection getConnection() throws URISyntaxException, SQLException {
	// 	URI dbUri = new URI(System.getenv("DATABASE_URL"));
	
	// 	String username = dbUri.getUserInfo().split(":")[0];
	// 	String password = dbUri.getUserInfo().split(":")[1];
	// 	String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
	
	// 	return DriverManager.getConnection(dbUrl, username, password);
	// }

	public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return conn;
	}

	public String findNameById(int id){
		String sql = "SELECT id, codename "
		+ "FROM player "
		+ "WHERE id = ?";

		try (Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs != null){
				return rs.toString();
			}
			else{
				//call function to insert player 
				return "";
			}
		}
		catch(SQLException ex){
			System.out.println(ex.getMessage());
		}

		return "Error";
	}
	
	public void createPlayerEntry(int id, String codename){
		String sql = "INSERT INTO PLAYER (ID, CODENAME) " 
				+ "VALUES " +"(" + id + ", " + codename + ")";

		try (Connection conn = connect();
			PreparedStatement pstmt = connect().prepareStatement(sql)){
			pstmt.executeUpdate(sql);
			pstmt.close();
		}
		catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
}
