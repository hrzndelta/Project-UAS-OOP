package kasir.controller;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import kasir.dao.BarangDAO;
import kasir.dao.DetailBarangDAO;
import kasir.dao.TransaksiDAO;
import kasir.model.BarangModel;
import kasir.model.DetailBarangModel;
import kasir.model.TransaksiModel;

/**
 * Mediates between the transaksi (point-of-sale) view and the barang /
 * detail_barang / transaksi data access layers. Holds the checkout business
 * logic (searching products, adding/removing cart lines, stock adjustment,
 * totals) that used to live directly inside the {@code transaksi} JFrame.
 */
public class TransaksiController {

    private final BarangDAO barangDAO = new BarangDAO();
    private final DetailBarangDAO detailBarangDAO = new DetailBarangDAO();
    private final TransaksiDAO transaksiDAO = new TransaksiDAO();

    /** The product currently selected from the search results table. */
    private BarangModel selectedBarang;

    public TableModel cariBarang(String keyword) {
        return barangDAO.search(keyword);
    }

    /**
     * Records which product was clicked in the search results table so
     * later calls (subtotal, add-to-cart) know what it refers to.
     */
    public BarangModel pilihBarang(String kodeBarang) {
        selectedBarang = barangDAO.findByKode(kodeBarang);
        return selectedBarang;
    }

    public BarangModel getSelectedBarang() {
        return selectedBarang;
    }

    public String nextKodeTransaksi() {
        return transaksiDAO.nextKodeTransaksi();
    }

    /** Subtotal for `jumlah` units of the currently selected product, after a percent discount. */
    public int hitungSubtotal(int jumlah, int diskonPersen) {
        if (selectedBarang == null) {
            return 0;
        }
        int hargaSatuan = Integer.parseInt(selectedBarang.getHarga());
        int totalHarga = jumlah * hargaSatuan;
        int potongan = (totalHarga * diskonPersen) / 100;
        return totalHarga - potongan;
    }

    /**
     * Adds the currently selected product to the running transaction:
     * inserts a detail_barang row and decrements stock.
     */
    public boolean tambahItem(String kodeTransaksiBerjalan, int jumlah, int diskonPersen, int subtotal) {
        if (selectedBarang == null) {
            return false;
        }
        String kodeDetail = "D" + kodeTransaksiBerjalan;
        DetailBarangModel detail = new DetailBarangModel(
                kodeDetail, selectedBarang.getKodeBarang(), selectedBarang.getHarga(),
                String.valueOf(jumlah), String.valueOf(diskonPersen), String.valueOf(subtotal));

        boolean ok = detailBarangDAO.insert(detail);
        int stokBaru = Integer.parseInt(selectedBarang.getStok()) - jumlah;
        ok &= barangDAO.updateStok(selectedBarang.getKodeBarang(), stokBaru);
        return ok;
    }

    public TableModel getDetail(String kodeTransaksiBerjalan) {
        String kodeDetail = "D" + kodeTransaksiBerjalan;
        return detailBarangDAO.findByKodeDetail(kodeDetail);
    }

    /**
     * Removes a cart line for the given product code and restores the
     * stock that was reserved for it.
     */
    public boolean hapusItem(String kodeBarang) {
        DetailBarangModel detail = detailBarangDAO.findByKodeBarang(kodeBarang);
        boolean ok = detailBarangDAO.deleteByKodeBarang(kodeBarang);
        if (detail != null) {
            BarangModel barang = barangDAO.findByKode(kodeBarang);
            if (barang != null) {
                int stokBaru = Integer.parseInt(barang.getStok()) + Integer.parseInt(detail.getJumlah());
                ok &= barangDAO.updateStok(kodeBarang, stokBaru);
            }
        }
        return ok;
    }

    /** Sums the "Subtotal" column (index 5) of a cart table model. */
    public int hitungTotal(DefaultTableModel cartModel) {
        int total = 0;
        int rows = cartModel.getRowCount();
        for (int i = 0; i < rows; i++) {
            total += Integer.parseInt(cartModel.getValueAt(i, 5).toString());
        }
        return total;
    }

    public int hitungKembalian(int bayar, int totalBelanja) {
        return bayar - totalBelanja;
    }

    public boolean simpanTransaksi(String kodeTransaksi, String tanggal, String jam, int total) {
        String kodeDetail = "D" + kodeTransaksi;
        TransaksiModel transaksi = new TransaksiModel(kodeTransaksi, kodeDetail, tanggal, jam, String.valueOf(total));
        return transaksiDAO.insert(transaksi);
    }
}
