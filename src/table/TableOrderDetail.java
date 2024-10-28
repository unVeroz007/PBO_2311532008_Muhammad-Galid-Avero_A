package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;
import model.OrderDetail;

public class TableOrderDetail extends AbstractTableModel {
	List<OrderDetail> ls;
	private String[] columnNames = {"id_order_detail", "id_order", "id_service", "jumlah", "total"};
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
		return 5;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		// TODO Auto-generated method stub
		case 0:
			return ls.get(rowIndex).getId_order();
		case 1:
			return ls.get(rowIndex).getId_service();
		case 2:
			return ls.get(rowIndex).getId_order_detail();
		case 3:
			return ls.get(rowIndex).getJumlah();
		case 4:
			return ls.get(rowIndex).getTotal();
		default:
			return null;	}
	}
}