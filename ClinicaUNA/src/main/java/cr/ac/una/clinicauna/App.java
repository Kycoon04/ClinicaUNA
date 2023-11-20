package cr.ac.una.clinicauna;

import cr.ac.una.clinicauna.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String idiom=Locale.getDefault().getLanguage();
        System.out.println(""+idiom);
        if(idiom.equals("es")){
            FlowController.getInstance().InitializeFlow(stage,ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
        }else if(idiom.equals("fr")){
            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/French"));
        }else if(idiom.equals("en")){
            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/English"));
        }else{
           FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Japanese"));
        }
        
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/clinicauna/resources/LogoMedicalClinic.png")));
        FlowController.getInstance().goMain("LoginView");
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