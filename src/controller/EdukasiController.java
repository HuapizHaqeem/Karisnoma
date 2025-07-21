package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.Edukasi;
import util.XMLService;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EdukasiController {
    @FXML private ListView<String> slideList;
    @FXML private TextArea slideContent;
    private List<Edukasi> slides;
    private int currentIndex = -1;

    @FXML
    public void initialize() {
        XMLService<Edukasi> service = new XMLService<>();
        slides = service.loadFromXML("src/data/edukasi.xml");
        ObservableList<String> titles = FXCollections.observableArrayList();
        if (slides != null) {
            for (Edukasi e : slides) {
                titles.add(e.getJudul());
            }
        }
        slideList.setItems(titles);
        slideList.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.intValue() >= 0) {
                currentIndex = newVal.intValue();
                showSlide(currentIndex);
            }
        });
        if (!titles.isEmpty()) {
            slideList.getSelectionModel().select(0);
        }
    }

    private void showSlide(int idx) {
        if (slides != null && idx >= 0 && idx < slides.size()) {
            slideContent.setText(slides.get(idx).getIsi());
        }
    }

    @FXML
    public void handlePrev() {
        if (currentIndex > 0) {
            currentIndex--;
            slideList.getSelectionModel().select(currentIndex);
            showSlide(currentIndex);
        }
    }

    @FXML
    public void handleNext() {
        if (slides != null && currentIndex < slides.size() - 1) {
            currentIndex++;
            slideList.getSelectionModel().select(currentIndex);
            showSlide(currentIndex);
        }
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) slideContent.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleBack() {
        try {
            Stage stage = (Stage) slideContent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PasienDashboard.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
