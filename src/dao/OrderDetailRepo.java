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
    final String insert = "INSERT INTO order_detail (id_order, id_service, jumlah, total) VALUES (?,?,?,?);";
    final String select = "SELECT * FROM order_detail;";
    final String delete = "DELETE FROM order_detail WHERE id_order_detail = ?;";
    final String update = "UPDATE order_detail SET id_order=?, id_service=?, jumlah=?, total=? WHERE id_order_detail=?;";

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
            st.setInt(3, orderDetail.getJumlah());
            st.setInt(4, orderDetail.getTotal());
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
    public List<OrderDetail> show() {
        List<OrderDetail> ls = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId_order_detail(rs.getInt("id_order_detail"));
                orderDetail.setId_order(rs.getString("id_order"));
                orderDetail.setId_service(rs.getString("id_service"));  
                orderDetail.setJumlah(rs.getInt("jumlah"));
                orderDetail.setTotal(rs.getInt("total"));
                ls.add(orderDetail);
            }
        } catch(SQLException e) {
            Logger.getLogger(OrderDetailRepo.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            st.setInt(3, orderDetail.getJumlah());
            st.setInt(4, orderDetail.getTotal());
            st.setInt(5, orderDetail.getId_order_detail());
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
            st = connection.prepareStatement(delete);
            st.setString(1, id);
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
}
