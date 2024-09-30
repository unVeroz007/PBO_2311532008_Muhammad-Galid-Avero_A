package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ServiceRepo;
import dao.UserRepo;
import model.Service;
import model.User;
import table.TableService;
import table.TableUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame<srvc> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtJenis;
	private JTextField txtHarga;
	private JTextField txtStatus;
	private JTable tableService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
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
	public ServiceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pilih Layanan");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 0, 290, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblID = new JLabel("ID           :");
		lblID.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblID.setBounds(63, 54, 134, 37);
		contentPane.add(lblID);
		
		JLabel lblJenis = new JLabel("Jenis       :");
		lblJenis.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblJenis.setBounds(63, 101, 134, 37);
		contentPane.add(lblJenis);
		
		JLabel lblHarga = new JLabel("Harga     :");
		lblHarga.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblHarga.setBounds(63, 148, 134, 37);
		contentPane.add(lblHarga);
		
		JLabel lblStatus = new JLabel("Status     :");
		lblStatus.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblStatus.setBounds(63, 195, 134, 37);
		contentPane.add(lblStatus);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		txtID.setBounds(207, 56, 392, 37);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtJenis = new JTextField();
		txtJenis.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		txtJenis.setColumns(10);
		txtJenis.setBounds(207, 103, 392, 37);
		contentPane.add(txtJenis);
		
		txtHarga = new JTextField();
		txtHarga.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		txtHarga.setColumns(10);
		txtHarga.setBounds(207, 150, 392, 37);
		contentPane.add(txtHarga);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		txtStatus.setColumns(10);
		txtStatus.setBounds(207, 197, 392, 37);
		contentPane.add(txtStatus);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Service service = new Service();
					service.setId(txtID.getText());
					service.setJenis(txtJenis.getText());
					service.setHarga(txtHarga.getText());
					service.setStatus(txtStatus.getText());
					srvc.save(service);
					reset();
					loadTable();
			}
		});
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(63, 254, 126, 39);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
		    	service.setId(txtID.getText());
		    	service.setJenis(txtJenis.getText());
		    	service.setHarga(txtHarga.getText());
		    	service.setStatus(txtStatus.getText());
		    	service.setId(id);
		    	srvc.update(service);
		    	reset();
		    	loadTable();
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBackground(Color.GRAY);
		btnUpdate.setBounds(215, 254, 126, 39);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					srvc.delete(id);
					reset();
					loadTable();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(374, 254, 126, 39);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setBounds(522, 254, 126, 39);
		contentPane.add(btnCancel);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 id = tableService.getValueAt(tableService.getSelectedRow(), 1).toString();
	                txtJenis.setText(tableService.getValueAt(tableService.getSelectedRow(), 2).toString()); 
	                txtHarga.setText(tableService.getValueAt(tableService.getSelectedRow(), 3).toString()); 
	                txtStatus.setText(tableService.getValueAt(tableService.getSelectedRow(), 4).toString()); 
			}
		});
		tableService.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tableService.setBounds(10, 308, 666, 233);
		contentPane.add(tableService);
	}

	ServiceRepo srvc = new ServiceRepo();
	List<Service> ls;
	public String id;
	
	protected void loadTable() {
		ls = srvc.show();
		TableService tu = new TableService(ls);
		tableService.setModel(tu);
		tableService.getTableHeader().setVisible(true);		
	}

	protected void reset() {
		txtID.setText("");
		txtJenis.setText("");
		txtHarga.setText("");
		txtStatus.setText("");		

	}
}
