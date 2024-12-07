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

//    public OrderDetailRepo() {
//        connection = Database.getInstance().getConnection();
//    }

    @Override
    public void save(OrderDetail orderDetail) {
    	try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement st = connection.prepareStatement(insert)) {
               st.setString(1, orderDetail.getId_order());
               st.setString(2, orderDetail.getId_service());
               st.setInt(3, orderDetail.getJumlah());
               st.setInt(4, orderDetail.getTotal());
               st.executeUpdate();
               System.out.println("Order detail berhasil disimpan.");
           } catch (SQLException e) {
               Logger.getLogger(OrderDetailRepo.class.getName()).log(Level.SEVERE, "Error saving order detail", e);
           }
       }

    @Override
    public List<OrderDetail> show() {
        List<OrderDetail> ls = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(select)) {
               while (rs.next()) {
            	   while (rs.next()) {
            		    OrderDetail orderDetail = new OrderDetail(); 
            		    orderDetail.setId_order_detail(rs.getInt("id_order_detail")); 
            		    orderDetail.setId_order(rs.getString("id_order"));
            		    orderDetail.setId_service(rs.getString("id_service"));
            		    orderDetail.setJumlah(rs.getInt("jumlah"));
            		    orderDetail.setTotal(rs.getInt("total"));
            		    ls.add(orderDetail);
            	   }
               }
               System.out.println("Order detail berhasil diambil.");
           } catch (SQLException e) {
               Logger.getLogger(OrderDetailRepo.class.getName()).log(Level.SEVERE, "Error fetching order details", e);
           }
           return ls;
       }


    @Override
    public void update(OrderDetail orderDetail) {
    	try (Connection connection = Database.getInstance().getConnection();
                PreparedStatement st = connection.prepareStatement(update)) {
               st.setString(1, orderDetail.getId_order());
               st.setString(2, orderDetail.getId_service());
               st.setDouble(3, orderDetail.getJumlah());
               st.setDouble(4, orderDetail.getTotal());
               st.setInt(5, orderDetail.getId_order_detail());
               st.executeUpdate();
               System.out.println("Order detail berhasil diperbarui.");
           } catch (SQLException e) {
               Logger.getLogger(OrderDetailRepo.class.getName()).log(Level.SEVERE, "Error updating order detail", e);
           }
       }
   

	@Override
	public void delete(String id) {
		  try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(delete)) {
		            st.setString(1, id);
		            st.executeUpdate();
		            System.out.println("Order detail berhasil dihapus.");
		        } catch (SQLException e) {
		            Logger.getLogger(OrderDetailRepo.class.getName()).log(Level.SEVERE, "Error deleting order detail", e);
		        }
		    }
	}
