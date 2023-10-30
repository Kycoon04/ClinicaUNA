/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.ExamDto;
import cr.ac.una.clinicauna.model.FProceedingsDto;
import cr.ac.una.clinicauna.model.FamilybackgroundDto;
import cr.ac.una.clinicauna.model.PProceedingsDto;
import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.PersonalbackgroundDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.DiseaseService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.ExamService;
import cr.ac.una.clinicauna.service.FProceedingsService;
import cr.ac.una.clinicauna.service.FamilybackgroundService;
import cr.ac.una.clinicauna.service.PProceedingsService;
import cr.ac.una.clinicauna.service.PersonalbackgroundService;
import cr.ac.una.clinicauna.service.ProceedingsService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ChoiceBox;
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
    private TextField textFieldSearchExam_Doctor;
    @FXML
    private TextField textFieldSearchExam_Name;
    @FXML
    private TableView<ExamDto> tableViewExamns;
    @FXML
    private TableColumn<ExamDto, String> tableColExamName;
    @FXML
    private TableColumn<ExamDto, String> tableColExamDoctor;
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
    private TextField textFieldSearchPersBg_Type;
    @FXML
    private TextField textFieldSearchPersBg_Context;
    @FXML
    private TableView<PersonalbackgroundDto> tableViewPersonalBg;
    @FXML
    private TableColumn<PersonalbackgroundDto, String> tableColPersBgType;
    @FXML
    private TableColumn<PersonalbackgroundDto, String> tableColPersBgContext;
    @FXML
    private TextField textFieldFamBgRelationship;
    @FXML
    private TextField textFieldFamBgDisease;
    @FXML
    private TextField textFieldSearchFamBg_Relationship;
    @FXML
    private TextField textFieldSearchFamBg_Disease;
    @FXML
    private TableView<FamilybackgroundDto> tableViewFamilyBg;
    @FXML
    private TableColumn<FamilybackgroundDto, String> tableColFamBgRelation;
    @FXML
    private TableColumn<FamilybackgroundDto, String> tableColFamBgDisease;
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
    private boolean deleteDisease = false;

    List<ExamDto> patiExams = new ArrayList<ExamDto>();

    PatientDto patientDto = new PatientDto();

    DiseaseDto diseaseDto = new DiseaseDto();
    ExamDto examDto = new ExamDto();

    ProceedingsDto proceedingsDto = new ProceedingsDto();

    List<DiseaseDto> diseaseList = new ArrayList<>();
    private ObservableList<DiseaseDto> diseaseObservableList;

    List<AppointmentDto> reportList = new ArrayList<>();
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
    DoctorDto doctorDto = new DoctorDto();
    @FXML
    private TextField textFieldPatientGender;
    @FXML
    private TextField textFieldPatientEmail;
    @FXML
    private TextField textFieldPatientBirthday;
    @FXML
    private ChoiceBox<String> choiceBoxPersBgType;
    List<DoctorDto> doctorList = new ArrayList<>();

    List<ExamDto> examList = new ArrayList<>();
    private ObservableList<DoctorDto> doctorObservableList;
    private ObservableList<ExamDto> examsObservableList;

    PersonalbackgroundDto personalBkDto = new PersonalbackgroundDto();
    List<PersonalbackgroundDto> personalBaList = new ArrayList<>();
    private ObservableList<PersonalbackgroundDto> personalBackObservableList;

    PProceedingsDto PProceedingsDto = new PProceedingsDto();
    String[] typeSpanish = {"Patológicos", "Hospitalización", "Cirugias", "Alergias", "Tratamientos"};
    String[] typeEnglish = {"Pathological", "Hospitalization", "Surgery", "Allergies", "Treatments"};
    String[] typeJaponese = {"病理的", "入院", "手術", "アレルギー", "治療"};
    String[] typeFrench = {"Pathologiques", "Hospitalisation", "Chirurgies", "Allergies", "Traitements"};
    UserDto userDto;

    FProceedingsDto FProceedingsDto = new FProceedingsDto();

    FamilybackgroundDto familyBkDto = new FamilybackgroundDto();
    List<FamilybackgroundDto> familyBaList = new ArrayList<>();
    private ObservableList<FamilybackgroundDto> familyBackObservableList;
    @FXML
    private TextField textFieldDoctorSelected;

    //List<AppointmentDto> reportList = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OptionsProceedingsView.toFront();

        patientDto = (PatientDto) AppContext.getInstance().get("Patient");
        proceedingsDto = (ProceedingsDto) AppContext.getInstance().get("Proceding");
        userDto = (UserDto) AppContext.getInstance().get("Usuario");
        if (userDto.getUsLenguage().equals("Spanish")) {
            choiceBoxPersBgType.getItems().addAll(typeSpanish);
        }
        if (userDto.getUsLenguage().equals("English")) {
            choiceBoxPersBgType.getItems().addAll(typeEnglish);
        }
        if (userDto.getUsLenguage().equals("France")) {
            choiceBoxPersBgType.getItems().addAll(typeFrench);
        }
        if (userDto.getUsLenguage().equals("Japonese")) {
            choiceBoxPersBgType.getItems().addAll(typeJaponese);
        }
        choiceBoxPersBgType.setValue("Pathological");

        this.tableColDocBreaks.setCellValueFactory(new PropertyValueFactory("DrBreak"));
        this.tableColDocFinishWork.setCellValueFactory(new PropertyValueFactory("DrFinisworking"));
        this.tableColDocLicense.setCellValueFactory(new PropertyValueFactory("DrLicense"));
        this.tableColDocId.setCellValueFactory(new PropertyValueFactory("DoctorPsurname"));
        this.tableColDocName.setCellValueFactory(new PropertyValueFactory("DoctorName"));
        this.tableColDocFolio.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode.setCellValueFactory(new PropertyValueFactory("DrCode"));

        this.tableColExamName.setCellValueFactory(new PropertyValueFactory("EmName"));
        this.tableColExamDoctor.setCellValueFactory(new PropertyValueFactory("EmDoctorName"));

        this.tableColDeseaseId.setCellValueFactory(new PropertyValueFactory("DsId"));
        this.tableColDeseaseName.setCellValueFactory(new PropertyValueFactory("DsName"));

        this.tableColPersBgType.setCellValueFactory(new PropertyValueFactory("PbType"));
        this.tableColPersBgContext.setCellValueFactory(new PropertyValueFactory("PbContext"));

        this.tableColFamBgRelation.setCellValueFactory(new PropertyValueFactory("FbRelationship"));
        this.tableColFamBgDisease.setCellValueFactory(new PropertyValueFactory("FbDiseaseName"));

        bindPatient();
        fillTableExams();
        fillTableDoctors();
        fillTableDiseases();
        fillTablePersonalBack();
        fillTableFamilyBack();
        nameDistMainField.setTextFormatter(Formato.getInstance().letrasFormat(10));
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

    private void fillTableExams() {
        ExamService service = new ExamService();

        System.out.println(patientDto.getPtId());
        Respuesta r = service.getExamsByPatientId(patientDto.getPtId());

        if (r.getEstado()) {
            examList = (List<ExamDto>) r.getResultado("Exams");

            if (examList == null) {
            } else {
                examsObservableList = FXCollections.observableArrayList(examList);
                this.tableViewExamns.refresh();
                this.tableViewExamns.setItems(examsObservableList);
            }
        }
    }

    private void fillTablePersonalBack() {
        PersonalbackgroundService service = new PersonalbackgroundService();
        personalBaList = service.getPersonalbackgrounds();

        if (personalBaList == null) {
            System.out.println("nula");
        } else {
            personalBackObservableList = FXCollections.observableArrayList(personalBaList);
            this.tableViewPersonalBg.refresh();
            this.tableViewPersonalBg.setItems(personalBackObservableList);
        }

    }

    private void fillTableFamilyBack() {
        FamilybackgroundService service = new FamilybackgroundService();
        familyBaList = service.getFamilybackground();

        if (familyBaList == null) {
            System.out.println("nula");
        } else {
            familyBackObservableList = FXCollections.observableArrayList(familyBaList);
            this.tableViewFamilyBg.refresh();
            this.tableViewFamilyBg.setItems(familyBackObservableList);
        }
    }

    @FXML
    private void searchPat_Name(KeyEvent event) {
        FilteredList<PersonalbackgroundDto> filteredUser = new FilteredList<>(personalBackObservableList, f -> true);
        textFieldSearchPersBg_Type.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(PersonalbackgroundDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PersonalbackgroundDto.getPbType().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);

    }

    private void filteredUsers(FilteredList<PersonalbackgroundDto> list) {
        SortedList<PersonalbackgroundDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewPersonalBg.comparatorProperty());
        tableViewPersonalBg.setItems(sorted);
    }

    @FXML
    private void searchPat_identification(KeyEvent event) {
        FilteredList<PersonalbackgroundDto> filteredUser = new FilteredList<>(personalBackObservableList, f -> true);
        textFieldSearchPersBg_Context.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(PersonalbackgroundDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (PersonalbackgroundDto.getPbContext().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
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

    private void bindPatient() {
        if (patientDto != null) {
            textProcName.setText(patientDto.getPtName());
            textFieldPatientIdent.setText(patientDto.getPtIdentification());
            Date fecha = patientDto.getPtBirthdate();
            LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String date = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
            System.out.println(date);
            textFieldPatientBirthday.setText(date);
            textFieldPatientGender.setText(patientDto.getPtGender());
            textFieldPatientEmail.setText(patientDto.getPtEmail());
        }
    }

    @FXML
    private void AddDesease(ActionEvent event) {
        //crear boton seleccionar y guardar

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
            // textFieldFamBgDisease.setText(diseaseDto.);
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Enfermedad", getStage(), "Error al Guardar Enfermedad");
        }

        fillTableDiseases();

        // OptionsProceedingsView.toFront();
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
                    textFieldFamBgDisease.setText(diseaseDto.getDsName());
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
                OptionsProceedingsView.toFront();
                textFieldFamBgDisease.setText(diseaseDto.getDsName());
            }
        }
    }

    private void fillDisease(DiseaseDto diseaseDto) {
        nameDistMainField.setText(diseaseDto.getDsName());

    }

    @FXML
    private void updateExam(ActionEvent event) {
        ProceedingsService serviceProced = new ProceedingsService();
        Respuesta hasProc = serviceProced.getProcedingsIdPatient(patientDto.getPtId());

        if (hasProc.getEstado()) {
            proceedingsDto = (ProceedingsDto) hasProc.getResultado("Proceedings");
        } else {
            proceedingsDto.setPsId(0);
            proceedingsDto.setPsPatient(patientDto);
        }

        Respuesta resp = serviceProced.saveProcedings(proceedingsDto);
        if (resp.getEstado()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), " Expediente Guardado Correctamente");
        }
        resp = serviceProced.getProcedingsIdPatient(patientDto.getPtId());
        proceedingsDto = (ProceedingsDto) resp.getResultado("Proceedings");

        ExamService serviceExam = new ExamService();
        if (textFieldNameExam.getText() != "" && doctorDto != null) {
            examDto.setEmId(0);
            examDto.setEmName(textFieldNameExam.getText());
            examDto.setEmDoctornote(textAreaDoctorNotes.getText());
            examDto.setEmDoctor(doctorDto);
            examDto.setEmProceedings(proceedingsDto);
            resp = serviceExam.saveExam(examDto);
            if (resp.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Examen Guardado Correctamente");
                fillTableExams();
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Examen no guardado ");
            }
        }

    }

    @FXML
    private void updatePersonalBg(ActionEvent event) {

        ProceedingsService serviceProced = new ProceedingsService();
        Respuesta hasProc = serviceProced.getProcedingsIdPatient(patientDto.getPtId());

        if (hasProc.getEstado()) {
            proceedingsDto = (ProceedingsDto) hasProc.getResultado("Proceedings");
        } else {
            proceedingsDto.setPsId(0);
            proceedingsDto.setPsPatient(patientDto);
        }
        if (proceedingsDto != null) {
            savePersonalBackground();
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "No existe un expediente para este paciente");
        }

    }

    private void savePersonalBackground() {
        PersonalbackgroundService service = new PersonalbackgroundService();
        PProceedingsService serviceProP = new PProceedingsService();
        int codigo = codeRandom();
        personalBkDto.setPbId(personalBkDto.getPbId());
        String typeSelected = choiceBoxPersBgType.getValue();
        String type = "";
        if (typeSelected.equals("Patológicos") || typeSelected.equals("Pathological") || typeSelected.equals("病理的") || typeSelected.equals("Pathologiques")) {
            type = "Pathological";
        }
        if (typeSelected.equals("Hospitalización") || typeSelected.equals("Hospitalization") || typeSelected.equals("入院") || typeSelected.equals("Hospitalisation")) {
            type = "Hospitalizations";
        }
        if (typeSelected.equals("Cirugias") || typeSelected.equals("Surgery") || typeSelected.equals("手術") || typeSelected.equals("Chirurgies")) {
            type = "Surgery";
        }
        if (typeSelected.equals("Alergias") || typeSelected.equals("Allergies") || typeSelected.equals("アレルギー") || typeSelected.equals("Allergies")) {
            type = "Allergies";
        }
        if (typeSelected.equals("Tratamientos") || typeSelected.equals("Treatments") || typeSelected.equals("治療") || typeSelected.equals("Traitements")) {
            type = "Treatments";
        }

        personalBkDto.setPbType(type);
        personalBkDto.setPbContext(textAreaPersBgContext.getText());
        personalBkDto.setPbFilecode(codigo);
        System.out.println(codigo);
        if (personalBkDto.getPbContext() != "") {
            Respuesta personalBac = service.savePersonalbackground(personalBkDto);

            if (personalBac.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Se guardo el antecedente");
                fillTablePersonalBack();
                personalBac = service.getPersonalbackgroundCode(codigo);
                personalBkDto = (PersonalbackgroundDto) personalBac.getResultado("PersonalBackground");
                if (personalBac.getEstado()) {
                    PProceedingsDto.setPpId(0);
                    PProceedingsDto.setPpPersonalback(personalBkDto);
                    PProceedingsDto.setPpProceedings(proceedingsDto);
                    Respuesta procedings = serviceProP.savePProceedings(PProceedingsDto);
                    if (procedings.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Se guardo en su expediente");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "no se guardo en su expediente");
                    }
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Ocurrio un error en el proceso");

        }

    }

    int codeRandom() {
        int i, r;
        for (i = 0; i < 6; i++) {
            r = (int) (Math.random() * (90 - 48 + 1) + 48);
            if ((r > 47 && r < 58) || (r > 64 && r < 91)) {
                return r;
            } else {
                i--;
            }
        }
        return 0;
    }

    @FXML
    private void personalBgClicked(MouseEvent event) {
        if(event.getClickCount()==2){
            personalBkDto= tableViewPersonalBg.getSelectionModel().getSelectedItem();
            textAreaPersBgContext.setText(personalBkDto.getPbContext());
        }
        
    }

    @FXML
    private void updateFamilyBg(ActionEvent event) {
        saveFamilyBackground();
    }

    private void saveFamilyBackground() {
        FamilybackgroundService service = new FamilybackgroundService();
        FProceedingsService serviceProP = new FProceedingsService();
        int codigo = codeRandom();
        familyBkDto.setFbId(familyBkDto.getFbId());
        familyBkDto.setFbFilecode(codigo);
        familyBkDto.setFbRelationship(textFieldFamBgRelationship.getText());
        familyBkDto.setFbDisease(diseaseDto);
        Respuesta familyBac = service.saveFamilybackground(familyBkDto);
        if (familyBac.getEstado()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Se guardo el antecedente");
            familyBac = service.getFamilybackgroundCode(codigo);
            familyBkDto = (FamilybackgroundDto) familyBac.getResultado("Familybackground");
            if (familyBac.getEstado()) {
                FProceedingsDto.setFpId(0);
                FProceedingsDto.setFpFamilyback(familyBkDto);
                FProceedingsDto.setFpProceedings(proceedingsDto);
                Respuesta procedings = serviceProP.savefProceedings(FProceedingsDto);
                if (procedings.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Se guardo en su expediente");
                    fillTableFamilyBack();
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "no se guardo en su expediente");
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, " ", getStage(), "Ocurrio un error en el proceso");
        }
    }

    @FXML
    private void familiyBgClicked(MouseEvent event) {
         if(event.getClickCount()==2){
            familyBkDto= tableViewFamilyBg.getSelectionModel().getSelectedItem();
            textFieldFamBgRelationship.setText(familyBkDto.getFbDiseaseName());
            textFieldFamBgDisease.setText(familyBkDto.getFbDiseaseName());
        } 
    }

    @FXML
    private void backReport(ActionEvent event) {
        ProceedingsService serviceProced = new ProceedingsService();
        Respuesta hasProc = serviceProced.getProcedingsIdPatient(patientDto.getPtId());

        if (hasProc.getEstado()) {
            proceedingsDto = (ProceedingsDto) hasProc.getResultado("Proceedings");
        } else {
            proceedingsDto.setPsId(0);
            proceedingsDto.setPsPatient(patientDto);
        }
        if (proceedingsDto != null) {
            saveFamilyBackground();
        } else {

        }
    }

    @FXML
    private void calculateBodyMass(ActionEvent event) {

        double height = 0;
        double weight = 0;

        if (textFieldRep_Height.getText() != "" && textFieldRep_Weight.getText() != "") {
            height = Double.parseDouble(textFieldRep_Height.getText());
            weight = Double.parseDouble(textFieldRep_Weight.getText());
            double IMC = weight / (height * height) * 10000;
            double roundedIMC = Math.round(IMC * 10.0) / 10.0;
            textFieldRep_BodyMass.setText(roundedIMC + "");
        }
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

    private void loadAppoinmentList() {
        reportList.clear();
    }

    @FXML
    private void cleanUpProcceding(ActionEvent event) {

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
    private void searchDoctor_State(KeyEvent event) {
    }

    private void filteredDoctors(FilteredList<DoctorDto> list) {
        SortedList<DoctorDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewDoctors.comparatorProperty());
        tableViewDoctors.setItems(sorted);
    }

    @FXML
    private void doctorClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            doctorDto = tableViewDoctors.getSelectionModel().getSelectedItem();
            OptionsProceedingsView.toFront();
        }

    }

    @FXML
    private void backSearchDoctor(ActionEvent event) {
        OptionsProceedingsView.toFront();
    }

    @FXML
    private void selectDoctor(MouseEvent event) {
        searchSelectDoctor.toFront();
    }

    @FXML
    private void updateReportAp(ActionEvent event) {

    }

}
