package model;

public class Gejala {
    private String tanggal;
    private String keluhan;
    private String tindakan;

    public Gejala() {}

    public Gejala(String tanggal, String keluhan, String tindakan) {
        this.tanggal = tanggal;
        this.keluhan = keluhan;
        this.tindakan = tindakan;
    }

    public String getTanggal() { return tanggal; }
    public String getKeluhan() { return keluhan; }
    public String getTindakan() { return tindakan; }

    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }
    public void setTindakan(String tindakan) { this.tindakan = tindakan; }
}
