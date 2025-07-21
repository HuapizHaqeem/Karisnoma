package model;

public class Pengingat {
    private String tanggal;
    private String jenis;
    private boolean sudahDijalankan;

    public Pengingat() {
    }

    public Pengingat(String tanggal, String jenis) {
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.sudahDijalankan = false;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public boolean isSudahDijalankan() {
        return sudahDijalankan;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setSudahDijalankan(boolean sudahDijalankan) {
        this.sudahDijalankan = sudahDijalankan;
    }

    @Override
    public String toString() {
        return getTanggal() + " - " + getJenis();
    }
}
