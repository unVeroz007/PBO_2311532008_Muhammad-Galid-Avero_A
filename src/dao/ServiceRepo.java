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
		final String insert = "INSERT INTO service (jenis, harga, status) VALUES (?,?,?);";
		final String select = "SELECT * FROM service;";
		final String delete = "DELETE FROM service WHERE id=?;";
		final String update = "UPDATE service SET jenis=?, harga=?, status=? WHERE id=?;";
		
//		public ServiceRepo() {
//			connection = Database.koneksi();
//		}

		@Override
		public void save(Service service) {
			try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(insert)) {
		            st.setString(1, service.getJenis());
		            st.setString(2, service.getStatus());
		            st.setDouble(3, service.getHarga());
		            st.executeUpdate();
		            System.out.println("Service berhasil disimpan.");
		        } catch (SQLException e) {
		            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, "Error saving service", e);
		        }
		    }


		@Override
		public List<Service> show() {
			List<Service> services = new ArrayList<>();
	        try (Connection connection = Database.getInstance().getConnection();
	             Statement st = connection.createStatement();
	             ResultSet rs = st.executeQuery(select)) {
	            while (rs.next()) {
	                Service service = new Service();
	                service.setId(rs.getString("id_service"));
	                service.setJenis(rs.getString("jenis"));
	                service.setStatus(rs.getString("status"));
	                service.setHarga(rs.getInt("harga"));
	                services.add(service);
	            }
	            System.out.println("Service berhasil diambil.");
	        } catch (SQLException e) {
	            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, "Error fetching services", e);
	        }
	        return services;
	    }


		@Override
		public void delete(String id) {
			 try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(delete)) {
		            st.setString(1, id);
		            st.executeUpdate();
		            System.out.println("Service berhasil dihapus.");
		        } catch (SQLException e) {
		            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, "Error deleting service", e);
		        }
		}
			


		
		@Override
		public void update(Service service) {
			try (Connection connection = Database.getInstance().getConnection();
		             PreparedStatement st = connection.prepareStatement(update)) {
		            st.setString(1, service.getJenis());
		            st.setString(2, service.getStatus());
		            st.setDouble(3, service.getHarga());
		            st.setString(4, service.getId());
		            st.executeUpdate();
		            System.out.println("Service berhasil diperbarui.");
		        } catch (SQLException e) {
		            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, "Error updating service", e);
				}
			}			
		}

		
		
















