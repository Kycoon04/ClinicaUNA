/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class ViewProceedingsOptionsController extends Controller implements Initializable {

    @FXML
    private BorderPane MenuView;
    @FXML
    private TabPane tabPaneMantWorkers111;
    @FXML
    private Tab tabPatient;
    @FXML
    private Tab tabDiary1;
    @FXML
    private Text textMainDoctor11;
    @FXML
    private RadioButton Attended;
    @FXML
    private RadioButton Scheduled;
    @FXML
    private RadioButton Cancelled;
    @FXML
    private RadioButton Absent;
    @FXML
    private TextArea reason;
    @FXML
    private TextField numberP;
    @FXML
    private TextField email;
    @FXML
    private TextField userLog;
    @FXML
    private TextField nameP;
    @FXML
    private TextField textFieldNameExam;
    @FXML
    private TextField textFieldDoctor;
    @FXML
    private TextArea textFieldDoctorNotes;
    @FXML
    private TextField textFieldSearchExam_Doctor;
    @FXML
    private TextField textFieldSearchExam_Name;
    @FXML
    private TableView<?> tableViewExamns;
    @FXML
    private TableColumn<?, ?> tableColExamName;
    @FXML
    private TableColumn<?, ?> tableColExamDoctor;
    @FXML
    private Tab tabDiary11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchPat_Name(KeyEvent event) {
    }

    @FXML
    private void searchPat_Pusername(KeyEvent event) {
    }

    @FXML
    private void searchPat_identification(KeyEvent event) {
    }



    @FXML
    private void ContinueDetail(ActionEvent event) {
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }

    @FXML
    private void backProceeding(ActionEvent event) {
        FlowController.getInstance().goMain("ViewMaintenanceOptions");
    }

    @FXML
    private void examClicked(MouseEvent event) {
    }

    @Override
    public void initialize() {
    }
    
}
