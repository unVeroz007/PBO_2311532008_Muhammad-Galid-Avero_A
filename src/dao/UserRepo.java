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

import javax.naming.spi.DirStateFactory.Result;

import confg.Database;
import model.User;

public class UserRepo implements UserDAO {
		private Connection connection;
		final String insert = "INSERT INTO user (name, username, password) VALUES (?,?,?);";
		final String select = "SELECT * FROM user;";
		final String delete = "DELETE FROM user WHERE id=?;";
		final String update = "UPDATE user SET name=?, username=?, password=? WHERE id=?;";

		
		public void save(User user) {
			 try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(insert)) {
		            st.setString(1, user.getNama());
		            st.setString(2, user.getUsername());
		            st.setString(3, user.getPassword());
		            st.executeUpdate();
		            System.out.println("User berhasil disimpan.");
		        } catch (SQLException e) {
		            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, "Error saving user", e);
		        }
		    }

		
		
		public List<User> show() {
			List<User> users = new ArrayList<>();
	        try (Connection connection = Database.getInstance().getConnection();
	             Statement st = connection.createStatement();
	             ResultSet rs = st.executeQuery(select)) {
	            while (rs.next()) {
	                User user = new User();
	                user.setId(rs.getString("id"));
	                user.setNama(rs.getString("name"));
	                user.setUsername(rs.getString("username"));
	                user.setPassword(rs.getString("password"));
	                users.add(user);
	            }
	            System.out.println("User data berhasil diambil.");
	        } catch (SQLException e) {
	            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, "Error fetching users", e);
	        }
	        return users;
	    }



		@Override
		public void update(User user) {
			 try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(update)) {
		            st.setString(1, user.getNama());
		            st.setString(2, user.getUsername());
		            st.setString(3, user.getPassword());
		            st.setString(4, user.getId());
		            st.executeUpdate();
		            System.out.println("User berhasil diperbarui.");
		        } catch (SQLException e) {
		            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, "Error updating user", e);
		        }
		    }

		
		
		@Override
		public void delete(String id) {
			try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(delete)) {
		            st.setString(1, id);
		            st.executeUpdate();
		            System.out.println("User berhasil dihapus.");
		        } catch (SQLException e) {
		            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, "Error deleting user", e);
		        }
		    }
		}	
















