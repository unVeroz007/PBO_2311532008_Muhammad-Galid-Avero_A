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

import model.Customer;

public class CustomerRepo implements CustomerDAO{
	private Connection connection;
	final String insert = "INSERT INTO service (id, name, addres, handphone) VALUES (?,?,?,?);";
	final String select = "SELECT * FROM service;";
	final String delete = "DELETE FROM service WHERE id=?;";
	final String update = "UPDATE service SET name=?, addres=?, handphone=? WHERE id=?;";
	

	@Override
	public void save(Customer customer) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, customer.getName());
			st.setString(2, customer.getAddres());
			st.setString(3, customer.getHandphone());
			st.setString(4, customer.getId());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Customer> show() {
		List<Customer> ls = new ArrayList<>();
	    try (Statement st = connection.createStatement();
	         ResultSet rs = st.executeQuery(select)) {
	        while (rs.next()) {
	            Customer customer = new Customer();
	            customer.setId(rs.getString("id"));
	            customer.setName(rs.getString("name"));
	            customer.setAddres(rs.getString("addres"));
	            customer.setHandphone(rs.getString("handphone"));
	            ls.add(customer);
	        }
	    } catch (SQLException e) {
//	        Logger.getLogger(ServiceDAO.class.getJenis()).log(Level.SEVERE, null, e);
	    	Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, null, e);

	    } 
	    return ls;
	}

	@Override
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
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
	public void update(Customer customer) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, customer.getName());
			st.setString(2, customer.getAddres());
			st.setString(3, customer.getHandphone());
			st.setString(4, customer.getId());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}					
	}

}
