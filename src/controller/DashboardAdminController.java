package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.OpenScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DashboardAdminController {
    @FXML
    private BorderPane mainPane;

    @FXML
    public void toRiwayat() throws Exception {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("RiwayatMedisView");
        mainPane.setCenter(halaman);

    }

    @FXML
    public void toAkun() throws Exception {
        OpenScene object = new OpenScene();
        Pane halaman = object.getPane("AkunView");
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
}
