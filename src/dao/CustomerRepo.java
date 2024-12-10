package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.CustomerBuilder;
import confg.Database;

public class CustomerRepo implements CustomerDAO {
    private Connection connection;

    // SQL Queries
    final String insert = "INSERT INTO customer (id, name, addres, handphone) VALUES (?,?,?,?);";
    final String select = "SELECT * FROM customer;";
    final String delete = "DELETE FROM customer WHERE id=?;";
    final String update = "UPDATE customer SET name=?, addres=?, handphone=? WHERE id=?;";

    public CustomerRepo() {
        // Connect to the database
        connection = Database.getInstance().getConnection();
    }

    @Override
    public void save(Customer customer) {
    	PreparedStatement st = null;
    	try {
    		st = connection.prepareStatement(insert);
            st.setString(1, customer.getId());
            st.setString(2, customer.getName());
            st.setString(3, customer.getAddres());
            st.setString(4, customer.getHandphone());
            st.executeUpdate();
            System.out.println("Customer berhasil disimpan.");
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	try {
        		st.close();
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        }
    }

    @Override
    public List<Customer> show() {
    	List<Customer> customers = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                Customer customer = new CustomerBuilder()
                .setId(rs.getString("id"))
                .setName(rs.getString("name"))
                .setAddres(rs.getString("addres"))
                .setHandphone(rs.getString("handphone"))
                .build();
                customers.add(customer);
            }
            System.out.println("Customer data berhasil diambil.");
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error fetching customers", e);
        }
        return customers;
    }

    @Override
    public void delete(String customerId) {
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, customerId);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer berhasil dihapus.");
            } else {
                System.out.println("Customer tidak ditemukan.");
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error deleting customer", e);
        }
    }
         
    
    public void update(Customer customer) {
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, customer.getName());
            st.setString(2, customer.getAddres());
            st.setString(3, customer.getHandphone());
            st.setString(4, customer.getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer berhasil diupdate.");
            } else {
                System.out.println("Customer tidak ditemukan.");
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error updating customer", e);
        }
    }
}
