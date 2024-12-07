package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import confg.Database;

public class CustomerRepo implements CustomerDAO {
    private Connection connection;

    // SQL Queries
    final String insert = "INSERT INTO service (id, name, addres, handphone) VALUES (?,?,?,?);";
    final String select = "SELECT * FROM service;";
    final String delete = "DELETE FROM service WHERE id=?;";
    final String update = "UPDATE service SET name=?, addres=?, handphone=? WHERE id=?;";

    public CustomerRepo() {
        // Connect to the database
        connection = Database.getInstance().getConnection();
    }

    @Override
    public void save(Customer customer) {
    	try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, customer.getId());
            st.setString(2, customer.getName());
            st.setString(2, customer.getAddres());
            st.setString(3, customer.getHandphone());
            st.executeUpdate();
            System.out.println("Customer berhasil disimpan.");
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, "Error saving customer", e);
        }
    }

    @Override
    public List<Customer> show() {
    	List<Customer> customers = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getString("id_customer"));
                customer.setName(rs.getString("Nama"));
                customer.setAddres(rs.getString("Alamat"));
                customer.setHandphone(rs.getString("NO HP"));
                customers.add(customer);
            }
            System.out.println("Customer data berhasil diambil.");
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, "Error fetching customers", e);
        }
        return customers;
    }

    @Override
    public void delete(String id) {
    	try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, id);
            st.executeUpdate();
            System.out.println("Customer berhasil dihapus.");
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, "Error deleting customer", e);
        }
    }
         
        
    @Override
    public void update(Customer customer) {
    	try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, customer.getName());
            st.setString(2, customer.getAddres());
            st.setString(3, customer.getHandphone());
            st.setString(4, customer.getId());
            st.executeUpdate();
            System.out.println("Customer berhasil diperbarui.");
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, "Error updating customer", e);

        }
    }
}
