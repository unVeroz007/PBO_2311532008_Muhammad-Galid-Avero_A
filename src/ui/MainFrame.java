package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 652);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJudul = new JLabel("SELAMAT MEMBERSIHKAN");
		lblJudul.setForeground(new Color(0, 0, 255));
		lblJudul.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblJudul.setHorizontalAlignment(SwingConstants.CENTER);
		lblJudul.setBounds(249, 34, 456, 49);
		contentPane.add(lblJudul);
		
		JLabel lblmotto = new JLabel("apapun bisa dibersihkan (kecuali cuci uang)");
		lblmotto.setForeground(new Color(0, 0, 255));
		lblmotto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblmotto.setHorizontalAlignment(SwingConstants.CENTER);
		lblmotto.setBounds(281, 73, 390, 34);
		contentPane.add(lblmotto);
		
		JButton btnTransaction = new JButton("Transaction");
		btnTransaction.setForeground(new Color(0, 128, 255));
		btnTransaction.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnTransaction.setBounds(61, 140, 226, 112);
		contentPane.add(btnTransaction);
		
		JButton btnService = new JButton("Service");
		btnService.setForeground(new Color(0, 128, 255));
		btnService.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnService.setBounds(339, 140, 226, 112);
		contentPane.add(btnService);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setForeground(new Color(0, 128, 255));
		btnCustomer.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnCustomer.setBounds(614, 140, 226, 112);
		contentPane.add(btnCustomer);
		
		JButton btnUser = new JButton("User");
		btnUser.setForeground(new Color(0, 128, 255));
		btnUser.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnUser.setBounds(61, 278, 226, 112);
		contentPane.add(btnUser);
		
		JButton btnReport = new JButton("Report");
		btnReport.setForeground(new Color(0, 128, 255));
		btnReport.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnReport.setBounds(339, 278, 226, 112);
		contentPane.add(btnReport);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setForeground(new Color(0, 128, 255));
		btnProfile.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnProfile.setBounds(614, 278, 226, 112);
		contentPane.add(btnProfile);
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnNewButton.setBounds(61, 427, 781, 61);
		contentPane.add(btnNewButton);
	}
	
}
