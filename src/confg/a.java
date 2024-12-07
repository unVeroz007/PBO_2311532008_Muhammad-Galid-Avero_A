package confg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class a {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/laudry_apps?useSSL=false";
        String user = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi berhasil!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }
}