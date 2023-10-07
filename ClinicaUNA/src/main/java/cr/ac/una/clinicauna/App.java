package cr.ac.una.clinicauna;

import cr.ac.una.clinicauna.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
        FlowController.getInstance().goMain();
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginView.fxml"));
        loader.setResources(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1200, 700));
        stage.show();*/
    }

    public static void main(String[] args) {
        launch();
    }

}