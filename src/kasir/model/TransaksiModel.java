package kasir.model;

/**
 * Plain data holder for a row of the {@code transaksi} table.
 */
public class TransaksiModel {

    private String kodeTransaksi;
    private String kodeDetail;
    private String tanggal;
    private String jam;
    private String total;

    public TransaksiModel() {
    }

    public TransaksiModel(String kodeTransaksi, String kodeDetail, String tanggal, String jam, String total) {
        this.kodeTransaksi = kodeTransaksi;
        this.kodeDetail = kodeDetail;
        this.tanggal = tanggal;
        this.jam = jam;
        this.total = total;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getKodeDetail() {
        return kodeDetail;
    }

    public void setKodeDetail(String kodeDetail) {
        this.kodeDetail = kodeDetail;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
