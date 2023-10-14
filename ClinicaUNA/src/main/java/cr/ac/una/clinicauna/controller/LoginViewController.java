package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.Email;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 *
 * @author jomav
 */
public class LoginViewController extends Controller implements Initializable {

    UserDto userDto;
    private List<UserDto> usersList;

    DoctorDto doctorDto;
    private List<DoctorDto> doctorList;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private BorderPane RegisterView;
    @FXML
    private TextField userRegisField;
    @FXML
    private TextField surname1RegisField;
    @FXML
    private TextField surname2RegisField;
    @FXML
    private TextField usernameRegisField;
    @FXML
    private TextField emailRegisField;
    @FXML
    private TextField idRegisField;
    @FXML
    private TextField passwordRegisField;
    @FXML
    private TextField password2RegisField;
    @FXML
    private BorderPane loginView;

    DiaryDto diaryDto;
    private List<DiaryDto> diaryList;

    DiseaseDto diseaseDto;
    private List<DiseaseDto> diseaseList;

    ProceedingsDto proceedingsDto;
    private List<ProceedingsDto> ProceedingsList;
    @FXML
    private VBox VboxChangeIdioms;
    @FXML
    private BorderPane RecoverView;
    @FXML
    private TextField emailRecoverField;
    @FXML
    private BorderPane RecoverFinalView;
    @FXML
    private TextField AceptRecoverField;
    @FXML
    private ChoiceBox<String> choiceBoxIdioms;
    String[] Spanish = {"Español", "Inglés", "Francés", "Japonés"};
    String[] English = {"Spanish", "English", "France", "Japonese"};
    String[] French = {"Espagnol", "Anglais", "Francais", "Japonais"};
    String[] Japanesse = {"スペイン語", "英語", "フランス語", "日本語"};

    List<Node> required = new ArrayList<>();
    List<Node> required1 = new ArrayList<>();
    List<Node> required2 = new ArrayList<>();
    private boolean pass= false; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBoxIdioms.getItems().addAll(Spanish);
        userRegisField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        idRegisField.setTextFormatter(Formato.getInstance().integerFormat());
        surname1RegisField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        surname2RegisField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        usernameRegisField.setTextFormatter(Formato.getInstance().letrasFormat(20));
        emailRegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        emailRecoverField.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        AceptRecoverField.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        passwordRegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        password2RegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        IndicateRequired();
    }

    @Override
    public void initialize() {

    }

    public boolean validateRequired(List<Node> re) {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : re) {
            if (node instanceof TextField && ((TextField) node).getText().isBlank()) {
                if (validos) {
                    invalidos += ((TextField) node).getPromptText();
                } else {
                    invalidos += "," + ((TextField) node).getPromptText();
                }
                return false;
            } else if (node instanceof PasswordField && ((PasswordField) node).getText().isEmpty()) {
                if (validos) {
                    invalidos += ((PasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((PasswordField) node).getPromptText();
                }
                return false;
            }
        }
        return true;
    }

    private void IndicateRequired() {
        required.clear();
        required.addAll(Arrays.asList(userRegisField, surname1RegisField, idRegisField, passwordRegisField, password2RegisField));
        required1.clear();
        required1.add(AceptRecoverField);
        required2.clear();
        required2.add(emailRecoverField);
    }

    private void saveUser(UserDto userDto) {
       Respuesta respuesta= new Respuesta();
        try {
            UserService service = new UserService();
            if(pass==true){
            respuesta = service.saveUser(userDto);
              }
            this.userDto = (UserDto) respuesta.getResultado("User");
            if (userDto != null) {
                if (userDto.getUsLenguage() != "") {
                    multiLenguage("ユーザーが正常に保存されました。","Usuario guardado correctamente.","User saved successfully.","Utilisateur enregistré avec succès.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Usuario", getStage(), "Usuario guardado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), "Ocurrió un error al guardar el usuario.");
        }
    }

    private void multiLenguage(String l1,String l2, String l3, String l4) {
        if(userDto.getUsLenguage()!=""){
        if ( userDto.getUsLenguage().equals("Japanese") ) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "ユーザー保存", getStage(), l1);
        }
        if ( userDto.getUsLenguage() .equals("Spanish") ) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Usuario", getStage(),  l2);
        }
        if ( userDto.getUsLenguage().equals("English") ) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save User", getStage(),  l3);
        }
        if ( userDto.getUsLenguage() .equals("French") ) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Enregistrer l'utilisateur", getStage(), l4);
        }
        }
    }

    private void login(String name, String password) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.getUser(name, password);
            if (respuesta.getEstado()) {
                this.userDto = (UserDto) respuesta.getResultado("User");
                AppContext.getInstance().set("Token", userDto.getToken());
                AppContext.getInstance().set("Usuario", userDto);
                if (service.isActive(name, password)) {
                    if (service.isTempPass(name, password)) {
                        RecoverFinalView.toFront();
                    } else {
                        if (service.isAdmin(name, password)) {
                            getUsLenguage();
                        } else {
                            System.out.println("sfedsdfsdf");
                        }
                    }
                } else {
                    ChoiceIdiom(this.userDto.getUsLenguage(),"L'utilisateur n'est pas actif","The user is not active", "El usuario no esta activo","ユーザーはアクティブではありません" );
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación Usuario", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {

            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el usuarios.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleado", getStage(), "Ocurrio un error consultando el usuarios.");
        }
    }

    private void getUsLenguage() {
        if (this.userDto.getUsLenguage().equals("Spanish")) {
            System.out.println("French");
            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
            FlowController.getInstance().goMain("ViewMaintenanceOptions");
        } else if (this.userDto.getUsLenguage().equals("English")) {
            System.out.println("English");
            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/English"));
            FlowController.getInstance().goMain("ViewMaintenanceOptions");
        } else if (this.userDto.getUsLenguage().equals("French")) {
            System.out.println("French");
            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/French"));
            FlowController.getInstance().goMain("ViewMaintenanceOptions");
        } else if (this.userDto.getUsLenguage().equals("Japanese")) {
            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Japanese"));
            FlowController.getInstance().goMain("ViewMaintenanceOptions");
        }
    }

    @FXML
    private void PasswordForget(MouseEvent event) {
        RecoverView.toFront();
    }

    @FXML
    private void AcceptLogin(ActionEvent event) {
        login(usernameField.getText(), passwordField.getText());
    }

    @FXML
    private void Register(ActionEvent event) {
        RegisterView.toFront();
    }

    @FXML
    private void AcceptLoginEnter(KeyEvent event) {

    }

    public void cleanScreen() {
        usernameField.setText("");
        surname1RegisField.setText("");
        surname2RegisField.setText("");
        usernameRegisField.setText("");
        emailRegisField.setText("");
        idRegisField.setText("");
        passwordRegisField.setText("");
        password2RegisField.setText("");
        emailRecoverField.setText("");
        passwordField.setText("");
        userRegisField.setText("");
    }

    @FXML
    private void Back(ActionEvent event) {
        cleanScreen();
        loginView.toFront();
    }

    @FXML
    private void ConfirmRegister(ActionEvent event) {
        if (!validateRequired(required)) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Validar campos", getStage(), "campos inccompletos");
        } else {
          
            saveUser(bindNewUser());
            
        }
    }

      private void ChoiceIdiom(String idiom, String l1,String l2,String l3,String l4) {
          System.out.println("sfddfsdfsdfsdfsfsdfsdfs");
          System.out.println("");
        if (idiom != "") {
            if (idiom == "French") {
                new Mensaje().showModal(Alert.AlertType.ERROR, "v", getStage(), l1);
                  System.out.println(idiom);
            }
            if (idiom == "English") {
                new Mensaje().showModal(Alert.AlertType.ERROR, "v", getStage(), l2);
            }
            if (idiom == "Spanish") {
                new Mensaje().showModal(Alert.AlertType.ERROR, "v", getStage(), l3);
            }
            if (idiom == "Japanese") {
                new Mensaje().showModal(Alert.AlertType.ERROR, "v", getStage(), l4);
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "v", getStage(), l3);
        }

    }
      
      
    UserDto bindNewUser() {
        String idiom = "";
        UserDto user = new UserDto();
        user.setUsName(userRegisField.getText());
        user.setUsPlastname(surname1RegisField.getText());
        user.setUsSlastname(surname2RegisField.getText());
        user.setUsUsername(usernameRegisField.getText());
        user.setUsEmail(emailRegisField.getText());
        user.setUsState("I");
        user.setUsType("Default");
        user.setUsRecover("N");
        String code = CodeRamdon();
        user.setUsCode(code);
        if (choiceBoxIdioms.getValue().equals("Espagnol") || choiceBoxIdioms.getValue().equals("Spanish") || choiceBoxIdioms.getValue().equals("Español")|| choiceBoxIdioms.getValue().equals("スペイン語")) {
            idiom = "Spanish";
            user.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Anglais") || choiceBoxIdioms.getValue().equals("English") || choiceBoxIdioms.getValue().equals("Inglés")|| choiceBoxIdioms.getValue().equals("英語")) {
            idiom = "English";
            user.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Francais") || choiceBoxIdioms.getValue().equals("France") || choiceBoxIdioms.getValue().equals("Francés")|| choiceBoxIdioms.getValue().equals("フランス語")) {
            idiom = "French";
            user.setUsLenguage(idiom);
        }
         if (choiceBoxIdioms.getValue().equals("Japonais") || choiceBoxIdioms.getValue().equals("Japonese") || choiceBoxIdioms.getValue().equals("Japonés")|| choiceBoxIdioms.getValue().equals("日本語")) {
            idiom = "Japonese";
            user.setUsLenguage(idiom);
        }
        user.setUsIdentification(idRegisField.getText());
        if (passwordRegisField.getText().equals(password2RegisField.getText())) {
            user.setUsPassword(passwordRegisField.getText());
            pass=true; 
        } else {
          ChoiceIdiom(idiom,"Différents mots de passe","Different passwords","Contraseña Distinta","異なるパスワード");
          pass=false; 
        }
        Email email;
        email = new Email(emailRegisField.getText(), userRegisField.getText() + " " + surname1RegisField.getText(), "Activacion de usuario");
        email.envioDeCorreos("http://localhost:8080/WsClinicaUNA/Activacion.html?Code=" + code);
        return user;
    }

    @FXML
    private void changeIdiomSpanish(MouseEvent event) {
        FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
        FlowController.getInstance().goMain("LoginView");
        choiceBoxIdioms.getItems().clear();
        choiceBoxIdioms.getItems().addAll(Spanish);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(VboxChangeIdioms);
        slide.setToY(-176);
        slide.play();
        VboxChangeIdioms.setTranslateY(0);
        slide.setOnFinished((ActionEvent e) -> {
            VboxChangeIdioms.setVisible(false);
        });

    }

    @FXML
    private void changeIdiomEnglish(MouseEvent event) {
        FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/English"));
        FlowController.getInstance().goMain("LoginView");
        choiceBoxIdioms.getItems().clear();
        choiceBoxIdioms.getItems().addAll(English);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(VboxChangeIdioms);
        slide.setToY(-176);
        slide.play();
        VboxChangeIdioms.setTranslateY(0);
        slide.setOnFinished((ActionEvent e) -> {
            VboxChangeIdioms.setVisible(false);
        });
    }

    @FXML
    private void changeIdiomFrench(MouseEvent event) {
        FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/French"));
        FlowController.getInstance().goMain("LoginView");
        choiceBoxIdioms.getItems().clear();
        choiceBoxIdioms.getItems().addAll(French);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(VboxChangeIdioms);
        slide.setToY(-176);
        slide.play();
        VboxChangeIdioms.setTranslateY(0);
        slide.setOnFinished((ActionEvent e) -> {
            VboxChangeIdioms.setVisible(false);
        });
    }

    @FXML
    private void changeIdiomJapanese(MouseEvent event) {
        FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Japanese"));
        FlowController.getInstance().goMain("LoginView");
        choiceBoxIdioms.getItems().clear();
        choiceBoxIdioms.getItems().addAll(Japanesse);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(VboxChangeIdioms);
        slide.setToY(-176);
        slide.play();
        VboxChangeIdioms.setTranslateY(0);
        slide.setOnFinished((ActionEvent e) -> {
            VboxChangeIdioms.setVisible(false);
        });

    }

    @FXML
    private void openChangeIdiom(MouseEvent event) {
        if (!VboxChangeIdioms.isVisible()) {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(VboxChangeIdioms);
            slide.setToY(0);
            slide.play();
            VboxChangeIdioms.setTranslateY(-176);
            slide.setOnFinished((ActionEvent e) -> {
                VboxChangeIdioms.setVisible(true);
            });
        } else {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(VboxChangeIdioms);
            slide.setToY(-176);
            slide.play();
            VboxChangeIdioms.setTranslateY(0);
            slide.setOnFinished((ActionEvent e) -> {
                VboxChangeIdioms.setVisible(false);
            });
        }
    }

    @FXML
    private void RecoverPassword(ActionEvent event) {
        UserService service = new UserService();
        String pass = PasswordRamdon();
        if (!validateRequired(required2)) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Validar campos", getStage(), "campos incompletos");
        } else {
            service.ResetTemp(emailRecoverField.getText(), pass);
            Email email;
            email = new Email(emailRecoverField.getText(), userRegisField.getText() + " " + surname1RegisField.getText(), "Recuperar contrañesa");
            email.envioCmbClave(pass);
        }
    }

    @FXML
    private void AceptPassword(ActionEvent event) {
        UserService service = new UserService();

        if (!validateRequired(required1)) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Validar campos", getStage(), "campos incompletos");
        } else {
            if (getUserName(usernameField.getText()) == "") {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reseteo", getStage(), "Sin exito");
            } else {
                service.resetAccontPassword(getUserName(usernameField.getText()), AceptRecoverField.getText());
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reseteo", getStage(), "Con exito");
            }
        }
    }

    private String getUserName(String user) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.getUserName(user);
            this.userDto = (UserDto) respuesta.getResultado("User");

        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el empleado.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleado", getStage(), "Ocurrio un error consultando el empleado.");
        }
        return userDto.getUsEmail();
    }

    String CodeRamdon() {
        char c1;
        String s = "";
        int i, r;
        for (i = 0; i < 14; i++) {
            r = (int) (Math.random() * (90 - 48 + 1) + 48);
            if ((r > 47 && r < 58) || (r > 64 && r < 91)) {
                c1 = (char) r;
                s += c1;
            } else {
                i--;
            }
        }
        return s;
    }

    String PasswordRamdon() {
        char c1;
        String s = "";
        int i, r;
        for (i = 0; i < 6; i++) {
            r = (int) (Math.random() * (90 - 48 + 1) + 48);
            if ((r > 47 && r < 58) || (r > 64 && r < 91)) {
                c1 = (char) r;
                s += c1;
            } else {
                i--;
            }
        }
        return s;
    }

}
