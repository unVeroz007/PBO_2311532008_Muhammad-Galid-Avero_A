package dao;

import java.security.Provider;
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
import model.Service;


public  class ServiceRepo implements ServiceDAO {
		private Connection connection;
		final String insert = "INSERT INTO service (jenis, harga, status) VALUES (?,?,?);";
		final String select = "SELECT * FROM service;";
		final String delete = "DELETE FROM service WHERE id=?;";
		final String update = "UPDATE service SET jenis=?, harga=?, status=? WHERE id=?;";
		
		public ServiceRepo() {
			connection = Database.koneksi();
		}

		@Override
		public void save(Service service) {
			PreparedStatement st = null;
			try {
				st = connection.prepareStatement(insert);
				st.setString(1, service.getJenis());
				st.setString(2, service.getHarga());
				st.setString(3, service.getStatus());
				st.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				if (st != null) {
				try {
					st.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			}

			
		}

		@Override
		public List<Service> show() {
			List<Service> ls = new ArrayList<>();
		    try (Statement st = connection.createStatement();
		         ResultSet rs = st.executeQuery(select)) {
		        while (rs.next()) {
		            Service service = new Service();
		            service.setId(rs.getString("id"));
		            service.setJenis(rs.getString("Jenis"));
		            service.setHarga(rs.getString("Harga"));
		            service.setStatus(rs.getString("Status"));
		            ls.add(service);
		        }
		    } catch (SQLException e) {
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
		public void update(Service service) {
			PreparedStatement st = null;
			try {
				st = connection.prepareStatement(update);
				st.setString(1, service.getJenis());
				st.setString(2, service.getHarga());
				st.setString(3, service.getStatus());
				st.setString(4, service.getId());
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















