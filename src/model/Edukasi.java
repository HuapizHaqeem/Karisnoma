package model;

public class Edukasi {
    private String judul;
    private String isi;

    public Edukasi() {}

    public Edukasi(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getIsi() { return isi; }
    public void setIsi(String isi) { this.isi = isi; }
}
