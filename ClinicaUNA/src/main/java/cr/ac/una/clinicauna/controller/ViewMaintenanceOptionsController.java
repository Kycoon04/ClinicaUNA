/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> tableViewUser;
    @FXML
    private TableColumn<?, ?> tableColAct;
    @FXML
    private TableColumn<?, ?> tableColIdentif;
    @FXML
    private TableColumn<?, ?> tableColName;
    @FXML
    private TableColumn<?, ?> tableColPsurname;
    @FXML
    private TableColumn<?, ?> tableColSsurname;
    @FXML
    private TableColumn<?, ?> tableColUserName;
    @FXML
    private TableColumn<?, ?> tableColEmail;
    @FXML
    private TableColumn<?, ?> tableColAdmi;
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
    private TableView<?> tableViewDoctors;
    @FXML
    private TableColumn<?, ?> tableColDocCode;
    @FXML
    private TableColumn<?, ?> tableColDocFolio;
    @FXML
    private TableColumn<?, ?> tableColDocName;
    @FXML
    private TableColumn<?, ?> tableColDocPsurname;
    @FXML
    private TableColumn<?, ?> tableColDocLicense;
    @FXML
    private TableColumn<?, ?> tableColDocIniWork;
    @FXML
    private TableColumn<?, ?> tableColDocFinishWork;
    @FXML
    private TableColumn<?, ?> tableColDocBreaks;
    @FXML
    private Button BtndeleteDoctor;
    @FXML
    private JFXTimePicker timepickerFinWork;
    @FXML
    private Button btnMantUsers;
    @FXML
    private Button btnMantDoctors;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OptionsMenuView.toFront();
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }

    @FXML
    private void searchWorker_Name(KeyEvent event) {
    }

    @FXML
    private void searchWorker_Pusername(KeyEvent event) {
    }

    @FXML
    private void searchWorker_Identification(KeyEvent event) {
    }

    @FXML
    private void searchWorker_Rol(KeyEvent event) {
    }

    @FXML
    private void searchWorker_Susername(KeyEvent event) {
    }

    @FXML
    private void searchWorker_State(KeyEvent event) {
    }

    @FXML
    private void workerClicked(MouseEvent event) {
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

}
