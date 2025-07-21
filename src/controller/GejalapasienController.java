package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Gejala;
import util.XMLService;

import java.util.List;
import java.util.ArrayList;

public class GejalapasienController {

    @FXML private TableView<Gejala> tableGejala;
    @FXML private TableColumn<Gejala, String> colTanggal, colKeluhan, colTindakan;
    @FXML private TextField inputTanggal, inputKeluhan, inputTindakan;

    private List<Gejala> gejalaList;
    private ObservableList<Gejala> dataGejala;

    @FXML
    public void initialize() {
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colKeluhan.setCellValueFactory(new PropertyValueFactory<>("keluhan"));
        colTindakan.setCellValueFactory(new PropertyValueFactory<>("tindakan"));

        XMLService<Gejala> service = new XMLService<>();
        gejalaList = service.loadFromXML("src/data/gejala.xml");
        if (gejalaList == null) gejalaList = new ArrayList<>();

        dataGejala = FXCollections.observableArrayList(gejalaList);
        tableGejala.setItems(dataGejala);
    }

    
}
