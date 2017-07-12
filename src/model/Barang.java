package model;

public class Barang {
    private String Kode;
    private String KodeDetail;
    private String Merk;
    private String UOM;
    private double Harga;

    public String getKode() {
        return Kode;
    }
    public void setKode(String Kode) {
        this.Kode = Kode;
    }
    public String getKodeDetail() {
        return KodeDetail;
    }
    public void setKodeDetail(String KodeDetail) {
        this.KodeDetail = KodeDetail;
    }
    public String getMerk() {
        return Merk;
    }
    public void setMerk(String Merk) {
        this.Merk = Merk;
    }
    public String getUOM() {
        return UOM;
    }
    public void setUOM(String UOM) {
        this.UOM = UOM;
    }
    public double getHarga() {
        return Harga;
    }
    public void setHarga(double Harga) {
        this.Harga = Harga;
    }
}
