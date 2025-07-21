package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Riwayat;
import util.XMLService;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class RiwayatController {

    @FXML
    private TableView<Riwayat> tableRiwayat;
    @FXML
    private TableColumn<Riwayat, String> colDiagnosa, colTerapi, colLab;
    @FXML
    private TextField inputDiagnosa, inputTerapi, inputLab;

    private List<Riwayat> riwayatList;
    private ObservableList<Riwayat> dataRiwayat;

    @FXML
    public void initialize() {
        colDiagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        colTerapi.setCellValueFactory(new PropertyValueFactory<>("terapi"));
        colLab.setCellValueFactory(new PropertyValueFactory<>("hasilLab"));

        XMLService<Riwayat> service = new XMLService<>();
        riwayatList = service.loadFromXML("src/data/riwayat.xml");

        if (riwayatList == null)
            riwayatList = new ArrayList<>();

        dataRiwayat = FXCollections.observableArrayList(riwayatList);
        tableRiwayat.setItems(dataRiwayat);
    }

    @FXML
    public void handleTambahRiwayat() {
        String diagnosa = inputDiagnosa.getText();
        String terapi = inputTerapi.getText();
        String lab = inputLab.getText();

        if (diagnosa.isEmpty() || terapi.isEmpty() || lab.isEmpty())
            return;

        Riwayat r = new Riwayat(diagnosa, terapi, lab);
        riwayatList.add(r);
        dataRiwayat.add(r);

        File folder = new File("data");
        if (!folder.exists())
            folder.mkdirs();

        new XMLService<Riwayat>().saveToXML(riwayatList, "src/data/riwayat.xml");

        inputDiagnosa.clear();
        inputTerapi.clear();
        inputLab.clear();
    }

    @FXML
    public void handleEditRiwayat() {
        Riwayat selected = tableRiwayat.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;
        String diagnosa = inputDiagnosa.getText();
        String terapi = inputTerapi.getText();
        String lab = inputLab.getText();

        if (diagnosa.isEmpty() || terapi.isEmpty() || lab.isEmpty())
            return;
        Riwayat updated = new Riwayat(diagnosa, terapi, lab);
        String oldId = selected.getDiagnosa() + "-" + selected.getTerapi();

        new XMLService<Riwayat>().updategejalaXML(
                updated,
                "src/data/riwayat.xml",
                oldId,
                g -> g.getDiagnosa() + "-" + g.getTerapi());

        int index = tableRiwayat.getItems().indexOf(selected);
        if (index >= 0) {
            tableRiwayat.getItems().set(index, updated);
        }
        inputDiagnosa.clear();
        inputTerapi.clear();
        inputLab.clear();
    }

    @FXML
    public void handlehapusRiwayat() {
        Riwayat selected = tableRiwayat.getSelectionModel().getSelectedItem();
        if (selected == null)
            return;

        new XMLService<Riwayat>().deleteFromXML(
                selected,
                "src/data/riwayat.xml",
                g -> g.getDiagnosa() + "-" + g.getTerapi());
        tableRiwayat.getItems().remove(selected);
    }

}
