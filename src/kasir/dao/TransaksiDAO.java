package kasir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import kasir.model.TransaksiModel;
import net.proteanit.sql.DbUtils;

/**
 * All SQL for the {@code transaksi} table (transaction headers) lives here.
 */
public class TransaksiDAO {

    public boolean insert(TransaksiModel transaksi) {
        String sql = "insert into transaksi (Kode_Transaksi,Kode_Detail,Tanggal,Jam,Total) value (?,?,?,?,?)";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, transaksi.getKodeTransaksi());
            pst.setString(2, transaksi.getKodeDetail());
            pst.setString(3, transaksi.getTanggal());
            pst.setString(4, transaksi.getJam());
            pst.setString(5, transaksi.getTotal());
            pst.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    /**
     * Generates the next transaction code, e.g. TRX001, TRX002, ...
     */
    public String nextKodeTransaksi() {
        String sql = "SELECT MAX(RIGHT(Kode_Transaksi,3)) AS NO FROM transaksi";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rst = pst.executeQuery()) {
            if (!rst.first()) {
                return "TRX001";
            }
            rst.last();
            int autoId = rst.getInt(1) + 1;
            String no = String.valueOf(autoId);
            int panjang = no.length();
            for (int j = 0; j < 3 - panjang; j++) {
                no = "0" + no;
            }
            return "TRX" + no;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return "TRX001";
        }
    }

    public TableModel findBefore(String tanggal) {
        return findWhere("select * from transaksi where Tanggal < ?", tanggal);
    }

    public TableModel findAfter(String tanggal) {
        return findWhere("select * from transaksi where Tanggal > ?", tanggal);
    }

    public TableModel findOn(String tanggal) {
        return findWhere("select * from transaksi where Tanggal = ?", tanggal);
    }

    public TableModel findBetween(String tanggalAwal, String tanggalAkhir) {
        String sql = "select * from transaksi where Tanggal between ? and ?";
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, tanggalAwal);
            pst.setString(2, tanggalAkhir);
            try (ResultSet rst = pst.executeQuery()) {
                return DbUtils.resultSetToTableModel(rst);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    private TableModel findWhere(String sql, String tanggal) {
        try (Connection koneksi = DatabaseConnection.getConnection();
                PreparedStatement pst = koneksi.prepareStatement(sql)) {
            pst.setString(1, tanggal);
            try (ResultSet rst = pst.executeQuery()) {
                return DbUtils.resultSetToTableModel(rst);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
