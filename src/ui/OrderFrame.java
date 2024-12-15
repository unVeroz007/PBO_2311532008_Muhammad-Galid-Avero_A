package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.OrderRepo;
import table.TableOrder;
import model.Order;
import table.TableService;
import ui.OrderDetailFrame;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JPanel contentPane;
	private JTable tblOrder;
	OrderDetailFrame newOrder = new OrderDetailFrame();
	public String id_cust;
	public String tanggal;
	public String status;
	public String status_bayar;
	public String total;
	public String tanggal_kembali;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
					frame.loadTableOrder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	OrderRepo repo_od = new OrderRepo();
	List<Order> ls_od;
	String order_id="";

	public void loadTableOrder() {
		ls_od = repo_od.show();
		TableOrder tu = new TableOrder(ls_od);
		tblOrder.setModel(tu);
		tblOrder.getTableHeader().setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 515);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJudul = new JLabel("Orderan\r\n");
		lblJudul.setHorizontalAlignment(SwingConstants.CENTER);
		lblJudul.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblJudul.setBounds(203, 27, 283, 46);
		contentPane.add(lblJudul);
		
		JButton btnBuatOrder = new JButton("Buat Order");
		btnBuatOrder.setBackground(Color.GREEN);
		btnBuatOrder.setForeground(Color.BLACK);
		btnBuatOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDetailFrame odf = new OrderDetailFrame();
				odf.setVisible(true);
				odf.loadTableDetail();
				odf.loadTableService();
				dispose();
			}

		});
		btnBuatOrder.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnBuatOrder.setBounds(26, 83, 131, 36);
		contentPane.add(btnBuatOrder);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBackground(Color.RED);
		btnHapus.setForeground(Color.BLACK);
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(order_id != "") {
					repo_od.delete(order_id);
					loadTableOrder();
				}else{
					JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
				}
			}
		});
		btnHapus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnHapus.setBounds(380, 83, 131, 36);
		contentPane.add(btnHapus);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBackground(Color.GRAY);
		btnEdit.setForeground(Color.BLACK);
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newOrder.loadTableDetail();
				newOrder.loadTableService();
				newOrder.setOrderID(order_id);
				newOrder.onDataReceived(id_cust, getCustomerNameById(id_cust));
				if (tanggal != null && !tanggal.isEmpty()) {
			        SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
			        try {
			            Date parsedDate_tanggal = (Date) sdf_tanggal.parse(tanggal);
			            newOrder.setTanggal(parsedDate_tanggal); 
			        } catch (ParseException ex) {
			            ex.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Format tanggal tidak valid.");
			        }
			    }
	            
				newOrder.setStatus(status);
	            newOrder.setStatusBayar(status_bayar);
	            newOrder.setTotal(total);
	            newOrder.setVisible(true);
	            dispose();
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnEdit.setBounds(541, 83, 131, 36);
		contentPane.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 131, 696, 337);
		contentPane.add(scrollPane);
		
		tblOrder = new JTable();
		tblOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblOrder.getSelectedRow();
                if (selectedRow != -1) { 
                    order_id = tblOrder.getValueAt(selectedRow, 0).toString(); 
                    id_cust = tblOrder.getValueAt(tblOrder.getSelectedRow(), 1).toString();
                    tanggal = tblOrder.getValueAt(tblOrder.getSelectedRow(), 2).toString();
                    status = tblOrder.getValueAt(tblOrder.getSelectedRow(), 3).toString();
                    status_bayar = tblOrder.getValueAt(tblOrder.getSelectedRow(), 4).toString();
                    total = tblOrder.getValueAt(tblOrder.getSelectedRow(), 5).toString();
                }
			}
		});
		scrollPane.setViewportView(tblOrder);
	}
	

	protected String getCustomerNameById(String id_cust2) {
		// TODO Auto-generated method stub
		return repo_od.getCustomerName(id_cust2);
	}
}
