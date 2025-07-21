package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Gejala;
import util.XMLService;

import java.util.List;
import java.util.ArrayList;

public class GejalaController {

    @FXML
    private TableView<Gejala> tableGejala;
    @FXML
    private TableColumn<Gejala, String> colTanggal, colKeluhan, colTindakan;
    @FXML
    private TextField inputTanggal, inputKeluhan, inputTindakan;

    private List<Gejala> gejalaList;
    private ObservableList<Gejala> dataGejala;

    @FXML
    public void initialize() {
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colKeluhan.setCellValueFactory(new PropertyValueFactory<>("keluhan"));
        colTindakan.setCellValueFactory(new PropertyValueFactory<>("tindakan"));

        XMLService<Gejala> service = new XMLService<>();
        gejalaList = service.loadFromXML("src/data/gejala.xml");
        if (gejalaList == null)
            gejalaList = new ArrayList<>();

        dataGejala = FXCollections.observableArrayList(gejalaList);
        tableGejala.setItems(dataGejala);
    }

    @FXML
    public void handleTambah() {
        String tgl = inputTanggal.getText();
        String keluhan = inputKeluhan.getText();
        String tindakan = inputTindakan.getText();

        if (tgl.isEmpty() || keluhan.isEmpty() || tindakan.isEmpty())
            return;

        Gejala g = new Gejala(tgl, keluhan, tindakan);
        gejalaList.add(g);
        dataGejala.add(g);

        new XMLService<Gejala>().saveToXML(gejalaList, "src/data/gejala.xml");

        inputTanggal.clear();
        inputKeluhan.clear();
        inputTindakan.clear();
    }

    @FXML
    public void handleedit() {
        Gejala selected = tableGejala.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        String tgl = inputTanggal.getText();
        String keluhan = inputKeluhan.getText();
        String tindakan = inputTindakan.getText();

        if (tgl.isEmpty() || keluhan.isEmpty() || tindakan.isEmpty())
            return;

        Gejala updated = new Gejala(tgl, keluhan, tindakan);
        String oldId = selected.getTanggal() + "-" + selected.getKeluhan();

        new XMLService<Gejala>().updategejalaXML(
                updated,
                "src/data/gejala.xml",
                oldId,
                g -> g.getTanggal() + "-" + g.getKeluhan());

        int index = tableGejala.getItems().indexOf(selected);
        if (index >= 0) {
            tableGejala.getItems().set(index, updated);
        }

        inputTanggal.clear();
        inputKeluhan.clear();
        inputTindakan.clear();
    }

    @FXML
    public void handlehapus() {
        Gejala selected = tableGejala.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        new XMLService<Gejala>().deleteFromXML(
                selected,
                "src/data/gejala.xml",
                g -> g.getTanggal() + "-" + g.getKeluhan());
        tableGejala.getItems().remove(selected);
    }

    @FXML
    public void handleBack() {
        try {
            Stage stage = (Stage) tableGejala.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DokterDashboard.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setMaximized(false);
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
