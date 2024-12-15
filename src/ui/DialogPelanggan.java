package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import dao.CustomerRepo;
import dao.ServiceRepo;
import listener.DataListener;
import model.Customer;
import model.Service;
import model.User;
import table.TableCustomer;
import table.TableService;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DialogPelanggan extends JDialog {

	private static final long serialVersionUID = 1L;
	CustomerRepo usr = new CustomerRepo();
	List<Customer> ls;
	private DataListener listener; 
	private TableCustomer tableCustomer;
	private TableService tableService;
	private ServiceRepo srv;
	private JTable tablePelanggan;

	/**
	 * Launch the application.
	 */

	public DialogPelanggan(DataListener listener) {
	    this.listener = listener; 
	    setModal(true);
	    setBounds(100, 100, 450, 300);
	    getContentPane().setLayout(null);

	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setToolTipText("ID\r\nNama\r\nAlamat\r\nHP");
	    scrollPane.setBounds(10, 10, 416, 243);
	    getContentPane().add(scrollPane);

	    tablePelanggan = new JTable();
	    tablePelanggan.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	tablePelanggan.addMouseListener(new MouseAdapter() {
	        	    @Override
	        	    public void mouseClicked(MouseEvent e) {
	        	        int selectedRow = tablePelanggan.getSelectedRow();
	        	        String id = tablePelanggan.getValueAt(selectedRow, 0).toString();
	        	        String name = tablePelanggan.getValueAt(selectedRow, 1).toString();
	        	        listener.onDataReceived(id, name);
	        	        dispose();
	        	    }
	        	});	            
	        }
	    });
	    scrollPane.setViewportView(tablePelanggan);
	    loadTable();
	}

	
	public void loadTable() {
		ls = usr.show();
		TableCustomer tu = new TableCustomer(ls);
		tablePelanggan.setModel(tu);
		tablePelanggan.getTableHeader().setVisible(true);
	    }
}


