package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.OrderDetailRepo;
import dao.OrderRepo;
import dao.ServiceRepo;
import model.Order;
import model.OrderDetail;
import model.Customer;
import model.Service;
import listener.DataListener;
import table.TableOrderDetail;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.List;
import java.util.Date;
import javax.swing.JInternalFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;

public class OrderDetailFrame extends JFrame implements DataListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHargaKg;
	private JTextField txtJumlah;
	private JTextField txtTotal;
	private JTable tableService;
	private JTable tableOrderDetail;
	private JTextField txtTrx;
	private JTextField txtPelanggan;
	private JLabel txtHargaTotal;
	private JComboBox boxStatus;
	private JComboBox boxStatusBayar;
	JDateChooser CalTanggal;
	JDateChooser CalTanggalKembali;
	
	ServiceRepo sr = new ServiceRepo();
	List<Service> ls_service;
	public String id_service;
	public static String txt_pelanggan="";
	
	public double total(String jumlah) {
		double result = 0;
		if(jumlah.isEmpty()) {
			result = 0;
		} else {
			result = Double.parseDouble(jumlah) * Double.parseDouble(txtHargaKg.getText());
		}
		return result;
	}
	
	public void reset() {
		txtHargaKg.setText("");
		txtJumlah.setText("");
		txtTotal.setText("");
		id_service=null;
		id_order_detail=null;
	}
	
	OrderDetailRepo repo_od = new OrderDetailRepo();
	List<OrderDetail> ls_od;
	public String id_order_detail;
	
	
	
	public void loadTableService() { 
		ls_service = sr.show();
		TableService tu = new TableService (ls_service);
		tableService.setModel(tu);
		tableService.getTableHeader().setVisible(true); 
	}
	
	
	public void loadTableDetail() {
	    if (txtTrx.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Order ID tidak ditemukan. Tidak dapat memuat Order Detail.");
	        return;
	    }

	    ls_od = repo_od.show(txtTrx.getText());
	    if (ls_od == null || ls_od.isEmpty()) {
	        return;
	    }

	    TableOrderDetail tu = new TableOrderDetail(ls_od);
	    tableOrderDetail.setModel(tu);
	    tableOrderDetail.getTableHeader().setVisible(true);
	}


	public String tgl;
	public String tgl_kbl;
	
	public void setOrderID(String id) {
		txtTrx.setText(id);
	}
	public void setCustID (String id) {
		txt_pelanggan=id;
	}
	public void setPelanggan (String pelanggan) {
		txtPelanggan.setText(pelanggan);
	}
	public void setTanggal(Date date) {
		CalTanggal.setDate(date);
	}
	public void setTanggalKembali(Date date) {
		CalTanggalKembali.setDate(date);
	}
	public void setStatus (String proses) {
		boxStatus.setSelectedItem(proses);
	}
	public void setTotal (String total) {
		txtHargaTotal.setText(total);
	}
	public void setStatusBayar (String status) {
		boxStatusBayar.setSelectedItem(status);
	}
	
	OrderRepo order_repo = new OrderRepo();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailFrame frame = new OrderDetailFrame();
					frame.loadTableDetail();
					frame.setVisible(true);
					frame.loadTableService();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public OrderDetailFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(272, 210, 403, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHargaKg = new JLabel("Harga/Kg");
		lblHargaKg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHargaKg.setBounds(10, 25, 96, 13);
		panel.add(lblHargaKg);
		
		txtHargaKg = new JTextField();
		txtHargaKg.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtHargaKg.setBounds(10, 43, 170, 28);
		panel.add(txtHargaKg);
		txtHargaKg.setColumns(10);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblJumlah.setBounds(10, 81, 96, 13);
		panel.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String value_jumlah = txtJumlah.getText().toString();
				txtTotal.setText(""+total(value_jumlah));
			}

			
		});

		txtJumlah.setColumns(10);
		txtJumlah.setBounds(10, 100, 160, 28);
		panel.add(txtJumlah);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTotal.setBounds(233, 81, 96, 13);
		panel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTotal.setColumns(10);
		txtTotal.setBounds(233, 100, 160, 28);
		panel.add(txtTotal);
		
		JButton btnSaveDetail = new JButton("SAVE");
		btnSaveDetail.setBackground(new Color(100, 149, 237));
		btnSaveDetail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSaveDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail == null) {
					OrderDetail od = new OrderDetail();
					od.setId_order(txtTrx.getText());
					od.setId_service(id_service);
					od.setHarga (txtHargaKg.getText());
					od.setJumlah(txtJumlah.getText());
					od.setTotal(txtTotal.getText());
					repo_od.save(od);
					JOptionPane.showMessageDialog(null, "berhasil disimpan");
					loadTableDetail();
					reset();
					txtHargaTotal.setText(""+repo_od.total(txtTrx.getText()));
				}
			}
		});
		btnSaveDetail.setBounds(2, 158, 95, 28);
		panel.add(btnSaveDetail);
		
		JButton btnUpdateDetail = new JButton("UPDATE");
		btnUpdateDetail.setBackground(new Color(0, 255, 127));
		btnUpdateDetail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUpdateDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail != null) {
					OrderDetail od = new OrderDetail();
					od.setId_order(txtTrx.getText());
					od.setId_service(id_service);
					od.setHarga(txtHargaKg.getText());
					od.setJumlah(txtJumlah.getText());
					od.setTotal(txtTotal.getText());
					od.setId_order_detail(id_order_detail);
					repo_od.update(od);
					loadTableDetail();
					reset();
					txtTotal.setText(""+repo_od.total(txtTrx.getText()));
				}else{
					JOptionPane.showMessageDialog(null, "silahkan pilih order terlebih dahulu");
				}
			}
		});
		btnUpdateDetail.setBounds(99, 158, 100, 28);
		panel.add(btnUpdateDetail);
		
		JButton btnDeleteDetail = new JButton("DELETE");
		btnDeleteDetail.setBackground(new Color(255, 0, 0));
		btnDeleteDetail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDeleteDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order_detail != null) {
					repo_od.delete(id_order_detail);
					reset();
					loadTableDetail();
					txtTotal.setText(""+repo_od.total(txtTrx.getText()));
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		btnDeleteDetail.setBounds(201, 158, 100, 28);
		panel.add(btnDeleteDetail);
		
		JButton btnCancellDetail = new JButton("CANCEL");
		btnCancellDetail.setBackground(new Color(255, 140, 0));
		btnCancellDetail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnCancellDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancellDetail.setBounds(303, 158, 100, 28);
		panel.add(btnCancellDetail);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 35, 252, 552);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblJudulOrder = new JLabel("Order ID");
		lblJudulOrder.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblJudulOrder.setBounds(10, 10, 93, 13);
		panel_1.add(lblJudulOrder);
		
		txtTrx = new JTextField();
		txtTrx.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
		txtTrx.setBounds(10, 29, 232, 28);
		panel_1.add(txtTrx);
		txtTrx.setColumns(10);
		String generatedOrderid = order_repo.generateOrderId();
		txtTrx.setText(generatedOrderid);
		txtTrx.setEditable(false);
				
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblPelanggan.setBounds(10, 67, 93, 13);
		panel_1.add(lblPelanggan);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblTanggal.setBounds(10, 131, 93, 17);
		panel_1.add(lblTanggal);
		
		JLabel lblKembali = new JLabel("Tanggal Pengambilan");
		lblKembali.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblKembali.setBounds(10, 202, 179, 13);
		panel_1.add(lblKembali);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblStatus.setBounds(10, 262, 93, 13);
		panel_1.add(lblStatus);
		
		boxStatus = new JComboBox();
		boxStatus.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		boxStatus.setModel(new DefaultComboBoxModel(new String[] {"Diproses", "Selesai"}));
		boxStatus.setBounds(10, 285, 232, 28);
		panel_1.add(boxStatus);
		
		JLabel lblTotalOrder = new JLabel("Total");
		lblTotalOrder.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblTotalOrder.setBounds(10, 331, 45, 13);
		panel_1.add(lblTotalOrder);
		
		txtHargaTotal = new JLabel("");
		txtHargaTotal.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		txtHargaTotal.setBounds(10, 343, 217, 37);
		panel_1.add(txtHargaTotal);
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblPembayaran.setBounds(10, 380, 179, 28);
		panel_1.add(lblPembayaran);
		
		JComboBox boxPembayaran = new JComboBox();
		boxPembayaran.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
		boxPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Tunai", "Transfer", "Qris"}));
		boxPembayaran.setBounds(10, 403, 232, 28);
		panel_1.add(boxPembayaran);
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		lblStatusPembayaran.setBounds(10, 441, 197, 13);
		panel_1.add(lblStatusPembayaran);
		
		boxStatusBayar = new JComboBox();
		boxStatusBayar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		boxStatusBayar.setModel(new DefaultComboBoxModel(new String[] {"Lunas", "Belum Lunas"}));
		boxStatusBayar.setBounds(10, 454, 232, 28);
		panel_1.add(boxStatusBayar);
		
		txtPelanggan = new JTextField();
		txtPelanggan.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 18));
		txtPelanggan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DialogPelanggan dialog = new DialogPelanggan(OrderDetailFrame.this);
				dialog.setVisible(true);
			}
		});
		txtPelanggan.setColumns(10);
		txtPelanggan.setBounds(10, 90, 232, 28);
		panel_1.add(txtPelanggan);
		
		JButton btnSaveOrder = new JButton("SAVE");
		btnSaveOrder.setBackground(new Color(100, 149, 237));
		btnSaveOrder.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		btnSaveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderRepo order_repo = new OrderRepo();
				if(txt_pelanggan !="") {
					Order order = new Order();
					order.setId(txtTrx.getText());
					order.setId_pelanggan(txt_pelanggan);
					order.setTanggal(tgl);
					order.setTanggal_pengambilan(tgl_kbl);
					order.setStatus(boxStatus.getSelectedItem().toString());
					order.setStatus_pembayaran(boxStatusBayar.getSelectedItem().toString());
					order.setPembayaran(boxPembayaran.getSelectedItem().toString());
					order.setTotal(txtHargaTotal.getText());
					boolean isExistingOrder = order_repo.checkOrderExists(txtTrx.getText());

		            if (isExistingOrder) {
		                // Jika Order ID sudah ada, lakukan update
		                order_repo.update(order);
		                JOptionPane.showMessageDialog(null, "Order berhasil diperbarui");
		            } else {
		                // Jika Order ID belum ada, lakukan save
		                order_repo.save(order);
		                JOptionPane.showMessageDialog(null, "Order berhasil disimpan");
		            }
		            OrderFrame orderFrame = new OrderFrame();
		            orderFrame.loadTableOrder();
		            orderFrame.setVisible(true);
		            dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih Pelanggan terlebih dahulu");
				}
			}
			
		});
		btnSaveOrder.setBounds(10, 514, 109, 28);
		panel_1.add(btnSaveOrder);
		
		CalTanggal = new JDateChooser();
		CalTanggal.setBounds(10, 158, 232, 27);
		panel_1.add(CalTanggal);
		CalTanggal.getDateEditor().addPropertyChangeListener("date", evt -> {
		    if (CalTanggal.getDate() != null) {
		    	SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
		        tgl = sdf_tanggal.format(CalTanggal.getDate());
		    }
		});
		
		CalTanggalKembali = new JDateChooser();
		CalTanggalKembali.setBounds(10, 225, 232, 27);
		panel_1.add(CalTanggalKembali);
		CalTanggalKembali.getDateEditor().addPropertyChangeListener("date", evt -> {
		    if (CalTanggalKembali.getDate() != null) {
		    	SimpleDateFormat sdf_tanggalkembali = new SimpleDateFormat("yyyy-MM-dd");
		        tgl_kbl = sdf_tanggalkembali.format(CalTanggalKembali.getDate());
		    }
		});
		
		JButton btnCancelOrder = new JButton("CANCEL");
		btnCancelOrder.setBackground(new Color(255, 140, 0));
		btnCancelOrder.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelOrder.setBounds(129, 514, 113, 28);
		panel_1.add(btnCancelOrder);
		
		JScrollPane scrollPaneService = new JScrollPane();
		scrollPaneService.setBounds(272, 58, 403, 142);
		contentPane.add(scrollPaneService);
		
		tableService = new JTable();
		scrollPaneService.setViewportView(tableService);
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_service = tableService.getValueAt(tableService.getSelectedRow(),0).toString();
				txtHargaKg.setText(tableService.getValueAt(tableService.getSelectedRow(),2).toString());
				if(!txtJumlah.getText().isEmpty()) {
					txtTotal.setText(""+total(txtJumlah.getText())); 
				}
			}
			
		});
		
		
		
		JLabel lblService = new JLabel("Service");
		lblService.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblService.setBounds(272, 35, 118, 13);
		contentPane.add(lblService);
		
		JScrollPane scrollPaneOrderDetail = new JScrollPane();
		scrollPaneOrderDetail.setBounds(272, 410, 403, 177);
		contentPane.add(scrollPaneOrderDetail);
		
		tableOrderDetail = new JTable();
		scrollPaneOrderDetail.setViewportView(tableOrderDetail);
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_order_detail = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),0).toString();
				id_service = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),2).toString();
				txtHargaKg.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),4).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(),5).toString());
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 3).toString());
			}
		});

	}
	
	public void onDataReceived(String id, String nama) {
		txtPelanggan.setText(nama);
		txt_pelanggan=id;
		
	}
}