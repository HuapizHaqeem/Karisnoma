package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pengingat;
import util.XMLService;

import java.util.ArrayList;
import java.util.List;

public class AkunController {
    @FXML
    private TableView<Pengingat> tableAkun;
    @FXML
    private TableColumn<Pengingat, String> col1, col2;
    @FXML
    private TextField inputTanggal;
    @FXML
    private ComboBox<String> inputJenis;

    private List<Pengingat> pengingatList;
    private ObservableList<Pengingat> dataPengingat;

    @FXML
    public void initialize() {
        col1.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        col2.setCellValueFactory(new PropertyValueFactory<>("jenis"));

        inputJenis.setItems(FXCollections.observableArrayList("Minum Obat", "Kontrol"));

        XMLService<Pengingat> service = new XMLService<>();
        pengingatList = service.loadFromXML("src/data/pengingat.xml");
        if (pengingatList == null)
            pengingatList = new ArrayList<>();
        dataPengingat = FXCollections.observableArrayList(pengingatList);
        tableAkun.setItems(dataPengingat);
    }

    @FXML
    public void handleUnggah() {
        String tanggal = inputTanggal.getText();
        String jenis = inputJenis.getValue();

        if (tanggal.isEmpty() || jenis == null) {
            showAlert("Tanggal dan jenis pengingat wajib diisi.");
            return;
        }

        Pengingat p = new Pengingat(tanggal, jenis);
        pengingatList.add(p);

        new XMLService<Pengingat>().saveToXML(pengingatList, "src/data/pengingat.xml");

        inputTanggal.clear();
        inputJenis.getSelectionModel().clearSelection();
        dataPengingat = FXCollections.observableArrayList(pengingatList);
        tableAkun.setItems(dataPengingat);
        showAlert("Pengingat berhasil diunggah.");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
