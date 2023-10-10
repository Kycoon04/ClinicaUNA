/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

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
    private Tab tabMantWorkers;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OptionsMenuView.toFront();
    }    


    @FXML
    private void UpdateWorker(ActionEvent event) {
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
    
}
