# Karisnoma - Aplikasi Deteksi & Manajemen Kanker

Karisnoma adalah aplikasi desktop berbasis JavaFX yang dirancang untuk membantu proses pencatatan gejala, pengelolaan riwayat medis, serta pemberian edukasi dan pengingat kepada pasien penderita kanker. Aplikasi ini dikembangkan sebagai bagian dari proyek tugas besar dengan pendekatan berbasis peran, yaitu Admin, Dokter, dan Pasien.

---
🧩 Fitur Utama
- 🔐 Autentikasi Pengguna
  - Login berdasarkan peran: Admin, Dokter, Pasien
  - Fitur "Lupa Password" untuk pemulihan akun

- 📊 Dashboard Berbasis Role
  - Admin: Mengelola akun pengguna dan pengingat sistem
  - Dokter: Melihat data gejala dan riwayat medis pasien
  - Pasien: Mencatat gejala, melihat edukasi, dan menerima pengingat

- 📋 Manajemen Gejala
  - Pasien dapat mencatat dan menghapus gejala yang dirasakan
  - Dokter dapat melihat daftar gejala yang dilaporkan pasien

- 📖 Edukasi Kesehatan
  - Menyediakan konten edukatif seputar kanker
  - Bisa diakses oleh pasien untuk menambah wawasan terkait kesehatan

- 🕒 Pengingat
  - Pengingat minum obat atau jadwal kontrol rutin
  - Dapat ditambahkan dan dihapus oleh Admin

- 🗃️ Riwayat Medis
  - Mencatat riwayat pemeriksaan atau pengobatan pasien
  - Dikelola oleh dokter dan bisa dilihat oleh pasien

- 🎨 Tampilan UI Interaktif
  - Desain modern menggunakan file CSS terpisah (Style.css, dll)
  - Tampilan bersih dan mudah dinavigasi

---
📁 Struktur Proyek
```
TubesV2Fix_AI/
├── src/                  # Kode sumber (Java + JavaFX)
│   ├── controller/       # Semua file controller berdasarkan peran dan fitur
│   ├── model/            # Class model (misal: Gejala, Pengingat, dll)
│   ├── view/             # File .fxml (jika ada)
│   ├── Main.java         # Entry point aplikasi
│   ├── Style.css         # Styling tampilan
├── lib/                  # Dependensi eksternal (jika ada)
├── bin/                  # Output kompilasi
├── README.md             # Dokumentasi proyek
├── TubesV2Fix_AI.jar     # File executable aplikasi
```
---
🚀 Cara Menjalankan Aplikasi<br>
**Menggunakan IDE (VS Code / IntelliJ):**<br>
**1.** Pastikan Java Development Kit (JDK) 9<br>
**2.** Buka proyek di VS Code<br>
**3.** Jalankan file ```Main.java``` sebagai program Java

Menggunakan Terminal:
```
java -jar TubesV2Fix_AI.jar
```
| Pastikan JavaFX sudah dikonfigurasi jika menggunakan JDK standalone.

---
🛠️ Teknologi yang Digunakan
- Java 9
- JavaFX & FXML
- CSS untuk UI Styling
- Penyimpanan data lokal (kemungkinan berbasis XML atau objek serialisasi)
- Visual Studio Code (sebagai IDE utama)

---
👥 Kontributor
- Muhammad Hafizh Hakim
- Mahija Ramadhan Prabaswarakha
- Muhammad Luthfi
- Akbar Wijaya Alamsyah
  
---
📌 Catatan
Proyek ini dibuat untuk memenuhi tugas besar mata kuliah [nama mata kuliah] dan berfokus pada penerapan pemrograman berorientasi objek, struktur kontrol, serta penerapan GUI berbasis JavaFX.
