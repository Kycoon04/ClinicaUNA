/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    private TabPane tabPaneMantWorkers;
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
    private TextField textFieldSearch_Identification;
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
    private TabPane tabPaneMantWorkers1;
    @FXML
    private Tab tabManDoctors;
    @FXML
    private TextField codeDocMainField;
    @FXML
    private TextField licenseDocMainField;
    @FXML
    private TextField folioDocMainField;
    @FXML
    private TextField usernameMainField1;
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
    @FXML
    private BorderPane MenuView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OptionsMenuView.toFront();
        this.tableColAct.setCellValueFactory(new PropertyValueFactory("UsState"));
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
        this.tableColDocId.setCellValueFactory(new PropertyValueFactory("DrId"));
        this.tableColDocName.setCellValueFactory(new PropertyValueFactory("DrUser"));
        this.tableColDocFolio.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode.setCellValueFactory(new PropertyValueFactory("DrCode"));

        fillTableUsers();
        fillTableDoctors();
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
//jfoenix + cambiar tabla
    private void fillTableDoctors() {
        DoctorService service = new DoctorService();
        doctorList = service.getDoctor();
        if (doctorList.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "No hay ningun Doctor", getStage(), "");
        } else {
            doctorObservableList = FXCollections.observableArrayList(doctorList);
        }
        this.tableViewDoctors.refresh();
        this.tableViewDoctors.setItems(doctorObservableList);
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }



    @FXML
    private void deleteClicked(MouseEvent event) {
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void openManUsers(ActionEvent event) {
        OptionsMainUsersView.toFront();
    }

    @FXML
    private void UpdateUser(ActionEvent event) {
    }

    @FXML
    private void UpdateDoctor(ActionEvent event) {
    }

    @FXML
    private void deleteDoctorClicked(MouseEvent event) {
    }

    @FXML
    private void openManDoctors(ActionEvent event) {
        OptionsMainDoctorsView.toFront();

    }

    @FXML
    private void exit(ActionEvent event) {
        FlowController.getInstance().goMain("LoginView");
        
   }

    @FXML
    private void searchUser_Name(KeyEvent event) {
    }

    @FXML
    private void searchUser_Pusername(KeyEvent event) {
    }

    @FXML
    private void searchUser_username(KeyEvent event) {
    }

    @FXML
    private void searchUser_Rol(KeyEvent event) {
    }

    @FXML
    private void searchUser_Susername(KeyEvent event) {
    }

    @FXML
    private void searchUser_State(KeyEvent event) {
    }

    @FXML
    private void userClicked(MouseEvent event) {
    }

    @FXML
    private void searchDoctor_Name(KeyEvent event) {
    }

    @FXML
    private void searchDoctor_Pusername(KeyEvent event) {
    }

    @FXML
    private void searchDoctor_code(KeyEvent event) {
    }

    @FXML
    private void searchDoctor_Folio(KeyEvent event) {
    }

    @FXML
    private void searchDoctor_License(KeyEvent event) {
    }

    @FXML
    private void doctorClicked(MouseEvent event) {
    }

}
