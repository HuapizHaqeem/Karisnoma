package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.akun;
import util.XMLService;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginController {

    @FXML
    private TextField inputUsername;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private ComboBox<String> inputRole;
    @FXML
    private ImageView logoImage;
    @FXML
    private ImageView backgroundImage;

    private List<akun> akunList;
    private ObservableList<akun> dataakun;

    @FXML
    public void initialize() {
        inputRole.setItems(FXCollections.observableArrayList("Pasien", "Dokter", "Admin"));
        XMLService<akun> service = new XMLService<>();
        akunList = service.loadFromXML("src/data/akun.xml");
        dataakun = FXCollections.observableArrayList(akunList);
        Platform.runLater(() -> {
            if (backgroundImage != null && inputUsername != null && inputUsername.getScene() != null) {
                AnchorPane root = (AnchorPane) inputUsername.getScene().getRoot();
                backgroundImage.fitWidthProperty().bind(root.widthProperty());
                backgroundImage.fitHeightProperty().bind(root.heightProperty());
            }
        });
    }

    @FXML
    private void handleMinimize() {
        Stage stage = (Stage) inputUsername.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximize() {
        Stage stage = (Stage) inputUsername.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) inputUsername.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void changeScene(String fxmlPath) throws Exception {
        Stage stage = (Stage) inputUsername.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
    }

    @FXML
    public void handleLogin() {
        String user = inputUsername.getText();
        String pass = inputPassword.getText();
        String role = inputRole.getValue();

        if (user.isEmpty() || pass.isEmpty() || role == null) {
            showAlert(Alert.AlertType.WARNING, "Login Error", "Please fill in all fields");
            return;
        }

        akun loggedInAkun = null;
        for (akun a : dataakun) {
            if (a.getUsername().equals(user) && a.getPassword().equals(pass) && a.getPeran().equals(role)) {
                loggedInAkun = a;
                break;
            }
        }

        if (loggedInAkun == null) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, password, or role.");
            inputPassword.clear();
            return;
        }

        try {
            Stage stage = (Stage) inputUsername.getScene().getWindow();
            FXMLLoader loader = null;

            String peranActual = loggedInAkun.getPeran();
            if (peranActual.equals("Admin")) {
                loader = new FXMLLoader(getClass().getResource("/fxml/AdminDashboard.fxml"));
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.setScene(scene);
                stage.setMaximized(false);
                stage.setMaximized(true);

            } else if (peranActual.equals("Dokter")) {
                loader = new FXMLLoader(getClass().getResource("/fxml/DokterDashboard.fxml"));
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.setScene(scene);
                stage.setMaximized(false);
                stage.setMaximized(true);

            } else if (peranActual.equals("Pasien")) {
                loader = new FXMLLoader(getClass().getResource("/fxml/PasienDashboard.fxml"));
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
                stage.setScene(scene);
                stage.setMaximized(false);
                stage.setMaximized(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the dashboard.");
        }
    }

    @FXML
    public void daftar() {
        try {
            changeScene("/fxml/Sigin.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the registration form.");
        }
    }

    @FXML
    public void lupa() {
        try {
            changeScene("/fxml/Lupa.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the password recovery form.");
        }
    }
}
