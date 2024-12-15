package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CustomerRepo;
import dao.ServiceRepo;
import model.Customer;
import model.CustomerBuilder;
import model.Service;
import model.User;
import table.TableCustomer;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddres;
	private JTextField txtHP;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.loadTable();
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
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(168, 10, 265, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name           :");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		lblName.setBounds(36, 79, 138, 41);
		contentPane.add(lblName);
		
		JLabel lblAddres = new JLabel("Addres         :");
		lblAddres.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddres.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		lblAddres.setBounds(36, 122, 165, 41);
		contentPane.add(lblAddres);
		
		JLabel lblHp = new JLabel("Handphone :");
		lblHp.setHorizontalAlignment(SwingConstants.LEFT);
		lblHp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		lblHp.setBounds(36, 162, 165, 41);
		contentPane.add(lblHp);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(180, 86, 427, 33);
		contentPane.add(txtName);
		
		txtAddres = new JTextField();
		txtAddres.setColumns(10);
		txtAddres.setBounds(180, 129, 427, 33);
		contentPane.add(txtAddres);
		
		txtHP = new JTextField();
		txtHP.setColumns(10);
		txtHP.setBounds(180, 169, 427, 33);
		contentPane.add(txtHP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("ID\r\nName\r\nAddress\r\nHandphone");
		scrollPane.setBounds(10, 264, 624, 250);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = table.getValueAt(table.getSelectedRow(),0).toString();
				txtName.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				txtAddres.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				txtHP.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new CustomerBuilder()
				.setName(txtName.getText())
				.setAddres(txtAddres.getText())
				.setHandphone(txtHP.getText())
				.build();
				
				ctm.save(customer);
				reset();
				loadTable();
			}
		});
		btnSave.setForeground(Color.BLACK);
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(84, 215, 126, 39);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(id != null) {
				Customer customer = new CustomerBuilder()
		    	.setName(txtName.getText())
		    	.setAddres(txtAddres.getText())
		    	.setHandphone(txtHP.getText())
		    	.setId(id)
		    	.build();
		    	
		    	ctm.update(customer);
		    	reset();
		    	loadTable();
		    	JOptionPane.showMessageDialog(null, "Data berhasil diperbarui");
			}else {
		    	JOptionPane.showMessageDialog(null, "Pilih Data yang akan diperbarui");

			}
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBackground(Color.GRAY);
		btnUpdate.setBounds(220, 215, 126, 39);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (id != null && !id.isEmpty()) {
		            try {
		            	JOptionPane.showMessageDialog(null, "YAKIN INGIN MENGHAPUS","KONFIRM", JOptionPane.YES_NO_OPTION);

		                Customer customer = new CustomerBuilder()
		                    .setId(id)
		                    .build();

		                CustomerRepo customerRepo = new CustomerRepo();
		                customerRepo.delete(customer.getId());

		                reset();
		                loadTable();

		                // Pesan konfirmasi
		                JOptionPane.showMessageDialog(null, "Customer berhasil dihapus.");
		            } catch (Exception ex) {
		                // Tangkap error
		                JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
		                ex.printStackTrace();
		            }
		        } else {
		            // Pesan jika ID tidak valid
		            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus.");
		        }
		    }
		});


		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(356, 215, 126, 39);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame Frame = new MainFrame();
				Frame.setVisible(true);
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setBounds(492, 215, 126, 39);
		contentPane.add(btnCancel);
	}
	
	CustomerRepo ctm = new CustomerRepo();
	List<Customer> ls;
	public String id;
	
	protected void loadTable() {
		ls = ctm.show();
		TableCustomer tu = new TableCustomer(ls);
		table.getTableHeader().setVisible(true);		
		table.setModel(tu);
	}

	protected void reset() {
		txtName.setText("");
		txtAddres.setText("");
		txtHP.setText("");		
	}
}
