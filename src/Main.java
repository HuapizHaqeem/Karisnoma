import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(loader.load());
        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/4.jpeg")));
        
        stage.setTitle("Karisnoma App");
        stage.setMaximized(true);
        stage.setScene(scene);
        
        scene.getStylesheets().add(getClass().getResource("/Style.css").toExternalForm());
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
