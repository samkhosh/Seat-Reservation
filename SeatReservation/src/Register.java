import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JTable;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JPasswordField passwordFieldReg;
	private JTable table;
	//Register frame = new Register();
	//frame.setVisible(true);
	JButton btnGoBack = new JButton("Go Back");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					//Register frame = new Register();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	Connection connection=null;
	private JTextField userID;

	
	
	/**
	 * Create the frame.
	 */
	public Register() {
		//frame.setVisible(true);
		connection=DatabaseConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setBounds(89, 178, 135, 20);
		contentPane.add(lblFirstname);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(89, 225, 83, 20);
		contentPane.add(lblLastName);
		
		JLabel lblEmailid = new JLabel("EmailID");
		lblEmailid.setBounds(89, 274, 69, 20);
		contentPane.add(lblEmailid);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(89, 324, 69, 20);
		contentPane.add(lblPassword);
		
		firstName = new JTextField();
		firstName.setBounds(238, 175, 146, 26);
		contentPane.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(238, 222, 146, 26);
		contentPane.add(lastName);
		lastName.setColumns(10);
		
		email = new JTextField();
		email.setBounds(238, 271, 146, 26);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int fnamel=firstName.getText().length();
				int lnamel=lastName.getText().length();
				int emaill=email.getText().length();
				int pwdl=passwordFieldReg.getText().length();
				int userIDl=userID.getText().length();
				try{
					
					if(fnamel==0 || lnamel==0 || emaill==0 || pwdl==0 || userIDl==0)
					{
						JOptionPane.showMessageDialog(null,"No field can be left empty.Please try again.");
					}
					else if(pwdl>=6 && pwdl<=12 && userIDl>=6 && userIDl<=12)
					{				
					String query="Insert into User values(?,?,?,?,?,null)";				
					PreparedStatement stmt=connection.prepareStatement(query);
					stmt.setString(1, userID.getText());
					stmt.setString(2, firstName.getText());
					stmt.setString(3, lastName.getText());
					stmt.setString(4, email.getText());
					stmt.setString(5,passwordFieldReg.getText());
					int a=stmt.executeUpdate();
					
									if(a==1)
									{
										JOptionPane.showMessageDialog(null, "New User Registered Successfully!");
									}
									else
									{
										JOptionPane.showMessageDialog(null, "New user cannot be registered!");
									}
					
					 }					
					else
					{
						JOptionPane.showMessageDialog(null, "UserId and password should be between 6 to 12 characters in length!");
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}
				
			
			}
		});
		btnRegister.setBounds(269, 440, 115, 29);
		contentPane.add(btnRegister);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				System.exit(0);
			}
		});
		btnQuit.setBounds(556, 506, 115, 29);
		contentPane.add(btnQuit);
		
		JLabel lblNewUserRegisteration = new JLabel("New User Registeration");
		lblNewUserRegisteration.setBounds(269, 36, 271, 57);
		contentPane.add(lblNewUserRegisteration);
		
		passwordFieldReg = new JPasswordField();
		passwordFieldReg.setBounds(238, 324, 146, 26);
		contentPane.add(passwordFieldReg);
		
		table = new JTable();
		table.setBounds(33, 403, 1, 1);
		contentPane.add(table);
		
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//register.this.dispose();
			}
		});
		btnGoBack.setBounds(426, 506, 115, 29);
		contentPane.add(btnGoBack);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setBounds(89, 377, 69, 20);
		contentPane.add(lblUserid);
		
		userID = new JTextField();
		userID.setBounds(238, 374, 146, 26);
		contentPane.add(userID);
		userID.setColumns(10);
	}
}
