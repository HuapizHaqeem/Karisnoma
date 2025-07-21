package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.OpenScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class DashboardDokterController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private ImageView logoImage;

    @FXML
    public void initialize() {

        Circle clip = new Circle();
        clip.setCenterX(logoImage.getFitWidth() / 2);
        clip.setCenterY(logoImage.getFitHeight() / 2);
        clip.setRadius(Math.min(logoImage.getFitWidth(), logoImage.getFitHeight()) / 2);
        logoImage.setClip(clip);

    }

    @FXML
    public void toGejala() throws Exception {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("GejalaView");
        mainPane.setCenter(halaman);

    }

    @FXML
    public void logout() throws Exception {
        Stage stage = (Stage) javafx.stage.Window.getWindows().get(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setMaximized(false);
        stage.setMaximized(true);
    }

    @FXML
    public void toRiwayat() throws Exception {
    OpenScene object = new OpenScene();
    Pane halaman = object.getPane("RiwayatLihatView");
    mainPane.setCenter(halaman);
    
    }

}
