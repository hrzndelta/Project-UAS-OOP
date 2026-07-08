package kasir.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Single place responsible for opening JDBC connections to the "kasir"
 * MySQL database. This is the only class in the application that knows
 * about the JDBC URL / driver, so all data-access classes (DAOs) go
 * through here rather than opening their own connections.
 */
public final class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/kasir";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
