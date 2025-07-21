package util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class XMLService<T> {

    private final XStream xstream = new XStream(new StaxDriver());

    public XMLService() {
        // Tambahkan izin agar XStream bisa load class yang dibutuhkan
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(new String[] { "model.**" });
        // atau jika hanya 1 class:
        // xstream.allowTypes(new Class[] { model.Riwayat.class });
    }

    public void saveToXML(List<T> dataList, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            xstream.toXML(dataList, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> loadFromXML(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists())
                return null;

            FileReader reader = new FileReader(file);
            List<T> dataList = (List<T>) xstream.fromXML(reader);
            reader.close();
            return dataList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <ID_TYPE> void updateXML(T updatedObject, String filename,
            java.util.function.Function<T, ID_TYPE> idExtractor) {
        List<T> existingData = loadFromXML(filename);
        if (existingData == null) {
            existingData = new ArrayList<>(); // Inisialisasi jika file tidak ada atau gagal dimuat
        }
        boolean found = false;
        for (int i = 0; i < existingData.size(); i++) {
            // Asumsi objek memiliki metode getId() atau properti unik lainnya
            // Anda perlu menyediakan cara untuk mengidentifikasi objek yang akan diperbarui
            // Contoh: if (existingData.get(i).getId().equals(updatedObject.getId()))
            // Untuk lebih fleksibel, kita bisa menggunakan Function untuk mengekstrak ID
            if (idExtractor.apply(existingData.get(i)).equals(idExtractor.apply(updatedObject))) {
                existingData.set(i, updatedObject); // Ganti objek lama dengan objek yang diperbarui
                found = true;
                break;
            }
        }
        if (!found) {
            // Jika objek tidak ditemukan (misalnya, ini adalah data baru), tambahkan saja
            // ke list
            existingData.add(updatedObject);
        }

        saveToXML(existingData, filename); // Simpan kembali seluruh list yang sudah dimodifikasi
    }

    public <ID_TYPE> void updategejalaXML(
            T updatedObject, String filename,
            ID_TYPE oldId,
            Function<T, ID_TYPE> idExtractor) {
        List<T> existingData = loadFromXML(filename);
        if (existingData == null)
            existingData = new ArrayList<>();

        boolean found = false;
        for (int i = 0; i < existingData.size(); i++) {
            if (idExtractor.apply(existingData.get(i)).equals(oldId)) {
                existingData.set(i, updatedObject);
                found = true;
                break;
            }
        }

        if (!found) {
            existingData.add(updatedObject);
        }

        saveToXML(existingData, filename);
    }

    public <ID_TYPE> void deleteFromXML(T objectToRemove, String filename,
        java.util.function.Function<T, ID_TYPE> idExtractor) {
        List<T> existingData = loadFromXML(filename);
        if (existingData == null) {
            return; // Tidak ada data untuk dihapus
        }

        // Hapus objek berdasarkan ID
        existingData.removeIf(item -> idExtractor.apply(item).equals(idExtractor.apply(objectToRemove)));

        saveToXML(existingData, filename); // Simpan kembali list setelah penghapusan
    }
}
