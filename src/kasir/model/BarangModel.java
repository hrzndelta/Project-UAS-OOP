package kasir.model;

/**
 * Plain data holder for a row of the {@code barang} (product) table.
 * Contains no Swing and no JDBC code, per MVC separation of concerns.
 */
public class BarangModel {

    private String kodeBarang;
    private String namaBarang;
    private String stok;
    private String satuan;
    private String harga;

    public BarangModel() {
    }

    public BarangModel(String kodeBarang, String namaBarang, String stok, String satuan, String harga) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.satuan = satuan;
        this.harga = harga;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
