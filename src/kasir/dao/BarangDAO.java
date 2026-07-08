package kasir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import kasir.model.BarangModel;
import net.proteanit.sql.DbUtils;

/**
 * All SQL for the {@code barang} table lives here. Views/controllers never
 * build SQL strings or talk to {@link java.sql.Connection} directly.
 */
public class BarangDAO {

    public TableModel findAll() {
        String sql = "select * from barang";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql);
                ResultSet rst = pst.executeQuery()) {
            return DbUtils.resultSetToTableModel(rst);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public TableModel search(String keyword) {
        String sql = "select * from barang where Nama_Barang LIKE ?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            try (ResultSet rst = pst.executeQuery()) {
                return DbUtils.resultSetToTableModel(rst);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public BarangModel findByKode(String kodeBarang) {
        String sql = "select * from barang where Kode_Barang=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, kodeBarang);
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    return new BarangModel(
                            rst.getString("Kode_Barang"),
                            rst.getString("Nama_Barang"),
                            rst.getString("Stok"),
                            rst.getString("Satuan"),
                            rst.getString("Harga"));
                }
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insert(BarangModel barang) {
        String sql = "insert into barang (Kode_Barang,Nama_Barang,Stok,Satuan,Harga) value (?,?,?,?,?)";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, barang.getKodeBarang());
            pst.setString(2, barang.getNamaBarang());
            pst.setString(3, barang.getStok());
            pst.setString(4, barang.getSatuan());
            pst.setString(5, barang.getHarga());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean update(BarangModel barang) {
        String sql = "update barang set Nama_Barang=?, Stok=?, Satuan=?, Harga=? where Kode_Barang=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, barang.getNamaBarang());
            pst.setString(2, barang.getStok());
            pst.setString(3, barang.getSatuan());
            pst.setString(4, barang.getHarga());
            pst.setString(5, barang.getKodeBarang());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean delete(String kodeBarang) {
        String sql = "delete from barang where Kode_Barang=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, kodeBarang);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean updateStok(String kodeBarang, int stokBaru) {
        String sql = "update barang set Stok=? where Kode_Barang=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, String.valueOf(stokBaru));
            pst.setString(2, kodeBarang);
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
