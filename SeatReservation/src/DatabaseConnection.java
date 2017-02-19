import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {

	
public static Connection dbConnector()
{
	try
	{
		Connection conn=null;
		Class.forName("org.sqlite.JDBC");
		conn=DriverManager.getConnection("jdbc:sqlite:F:\\SQLite\\TicketReservation.sqlite");		
		return conn;
	}
	catch(SQLException ex)
	{
		JOptionPane.showMessageDialog(null, ex);
		return null;
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, e);
		return null;
	}
}
}
