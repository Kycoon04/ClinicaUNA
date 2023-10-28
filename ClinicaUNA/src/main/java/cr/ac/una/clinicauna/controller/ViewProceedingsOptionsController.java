/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.service.DiseaseService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

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
    private TableView<DiseaseDto> tableViewDisease;
    @FXML
    private TableColumn<DiseaseDto, String> tableColDeseaseId;
    @FXML
    private TableColumn<DiseaseDto, String> tableColDeseaseName;
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
    @FXML
    private Spinner<?> spinnerAppoinment;
    @FXML
    private TextField textFieldPatientIdent;
    @FXML
    private LineChart<?, ?> lineChartBodyMass;
    private boolean deleteDisease= false;
    

    DiseaseDto diseaseDto= new DiseaseDto();
    
    List<DiseaseDto> diseaseList = new ArrayList<>();
    private ObservableList<DiseaseDto> diseaseObservableList;
    
    List<AppointmentDto> reportList=new ArrayList<>();
    @FXML
    private BorderPane searchSelectDoctor;
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
    private TextField textFieldSearchDoc_State;
    @FXML
    private TableView<?> tableViewDoctors;
    @FXML
    private TableColumn<?, ?> tableColDocCode;
    @FXML
    private TableColumn<?, ?> tableColDocFolio;
    @FXML
    private TableColumn<?, ?> tableColDocName;
    @FXML
    private TableColumn<?, ?> tableColDocId;
    @FXML
    private TableColumn<?, ?> tableColDocLicense;
    @FXML
    private TableColumn<?, ?> tableColDocIniWork;
    @FXML
    private TableColumn<?, ?> tableColDocFinishWork;
    @FXML
    private TableColumn<?, ?> tableColDocBreaks;
    @FXML
    private TextField textFieldPatientGender;
    @FXML
    private TextField textFieldPatientEmail;
    @FXML
    private TextField textFieldPatientBirthday;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         OptionsProceedingsView.toFront();
         
        this.tableColDeseaseId.setCellValueFactory(new PropertyValueFactory("DsId"));
        this.tableColDeseaseName.setCellValueFactory(new PropertyValueFactory("DsName"));
     
        
        fillTableDiseases();
        nameDistMainField.setTextFormatter(Formato.getInstance().letrasFormat(10));
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

    
    private void fillTableDiseases() {

        DiseaseService service = new DiseaseService();
        diseaseList = service.getDisease();
        if (diseaseList.isEmpty()) {
        } else {
            diseaseObservableList = FXCollections.observableArrayList(diseaseList);
        }

        this.tableViewDisease.refresh();
        this.tableViewDisease.setItems(diseaseObservableList);
    }


    
    @FXML
    private void AddDesease(ActionEvent event) {
        DiseaseService service = new DiseaseService();
        Respuesta r = null;

        if (diseaseDto == null) {
            diseaseDto.setDsName(nameDistMainField.getText());
            r = service.saveDisease(diseaseDto);
            diseaseDto = new DiseaseDto();
        } else if (diseaseDto != null) {
            diseaseDto.setDsId(diseaseDto.getDsId());
            diseaseDto.setDsName(nameDistMainField.getText());
            r = service.saveDisease(diseaseDto);
            diseaseDto = new DiseaseDto();
        }
        if (r.getEstado()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Enfermedad", getStage(), "Enfermedad guardada");
            //limpiar campos
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Enfermedad", getStage(), "Error al Guardar Enfermedad");
        }

        fillTableDiseases();
        
    }

    @FXML
    private void searchDesease_Id(KeyEvent event) {
         FilteredList<DiseaseDto> filteredDisease = new FilteredList<>(diseaseObservableList, f -> true);
        textFieldSearchDesease_ID.textProperty().addListener((observable, value, newValue) -> {
            filteredDisease.setPredicate(DiseaseDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String search = newValue.toLowerCase();
                String disease = String.valueOf(DiseaseDto.getDsId());

                if (disease.indexOf(search) == 0) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDisease(filteredDisease);
    }

      private void filteredDisease(FilteredList<DiseaseDto> list) {
        SortedList<DiseaseDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewDisease.comparatorProperty());
        tableViewDisease.setItems(sorted);
    }
      
    @FXML
    private void searchDesease_Name(KeyEvent event) {
                FilteredList<DiseaseDto> filteredDisease = new FilteredList<>(diseaseObservableList, f -> true);
        textFieldSearchDesease_Name.textProperty().addListener((observable, value, newValue) -> {
            filteredDisease.setPredicate(DiseaseDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (DiseaseDto.getDsName().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredDisease(filteredDisease);
    }

    @FXML
    private void deleteDeseaseClicked(MouseEvent event) {
          deleteDisease = true;
    }

    @FXML
    private void deseaseClicked(MouseEvent event) {
        
  DiseaseService service = new DiseaseService();
        Respuesta r;
        if (event.getClickCount() == 1) {
            if (deleteDisease) {
                diseaseDto = tableViewDisease.getSelectionModel().getSelectedItem();
                if (diseaseDto != null) {
                    r = service.deleteDisease(diseaseDto.getDsId());
                    diseaseList.clear();
                    diseaseObservableList.clear();
                    fillTableDiseases();
                    deleteDisease = false;
                    diseaseDto = new DiseaseDto();
                    if (r.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Enfermedad Eliminada Correctamente");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Error al eliminar Enfermedad");
                    }
                }
            }
        }
        if (event.getClickCount() == 2) {
            diseaseDto = tableViewDisease.getSelectionModel().getSelectedItem();
            if (diseaseDto != null) {
                fillDisease(diseaseDto);
            }
        }
    }

      private void fillDisease(DiseaseDto diseaseDto) {
        nameDistMainField.setText(diseaseDto.getDsName());

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

    @FXML
    private void changeViewAppointment(MouseEvent event) {
        /*spinnerAppoinment = new Spinner<>(appointmentsList);

        // Configurar el StringConverter personalizado
        spinnerAppoinment.getValueFactory().setConverter(new StringConverter<>() {
            @Override
            public String toString(AppointmentDto appointment) {
                return appointment.;
            }

            @Override
            public Person fromString(String string) {
                return people.stream()
                        .filter(person -> person.getName().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });*/
    }
    
    private void loadAppoinmentList(){
        reportList.clear();
    }

    @FXML
    private void cleanUpProcceding(ActionEvent event) {
        
        
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
    private void searchDoctor_State(KeyEvent event) {
    }

    @FXML
    private void doctorClicked(MouseEvent event) {
    }

    @FXML
    private void backSearchDoctor(ActionEvent event) {
    }

    @FXML
    private void selectDoctor(ActionEvent event) {
    }
    
}
