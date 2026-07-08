package kasir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * All SQL for the {@code login} (user account) table lives here.
 */
public class LoginDAO {

    public boolean authenticate(String username, String password) {
        String sql = "select * from login where username=? and password=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rst = pst.executeQuery()) {
                return rst.next();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
