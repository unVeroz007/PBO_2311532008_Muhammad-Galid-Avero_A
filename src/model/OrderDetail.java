package model;

public class OrderDetail {
	String id_order, id_service;
	int id_order_detail, jumlah, total;
	
	public String getId_order() {
		return id_order;
	}
	public void setId_order(String id_order) {
		this.id_order = id_order;
	}
	public String getId_service() {
		return id_service;
	}
	public void setId_service(String id_service) {
		this.id_service = id_service;
	}
	public int getId_order_detail() {
		return id_order_detail;
	}
	public void setId_order_detail(int id_order_detail) {
		this.id_order_detail = id_order_detail;
	}
	public int getJumlah() {
		return jumlah;
	}
	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
