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
    final String insert = "INSERT INTO customer (id, name, addres, handphone) VALUES (?,?,?,?);";
    final String select = "SELECT * FROM customer;";
    final String delete = "DELETE FROM customer WHERE id=?;";
    final String update = "UPDATE customer SET name=?, addres=?, handphone=? WHERE id=?;";

    public CustomerRepo() {
        // Connect to the database
        this.connection = Database.koneksi();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Customer> show() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer"; // Sesuaikan dengan struktur tabel Anda
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setName(rs.getString("name"));
                customer.setAddres(rs.getString("addres"));
                customer.setHandphone(rs.getString("handphone"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded customers: " + customers.size()); // Debug jumlah data
        return customers;
    }


    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, customer.getName());
            st.setString(2, customer.getAddres());
            st.setString(3, customer.getHandphone());
            st.setString(4, customer.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
