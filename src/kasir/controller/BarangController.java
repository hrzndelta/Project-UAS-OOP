package kasir.controller;

import javax.swing.table.TableModel;
import kasir.dao.BarangDAO;
import kasir.model.BarangModel;

/**
 * Mediates between the Barang view and the barang data access layer.
 * Holds the product-management business logic that used to live directly
 * inside the {@code Barang} JFrame.
 */
public class BarangController {

    private final BarangDAO barangDAO = new BarangDAO();

    public TableModel getAllBarang() {
        return barangDAO.findAll();
    }

    public BarangModel getBarang(String kodeBarang) {
        return barangDAO.findByKode(kodeBarang);
    }

    public boolean simpanBarang(BarangModel barang) {
        boolean ok = barangDAO.insert(barang);
        return ok;
    }

    public boolean updateBarang(BarangModel barang) {
        return barangDAO.update(barang);
    }

    public boolean hapusBarang(String kodeBarang) {
        return barangDAO.delete(kodeBarang);
    }
}
