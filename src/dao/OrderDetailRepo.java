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
import model.OrderDetail;

public class OrderDetailRepo implements OrderDetailDAO { 
    private Connection connection;
    final String insert = "INSERT INTO order_detail (id_order, id_service, jumlah, total, harga) VALUES (?,?,?,?,?);";
    final String select = "SELECT * FROM order_detail;";
    final String delete = "DELETE FROM order_detail WHERE id_order_detail = ?;";
    final String update = "UPDATE order_detail SET id_order=?, id_service=?, jumlah=?, total=?, harga=? WHERE id_order_detail=?;";

    public OrderDetailRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(OrderDetail orderDetail) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, orderDetail.getId_order());
            st.setString(2, orderDetail.getId_service());
            st.setString(3, orderDetail.getJumlah());
            st.setString(4, orderDetail.getTotal());
			st.setString(5, orderDetail.getHarga());

            // Cek jika harga kosong, berikan nilai default 0
            String harga = orderDetail.getHarga();
            if (harga == null || harga.trim().isEmpty()) {
                harga = "0"; 
            }
            st.setString(5, harga);
            
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<OrderDetail> show(String id_order) {
        List<OrderDetail> ls = new ArrayList<>();
        String query = "SELECT * FROM order_detail WHERE id_order = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, id_order);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    OrderDetail od = new OrderDetail();
                    od.setId_order_detail(rs.getString("id_order_detail"));
                    od.setId_order(rs.getString("id_order"));
                    od.setId_service(rs.getString("id_service"));
                    od.setJumlah(rs.getString("jumlah"));
                    od.setTotal(rs.getString("total"));
                    od.setHarga(rs.getString("harga"));
                    ls.add(od);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }





    @Override
    public void update(OrderDetail orderDetail) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, orderDetail.getId_order());
            st.setString(2, orderDetail.getId_service());  
            st.setString(3, orderDetail.getJumlah());
            st.setString(4, orderDetail.getTotal());
            st.setString(5, orderDetail.getHarga());
            st.setString(6, orderDetail.getId_order_detail());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


	@Override
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement (delete);
			st.setString(1, id);
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			} 
		} 
	}

	@Override
	public String total(String id_order) {
	    String query_total = "SELECT sum(total) as total FROM order_detail WHERE id_order = ?;";
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    String result = "";
	    try {
	        st = connection.prepareStatement(query_total);
	        st.setString(1, id_order);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            result = String.valueOf(rs.getDouble("total"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}




}
