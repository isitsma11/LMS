package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    public static Connection connection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "");
            System.out.println("Connected to database.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
        return conn;
    }
}
