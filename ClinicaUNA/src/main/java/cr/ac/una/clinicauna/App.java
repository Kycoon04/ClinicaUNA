package cr.ac.una.clinicauna;

import cr.ac.una.clinicauna.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,null);
        FlowController.getInstance().goMain();
    }

    public static void main(String[] args) {
        launch();
    }

}