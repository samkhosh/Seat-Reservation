import java.sql.*;
import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class LoginPage {

	private JFrame frame;
	private JTextField emailId;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	
	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
		connection=DatabaseConnection.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		emailId = new JTextField();
		emailId.setBounds(205, 171, 146, 26);
		frame.getContentPane().add(emailId);
		emailId.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(93, 236, 69, 20);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblEmailid = new JLabel("UserID");
		lblEmailid.setBounds(93, 174, 69, 20);
		frame.getContentPane().add(lblEmailid);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query="Select * from User where UserID=? and Password=?";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, emailId.getText());
					ps.setString(2, passwordField.getText());
				
					ResultSet rs=ps.executeQuery();
					int count=0;
					while(rs.next())
					{
						count=count+1;
					}
										
				if(count==1 )
				{					
					frame.setVisible(false);
					PerformanceSelection pers=new PerformanceSelection();
					pers.setVisible(true);
					pers.btnDisplaySeats.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							DisplaySeats ds=new DisplaySeats(emailId.getText());
							pers.dispose();
							ds.btnSelect.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									Summary summary=new Summary(ds.ss[0]-ds.bookedLength,pers.performance,pers.timings,emailId.getText());
									ds.setVisible(false);
									summary.setVisible(true);
									
									
								}
							});
							
							
							}
						});
										
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Username and password is incorrect!!");
				}
				
				rs.close();
				ps.close();
					}
				
			
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,e);
			}
				
			
			}
		});
		btnLogin.setBounds(277, 298, 115, 29);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register=new Register();
				register.setVisible(true);
				register.btnGoBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						register.dispose();
						}
					});
			}
		});
		btnRegister.setBounds(106, 298, 115, 29);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblWelcomeToSeat = new JLabel("Welcome to Seat Reservation System");
		lblWelcomeToSeat.setBounds(172, 50, 276, 29);
		frame.getContentPane().add(lblWelcomeToSeat);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 233, 146, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			}
		});
		btnQuit.setBounds(456, 424, 115, 29);
		frame.getContentPane().add(btnQuit);
		
		JButton btnPingDatabase = new JButton("Ping Database");
		btnPingDatabase.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnPingDatabase.setBackground(Color.RED);
		btnPingDatabase.setBounds(406, 102, 195, 36);
		btnPingDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				ClientPage clientPage = new ClientPage();
				//clientPage.setVisible(true);
				try {
					clientPage.databaseConnect();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
		});
		frame.getContentPane().add(btnPingDatabase);
	}
}
