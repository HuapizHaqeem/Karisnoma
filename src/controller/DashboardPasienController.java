package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.OpenScene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javafx.scene.control.Alert;

public class DashboardPasienController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private BorderPane mainContent;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label notificationCount;

    @FXML
    private Label nextAppointment;

    @FXML
    private Label medicationReminders;

    @FXML
    private Label lastCheckup;

    @FXML
    public void initialize() {
        javafx.application.Platform.runLater(() -> {
            if (mainPane != null && mainPane.getScene() != null && mainPane.getScene().getWindow() != null) {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                boolean wasMaximized = stage.isMaximized();
                stage.setMaximized(wasMaximized);
                stage.maximizedProperty().addListener((obs, wasMax, isNowMax) -> {
                    onWindowResizeOrMaximize(isNowMax);
                });
                stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                    onWindowResizeOrMaximize(stage.isMaximized());
                });
                stage.heightProperty().addListener((obs, oldVal, newVal) -> {
                    onWindowResizeOrMaximize(stage.isMaximized());
                });
            }
        });
    }

    private void onWindowResizeOrMaximize(boolean maximized) {
        if (mainPane != null) {
            if (maximized) {
                mainPane.setStyle("-fx-padding: 30; -fx-font-size: 18;");
            } else {
                mainPane.setStyle("-fx-padding: 15; -fx-font-size: 14;");
            }
        }
    }

    @FXML
    private void handleMinimize() {
        if (mainPane != null && mainPane.getScene() != null && mainPane.getScene().getWindow() != null) {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setIconified(true);
        }
    }

    @FXML
    private void handleMaximize() {
        if (mainPane != null && mainPane.getScene() != null && mainPane.getScene().getWindow() != null) {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        } else {
            showError("Cannot maximize: window is not available.");
        }
    }

    @FXML
    private void handleClose() {
        if (mainPane != null && mainPane.getScene() != null && mainPane.getScene().getWindow() != null) {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void toEdukasi() {
        try {
            OpenScene object = new OpenScene();
            Pane halaman = object.getPane("EdukasiView");
            mainContent.setCenter(halaman);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to load education view");
        }
    }

    @FXML
    public void toGejala() {
        try {
            OpenScene object = new OpenScene();
            Pane halaman = object.getPane("GejalapasienView");
            mainContent.setCenter(halaman);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to load symptoms view");
        }
    }

    @FXML
    public void toPengingat() {
        try {
            OpenScene object = new OpenScene();
            Pane halaman = object.getPane("PengingatView");
            mainContent.setCenter(halaman);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to load reminders view");
        }
    }

    @FXML
    public void logout() {
        try {
            Stage stage = null;
            if (mainPane != null && mainPane.getScene() != null && mainPane.getScene().getWindow() != null) {
                stage = (Stage) mainPane.getScene().getWindow();
                stage.setMaximized(false);
                stage.setMaximized(true);
            } else {
                for (Window window : Stage.getWindows()) {
                    if (window.isShowing() && window instanceof Stage) {
                        stage = (Stage) window;
                        stage.setMaximized(false);
                        stage.setMaximized(true);
                        break;
                    }
                }
            }
            if (stage != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.setScene(scene);
                stage.setMaximized(false);
                stage.setMaximized(true);
            } else {
                showError("Cannot logout: no window found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to logout");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
