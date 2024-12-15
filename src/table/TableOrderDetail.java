package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;
import model.OrderDetail;

public class TableOrderDetail extends AbstractTableModel {
	List<OrderDetail> ls;
	private String[] columnNames = {"id_order_detail", "id_order", "id_service", "jumlah", "harga", "total"};
	public TableOrderDetail(List<OrderDetail> ls) {
		this.ls = ls; 
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    OrderDetail orderDetail = ls.get(rowIndex); 
	    switch (columnIndex) {
	        case 0: 
	            return orderDetail.getId_order_detail(); 
	        case 1: 
	            return orderDetail.getId_order(); 
	        case 2: 
	            return orderDetail.getId_service(); 
	        case 3: 
	            return orderDetail.getJumlah(); 
	        case 4: 
	        	return orderDetail.getHarga(); 
	        case 5: 
	            return orderDetail.getTotal(); 
	        default:
	            return null; 
	    }
	}

}