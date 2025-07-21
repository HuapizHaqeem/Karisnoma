package model;

import java.time.LocalDate;

public class Riwayat {
    private String diagnosa;
    private String terapi;
    private String hasilLab;
    private LocalDate tanggal;
    private String notes;

    public Riwayat() {
        
    }

    public Riwayat(String diagnosa, String terapi, String hasilLab) {
        this.diagnosa =diagnosa;
        this.terapi = terapi;
        this.hasilLab = hasilLab;
    }

    public Riwayat(LocalDate tanggal, String notes) {
        this.tanggal = tanggal;
        this.notes = notes;
    }

    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { this.diagnosa = diagnosa; }
    
    public String getTerapi() { return terapi; }
    public void setTerapi(String terapi) { this.terapi=terapi; }
    

    public String getHasilLab() { return hasilLab; }
    public void setHasilLab(String hasilLab) { this.hasilLab=hasilLab; }
    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
