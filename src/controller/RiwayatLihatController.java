package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Riwayat;
import util.XMLService;

import java.util.ArrayList;
import java.util.List;

public class RiwayatLihatController {

    @FXML
    private TableView<Riwayat> tableRiwayat;
    @FXML
    private TableColumn<Riwayat, String> colDiagnosa, colTerapi, colLab;

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
    }

