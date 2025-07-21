package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.akun;
import util.XMLService;

public class SiginController {
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private PasswordField inputPassword1;
    @FXML
    private ComboBox<String> inputRole;
    @FXML
    private ImageView logoImage;

    private List<akun> akunList;
    private ObservableList<akun> dataakun;

    @FXML
    public void initialize() {
        inputRole.setItems(FXCollections.observableArrayList("Pasien", "Dokter"));
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
        String passk = inputPassword1.getText();
        String eml = inputEmail.getText();
        String role = inputRole.getValue();

        if (user.isEmpty() || pass.isEmpty() || eml.isEmpty() || role == null)
            return;

        akun loggedInAkun = null;
        
        for (akun a : dataakun) {
            if (a.getUsername().equals(user) && a.getPeran().equals(role) || 
               (a.getEmail() != null && a.getEmail().equals(eml))) {
                loggedInAkun = a;
                break;
            }
             
        }

        if (pass.equals(passk)) {
            if (loggedInAkun == null) {
                akun p = new akun(user, pass, role, eml);
                akunList.add(p);
                new XMLService<akun>().saveToXML(akunList, "src/data/Akun.xml");
                showAlert("Akun berhasil dibuat, silahkan kembali");
            } else {
                showAlert("sig in gagal. Username / email Akun sudah ada.");
                inputPassword.clear();
                inputUsername.clear();
                inputPassword1.clear();
                inputEmail.clear();
                return;
            }
        } else {
            showAlert("password dan konfirm password tidak sama");
            inputPassword1.clear();
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
