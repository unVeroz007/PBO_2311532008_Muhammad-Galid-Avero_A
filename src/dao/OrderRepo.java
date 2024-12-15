package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import confg.Database;
import model.Order;
import model.Customer;

public class OrderRepo implements OrderDAO {
	private static Connection connection;
	final String insert = "INSERT INTO orders (id, id_pelanggan, tanggal, tanggal_pengambilan, status, pembayaran, status_pembayaran, total) VALUES (?,?,?,?,?,?,?,?);";
	final String select = "SELECT * FROM orders;";
	final String delete = "DELETE FROM orders WHERE id=?;";
	final String delete_detail = "DELETE FROM order_detail WHERE id_order=?;";
	final String update = "UPDATE orders SET id_pelanggan=?, tanggal=?, tanggal_pengambilan=?, status=?, pembayaran=?, status_pembayaran=?, total=? WHERE id=?;";
	
	public OrderRepo() {
        this.connection = Database.koneksi();
	}

	@Override
	public void save(Order cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, cs.getId());
			st.setString(2, cs.getId_pelanggan());
			st.setString(3, cs.getTanggal());
			st.setString(4, cs.getTanggal_pengambilan());
			st.setString(5, cs.getStatus());
			st.setString(6, cs.getPembayaran());
			st.setString(7, cs.getStatus_pembayaran());
			st.setString(8, cs.getTotal());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Order> show() {
		List<Order> ls = null;
		try {
			ls = new ArrayList<Order>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				Order cs = new Order();
				cs.setId(rs.getString("id"));
				cs.setId_pelanggan(rs.getString("id_pelanggan"));
				cs.setTanggal(rs.getString("tanggal"));
				cs.setTanggal_pengambilan(rs.getString("tanggal_pengambilan"));
				cs.setStatus(rs.getString("status"));
				cs.setPembayaran(rs.getString("pembayaran"));
				cs.setStatus_pembayaran(rs.getString("status_pembayaran"));
				cs.setTotal(rs.getString("total"));
				ls.add(cs);
			}
		} catch (SQLException e) {
			Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}

	@Override
	public void delete(String id) {
		PreparedStatement st = null;
		PreparedStatement st_detail = null;
		try {
			st = connection.prepareStatement(delete);
			st.setString(1, id);
			st.executeUpdate();
			
			st_detail = connection.prepareStatement(delete_detail);
			st_detail.setString(1, id);
			st_detail.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				st_detail.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Order cs) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, cs.getId_pelanggan());
			st.setString(2, cs.getTanggal());
			st.setString(3, cs.getTanggal_pengambilan());
			st.setString(4, cs.getStatus());
			st.setString(5, cs.getPembayaran());
			st.setString(6, cs.getStatus_pembayaran());
			st.setString(7, cs.getTotal());
			st.setString(8, cs.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String getCustomerName(String customerId) {
	    String name = "";
	    try {
	        String query = "SELECT name FROM customer WHERE id = ?";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, customerId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            name = rs.getString("name");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return name;
	}
	
	public boolean checkOrderExists(String orderId) {
	    boolean exists = false;
	    try {
	        String query = "SELECT COUNT(*) FROM orders WHERE id = ?";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, orderId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            exists = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return exists;
	}
	
	public static String generateOrderId() {
	    String newOrderId = "TRX-0001"; 
	    try {
	        String query = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
	        PreparedStatement stmt = connection.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            String lastOrderId = rs.getString("id");
	            if (lastOrderId != null && lastOrderId.matches("TRX-\\d{4}")) { 
	                int lastNumber = Integer.parseInt(lastOrderId.split("-")[1]);
	                int newNumber = lastNumber + 1;
	                newOrderId = String.format("TRX-%04d", newNumber);
	            } else {
	                System.err.println("Format ID tidak sesuai: " + lastOrderId);
	            }
	        } else {
	            System.out.println("Tabel orders kosong. Menggunakan ID default.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Kesalahan SQL: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return newOrderId;
	}


	@Override
	public void delete(Order id) {
		// TODO Auto-generated method stub
		
	}





}