package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.DiaryService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;

import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.input.KeyCode;
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
    private boolean pass = false;

    String idiomInterface="Spanish";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginView.toFront();
        choiceBoxIdioms.getItems().addAll(Spanish);
        userRegisField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        idRegisField.setTextFormatter(Formato.getInstance().integerFormat());
        surname1RegisField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        surname2RegisField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        usernameRegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        emailRegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        emailRecoverField.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        AceptRecoverField.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        passwordRegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        password2RegisField.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
         System.out.println(""+FlowController.getInstance().getIdioma());
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

    private void saveUser(UserDto userDtop) {
        Respuesta respuesta = new Respuesta();
        try {
            UserService service = new UserService();
            if (pass == true) {
                respuesta = service.saveUser(userDtop);
            }
            this.userDto = (UserDto) respuesta.getResultado("User");
            if (userDtop != null) {
                if (userDto.getUsLenguage() != "") {
                    multiLenguage("ユーザーが正常に保存されました。", "Usuario guardado correctamente.", "User saved successfully.", "Utilisateur enregistré avec succès.");
                    //email
                } else {
                    if (idiomInterface.equals("Spanish")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Usuario", getStage(), "Usuario guardado");
                    } else if (idiomInterface.equals("English")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save User", getStage(), "Saved user");
                    } else if (idiomInterface.equals("French")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Enregistrer l'utilisateur", getStage(), "Utilisateur enregistré");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "ユーザーを保存する", getStage(), "保存されたユーザー");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            if (idiomInterface.equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), "Error al guardar");
            } else if (idiomInterface.equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Save User", getStage(), "Error when saving");
            } else if (idiomInterface.equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Enregistrer l'utilisateur", getStage(), "Erreur lors de l'enregistrement");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "ユーザーを保存する", getStage(), "保存時のエラー");
            }
        }
    }

    private void multiLenguage(String l1, String l2, String l3, String l4) {
        if (userDto.getUsLenguage() != "") {
            if (userDto.getUsLenguage().equals("Japanese")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "ユーザー保存", getStage(), l1);
            }
            if (userDto.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Usuario", getStage(), l2);
            }
            if (userDto.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save User", getStage(), l3);
            }
            if (userDto.getUsLenguage().equals("French")) {
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
                            getUsLenguage();
                        }
                    }
                } else {
                    ChoiceIdiom(this.userDto.getUsLenguage(), "L'utilisateur n'est pas actif", "The user is not active", "El usuario no esta activo", "ユーザーはアクティブではありません");
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

            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
            FlowController.getInstance().goMain("ViewMaintenanceOptions");
        } else if (this.userDto.getUsLenguage().equals("English")) {

            FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/English"));
            FlowController.getInstance().goMain("ViewMaintenanceOptions");
        } else if (this.userDto.getUsLenguage().equals("French")) {

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
        if (event.getCode() == KeyCode.ENTER) {
            login(usernameField.getText(), passwordField.getText());
        }

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
        choiceBoxIdioms.setValue(null);

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

    private void ChoiceIdiom(String idiom, String l1, String l2, String l3, String l4) {

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
        if (choiceBoxIdioms.getValue().equals("Espagnol") || choiceBoxIdioms.getValue().equals("Spanish") || choiceBoxIdioms.getValue().equals("Español") || choiceBoxIdioms.getValue().equals("スペイン語")) {
            idiom = "Spanish";
            idiomInterface = "Spanish";
            user.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Anglais") || choiceBoxIdioms.getValue().equals("English") || choiceBoxIdioms.getValue().equals("Inglés") || choiceBoxIdioms.getValue().equals("英語")) {
            idiom = "English";
            idiomInterface = "English";
            user.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Francais") || choiceBoxIdioms.getValue().equals("France") || choiceBoxIdioms.getValue().equals("Francés") || choiceBoxIdioms.getValue().equals("フランス語")) {
            idiom = "French";
            idiomInterface = "French";
            user.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Japonais") || choiceBoxIdioms.getValue().equals("Japanese") || choiceBoxIdioms.getValue().equals("Japonés") || choiceBoxIdioms.getValue().equals("日本語")) {
            idiom = "Japanese";
            idiomInterface = "Japanese";
            user.setUsLenguage(idiom);
        }
        user.setUsIdentification(idRegisField.getText());
        if (passwordRegisField.getText().equals(password2RegisField.getText())) {
            user.setUsPassword(passwordRegisField.getText());
            pass = true;
        } else {
            ChoiceIdiom(idiom, "Différents mots de passe", "Different passwords", "Contraseña Distinta", "異なるパスワード");
            pass = false;
        }

        return user;
    }

    @FXML
    private void changeIdiomSpanish(MouseEvent event) {
        FlowController.setIdioma(ResourceBundle.getBundle("cr/ac/una/clinicauna/idioms/Spanish"));
        FlowController.getInstance().goMain("LoginView");
        choiceBoxIdioms.getItems().clear();
        choiceBoxIdioms.getItems().addAll(Spanish);
        idiomInterface = "Spanish";
        FlowController.getInstance().setIdioma(idiomInterface);
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
        idiomInterface = "English";
        FlowController.getInstance().setIdioma(idiomInterface);
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
        FlowController.getInstance().setIdioma(idiomInterface);
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
       FlowController.getInstance().setIdioma(idiomInterface);
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
           if (idiomInterface.equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validar campos", getStage(), "Campos incompletos");
            } else if (idiomInterface.equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validate Fields", getStage(), "Incomplete Fields");
            } else if (idiomInterface.equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Valider les champs", getStage(), "Champs incomplets");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "フィールドを検証する", getStage(), "不完全なフィールド");
            }
        } else {
            Respuesta resp=new Respuesta();
            resp=service.ResetTemp(emailRecoverField.getText(), pass);
            if(resp.getEstado()){
                 if (idiomInterface.equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Contraseña temporal", getStage(), "Contraseña enviada a su correo. Volver al loguearse.");
                } else if (idiomInterface.equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Temporal password", getStage(), "Password sent to your email. Return to login.");
                } else if (idiomInterface.equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Mot de passe temporaire", getStage(), "Mot de passe envoyé à votre email. Revenez à la connexion.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "仮パスワード", getStage(), "パスワードがメールに送信されました。ログインに戻ります。");
                }
            }else{
                 if (idiomInterface.equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Contraseña temporal", getStage(), "Error al enviar contraseña temporal.");
                } else if (idiomInterface.equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Temporal password", getStage(), "Error sending temporary password.");
                } else if (idiomInterface.equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Mot de passe temporaire", getStage(), "Erreur lors de l'envoi du mot de passe temporaire.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "仮パスワード", getStage(), "一時パスワードの送信中にエラーが発生しました。");
                }
            }

        }
    }

    @FXML
    private void AceptPassword(ActionEvent event) {
        UserService service = new UserService();

        if (!validateRequired(required1)) {
            if (idiomInterface.equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validar campos", getStage(), "Campos incompletos");
            } else if (idiomInterface.equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validate Fields", getStage(), "Incomplete Fields");
            } else if (idiomInterface.equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Valider les champs", getStage(), "Champs incomplets");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "フィールドを検証する", getStage(), "不完全なフィールド");
            }
        } else {
            if (getUserName(usernameField.getText()) == "") {

                if (idiomInterface.equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Cambio de contraseña", getStage(), "Undone");
                } else if (idiomInterface.equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Change of password", getStage(), "Incomplete Fields");
                } else if (idiomInterface.equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Changement de mot de passe", getStage(), "Défait");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "パスワードの変更", getStage(), "元に戻す");
                }
            } else {
                service.resetAccontPassword(getUserName(usernameField.getText()), AceptRecoverField.getText());

                if (idiomInterface.equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Cambio de contraseña", getStage(), "Realizado");
                } else if (idiomInterface.equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Change of password", getStage(), "Completed");
                } else if (idiomInterface.equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Changement de mot de passe", getStage(), "Fait");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "パスワードの変更", getStage(), "終わり");
                }
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
