package kasir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import kasir.model.DetailBarangModel;
import net.proteanit.sql.DbUtils;

/**
 * All SQL for the {@code detail_barang} table (the line items of a
 * transaction) lives here.
 */
public class DetailBarangDAO {

    public TableModel findByKodeDetail(String kodeDetail) {
        String sql = "select * from detail_barang where Kode_Detail=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, kodeDetail);
            try (ResultSet rst = pst.executeQuery()) {
                return DbUtils.resultSetToTableModel(rst);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public DetailBarangModel findByKodeBarang(String kodeBarang) {
        String sql = "select * from detail_barang where Kode_Barang=?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, kodeBarang);
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    return new DetailBarangModel(
                            rst.getString("Kode_Detail"),
                            rst.getString("Kode_Barang"),
                            rst.getString("Harga"),
                            rst.getString("Jumlah"),
                            rst.getString("Discount"),
                            rst.getString("Subtotal"));
                }
                return null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public boolean insert(DetailBarangModel detail) {
        String sql = "insert into detail_barang (Kode_Detail,Kode_Barang,Harga,Jumlah,Discount,Subtotal) value (?,?,?,?,?,?)";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, detail.getKodeDetail());
            pst.setString(2, detail.getKodeBarang());
            pst.setString(3, detail.getHarga());
            pst.setString(4, detail.getJumlah());
            pst.setString(5, detail.getDiscount());
            pst.setString(6, detail.getSubtotal());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean deleteByKodeBarang(String kodeBarang) {
        String sql = "delete from detail_barang where Kode_Barang=?";
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
}
