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
    final String insert = "INSERT INTO customer (name, addres, handphone) VALUES (?,?,?);";
    final String select = "SELECT * FROM customer;";
    final String delete = "DELETE FROM customer WHERE id=?;";
    final String update = "UPDATE customer SET name=?, addres=?, handphone=? WHERE id=?;";

    public CustomerRepo() {
        // Connect to the database
        connection = Database.getInstance().getConnection();
    }

    @Override
    public void save(Customer customer) {
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            // Gunakan Builder Pattern untuk membuat objek Customer
            Customer newCustomer = new CustomerBuilder()
            		
                .setName(customer.getName())
                .setAddres(customer.getAddres())
                .setHandphone(customer.getHandphone())
                .build();

            // Masukkan nilai ke dalam query
            st.setString(1, newCustomer.getName());
            st.setString(2, newCustomer.getAddres());
            st.setString(3, newCustomer.getHandphone());
            st.executeUpdate();

            System.out.println("Customer berhasil disimpan.");
        } catch (SQLException e) {
            e.printStackTrace();
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
    public void delete(String id) {
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            Customer customer = new CustomerBuilder()
                .setId(id)
                .build();
            statement.setString(1, customer.getId());  
            int rowsAffected = statement.executeUpdate();
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
            Customer updatedCustomer = new CustomerBuilder()
//                .setId(customer.getId())
                .setName(customer.getName())
                .setAddres(customer.getAddres())
                .setHandphone(customer.getHandphone())
                .build();

            st.setString(1, updatedCustomer.getName());
            st.setString(2, updatedCustomer.getAddres());
            st.setString(3, updatedCustomer.getHandphone());
            st.setString(4, updatedCustomer.getId());

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
