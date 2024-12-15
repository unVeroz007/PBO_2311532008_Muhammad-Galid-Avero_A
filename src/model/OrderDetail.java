package model;

public class OrderDetail {
	
	String id_order, id_service, id_order_detail, jumlah, harga, total;

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

	public String getId_order_detail() {
		return id_order_detail;
	}

	public void setId_order_detail(String id_order_detail) {
		this.id_order_detail = id_order_detail;
	}

	public String getJumlah() {
		return jumlah;
	}

	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}


}