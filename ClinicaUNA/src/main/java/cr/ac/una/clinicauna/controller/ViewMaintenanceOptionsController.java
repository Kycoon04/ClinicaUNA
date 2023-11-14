package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.EmailDto;
import cr.ac.una.clinicauna.model.ExcelDto;
import cr.ac.una.clinicauna.model.HistoryDto;
import cr.ac.una.clinicauna.model.ParametersDto;
import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.DiseaseService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.HistoryService;
import cr.ac.una.clinicauna.service.JasperReportService;
import cr.ac.una.clinicauna.service.PatientService;
import cr.ac.una.clinicauna.service.ProceedingsService;
import cr.ac.una.clinicauna.service.SqlService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.io.FileNotFoundException;
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
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TextArea;
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

    UserDto userDto;
    DoctorDto doctorDto;
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
    String jobsFrench[] = {"Administrateur", "Réceptionniste", "Médecin"};
    String jobsJapanese[] = {"管理者","受付","医師"};
    boolean userDoctor = false;
    int respaldo = 0;
    String respaldoFechaInit;
    String respaldoFechaFinal;
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
    @FXML
    private TextField telephonePatMainField;
    @FXML
    private BorderPane OptionsReportsView;
    @FXML
    private JFXDatePicker datePickerInitialReport;
    @FXML
    private JFXDatePicker datePickerFinalReport;
    @FXML
    private TextField textReportNameDoctor;
    @FXML
    private TextField textReportIDDoctor;
    @FXML
    private BorderPane OptionsReportExcel;
    @FXML
    private ChoiceBox<String> choiceBoxPeriodReport;
    @FXML
    private TextField textFieldNameReport;
    @FXML
    private TextArea textAreaDescripReport;
    @FXML
    private TextArea textAreaQueryReport;
    @FXML
    private TextField textFieldEmailReport;
    @FXML
    private TableView<String> tableViewEmails;
    @FXML
    private TableColumn<String, String> tableColEmailReport;

    private List<String> listEmails = new ArrayList<>();
    private ObservableList<String> emails = FXCollections.observableArrayList();
    String periodSpanish[] = {"Diario", "Semanal", "Mensual"};
    String periodEnglish[] = {"Daily", "Weekly", "Monthly"};
    String periodFrench[] = {"Quotidien", "Hebdomadaire", "Mensuel"};
    String periodJapanese[] = {"「日次」", "「週次」", "「月次」"};
    @FXML
    private TextField textFieldTitleReport;
    @FXML
    private ChoiceBox<String> choiceBoxIdioms;
    String[] Spanish = {"Español", "Inglés", "Francés", "Japonés"};
    String[] English = {"Spanish", "English", "France", "Japonese"};
    String[] French = {"Espagnol", "Anglais", "Francais", "Japonais"};
    String[] Japanesse = {"スペイン語", "英語", "フランス語", "日本語"};
    @FXML
    private JFXDatePicker datePickerExcelInit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formater();
        MenuView.toFront();
        OptionsMenuView.toFront();
        UserDto us = new UserDto();
        us = (UserDto) AppContext.getInstance().get("Usuario");
        if (us.getUsType().equals("Receptionist")) {
            btnMantUsers.setDisable(true);
            btnMantDoctors.setDisable(true);
        }
        //choiceBoxIdioms.getItems().addAll(jobsEnglish);
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

        this.tableColEmailReport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        this.tableViewEmails.setItems(emails);
        loadChoiceIdioms();
        loadChoiceJobs();
        fillTableUsers();
        fillTableDoctors();
        fillTablePatient();

    }
    private void loadChoiceJobs(){
        if (usrIdiom.getUsLenguage().equals("Japanese")) {
            choiceBoxJobsTypes.getItems().clear();
            choiceBoxJobsTypes.getItems().addAll(jobsJapanese);
        }
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            choiceBoxJobsTypes.getItems().clear();
            choiceBoxJobsTypes.getItems().addAll(jobsSpanish);

        }
        if (usrIdiom.getUsLenguage().equals("English")) {
            choiceBoxJobsTypes.getItems().clear();
            choiceBoxJobsTypes.getItems().addAll(jobsEnglish);
        }
        if (usrIdiom.getUsLenguage().equals("French")) {
           choiceBoxJobsTypes.getItems().clear();
            choiceBoxJobsTypes.getItems().addAll(jobsFrench);
        }
    }

    private void loadChoiceIdioms() {
        if (usrIdiom.getUsLenguage().equals("Japanese")) {
            choiceBoxIdioms.getItems().clear();
            choiceBoxIdioms.getItems().addAll(Japanesse);
        }
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            choiceBoxIdioms.getItems().clear();
            choiceBoxIdioms.getItems().addAll(Spanish);

        }
        if (usrIdiom.getUsLenguage().equals("English")) {
            choiceBoxIdioms.getItems().clear();
            choiceBoxIdioms.getItems().addAll(English);
        }
        if (usrIdiom.getUsLenguage().equals("French")) {
            choiceBoxIdioms.getItems().clear();
            choiceBoxIdioms.getItems().addAll(French);
        }
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
        telephonePatMainField.setText(patientsDto.getPtTelephone());
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
            doctorDto = new DoctorDto();
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
        String idiom="";
        if (choiceBoxIdioms.getValue().equals("Espagnol") || choiceBoxIdioms.getValue().equals("Spanish") || choiceBoxIdioms.getValue().equals("Español") || choiceBoxIdioms.getValue().equals("スペイン語")) {
            idiom = "Spanish";
            userDto.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Anglais") || choiceBoxIdioms.getValue().equals("English") || choiceBoxIdioms.getValue().equals("Inglés") || choiceBoxIdioms.getValue().equals("英語")) {
            idiom = "English";
            userDto.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Francais") || choiceBoxIdioms.getValue().equals("France") || choiceBoxIdioms.getValue().equals("Francés") || choiceBoxIdioms.getValue().equals("フランス語")) {
            idiom = "French";
            userDto.setUsLenguage(idiom);
        }
        if (choiceBoxIdioms.getValue().equals("Japonais") || choiceBoxIdioms.getValue().equals("Japanese") || choiceBoxIdioms.getValue().equals("Japonés") || choiceBoxIdioms.getValue().equals("日本語")) {
            idiom = "Japanese";
            userDto.setUsLenguage(idiom);
        }
        String job="";
         if (choiceBoxJobsTypes.getValue().equals("Administrador") ||choiceBoxJobsTypes.getValue().equals("Administrator") || choiceBoxJobsTypes.getValue().equals("Administrateur") || choiceBoxJobsTypes.getValue().equals("管理者")) {
            job = "Administrator";
            userDto.setUsType(job);
        }
        if (choiceBoxJobsTypes.getValue().equals("Recepcionista") || choiceBoxJobsTypes.getValue().equals("Receptionist") || choiceBoxJobsTypes.getValue().equals("Réceptionniste") || choiceBoxJobsTypes.getValue().equals("受付")) {
            job = "Receptionist";
            userDto.setUsType(job);
        }
        if (choiceBoxJobsTypes.getValue().equals("Doctor") || choiceBoxJobsTypes.getValue().equals("Doctor") || choiceBoxJobsTypes.getValue().equals("Médecin") || choiceBoxJobsTypes.getValue().equals("医師")) {
            job = "Doctor";
            userDto.setUsType(job);
        }
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
        choiceBoxIdioms.setValue(user.getUsLenguage());
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
            doctorDto.setDrIniworking(timepickerIniWork.getValue().toString());
            doctorDto.setDrFinisworking(timepickerFinWork.getValue().toString());
        } else if (userDto != null) {
            doctorDto.setDrId(doctorDto.getDrId());
            doctorDto.setDrUser(userDto);
            doctorDto.setDrBreak(breaksMainField.getText());
            doctorDto.setDrCode(Integer.parseInt(codeDocMainField.getText()));
            doctorDto.setDrLicense(Integer.parseInt(licenseDocMainField.getText()));
            doctorDto.setDrFol(Integer.parseInt(folioDocMainField.getText()));
            doctorDto.setDrSpaces(intToShort(3));
            doctorDto.setDrIniworking(timepickerIniWork.getValue().toString());
            doctorDto.setDrFinisworking(timepickerFinWork.getValue().toString());
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
            HistoryService serviceHistorial = new HistoryService();
            UserService service = new UserService();
            service.saveUser(bindNewUser());
            DoctorService serviceD = new DoctorService();

            response = serviceD.saveDoctor(bindNewDoctor());
            doctorDto = (DoctorDto) serviceD.getDoctorUser(userDto.getUsId()).getResultado("Doctor");
            HistoryDto actual = new HistoryDto();
            actual.setHtDate(LocalDate.now());
            actual.setHtDoctor(doctorDto);
            actual.setHtIniworking(doctorDto.getDrIniworking());
            actual.setHtFinisworking(doctorDto.getDrFinisworking());
            actual.setHtSpaces(doctorDto.drSpaces);
            actual.setHtId(0);
            response = serviceHistorial.saveHistory(actual);

            fillTableUsers();
            fillTableDoctors();
            userDoctor = false;

        } else {
            if (doctorDto != null) {
                DoctorService serviceD = new DoctorService();
                response = serviceD.saveDoctor(bindNewDoctor());
                fillTableDoctors();

                if (respaldo != doctorDto.drSpaces || respaldoFechaInit != doctorDto.getDrIniworking() || respaldoFechaFinal != doctorDto.getDrFinisworking()) {
                    HistoryService serviceHistorial = new HistoryService();
                    List<HistoryDto> lista = serviceHistorial.getHistorysByDoctor(doctorDto.getDrId());
                    HistoryDto ultimo = lista.stream().filter(x -> x.getHtDateFinal() == null).findAny().get();
                    LocalDate today = LocalDate.now();
                    ultimo.setHtDateFinal(today);
                    response = serviceHistorial.saveHistory(ultimo);

                    HistoryDto actual = new HistoryDto();
                    actual.setHtDate(today);
                    actual.setHtDoctor(doctorDto);
                    actual.setHtIniworking(doctorDto.getDrIniworking());
                    actual.setHtFinisworking(doctorDto.getDrFinisworking());
                    actual.setHtSpaces(doctorDto.drSpaces);
                    actual.setHtId(0);
                    response = serviceHistorial.saveHistory(actual);
                }

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
        SpacesDocMainField1.setText(String.valueOf(doctorDto.getDrSpaces()));
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
            doctorDto = new DoctorDto();
            doctorDto = tableViewDoctors.getSelectionModel().getSelectedItem();
            respaldo = doctorDto.getDrSpaces();
            respaldoFechaInit = doctorDto.getDrIniworking();
            respaldoFechaFinal = doctorDto.getDrFinisworking();
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
        patientDto.setPtTelephone(telephonePatMainField.getText());
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
            //patientDto = null;
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
                        if (usrIdiom.getUsLenguage().equals("Spanish")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Paciente", getStage(), "Paciente Eliminado Correctamente");
                        } else if (usrIdiom.getUsLenguage().equals("English")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Delete Patient", getStage(), "Patient Deleted Successfully");
                        } else if (usrIdiom.getUsLenguage().equals("French")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Supprimer un patient", getStage(), "Patient supprimé avec succès");
                        } else {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "患者を削除する", getStage(), "患者は正常に削除されました");
                        }
                    } else {
                        if (usrIdiom.getUsLenguage().equals("Spanish")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Paciente", getStage(), "Error al eliminar paciente");
                        } else if (usrIdiom.getUsLenguage().equals("English")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Delete Patient", getStage(), "Error when deleting patient");
                        } else if (usrIdiom.getUsLenguage().equals("French")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Supprimer un patient", getStage(), "Erreur lors de la suppression du patient");
                        } else {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "患者を削除する", getStage(), "患者の削除時にエラーが発生しました");
                        }
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
        System.out.println(patientDto.getPtId());
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
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Debes cargar un paciente");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "You must load a patient");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Vous devez charger un patient");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "患者をロードする必要があります");
            }
        }
    }

    @FXML
    private void cleanUpMantDoctors(ActionEvent event) {
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            if (new Mensaje().showConfirmation("Limpiar Doctor", getStage(), "¿Está seguro que desea limpiar el registro?")) {
                cleanUpDoctor();
            }
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            if (new Mensaje().showConfirmation("Clean Doctor", getStage(), "Are you sure you want to clear the record?")) {
                cleanUpDoctor();
            }
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            if (new Mensaje().showConfirmation("Nettoyer le médecin", getStage(), "Êtes-vous sûr de vouloir effacer l'enregistrement ?")) {
                cleanUpDoctor();
            }
        } else {
            if (new Mensaje().showConfirmation("医師をクリア", getStage(), "レコードをクリアしてもよろしいですか？")) {
                cleanUpDoctor();
            }
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
        doctorDto = null;
    }

    @FXML
    private void cleanUpMantUser(ActionEvent event) {
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            if (new Mensaje().showConfirmation("Limpiar Usuario", getStage(), "¿Está seguro que desea limpiar el registro?")) {
                cleanUpUser();
            }
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            if (new Mensaje().showConfirmation("Clean User", getStage(), "Are you sure you want to clear the record?")) {
                cleanUpUser();
            }
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            if (new Mensaje().showConfirmation("Nettoyer l'utilisateur", getStage(), "Êtes-vous sûr de vouloir effacer l'enregistrement ?")) {
                cleanUpUser();
            }
        } else {
            if (new Mensaje().showConfirmation("ユーザーをクリア", getStage(), "レコードをクリアしてもよろしいですか？")) {
                cleanUpUser();
            }
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
        choiceBoxIdioms.setValue(null);
        rdBtnUserActive.setSelected(false);
        rdBtnUserInactive.setSelected(false);
    }

    @FXML
    private void cleanUpMantPatient(ActionEvent event) {
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            if (new Mensaje().showConfirmation("Limpiar Paciente", getStage(), "¿Está seguro que desea limpiar el registro?")) {
                cleanUpPatient();
            }
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            if (new Mensaje().showConfirmation("Clean Patient", getStage(), "Are you sure you want to clear the record?")) {
                cleanUpPatient();
            }
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            if (new Mensaje().showConfirmation("Nettoyer le patient", getStage(), "Êtes-vous sûr de vouloir effacer l'enregistrement ?")) {
                cleanUpPatient();
            }
        } else {
            if (new Mensaje().showConfirmation("患者をクリア", getStage(), "レコードをクリアしてもよろしいですか？")) {
                cleanUpPatient();
            }
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
        telephonePatMainField.clear();
    }

    @FXML
    private void openReportDoctor(MouseEvent event) {
        if (doctorDto != null) {
            textReportNameDoctor.setText(doctorDto.getDoctorName() + " " + doctorDto.getDoctorPsurname() + " " + doctorDto.getDrUser().getUsSlastname());
            textReportIDDoctor.setText(doctorDto.getDrUser().getUsIdentification());
            OptionsReportsView.toFront();
        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Reportes de Doctor", getStage(), "Debes cargar un doctor");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Doctor reports", getStage(), "You must carry a doctor.");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Rapports du médecin", getStage(), "Tu dois avoir un médecin.");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "医師の報告", getStage(), "あなたは医者を連れていかなければなりません.");
            }
        }
    }

    @FXML
    private void createReportBlankSpaces(MouseEvent event) throws FileNotFoundException {
        JasperReportService serviceJasper = new JasperReportService();
        String initialDate = "", finalDate = "";
        if (datePickerInitialReport.getValue() == null) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reportes de Doctor", getStage(), "Debes seleccionar fecha inicial.");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Doctor reports", getStage(), "You must select starting date.");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Rapports du médecin", getStage(), "Vous devez sélectionner la date de début.");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "医師の報告", getStage(), "開始日を選択する必要があります");
            }

        } else {
            if (datePickerFinalReport.getValue() == null) {
                finalDate = "N/A";
            } else {
                finalDate = datePickerFinalReport.getValue().toString();
            }
            initialDate = datePickerInitialReport.getValue().toString();

            Respuesta respuesta = serviceJasper.getNotDiaryDoctor(doctorDto.getDrId(), initialDate, finalDate, userDto.getUsLenguage());
            if (respuesta.getEstado()) {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reportes de Doctor", getStage(), "Reporte generado.");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Doctor reports", getStage(), "Report generated.");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Rapports du médecin", getStage(), "Rapport généré.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "医師の報告", getStage(), "生成されたレポート");
                }
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Reportes de Doctor", getStage(), "Error al generar el reporte.");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "File Report", getStage(), "Error generating the report.");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Rapports du médecin", getStage(), "Erreur lors de la génération du rapport.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "医師の報告", getStage(), "レポート生成エラー.");
                }
            }
        }
    }

    @FXML
    private void createReportAppoinments(MouseEvent event) throws FileNotFoundException {
        JasperReportService serviceJasper = new JasperReportService();
        String initialDate = "", finalDate = "";
        if (datePickerInitialReport.getValue() == null) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reportes de Doctor", getStage(), "Debes seleccionar fecha inicial.");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Doctor reports", getStage(), "You must select starting date.");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Rapports du médecin", getStage(), "Vous devez sélectionner la date de début.");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "医師の報告", getStage(), "開始日を選択する必要があります");
            }

        } else {
            if (datePickerFinalReport.getValue() == null) {
                finalDate = "N/A";
            } else {
                finalDate = datePickerFinalReport.getValue().toString();
            }
            initialDate = datePickerInitialReport.getValue().toString();
            Respuesta respuesta = serviceJasper.getDiaryDoctor(doctorDto.getDrId(), initialDate, finalDate, userDto.getUsLenguage());
            if (respuesta.getEstado()) {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reportes de Doctor", getStage(), "Reporte generado.");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Doctor reports", getStage(), "Report generated.");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Rapports du médecin", getStage(), "Rapport généré.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "医師の報告", getStage(), "生成されたレポート");
                }
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Reportes de Doctor", getStage(), "Error al generar el reporte.");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Doctor reports", getStage(), "Error generating the report.");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Rapports du médecin", getStage(), "Erreur lors de la génération du rapport.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "医師の報告", getStage(), "レポート生成エラー.");
                }
            }
        }
    }

    @FXML
    private void backReport(ActionEvent event) {
        datePickerInitialReport.setValue(null);
        datePickerFinalReport.setValue(null);
        OptionsReportsView.toBack();

    }

    @FXML
    private void openReportExcel(ActionEvent event) {
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            choiceBoxPeriodReport.getItems().addAll(periodSpanish);
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            choiceBoxPeriodReport.getItems().addAll(periodEnglish);
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            choiceBoxPeriodReport.getItems().addAll(periodFrench);
        } else {
            choiceBoxPeriodReport.getItems().addAll(periodJapanese);
        }
        OptionsReportExcel.toFront();
        listEmails = new ArrayList<>();
        emails.clear();
        this.tableViewEmails.refresh();
        cleanReportExcel();

    }

    @FXML
    private void cleanUpReportExcel(ActionEvent event) {
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            if (new Mensaje().showConfirmation("Limpiar Reporte", getStage(), "¿Está seguro que desea limpiar el registro?")) {
                cleanReportExcel();
            }
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            if (new Mensaje().showConfirmation("Clean Report", getStage(), "Are you sure you want to clear the record?")) {
                cleanReportExcel();
            }
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            if (new Mensaje().showConfirmation("Effacer le rapport", getStage(), "Êtes-vous sûr de vouloir effacer l'enregistrement ?")) {
                cleanReportExcel();
            }
        } else {
            if (new Mensaje().showConfirmation("クリアレポート", getStage(), "レコードをクリアしてもよろしいですか？")) {
                cleanReportExcel();
            }
        }
    }

    private void cleanReportExcel() {
        textFieldEmailReport.clear();
        textFieldNameReport.clear();
        textAreaDescripReport.clear();
        textAreaQueryReport.clear();
        choiceBoxPeriodReport.setValue(null);
    }

    @FXML
    private void addEmail(MouseEvent event) {
        String email = "";
        if (!textFieldEmailReport.getText().isEmpty()) {
            email = textFieldEmailReport.getText();
            listEmails.add(email);
            emails.add(email);
            this.tableViewEmails.refresh();
            this.tableViewEmails.setItems(emails);

        }
    }

    @FXML
    private void emailClicked(MouseEvent event) throws FileNotFoundException {
    }

    @FXML
    private void sendReport(MouseEvent event) throws FileNotFoundException {
        SqlService serviceSql = new SqlService();
        ParametersDto parametersDto = new ParametersDto();
        List<EmailDto> emailDto = new ArrayList<>();

        parametersDto.setPsId(0);
        parametersDto.setPsName(textFieldNameReport.getText());
        if (choiceBoxPeriodReport.getValue().equals("Diario") || choiceBoxPeriodReport.getValue().equals("Daily")
                || choiceBoxPeriodReport.getValue().equals("Quotidien") || choiceBoxPeriodReport.getValue().equals("「日次」")) {
            parametersDto.setPsTime("Daily");
        }
        if (choiceBoxPeriodReport.getValue().equals("Semanal") || choiceBoxPeriodReport.getValue().equals("Weekly")
                || choiceBoxPeriodReport.getValue().equals("Hebdomadaire") || choiceBoxPeriodReport.getValue().equals("「週次」")) {
            parametersDto.setPsTime("Weekly");
        }
        if (choiceBoxPeriodReport.getValue().equals("Mensual") || choiceBoxPeriodReport.getValue().equals("Monthly")
                || choiceBoxPeriodReport.getValue().equals("Mensuel") || choiceBoxPeriodReport.getValue().equals("「月次」")) {
            parametersDto.setPsTime("Monthly");
        }
        parametersDto.setPsTitule(textFieldTitleReport.getText());
        parametersDto.setPsDescription(textAreaDescripReport.getText());
        parametersDto.setPsQuery(textAreaQueryReport.getText());
        parametersDto.setPsDateInit(datePickerExcelInit.getValue());
        for (String p : listEmails) {
            EmailDto emailsDto = new EmailDto();
            emailsDto.setElId(0);
            emailsDto.setElEmail(p);
            emailsDto.setElIdsql(parametersDto);
            emailDto.add(emailsDto);
        }
        ExcelDto excelDto = new ExcelDto(parametersDto,emailDto);
        Respuesta res = serviceSql.getExcel(excelDto);
        if (res.getEstado()) {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Reportes de Excel", getStage(), "Reporte generado.");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Excel reports", getStage(), "Report generated.");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Rapports Excel", getStage(), "Rapport généré.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "エクセルレポート", getStage(), "生成されたレポート");
                }
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Reportes de Excel", getStage(), "Error al generar el reporte.");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Excel reports", getStage(), "Error generating the report.");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Rapports Excel", getStage(), "Erreur lors de la génération du rapport.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "エクセルレポート", getStage(), "レポート生成エラー.");
                }
            }
    }

}
