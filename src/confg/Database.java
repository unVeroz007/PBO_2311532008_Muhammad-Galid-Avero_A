package confg;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Database {
    
    private static Database instance;
    private Connection conn;
    
    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laudry_apps", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + e.getMessage());
        }
    }
    
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return conn;
    }
}