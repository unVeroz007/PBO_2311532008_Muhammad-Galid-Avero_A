package model;

public class Order {
	
	String id, id_customer, id_service, id_user, total, tanggal, tanggal_selesai, status_pembayaran;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_customer() {
		return id_customer;
	}

	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}

	public String getId_service() {
		return id_service;
	}

	public void setId_service(String id_service) {
		this.id_service = id_service;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getTanggal_selesai() {
		return tanggal_selesai;
	}

	public void setTanggal_selesai(String tanggal_selesai) {
		this.tanggal_selesai = tanggal_selesai;
	}

	public String getStatus_pembayaran() {
		return status_pembayaran;
	}

	public void setStatus_pembayaran(String status_pembayaran) {
		this.status_pembayaran = status_pembayaran;
	}

}
