package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.akun;
import util.XMLService;

public class LupaController {
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPassword;

    @FXML
    private ImageView logoImage;

    private List<akun> akunList;
    private ObservableList<akun> dataakun;
    private String filename = "src/data/Akun.xml";

    @FXML
    public void initialize() {
        XMLService<akun> service = new XMLService<>();
        akunList = service.loadFromXML("src/data/akun.xml");
        dataakun = FXCollections.observableArrayList(akunList);

        Circle clip = new Circle();
        clip.setCenterX(logoImage.getFitWidth() / 2);
        clip.setCenterY(logoImage.getFitHeight() / 2);
        clip.setRadius(Math.min(logoImage.getFitWidth(), logoImage.getFitHeight()) / 2);
        logoImage.setClip(clip);
        
        
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    public void handleLogin() {
        String user = inputUsername.getText();
        String pass = inputPassword.getText();
        String eml = inputEmail.getText();

        if (user.isEmpty() || pass.isEmpty() || eml.isEmpty())
            return;

        akun loggedInAkun = null;
        
        for (akun a : dataakun) {
            if (a.getUsername().equals(user) && a.getEmail().equals(eml)) {
                loggedInAkun = a;
                break;
            }
        }

            if (loggedInAkun != null) {
                loggedInAkun.setPassword(pass);
                XMLService<akun> akunService = new XMLService<>();

                akunService.updateXML(loggedInAkun, filename, akun::getUsername);
                showAlert("Password berhasil dirubah, silahkan kembali");
                inputPassword.clear();
                inputUsername.clear();
                inputEmail.clear();
            } else {
                showAlert("Email dan username tidak cocok,tidak dapat mengubah password");
                inputPassword.clear();
                inputUsername.clear();
                inputEmail.clear();
                return;
            }
        } 

    private void changeScene(String fxmlPath) throws Exception {
        Stage stage = (Stage) javafx.stage.Window.getWindows().get(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        stage.setScene(new Scene(loader.load()));
        stage.setMaximized(false);
        stage.setMaximized(true);
    }

    @FXML
    public void toLogin() throws Exception {
        changeScene("/fxml/Login.fxml");
    }
}
