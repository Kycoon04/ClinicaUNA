package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.DiseaseService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.PatientService;
import cr.ac.una.clinicauna.service.ProceedingsService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class ViewMaintenanceOptionsController extends Controller implements Initializable {

    @FXML
    private BorderPane OptionsMenuView;
    @FXML
    private BorderPane OptionsMainDoctorsView;
    @FXML
    private BorderPane OptionsMainUsersView;
    @FXML
    private TextField userMainField;
    @FXML
    private TextField psurnameMainField;
    @FXML
    private TextField ssurnameMainField;
    @FXML
    private TextField identMainField;
    @FXML
    private TextField usernameMainField;
    @FXML
    private TextField emailMainField;
    @FXML
    private TextField textFieldSearch_Name;
    @FXML
    private TextField textFieldSearch_Pusername;
    @FXML
    private TextField textFieldSearch_Rol;
    @FXML
    private TextField textFieldSearch_Susername;
    @FXML
    private TextField textFieldSearch_State;
    @FXML
    private TableView<UserDto> tableViewUser;
    @FXML
    private TableColumn<UserDto, String> tableColAct;
    @FXML
    private TableColumn<UserDto, String> tableColIdentif;
    @FXML
    private TableColumn<UserDto, String> tableColName;
    @FXML
    private TableColumn<UserDto, String> tableColPsurname;
    @FXML
    private TableColumn<UserDto, String> tableColSsurname;
    @FXML
    private TableColumn<UserDto, String> tableColUserName;
    @FXML
    private TableColumn<UserDto, String> tableColEmail;
    @FXML
    private TableColumn<UserDto, String> tableColAdmi;
    @FXML
    private Button BtndeleteWorker;
    @FXML
    private Tab tabMantUsers;
    @FXML
    private Tab tabManDoctors;
    @FXML
    private TextField codeDocMainField;
    @FXML
    private TextField licenseDocMainField;
    @FXML
    private TextField folioDocMainField;
    @FXML
    private JFXTimePicker timepickerIniWork;
    @FXML
    private Text textMainDoctor;
    @FXML
    private TextField textFieldSearchDoc_Name;
    @FXML
    private TextField textFieldSearchDoc_Pusername;
    @FXML
    private TextField textFieldSearchDoc_Code;
    @FXML
    private TextField textFieldSearchDoc_Folio;
    @FXML
    private TextField textFieldSearchDoc_License;
    @FXML
    private TableView<DoctorDto> tableViewDoctors;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocCode;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocFolio;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocName;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocLicense;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocId;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocIniWork;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocFinishWork;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocBreaks;
    @FXML
    private Button BtndeleteDoctor;
    @FXML
    private JFXTimePicker timepickerFinWork;
    @FXML
    private Button btnMantUsers;
    @FXML
    private Button btnMantDoctors;
    List<UserDto> userList = new ArrayList<>();
    private ObservableList<UserDto> userObservableList;
    List<DoctorDto> doctorList = new ArrayList<>();
    private ObservableList<DoctorDto> doctorObservableList;

    List<PatientDto> patientList = new ArrayList<>();
    private ObservableList<PatientDto> patientObservableList;

    List<DiseaseDto> diseaseList = new ArrayList<>();
    private ObservableList<DiseaseDto> diseaseObservableList;

    @FXML
    private BorderPane MenuView;

    UserDto userDto = new UserDto();
    DoctorDto doctorDto = new DoctorDto();
    PatientDto patientDto = new PatientDto();
    DiseaseDto diseaseDto = new DiseaseDto();

    private TextField textFieldSearch_Ident;
    @FXML
    private TextField textFieldSearch_Usuario;
    @FXML
    private TextField textFieldSearchDoc_State;
    @FXML
    private ChoiceBox<String> choiceBoxJobsTypes;
    String jobsSpanish[] = {"Administrador", "Recepcionista", "Doctor"};
    String jobsEnglish[] = {"Administrator", "Receptionist", "Doctor"};
    boolean userDoctor = false;

    boolean deleteDoctor = false;
    boolean deleteUser = false;
    boolean deletePatient = false;
    boolean deleteDisease = false;

    @FXML
    private BorderPane OptionsMainPatientView;
    @FXML
    private Tab tabMantPatient;
    @FXML
    private RadioButton radioBtnMale;
    @FXML
    private RadioButton radioBtnFemale;
    @FXML
    private TextField textFieldSearchPat_Name;
    @FXML
    private TextField textFieldSearchPat_Pusername;
    @FXML
    private TextField textFieldSearchPat_Identification;
    @FXML
    private TextField textFieldSearchPat_Gender;
    @FXML
    private TextField textFieldSearchPat_Susername;
    @FXML
    private TableView<PatientDto> tableViewPatient;
    @FXML
    private TableColumn<PatientDto, String> tableColId;
    @FXML
    private TableColumn<PatientDto, String> tableColPatIdentif;
    @FXML
    private TableColumn<PatientDto, String> tableColPatName;
    @FXML
    private TableColumn<PatientDto, String> tableColPatPsurname;
    @FXML
    private TableColumn<PatientDto, String> tableColPatSsurname;
    @FXML
    private TableColumn<PatientDto, String> tableColPatGender;
    @FXML
    private TableColumn<PatientDto, String> tableColPatEmail;
    @FXML
    private Button BtndeletePatient;
    public static ToggleGroup gender;
    @FXML
    private TextField namePatMainField;
    @FXML
    private TextField firstNamePatMainField;
    @FXML
    private TextField lastNamePatMainField;
    @FXML
    private TextField identPatMainField;
    @FXML
    private TextField emailPatMainField;
    @FXML
    private JFXDatePicker datePickerBirthdayPat;
    @FXML
    private TextField breaksMainField;
    private TextField namePatMainField1;

    private TextField nameDistMainField;

    private String matrizAgenda[][] = new String[15][8];
    //private Map<Node, Posicion> mapaPosiciones = new HashMap<>();
    private Map<Integer, String> horas = new HashMap<>();
    private Map<Integer, String> dias = new HashMap<>();
    ToggleGroup Tou = new ToggleGroup();

    UserDto usrIdiom = (UserDto) AppContext.getInstance().get("Usuario");
    @FXML
    private TextField SpacesDocMainField1;
    @FXML
    private TabPane tabPaneMantPatient;
    @FXML
    private TabPane tabPaneMantUsers;
    @FXML
    private TabPane tabPaneMantDoctors;
    @FXML
    private RadioButton rdBtnUserActive;
    @FXML
    private RadioButton rdBtnUserInactive;
    public static ToggleGroup userActive;
    ProceedingsDto proceedingsDto = new ProceedingsDto();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formater();
        OptionsMenuView.toFront();
        UserDto us = new UserDto();
        us = (UserDto) AppContext.getInstance().get("Usuario");
        if (us.getUsType().equals("Receptionist")) {
            btnMantUsers.setDisable(true);
            btnMantDoctors.setDisable(true);
        }
        choiceBoxJobsTypes.getItems().addAll(jobsEnglish);
        gender = new ToggleGroup();
        this.radioBtnMale.setToggleGroup(gender);
        this.radioBtnFemale.setToggleGroup(gender);
        userActive = new ToggleGroup();
        this.rdBtnUserActive.setToggleGroup(userActive);
        this.rdBtnUserInactive.setToggleGroup(userActive);
        this.tableColAct.setCellValueFactory(new PropertyValueFactory("UsSState"));
        this.tableColIdentif.setCellValueFactory(new PropertyValueFactory("UsIdentification"));
        this.tableColName.setCellValueFactory(new PropertyValueFactory("UsName"));
        this.tableColPsurname.setCellValueFactory(new PropertyValueFactory("UsPlastname"));
        this.tableColSsurname.setCellValueFactory(new PropertyValueFactory("UsSlastname"));
        this.tableColUserName.setCellValueFactory(new PropertyValueFactory("UsUsername"));
        this.tableColEmail.setCellValueFactory(new PropertyValueFactory("UsEmail"));
        this.tableColAdmi.setCellValueFactory(new PropertyValueFactory("UsType"));

        this.tableColDocBreaks.setCellValueFactory(new PropertyValueFactory("DrBreak"));
        this.tableColDocFinishWork.setCellValueFactory(new PropertyValueFactory("DrFinisworking"));
        this.tableColDocLicense.setCellValueFactory(new PropertyValueFactory("DrLicense"));
        this.tableColDocId.setCellValueFactory(new PropertyValueFactory("DoctorPsurname"));
        this.tableColDocName.setCellValueFactory(new PropertyValueFactory("DoctorName"));
        this.tableColDocFolio.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode.setCellValueFactory(new PropertyValueFactory("DrCode"));

        this.tableColPatIdentif.setCellValueFactory(new PropertyValueFactory("PtIdentification"));
        this.tableColPatName.setCellValueFactory(new PropertyValueFactory("PtName"));
        this.tableColPatPsurname.setCellValueFactory(new PropertyValueFactory("PtPlastname"));
        this.tableColPatSsurname.setCellValueFactory(new PropertyValueFactory("PtSlastname"));
        this.tableColPatGender.setCellValueFactory(new PropertyValueFactory("PtGender"));
        this.tableColPatEmail.setCellValueFactory(new PropertyValueFactory("PtEmail"));
        this.tableColId.setCellValueFactory(new PropertyValueFactory("PtId"));

        fillTableUsers();
        fillTableDoctors();
        fillTablePatient();

    }

    private void formater() {

        userMainField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        identMainField.setTextFormatter(Formato.getInstance().integerFormat());
        psurnameMainField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        ssurnameMainField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        usernameMainField.setTextFormatter(Formato.getInstance().letrasFormat(20));
        emailMainField.setTextFormatter(Formato.getInstance().maxLengthFormat(80));

        namePatMainField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        identPatMainField.setTextFormatter(Formato.getInstance().integerFormat());
        firstNamePatMainField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        lastNamePatMainField.setTextFormatter(Formato.getInstance().letrasFormat(18));
        emailPatMainField.setTextFormatter(Formato.getInstance().maxLengthFormat(80));

        codeDocMainField.setTextFormatter(Formato.getInstance().integerFormat());
        licenseDocMainField.setTextFormatter(Formato.getInstance().integerFormat());
        folioDocMainField.setTextFormatter(Formato.getInstance().integerFormat());
        breaksMainField.setTextFormatter(Formato.getInstance().integerFormat());

    }

    @Override
    public void initialize() {

    }

    private void fillTableUsers() {
        UserService service = new UserService();
        userList = service.getUsers();
        if (userList.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "No hay ningun Usuario", getStage(), "");
        } else {
            userObservableList = FXCollections.observableArrayList(userList);
        }
        this.tableViewUser.refresh();
        this.tableViewUser.setItems(userObservableList);
    }

    private void fillTableDoctors() {

        DoctorService service = new DoctorService();
        doctorList = service.getDoctor();
        if (doctorList == null) {
        } else {
            doctorObservableList = FXCollections.observableArrayList(doctorList);
            this.tableViewDoctors.refresh();
            this.tableViewDoctors.setItems(doctorObservableList);
        }
    }

    private void fillTablePatient() {

        PatientService service = new PatientService();
        patientList = service.getPatients();
        if (patientList.isEmpty()) {
        } else {
            patientObservableList = FXCollections.observableArrayList(patientList);
        }

        this.tableViewPatient.refresh();
        this.tableViewPatient.setItems(patientObservableList);
    }

    private void fillPatient(PatientDto patientsDto) {
        namePatMainField.setText(patientsDto.getPtName());
        firstNamePatMainField.setText(patientsDto.getPtPlastname());
        lastNamePatMainField.setText(patientsDto.getPtSlastname());
        emailPatMainField.setText(patientsDto.getPtEmail());
        if (patientsDto.getPtGender().equals("M")) {
            radioBtnMale.setSelected(true);
        } else {
            radioBtnFemale.setSelected(true);
        }
        datePickerBirthdayPat.setValue(patientsDto.getPtBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        identPatMainField.setText(patientsDto.getPtIdentification());
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }

    @FXML
    private void deleteClicked(MouseEvent event) {
        deleteUser = true;
    }

    @FXML
    private void openManUsers(ActionEvent event) {
        OptionsMainUsersView.toFront();
    }

    @FXML
    private void UpdateUser(ActionEvent event) {
        Respuesta response;
        if (userDto != null && !choiceBoxJobsTypes.getValue().equals("Doctor")) {
            UserDto us = new UserDto();
            UserService service = new UserService();
            response = service.saveUser(bindNewUser());
            if (response.getEstado()) {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Usuario", getStage(), "Usuario guardado");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save User", getStage(), "Saved user");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Enregistrer l'utilisateur", getStage(), "Utilisateur enregistré");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "ユーザーを保存する", getStage(), "保存されたユーザー");
                }
                cleanUpUser();
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), "Error al guardar");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Save User", getStage(), "Error when saving");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Enregistrer l'utilisateur", getStage(), "Erreur lors de l'enregistrement");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "ユーザーを保存する", getStage(), "保存時のエラー");
                }
            }

        } else {
            userDoctor = true;
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Doctor", getStage(), "Debes completar la siguiente información");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save Doctor", getStage(), "You must complete the following information");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Enregistrer le médecin", getStage(), "Vous devez compléter les informations suivantes");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "医師を保存する", getStage(), "次の情報を入力してください");
            }
            textMainDoctor.setText(userMainField.getText() + " " + psurnameMainField.getText() + " " + ssurnameMainField.getText());
            OptionsMainDoctorsView.toFront();
        }
        fillTableUsers();
    }

    UserDto bindNewUser() {
        userDto.setUsId(userDto.getUsId());
        userDto.setUsName(userMainField.getText());
        userDto.setUsPlastname(psurnameMainField.getText());
        userDto.setUsSlastname(ssurnameMainField.getText());
        userDto.setUsUsername(usernameMainField.getText());
        userDto.setUsEmail(emailMainField.getText());
        userDto.setUsState(userDto.getUsState());
        userDto.setUsType(choiceBoxJobsTypes.getValue());
        userDto.setUsIdentification(identMainField.getText());
        if (rdBtnUserActive.isSelected()) {
            userDto.setUsState("A");
        } else {
            if (rdBtnUserInactive.isSelected()) {
                userDto.setUsState("I");
            }
        }
        return userDto;
    }

    @FXML
    private void searchUser_Name(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);

        textFieldSearch_Name.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsName().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    private void filteredUsers(FilteredList<UserDto> list) {
        SortedList<UserDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewUser.comparatorProperty());
        tableViewUser.setItems(sorted);
    }

    @FXML
    private void searchUser_Pusername(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Pusername.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsPlastname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_username(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Usuario.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsUsername().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_Rol(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Rol.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsType().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_Susername(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Susername.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsSlastname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_State(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_State.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsSState().toLowerCase().indexOf(search) == 0) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void userClicked(MouseEvent event) {
        DoctorService serviceD = new DoctorService();
        UserService serviceU = new UserService();
        Respuesta doc;
        Respuesta us;
        if (event.getClickCount() == 1) {
            if (deleteUser == true) {
                userDto = tableViewUser.getSelectionModel().getSelectedItem();
                if (userDto.getUsType().equals("Doctor")) {
                    doc = serviceD.getDoctorUser(userDto.getUsId());
                    this.doctorDto = (DoctorDto) doc.getResultado("Doctor");
                    if (doctorDto != null) {
                        doc = serviceD.deleteDoctor(doctorDto.getDrId());
                        us = serviceU.deleteUser(userDto.getUsId());
                        deleteUser = false;
                        userDto = new UserDto();
                        if (doc.getEstado() && us.getEstado()) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Usuario y Doctor eliminado");
                        }
                    }
                } else {
                    serviceU.deleteUser(userDto.getUsId());
                    deleteUser = false;
                }
                userList.clear();
                userObservableList.clear();
                doctorList.clear();
                doctorObservableList.clear();
                fillTableUsers();
                fillTableDoctors();
            }
        }
        if (event.getClickCount() == 2) {
            userDto = tableViewUser.getSelectionModel().getSelectedItem();
            fillUser(userDto);
            tabPaneMantUsers.getSelectionModel().select(tabMantUsers);
            System.out.println(userDto.getUsName());
        }
    }

    private void fillUser(UserDto user) {
        userMainField.setText(user.getUsName());
        psurnameMainField.setText(user.getUsPlastname());
        ssurnameMainField.setText(user.getUsSlastname());
        usernameMainField.setText(user.getUsUsername());
        emailMainField.setText(user.getUsUsername());
        identMainField.setText(user.getUsIdentification());
        choiceBoxJobsTypes.setValue(user.getUsType());
        if (user.getUsState().equals("A")) {
            rdBtnUserActive.setSelected(true);
        } else {
            if (user.getUsState().equals("I")) {
                rdBtnUserInactive.setSelected(true);
            }
        }
    }

    DoctorDto bindNewDoctor() {

        if (doctorDto != null && userDto == null) {
            doctorDto.setDrId(doctorDto.getDrId());
            doctorDto.setDrUser(doctorDto.getDrUser());
            doctorDto.setDrBreak(breaksMainField.getText());
            doctorDto.setDrCode(Integer.parseInt(codeDocMainField.getText()));
            doctorDto.setDrLicense(Integer.parseInt(licenseDocMainField.getText()));
            doctorDto.setDrFol(Integer.parseInt(folioDocMainField.getText()));
            doctorDto.setDrSpaces(intToShort(Integer.parseInt(SpacesDocMainField1.getText())));
            System.out.println(timepickerIniWork.getValue().toString());
            System.out.println(timepickerFinWork.getValue().toString());

            doctorDto.setDrIniworking(timepickerIniWork.getValue().toString());
            doctorDto.setDrFinisworking(timepickerFinWork.getValue().toString());

            doctorDto.toString();
            userDto.toString();

        } else if (userDto != null) {
            doctorDto.setDrId(doctorDto.getDrId());
            doctorDto.setDrUser(userDto);
            doctorDto.setDrBreak(breaksMainField.getText());
            doctorDto.setDrCode(Integer.parseInt(codeDocMainField.getText()));
            doctorDto.setDrLicense(Integer.parseInt(licenseDocMainField.getText()));
            doctorDto.setDrFol(Integer.parseInt(folioDocMainField.getText()));
            doctorDto.setDrSpaces(intToShort(3));
            System.out.println(timepickerIniWork.getValue().toString());
            System.out.println(timepickerFinWork.getValue().toString());

            doctorDto.setDrIniworking(timepickerIniWork.getValue().toString());
            doctorDto.setDrFinisworking(timepickerFinWork.getValue().toString());

            doctorDto.toString();
            userDto.toString();
        }
        return doctorDto;
    }

    public static short intToShort(int x) {
        if (x < Short.MIN_VALUE) {
            return Short.MIN_VALUE;
        }
        if (x > Short.MAX_VALUE) {
            return Short.MAX_VALUE;
        }
        return (short) Math.round(x);
    }

    @FXML
    private void UpdateDoctor(ActionEvent event) {
        Respuesta response = null;
        if (userDoctor) {
            UserService service = new UserService();
            service.saveUser(bindNewUser());
            DoctorService serviceD = new DoctorService();
            response = serviceD.saveDoctor(bindNewDoctor());
            fillTableUsers();
            fillTableDoctors();
            userDoctor = false;
            doctorDto = new DoctorDto();
        } else {
            if (doctorDto != null) {
                DoctorService serviceD = new DoctorService();
                response = serviceD.saveDoctor(bindNewDoctor());
                fillTableDoctors();
                doctorDto = new DoctorDto();
            }
        }
        if (response.getEstado()) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Doctor", getStage(), "Doctor guardado");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save Doctor", getStage(), "Saved doctor");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Sauver le docteur", getStage(), "Docteur sauvé");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "医者を救う", getStage(), "救われた医師");
            }
            cleanUpDoctor();
        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Doctor", getStage(), "Error al guardar");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Save Doctor", getStage(), "Error when saving");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Sauver le docteur", getStage(), "Erreur lors de l'enregistrement");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "医者を救う", getStage(), "保存時のエラー");
            }
        }
    }

    @FXML
    private void deleteDoctorClicked(MouseEvent event) {
        deleteDoctor = true;

    }

    @FXML
    private void openManDoctors(ActionEvent event) {
        OptionsMainDoctorsView.toFront();
    }

    @FXML
    private void exit(ActionEvent event) {
        FlowController.getInstance().goMain("LoginView");
    }

    private void fillDoctors(DoctorDto doctorDto) {
        codeDocMainField.setText(doctorDto.getDrCode() + "");
        licenseDocMainField.setText(doctorDto.getDrLicense() + "");
        folioDocMainField.setText(doctorDto.getDrFol() + "");
        breaksMainField.setText(doctorDto.getDrBreak());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime iniWorkin = LocalTime.parse(doctorDto.getDrIniworking(), formatter);
        LocalTime finiWorkin = LocalTime.parse(doctorDto.getDrFinisworking(), formatter);

        timepickerIniWork.setValue(iniWorkin);
        timepickerFinWork.setValue(finiWorkin);
    }

    @FXML
    private void searchDoctor_Name(KeyEvent event) {
        FilteredList<DoctorDto> filteredDoctor = new FilteredList<>(doctorObservableList, f -> true);
        textFieldSearchDoc_Name.textProperty().addListener((observable, value, newValue) -> {
            filteredDoctor.setPredicate(DoctorDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (DoctorDto.getDoctorName().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDoctors(filteredDoctor);
    }

    private void filteredDoctors(FilteredList<DoctorDto> list) {
        SortedList<DoctorDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewDoctors.comparatorProperty());
        tableViewDoctors.setItems(sorted);
    }

    @FXML
    private void searchDoctor_Pusername(KeyEvent event) {
        FilteredList<DoctorDto> filteredDoctor = new FilteredList<>(doctorObservableList, f -> true);
        textFieldSearchDoc_Pusername.textProperty().addListener((observable, value, newValue) -> {
            filteredDoctor.setPredicate(DoctorDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (DoctorDto.getDoctorPsurname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDoctors(filteredDoctor);
    }

    @FXML
    private void searchDoctor_code(KeyEvent event) {
        FilteredList<DoctorDto> filteredDoctor = new FilteredList<>(doctorObservableList, f -> true);
        textFieldSearchDoc_Code.textProperty().addListener((observable, value, newValue) -> {
            filteredDoctor.setPredicate(DoctorDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                String doctorPsurnameAsString = String.valueOf(DoctorDto.getDrCode());

                if (doctorPsurnameAsString.indexOf(search) == 0) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDoctors(filteredDoctor);
    }

    @FXML
    private void searchDoctor_Folio(KeyEvent event) {
        FilteredList<DoctorDto> filteredDoctor = new FilteredList<>(doctorObservableList, f -> true);
        textFieldSearchDoc_Folio.textProperty().addListener((observable, value, newValue) -> {
            filteredDoctor.setPredicate(DoctorDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                String doctorPsurnameAsString = String.valueOf(DoctorDto.getDrFol());

                if (doctorPsurnameAsString.indexOf(search) == 0) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDoctors(filteredDoctor);
    }

    @FXML
    private void searchDoctor_License(KeyEvent event) {
        FilteredList<DoctorDto> filteredDoctor = new FilteredList<>(doctorObservableList, f -> true);
        textFieldSearchDoc_License.textProperty().addListener((observable, value, newValue) -> {
            filteredDoctor.setPredicate(DoctorDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                String doctorPsurnameAsString = String.valueOf(DoctorDto.getDrLicense());

                if (doctorPsurnameAsString.indexOf(search) == 0) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDoctors(filteredDoctor);
    }

    @FXML
    private void doctorClicked(MouseEvent event) {
        DoctorService serviceD = new DoctorService();
        UserService serviceU = new UserService();
        if (event.getClickCount() == 1) {
            if (deleteDoctor == true) {
                doctorDto = tableViewDoctors.getSelectionModel().getSelectedItem();
                userDto = doctorDto.getDrUser();
                userDto.setUsType("Default");
                serviceU.saveUser(userDto);
                Respuesta r = serviceD.deleteDoctor(doctorDto.getDrId());
                deleteDoctor = false;
                doctorList.clear();
                doctorObservableList.clear();
                userList.clear();
                userObservableList.clear();
                fillTableDoctors();
                fillTableUsers();
                doctorDto = new DoctorDto();
                userDto = new UserDto();
                if (r.getEstado()) {

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Doctor", getStage(), "Usuario eliminado");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Doctor", getStage(), "Error aleliminar usuario");
                }
            }
        } else if (event.getClickCount() == 2) {
            doctorDto = tableViewDoctors.getSelectionModel().getSelectedItem();
            fillDoctors(doctorDto);
            tabPaneMantDoctors.getSelectionModel().select(tabManDoctors);

        }
    }

    @FXML
    private void searchDoctor_State(KeyEvent event) {

    }

    PatientDto bindNewPatient() {
        LocalDate localDate = datePickerBirthdayPat.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String genere = "";
        patientDto.setPtBirthdate(date);
        patientDto.setPtEmail(emailPatMainField.getText());
        patientDto.setPtId(patientDto.getPtId());
        if (radioBtnMale.isSelected()) {
            genere = "M";
        } else {
            genere = "F";
        }
        patientDto.setPtGender(genere);
        patientDto.setPtIdentification(identPatMainField.getText());
        patientDto.setPtName(namePatMainField.getText());
        patientDto.setPtPlastname(firstNamePatMainField.getText());
        patientDto.setPtSlastname(lastNamePatMainField.getText());
        return patientDto;
    }

    @FXML
    private void UpdatePatient(ActionEvent event) {
        PatientService patient = new PatientService();
        Respuesta r = null;
        if (patientDto != null) {
            r = patient.savePatient(bindNewPatient());
            fillTablePatient();
            patientDto = new PatientDto();
        }

        if (r.getEstado()) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Paciente", getStage(), "Paciente guardado");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save Patient", getStage(), "Saved patient");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Sauver un patient", getStage(), "Patient sauvé");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "患者を救う", getStage(), "救われた患者");
            }
            cleanUpPatient();
            patientDto = null;
        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Paciente", getStage(), "Error al guardar");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Save Patient", getStage(), "Error when saving");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Sauver un patient", getStage(), "Erreur lors de l'enregistrement");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "患者を救う", getStage(), "保存時のエラー");
            }
        }

    }

    @FXML
    private void searchPat_Name(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Name.textProperty().addListener((observable, value, newValue) -> {
            filteredPatient.setPredicate(PatientDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PatientDto.getPtName().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredPatient(filteredPatient);
    }

    private void filteredPatient(FilteredList<PatientDto> list) {
        SortedList<PatientDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewPatient.comparatorProperty());
        tableViewPatient.setItems(sorted);
    }

    @FXML
    private void searchPat_Pusername(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Pusername.textProperty().addListener((observable, value, newValue) -> {
            filteredPatient.setPredicate(PatientDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PatientDto.getPtPlastname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredPatient(filteredPatient);
    }

    @FXML
    private void searchPat_identification(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Identification.textProperty().addListener((observable, value, newValue) -> {
            filteredPatient.setPredicate(PatientDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PatientDto.getPtIdentification().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredPatient(filteredPatient);
    }

    @FXML
    private void searchPat_Gender(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Gender.textProperty().addListener((observable, value, newValue) -> {
            filteredPatient.setPredicate(PatientDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PatientDto.getPtGender().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredPatient(filteredPatient);
    }

    @FXML
    private void searchPat_Susername(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Susername.textProperty().addListener((observable, value, newValue) -> {
            filteredPatient.setPredicate(PatientDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PatientDto.getPtSlastname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredPatient(filteredPatient);
    }

    @FXML
    private void patientClicked(MouseEvent event) {
        PatientService patient = new PatientService();
        Respuesta r;
        if (event.getClickCount() == 1) {
            if (deletePatient) {
                patientDto = tableViewPatient.getSelectionModel().getSelectedItem();
                if (patientDto != null) {
                    r = patient.deletePatient(patientDto.getPtId());
                    deletePatient = false;
                    patientList.clear();
                    patientObservableList.clear();
                    fillTablePatient();
                    patientDto = new PatientDto();
                    if (r.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Paciente Eliminado Correctamente");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Error al eliminar paciente");
                    }
                }
            }
        }
        if (event.getClickCount() == 2) {
            patientDto = tableViewPatient.getSelectionModel().getSelectedItem();
            if (patientDto != null) {
                fillPatient(patientDto);
                tabPaneMantPatient.getSelectionModel().select(tabMantPatient);
            }
        }
    }

    @FXML
    private void deletePatientClicked(MouseEvent event) {
        deletePatient = true;
    }

    @FXML
    private void openManPatient(ActionEvent event) {
        OptionsMainPatientView.toFront();
    }

    @FXML
    private void openManDiary(ActionEvent event) {
        FlowController.getInstance().goMain("ViewDiariesOptions");
    }

    @FXML
    private void openProceeding(ActionEvent event) {
        if (patientDto.getPtId() != 0) {

            ProceedingsService serviceProced = new ProceedingsService();
            Respuesta hasProc = serviceProced.getProcedingsIdPatient(patientDto.getPtId());

            if (hasProc.getEstado()) {
                proceedingsDto = (ProceedingsDto) hasProc.getResultado("Proceedings");
                AppContext.getInstance().set("Proceding", proceedingsDto);
            } else {
                proceedingsDto.setPsId(0);
                proceedingsDto.setPsPatient(patientDto);
                 Respuesta resp = serviceProced.saveProcedings(proceedingsDto);
                if (resp.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), " Expediente Guardado Correctamente");
                }
                hasProc = serviceProced.getProcedingsIdPatient(patientDto.getPtId());
                proceedingsDto = (ProceedingsDto) hasProc.getResultado("Proceedings");
                AppContext.getInstance().set("Proceding", proceedingsDto);

               
            }

            AppContext.getInstance().set("Patient", patientDto);
            FlowController.getInstance().goMain("ViewProceedingsOptions");
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Debes cargar un paciente ");
        }
    }

    @FXML
    private void cleanUpMantDoctors(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Doctor", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            cleanUpDoctor();
        }
    }

    private void cleanUpDoctor() {
        codeDocMainField.clear();
        licenseDocMainField.clear();
        folioDocMainField.clear();
        SpacesDocMainField1.clear();
        breaksMainField.clear();
        timepickerIniWork.setValue(null);
        timepickerFinWork.setValue(null);
    }

    @FXML
    private void cleanUpMantUser(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Usuario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            cleanUpUser();
        }
    }

    private void cleanUpUser() {
        userMainField.clear();
        psurnameMainField.clear();
        ssurnameMainField.clear();
        usernameMainField.clear();
        emailMainField.clear();
        identMainField.clear();
        choiceBoxJobsTypes.setValue(null);
        rdBtnUserActive.setSelected(false);
        rdBtnUserInactive.setSelected(false);
    }

    @FXML
    private void cleanUpMantPatient(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar Paciente", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            cleanUpPatient();
        }
    }

    private void cleanUpPatient() {
        namePatMainField.clear();
        firstNamePatMainField.clear();
        lastNamePatMainField.clear();
        emailPatMainField.clear();
        datePickerBirthdayPat.setValue(null);
        identPatMainField.clear();
        gender.selectToggle(null);
    }

}
