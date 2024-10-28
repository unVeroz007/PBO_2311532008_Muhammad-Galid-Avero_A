package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.OrderDetailRepo;
import dao.ServiceRepo;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Service;
import table.TableOrderDetail;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOrderID;
	private JTextField txtTanggal;
	private JTextField txtTanggalPengambilan;
	private JTextField txtHargaKg;
	private JTextField txtJumlah;
	private JTextField txtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
					frame.loadTable();
					frame.loadTableOrderDetail();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reset() {
		txtHargaKg.setText("");
		txtJumlah.setText("");
		txtTotal.setText("");
	}
	
	ServiceRepo srv = new ServiceRepo();
	List<Service> ls;
	public String id;
	private JTable tableService;
	
	OrderDetailRepo ord = new OrderDetailRepo();
	List<OrderDetail> ls_ord;
	public int id_ord;
	public String id_order;
	private JTable tableOrderDetail;
	
	
	public void loadTable() {
		ls = srv.show();
		TableService ts = new TableService(ls);
		tableService.setModel(ts);
		tableService.getTableHeader().setVisible(true);
	}
	
	public void loadTableOrderDetail() {
		ls_ord = ord.show();
		TableOrderDetail tod = new TableOrderDetail(ls_ord);
		tableOrderDetail.setModel(tod);
		tableOrderDetail.getTableHeader().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		
		setBackground(new Color(246, 246, 246));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 246, 246));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrderID = new JLabel("Order ID");
		lblOrderID.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblOrderID.setBounds(20, 22, 81, 25);
		contentPane.add(lblOrderID);
		
		txtOrderID = new JTextField();
		txtOrderID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				id_order = txtOrderID.getText();
			}
		});
		txtOrderID.setBounds(20, 48, 170, 19);
		contentPane.add(txtOrderID);
		txtOrderID.setColumns(10);
		
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblPelanggan.setBounds(20, 75, 92, 25);
		contentPane.add(lblPelanggan);
		
		JComboBox boxPelanggan = new JComboBox();
		boxPelanggan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		boxPelanggan.setModel(new DefaultComboBoxModel(new String[] {"Pilih"}));
		boxPelanggan.setBounds(20, 102, 170, 25);
		contentPane.add(boxPelanggan);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblTanggal.setBounds(20, 133, 81, 25);
		contentPane.add(lblTanggal);
		
		txtTanggal = new JTextField();
		txtTanggal.setColumns(10);
		txtTanggal.setBounds(20, 159, 170, 19);
		contentPane.add(txtTanggal);
		
		JLabel lblTanggalPengambilan = new JLabel("Tanggal Pengambilan");
		lblTanggalPengambilan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblTanggalPengambilan.setBounds(20, 188, 170, 25);
		contentPane.add(lblTanggalPengambilan);
		
		txtTanggalPengambilan = new JTextField();
		txtTanggalPengambilan.setColumns(10);
		txtTanggalPengambilan.setBounds(20, 213, 170, 19);
		contentPane.add(txtTanggalPengambilan);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblStatus.setBounds(20, 239, 81, 25);
		contentPane.add(lblStatus);
		
		JComboBox boxStatus = new JComboBox();
		boxStatus.setBounds(20, 266, 170, 21);
		contentPane.add(boxStatus);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblTotal.setBounds(20, 297, 53, 25);
		contentPane.add(lblTotal);
		
		JLabel lblTotalHargaShow = new JLabel("");
		lblTotalHargaShow.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblTotalHargaShow.setBounds(20, 321, 170, 25);
		contentPane.add(lblTotalHargaShow);
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblPembayaran.setBounds(20, 356, 103, 25);
		contentPane.add(lblPembayaran);
		
		JComboBox boxPembayaran = new JComboBox();
		boxPembayaran.setBounds(20, 380, 170, 21);
		contentPane.add(boxPembayaran);
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblStatusPembayaran.setBounds(20, 411, 148, 25);
		contentPane.add(lblStatusPembayaran);
		
		JComboBox boxPembayaran_1 = new JComboBox();
		boxPembayaran_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		boxPembayaran_1.setModel(new DefaultComboBoxModel(new String[] {"PROSES", "TIDAK DIPROSES"}));
		boxPembayaran_1.setBounds(20, 436, 170, 21);
		contentPane.add(boxPembayaran_1);
		
		JLabel lblLayanan = new JLabel("Layanan");
		lblLayanan.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblLayanan.setBounds(220, 22, 103, 25);
		contentPane.add(lblLayanan);
		
		JLabel lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setHorizontalAlignment(SwingConstants.CENTER);
		lblHargakg.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblHargakg.setBounds(257, 225, 103, 25);
		contentPane.add(lblHargakg);
		
		JLabel lblId_layanan = new JLabel("");
		lblId_layanan.setEnabled(false);
		lblId_layanan.setVisible(false);
		lblId_layanan.setBounds(400, 248, 326, 13);
		contentPane.add(lblId_layanan);
		
		txtHargaKg = new JTextField();
		txtHargaKg.setColumns(10);
		txtHargaKg.setBounds(220, 250, 170, 19);
		contentPane.add(txtHargaKg);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setHorizontalAlignment(SwingConstants.CENTER);
		lblJumlah.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblJumlah.setBounds(257, 279, 103, 25);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int totalharga = Integer.parseInt(txtJumlah.getText()) * Integer.parseInt(txtHargaKg.getText());
			txtTotal.setText("" + totalharga);
			}
		});
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(220, 303, 170, 19);
		contentPane.add(txtJumlah);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		lblTotal_1.setBounds(602, 279, 103, 25);
		contentPane.add(lblTotal_1);
		
		JLabel lblId_order = new JLabel("");
		lblId_order.setEnabled(false);
		lblId_order.setVisible(false);
		lblId_order.setBounds(400, 248, 326, 13);
		contentPane.add(lblId_order);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(567, 303, 170, 19);
		contentPane.add(txtTotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(220, 50, 556, 165);
		contentPane.add(scrollPane);
		
		tableService = new JTable();
		tableService.getTableHeader().setVisible(true);
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtHargaKg.setText(tableService.getValueAt(tableService.getSelectedRow(), 2).toString());
				lblId_layanan.setText(tableService.getValueAt(tableService.getSelectedRow(), 0).toString());
			}
		});
		scrollPane.setViewportView(tableService);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					OrderDetail orderdetail = new OrderDetail();
					orderdetail.setId_order(txtOrderID.getText());
					orderdetail.setId_service(lblId_layanan.getText());
					orderdetail.setJumlah(Integer.parseInt(txtJumlah.getText()));
					orderdetail.setTotal(Integer.parseInt(txtTotal.getText()));
					ord.save(orderdetail);
					txtOrderID.setText("");
					id_order = null;
					reset();
					loadTableOrderDetail();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yg ingin disimpan");
				}
				
			}
		});
		btnSave.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnSave.setBounds(220, 336, 103, 34);
		contentPane.add(btnSave);
		
		JButton btnUbah = new JButton("UPDATE");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					OrderDetail orderdetail = new OrderDetail();
					orderdetail.setId_order(lblId_order.getText());
					orderdetail.setId_service(lblId_layanan.getText());
					orderdetail.setJumlah(Integer.parseInt(txtJumlah.getText()));
					orderdetail.setTotal(Integer.parseInt(txtTotal.getText()));
					orderdetail.setId_order_detail(id_ord);
					ord.update(orderdetail);
					reset();
					loadTableOrderDetail();
				}
				else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di UPDATE");
				}
			}
		});
		btnUbah.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnUbah.setBounds(357, 336, 103, 34);
		contentPane.add(btnUbah);
		
		JButton btnHapus = new JButton("DELETE");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					ord.delete(id);
					reset();
					loadTableOrderDetail();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnHapus.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnHapus.setBounds(508, 336, 103, 34);
		contentPane.add(btnHapus);
		
		JButton btnBatal = new JButton("CANCEL");
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnBatal.setFont(new Font("HP Simplified Jpan Light", Font.PLAIN, 16));
		btnBatal.setBounds(651, 336, 110, 34);
		contentPane.add(btnBatal);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(222, 383, 554, 170);
		contentPane.add(scrollPane_1);
		
		tableOrderDetail = new JTable();
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 0).toString();
				int selectedRow = tableOrderDetail.getSelectedRow();
				if (selectedRow != -1) {
					String idLayanan = tableOrderDetail.getValueAt(selectedRow, 2).toString();
					for (Service service : ls) {
						if (service.getId().equals(idLayanan)) {
							txtHargaKg.setText(String.valueOf(service.getHarga()));
							break;
						}
					}
				}
				
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 3).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 4).toString());
				lblId_order.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 1).toString());
				lblId_layanan.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 2).toString());
				
			}
		});
		scrollPane_1.setViewportView(tableOrderDetail);
		
		JButton btnSave_1 = new JButton("SAVE");
		btnSave_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnSave_1.setBounds(57, 467, 111, 34);
		contentPane.add(btnSave_1);
		
		JButton btnBatal_1 = new JButton("CANCEL");
		btnBatal_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnBatal_1.setBounds(58, 511, 110, 34);
		contentPane.add(btnBatal_1);
		
		
		
		
	}
}
