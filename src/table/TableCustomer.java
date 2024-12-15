package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class TableCustomer extends AbstractTableModel {
	List<Customer> ls;
	private String[] columnNames = {"ID", "Name", "Addres", "Handphone"};
	public TableCustomer(List<Customer> ls) {
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
		return 4;
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
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getName();
		case 2:
			return ls.get(rowIndex).getAddres();
		case 3:
			return ls.get(rowIndex).getHandphone();
		default:
			return null;	
			}
	}
}