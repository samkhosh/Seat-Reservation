import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Reports extends JFrame {

	private JPanel contentPane;
	String userID;
	JComboBox comboBox;
		
	
	public Reports(String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectUser = new JLabel("Select User");
		lblSelectUser.setBounds(38, 58, 101, 20);
		contentPane.add(lblSelectUser);
		
		
		Connection conn=DatabaseConnection.dbConnector();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(109, 89, 205, 99);
		contentPane.add(lblNewLabel);
	
		
		
		JButton btnNewButton = new JButton("Display");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userID = comboBox.getSelectedItem().toString();
				System.out.println(userID);
			lblNewLabel.setText(getReports(userID));			
			}
		});
		btnNewButton.setBounds(298, 54, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnViewAll = new JButton("View All");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JTable table = new JTable(buildTableModel());
					JOptionPane.showMessageDialog(null, new JScrollPane(table));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			//	JScrollPane jsp =new JScrollPane(table);
				//JOptionPane.showMessageDialog(null, new JScrollPane(table));
				//lblNewLabel.setText(jsp);			
			}
		});
		btnViewAll.setBounds(298, 199, 115, 29);
		contentPane.add(btnViewAll);
		
		comboBox = new JComboBox();
		comboBox.setBounds(180, 55, 88, 26);
		contentPane.add(comboBox);
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnQuit.setBounds(113, 199, 115, 29);
		contentPane.add(btnQuit);
		fillTimingBox();
		
	//	System.out.println("OUTSIDE"+userID);
			}

	public void fillTimingBox(){
		try{
			String query1="SELECT UserID from User";
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next())
			{
				
				comboBox.addItem(rs1.getString("UserID"));				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	Connection conn = DatabaseConnection.dbConnector();
	public String getReports(String userID){
		
		try{
			System.out.println(userID);
		String query="SELECT Tickets_Booked FROM User where UserID='"+userID+"'";				
		PreparedStatement stmt=conn.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		String tickets=rs.getString("Tickets_Booked").replaceAll(" ","").replaceAll(",", "--");
		return tickets;
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	Connection conRS = DatabaseConnection.dbConnector();
	
	public ResultSet getTables() throws SQLException{
		//System.out.println(userID);
				String query="SELECT UserID, Tickets_Booked FROM User";
				PreparedStatement stmt=conRS.prepareStatement(query);
				ResultSet rs1=stmt.executeQuery();
				return rs1;
	}
	
	public DefaultTableModel buildTableModel() throws SQLException{
		
		ResultSet rs = getTables();
			
		ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}
		/*String tickets=rs.getString("Tickets_Booked");
		return tickets;
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}*/
	
}
