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
import javafx.scene.control.Button;
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
    private BorderPane OptionsMainDesease;
    @FXML
    private TextField nameDistMainField;
    @FXML
    private TextField textFieldSearchDesease_ID;
    @FXML
    private TextField textFieldSearchDesease_Name;
    @FXML
    private Button BtndeletePatient1;
    @FXML
    private TableView<?> tableViewDisease;
    @FXML
    private TableColumn<?, ?> tableColDeseaseId;
    @FXML
    private TableColumn<?, ?> tableColDeseaseName;
    @FXML
    private Text textProcName;
    @FXML
    private TextField textFieldPersBgType;
    @FXML
    private TextField textFieldSearchPersBg_Type;
    @FXML
    private TextField textFieldSearchPersBg_Context;
    @FXML
    private TableView<?> tableViewPersonalBg;
    @FXML
    private TableColumn<?, ?> tableColPersBgType;
    @FXML
    private TableColumn<?, ?> tableColPersBgContext;
    @FXML
    private TextField textFieldFamBgRelationship;
    @FXML
    private TextField textFieldFamBgDisease;
    @FXML
    private TextField textFieldSearchFamBg_Relationship;
    @FXML
    private TextField textFieldSearchFamBg_Disease;
    @FXML
    private TableView<?> tableViewFamilyBg;
    @FXML
    private TableColumn<?, ?> tableColFamBgRelation;
    @FXML
    private TableColumn<?, ?> tableColFamBgDisease;
    @FXML
    private BorderPane ReportView;
    @FXML
    private TextField textFieldRep_Pressure;
    @FXML
    private TextField textFieldRep_HeartRate;
    @FXML
    private TextField textFieldRep_Height;
    @FXML
    private TextField textFieldRep_Weight;
    @FXML
    private TextField textFieldRep_Temperature;
    @FXML
    private TextField textFieldRep_BodyMass;
    @FXML
    private TextArea textAreaRep_Reason;
    @FXML
    private TextArea textAreaRep_Notes;
    @FXML
    private BorderPane OptionsProceedingsView;
    @FXML
    private TextArea textAreaDoctorNotes;
    @FXML
    private TextArea textAreaPersBgContext;
    @FXML
    private TextField textFieldSpaceState;
    @FXML
    private TextField textFieldSpaceDate;
    @FXML
    private TextField textFieldSpaceHour;
    @FXML
    private TextField textFieldSearchSpace_State;
    @FXML
    private TableView<?> tableViewPersonalBg1;
    @FXML
    private TableColumn<?, ?> tableColSpaceState;
    @FXML
    private TableColumn<?, ?> tableColPersBgContext1;
    @FXML
    private TableColumn<?, ?> tableColPersBgType11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         OptionsProceedingsView.toFront();
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

    @FXML
    private void AddDesease(ActionEvent event) {
    }

    @FXML
    private void searchDesease_Id(KeyEvent event) {
    }

    @FXML
    private void searchDesease_Name(KeyEvent event) {
    }

    @FXML
    private void deleteDeseaseClicked(MouseEvent event) {
    }

    @FXML
    private void deseaseClicked(MouseEvent event) {
    }

    @FXML
    private void updateExam(ActionEvent event) {
    }

    @FXML
    private void updatePersonalBg(ActionEvent event) {
    }

    @FXML
    private void personalBgClicked(MouseEvent event) {
    }


    @FXML
    private void updateFamilyBg(ActionEvent event) {
    }

    @FXML
    private void familiyBgClicked(MouseEvent event) {
    }

    @FXML
    private void backReport(ActionEvent event) {
        OptionsProceedingsView.toFront();
    }

    @FXML
    private void calculateBodyMass(ActionEvent event) {
    }

    @FXML
    private void generateReport(ActionEvent event) {
        ReportView.toFront();
    }

    @FXML
    private void selectDisease(MouseEvent event) {
         OptionsMainDesease.toFront();
    }
    
}
