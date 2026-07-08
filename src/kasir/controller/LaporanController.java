package kasir.controller;

import javax.swing.table.TableModel;
import kasir.dao.DetailBarangDAO;
import kasir.dao.TransaksiDAO;

/**
 * Mediates between the reporting views ({@code subpopup} / {@code popup})
 * and the transaksi / detail_barang data access layers.
 */
public class LaporanController {

    private final TransaksiDAO transaksiDAO = new TransaksiDAO();
    private final DetailBarangDAO detailBarangDAO = new DetailBarangDAO();

    public TableModel laporanSebelumTanggal(String tanggal) {
        return transaksiDAO.findBefore(tanggal);
    }

    public TableModel laporanSetelahTanggal(String tanggal) {
        return transaksiDAO.findAfter(tanggal);
    }

    public TableModel laporanPadaTanggal(String tanggal) {
        return transaksiDAO.findOn(tanggal);
    }

    public TableModel laporanAntaraTanggal(String tanggalAwal, String tanggalAkhir) {
        return transaksiDAO.findBetween(tanggalAwal, tanggalAkhir);
    }

    public TableModel detailTransaksi(String kodeDetail) {
        return detailBarangDAO.findByKodeDetail(kodeDetail);
    }
}
