import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PerformanceSelection extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxName;
	private JComboBox<String> comboBox_1;
	JButton btnDisplaySeats = new JButton("Display Seats");
	String performance;
	String timings;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerformanceSelection frame = new PerformanceSelection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	Connection con=DatabaseConnection.dbConnector();	
	public void fillComboBox()
	{
		try
		{
			String query="select DISTINCT Show from Performance;";
			PreparedStatement ps= con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				//System.out.println(rs.getString("Show"));
				comboBoxName.addItem(rs.getString("Show"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	Connection con1=DatabaseConnection.dbConnector();
	public void fillTimingBox(){
		try{
			String query1="SELECT DISTINCT Timings from Performance";
			PreparedStatement ps1 = con1.prepareStatement(query1);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next())
			{
				comboBox_1.addItem(rs1.getString("Timings"));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Create the frame.
	 */
	public PerformanceSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPerformance = new JLabel("Performance");
		lblPerformance.setBounds(93, 141, 137, 20);
		contentPane.add(lblPerformance);
		
		JLabel lblSelectPerformances = new JLabel("SELECT PERFORMANCES");
		lblSelectPerformances.setBounds(283, 49, 225, 35);
		contentPane.add(lblSelectPerformances);
		
		comboBoxName = new JComboBox<String>();
		comboBoxName.setBounds(270, 138, 225, 35);
		contentPane.add(comboBoxName);
		//refreshTable();
		fillComboBox();
		performance = comboBoxName.getSelectedItem().toString();
		//System.out.println(performance);
		
		
		JLabel lblTimings = new JLabel("Timings");
		lblTimings.setBounds(93, 224, 69, 20);
		contentPane.add(lblTimings);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(270, 221, 225, 26);
		contentPane.add(comboBox_1);
		fillTimingBox();
		timings = comboBox_1.getSelectedItem().toString();
		//System.out.println(timings);
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(614, 387, 115, 29);
		contentPane.add(btnQuit);
			
		
		btnDisplaySeats.setBounds(317, 310, 152, 29);
		contentPane.add(btnDisplaySeats);
	}
}
