import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;

public class DisplaySeats extends JFrame {

    private int rows = 5;
    private int columns = 20;
    int row, column;
    String b1;
    private Icon res = (UIManager.getIcon("OptionPane.questionIcon"));
    private Icon cross = (UIManager.getIcon("OptionPane.errorIcon"));
    JPanel big=new JPanel(new BorderLayout());
    JPanel subPanel=new JPanel();
    JPanel panel;
    final int[] ss={0};
    final int n=0;
    JButton btnSelect = new JButton("CONFIRM");
    JButton btnReset = new JButton("RESET");
    ArrayList<String> seatsSelected = new ArrayList<String>();
    Connection connection=null;
    String list;
    String b2;
    int bookedLength;
    
    public DisplaySeats(String user) {
       
    	setBounds(200, 100, 812, 526);
    	connection=DatabaseConnection.dbConnector();
    	Seats(user);
    	    		  		  		      
            
        JButton btnQuit = new JButton("QUIT");
                
        btnQuit.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0) {
        	System.exit(0);
        	}
        	
        });       
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				list=String.join(",", seatsSelected);
		        System.out.println(list);
		        list=b2+","+list;
		        System.out.println(list);
		        try
		        {
		        	String query="UPDATE User SET Tickets_Booked=(?) WHERE UserID='"+user+"'";;				
					PreparedStatement stmt=connection.prepareStatement(query);
					stmt.setString(1, list);		
					stmt.executeUpdate();
							        }
		    	catch(Exception ep)
		        {
		    		ep.printStackTrace();
		        }
			}
		});
				
		btnSelect.setBounds(714, 587, 100, 50);
		big.add(panel,BorderLayout.NORTH);		
		subPanel.add(btnSelect);
		subPanel.add(btnQuit);
		
		big.add(subPanel, BorderLayout.SOUTH);
		
		
        final JFrame frame = new JFrame("Display Seats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(big);
        frame.pack();
        //frame.setLocation(250, 250);
        frame.setVisible(true);
        frame.setSize( 2000, 400 );
    }

    
    public void Seats(String user)
    {
    	panel = new JPanel(new GridLayout(rows,columns));
		char a='A';
		int b=a;	
		char c=(char) b;
		//String[] TicketCodes = new String[4];
		ButtonGroup group1=new ButtonGroup();
        ButtonGroup group2=new ButtonGroup();
        
        try
        {
        	String query1="SELECT Tickets_Booked FROM User";		
        	String query2="SELECT Tickets_Booked FROM User where UserID='"+user+"'";
			PreparedStatement stmt1=connection.prepareStatement(query1);
			PreparedStatement stmt2=connection.prepareStatement(query2);
			ResultSet rs=stmt1.executeQuery();
			ResultSet rs2=stmt2.executeQuery();
			 b1=",";
			while(rs.next())
			{
				b1=b1+rs.getString("Tickets_Booked")+",";
			}							
			b1=b1.substring(1, b1.length()-1);
			System.out.println(b1);			
			b2=rs2.getString("Tickets_Booked");			
			String userBooked[] = b2.split(",");
			bookedLength=userBooked.length;
			System.out.println(userBooked.length);
			
}
    	catch(Exception ep)
        {
    		ep.printStackTrace();
        }
        
        	if(bookedLength<4)
        	{
        		System.out.println("Inside");
        		ss[0]=bookedLength;
        		System.out.println(ss[0]);
        	   for (row = 1; row <= rows; row=row+1){
    			for (column = 1; column <= columns; column=column+1)
    			{    				
                JToggleButton button = new JToggleButton(c+""+column);
                button.setPreferredSize(new Dimension(100, 50));
                group1.add(button); 
                
                String buttonName[] = b1.split(",");
                
                for( int i = 0; i <= buttonName.length-1; i++)
                {
                	buttonName[i] = buttonName[i].replaceAll("\\s+","");
                	if(buttonName[i].equals(button.getText()))
                   {                		   
                	   button.setEnabled(false); 
                	   button.setBackground(Color.RED);
                	   button.setIcon(cross);
                   }
                }                    	
                
                button.addActionListener(new ActionListener(){  
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                    boolean selected = abstractButton.getModel().isSelected();                                                        
                   if (selected) 
                    {              	
                    	
                    	seatsSelected.add(abstractButton.getText());
                    	abstractButton.setIcon(res);                        	
                        group1.remove(abstractButton);                    	
                        ss[0]=ss[0]+1;       
                    	System.out.println(ss[0]);                      	
                    	
                    	if(ss[0]>4)
                        	{
                        		JOptionPane.showMessageDialog(null, "Sorry!Only four sets allowed per account");
                        		abstractButton.setIcon(null);
                        		abstractButton.setSelected(false);
                        		ss[0]=ss[0]-1;
                        		seatsSelected.remove(abstractButton.getText());
                        		System.out.println(ss[0]);
                        	}
                      }                       
                    else
                    {
                    	ss[0]=ss[0]-1;
                    	seatsSelected.remove(abstractButton.getText());
                    	System.out.println("inside else"+ss[0]);                    	
                    	abstractButton.setIcon(null);
                    }
          
                }
             });           
           panel.add(button);
            }            
    	b=b+1;
    	c=(char) b;
    }}
        	else
        	{
        		JOptionPane.showMessageDialog(null, "You have already reached your 4 seats quota.Cannot book more.Please exit");
        		panel.setVisible(false);
        		ss[0]=4;
        	//	frame.setVisible(false);
        		System.exit(0);
        	}
        	   
    }

	}