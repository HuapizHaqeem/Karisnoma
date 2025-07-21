package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pasien extends akun {
    private String fullName;
    private String gender;
    private LocalDate birthDate;
    private List<Gejala> daftarGejala;
    private List<Pengingat> pengingat;
    private List<Riwayat> riwayat;
    private String appointmentInfo;

    public Pasien() {
        super();
        this.daftarGejala = new ArrayList<>();
        this.pengingat = new ArrayList<>();
        this.riwayat = new ArrayList<>();
    }

    public Pasien(String username, String password, String peran, String email, String fullName, String gender, LocalDate birthDate) {
        super(username, password, peran, email);
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.daftarGejala = new ArrayList<>();
        this.pengingat = new ArrayList<>();
        this.riwayat = new ArrayList<>();
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public List<Gejala> getDaftarGejala() { return daftarGejala; }
    public void setDaftarGejala(List<Gejala> daftarGejala) { this.daftarGejala = daftarGejala; }

    public List<Pengingat> getPengingat() { return pengingat; }
    public void setPengingat(List<Pengingat> pengingat) { this.pengingat = pengingat; }

    public List<Riwayat> getRiwayat() { return riwayat; }
    public void setRiwayat(List<Riwayat> riwayat) { this.riwayat = riwayat; }

    public String getAppointmentInfo() { return appointmentInfo; }
    public void setAppointmentInfo(String appointmentInfo) { this.appointmentInfo = appointmentInfo; }
}
