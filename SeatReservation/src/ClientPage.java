import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientPage extends JFrame {

	private JPanel contentPane;
	public boolean flag;

	/**
	 * Launch the application.
	 */
	
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientPage frame = new ClientPage();
					frame.setVisible(true);
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
/*	public ClientPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
	
	}*/
	
	Connection conP = DatabaseConnection.dbConnector();	
	
	public void databaseConnect() throws Exception {


		  
		  if(conP.isClosed()) //here's how to check
		     {
		    	// 
		    		
			  JOptionPane.showMessageDialog(null, "Not connected!");	 
			  
		     }
		     else{
		    	 
		 		//System.out.print("This database name already exists");
		 		JOptionPane.showMessageDialog(null, "Connection to Database Exists...Getting table Names!");
		  		getTables();
		    	 
		      }
		}
	

	public void getTables(){
		try
		{
			String query= "SELECT name FROM sqlite_master WHERE type='table'";
			PreparedStatement ps= conP.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			ArrayList<String> tableList = new ArrayList<>(); 
			while(rs.next())
			{
				//System.out.println(rs.getString("Show"));
				tableList.add(rs.getString("name"));
				
			}
			JOptionPane.showMessageDialog(null, new JScrollPane(new JList(tableList.toArray())));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

