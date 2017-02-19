import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Summary extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Summary frame = new Summary();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Summary(int n,String a,String b,String user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSummary = new JLabel("SUMMARY");
		lblSummary.setBounds(305, 46, 129, 39);
		contentPane.add(lblSummary);
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		btnQuit.setBounds(463, 445, 115, 29);
		contentPane.add(btnQuit);
		
				
		
		JLabel lblThanksForBooking = new JLabel("Thanks for Booking with Us!!");
		lblThanksForBooking.setBounds(266, 88, 267, 20);
		contentPane.add(lblThanksForBooking);
		
		JLabel lblPerformance = new JLabel("Performance:");
		lblPerformance.setBounds(58, 175, 101, 20);
		contentPane.add(lblPerformance);
		
		JLabel lblTimings = new JLabel("Timings");
		lblTimings.setBounds(58, 230, 69, 20);
		contentPane.add(lblTimings);
		
		JLabel lblNumberOfSeats = new JLabel("Number of Seats Booked");
		lblNumberOfSeats.setBounds(59, 296, 185, 20);
		contentPane.add(lblNumberOfSeats);
		
		JLabel pLabel = new JLabel("New label");
		pLabel.setBounds(305, 175, 149, 20);
		contentPane.add(pLabel);
		pLabel.setText(a);
		
		JLabel tLabel = new JLabel("New label");
		tLabel.setBounds(305, 230, 149, 20);
		contentPane.add(tLabel);
		tLabel.setText(b);
		
		JLabel seatsLabel = new JLabel("New label");
		seatsLabel.setBounds(305, 296, 69, 20);
		contentPane.add(seatsLabel);
		seatsLabel.setText(n+"Seats");
		
		JLabel lblPriceOfEach = new JLabel("Price of each Ticket");
		lblPriceOfEach.setBounds(58, 358, 137, 20);
		contentPane.add(lblPriceOfEach);
		
		JLabel priceLabel = new JLabel("$10");
		priceLabel.setBounds(305, 358, 69, 20);
		contentPane.add(priceLabel);
		
		JLabel lblTotalAmountPaid = new JLabel("Total Amount Paid");
		lblTotalAmountPaid.setBounds(58, 417, 149, 20);
		contentPane.add(lblTotalAmountPaid);
		
		JLabel amountLabel = new JLabel("New label");
		amountLabel.setBounds(305, 417, 69, 20);
		contentPane.add(amountLabel);
		amountLabel.setText("$"+n*10);
		
		JButton btnNewButton = new JButton("View Reports");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reports report = new Reports(user);
				report.setVisible(true);
			}
		});
		btnNewButton.setBounds(620, 445, 138, 29);
		contentPane.add(btnNewButton);
		
	}
}
