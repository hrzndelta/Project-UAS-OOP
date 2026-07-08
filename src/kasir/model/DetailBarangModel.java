package kasir.model;

/**
 * Plain data holder for a row of the {@code detail_barang} table - a single
 * line item (one product) inside a transaction.
 */
public class DetailBarangModel {

    private String kodeDetail;
    private String kodeBarang;
    private String harga;
    private String jumlah;
    private String discount;
    private String subtotal;

    public DetailBarangModel() {
    }

    public DetailBarangModel(String kodeDetail, String kodeBarang, String harga,
            String jumlah, String discount, String subtotal) {
        this.kodeDetail = kodeDetail;
        this.kodeBarang = kodeBarang;
        this.harga = harga;
        this.jumlah = jumlah;
        this.discount = discount;
        this.subtotal = subtotal;
    }

    public String getKodeDetail() {
        return kodeDetail;
    }

    public void setKodeDetail(String kodeDetail) {
        this.kodeDetail = kodeDetail;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
