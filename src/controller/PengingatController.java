package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import model.Pengingat;
import util.XMLService;

import java.util.List;
import java.util.ArrayList;

public class PengingatController {

    @FXML
    private ListView<Pengingat> listPengingat;
    @FXML
    private PieChart chart;

    private List<Pengingat> pengingatList;
    private ObservableList<Pengingat> observableList;

    @FXML
    public void initialize() {
         XMLService<Pengingat> service = new XMLService<>();
        pengingatList = service.loadFromXML("src/data/pengingat.xml");
        if (pengingatList == null)
            pengingatList = new ArrayList<>();


        observableList = FXCollections.observableArrayList(); 
        for (Pengingat p : pengingatList) {
            if (!p.isSudahDijalankan()) {
                observableList.add(p); 
            }
        }
        
        listPengingat.setItems(observableList);
        int completedCount = 0;
        int pendingCount = 0;

        for (Pengingat p : pengingatList) {
            if (p.isSudahDijalankan()) {
                completedCount++;
            } else {
                pendingCount++;
            }
        }

        ObservableList<PieChart.Data> pieChartDatas = FXCollections.observableArrayList(
                new PieChart.Data("Selesai", completedCount),
                new PieChart.Data("Belum Selesai", pendingCount));

        chart.setData(pieChartDatas);

    }

    @FXML
    public void handleTandaiSudah() {
        int index = listPengingat.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Pengingat target = observableList.get(index);
            target.setSudahDijalankan(true);
            System.out.println("TARGET INDEX: " + index);
            System.out.println("STATUS DI TARGET: " + target.isSudahDijalankan());
            System.out.println("STATUS DI LIST:");

            for (int i = 0; i < pengingatList.size(); i++) {
                System.out.println(i + ": " + pengingatList.get(i).isSudahDijalankan());
            }
            new XMLService<Pengingat>().saveToXML(pengingatList, "src/data/pengingat.xml");
            observableList.remove(index);
            int completedCount = 0;
            int pendingCount = 0;

            for (Pengingat p : pengingatList) {
                if (p.isSudahDijalankan()) {
                    completedCount++;
                } else {
                    pendingCount++;
                }
            }
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Selesai", completedCount),
                    new PieChart.Data("Belum Selesai", pendingCount));

            chart.setData(pieChartData);
        }
    }

}
