/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.SpaceDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.AppointmentService;
import cr.ac.una.clinicauna.service.DiaryService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.PatientService;
import cr.ac.una.clinicauna.service.SpaceService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author dilan
 */
public class ViewDiariesOptionsController extends Controller implements Initializable {

    @FXML
    private BorderPane MenuView;
    @FXML
    private TextField textFieldSearchPat_Name1;
    @FXML
    private TextField textFieldSearchPat_Pusername1;
    @FXML
    private TextField textFieldSearchPat_Identification1;
    @FXML
    private TextField textFieldSearchPat_Gender1;
    @FXML
    private TextField textFieldSearchPat_Susername1;
    @FXML
    private TableView<PatientDto> tableViewPatient1;
    @FXML
    private TableColumn<PatientDto, String> tableColId1;
    @FXML
    private TableColumn<PatientDto, String> tableColPatIdentif1;
    @FXML
    private TableColumn<PatientDto, String> tableColPatName1;
    @FXML
    private TableColumn<PatientDto, String> tableColPatPsurname1;
    @FXML
    private TableColumn<PatientDto, String> tableColPatSsurname1;
    @FXML
    private TableColumn<PatientDto, String> tableColPatGender1;
    @FXML
    private TableColumn<PatientDto, String> tableColPatEmail1;
    @FXML
    private Text textMainDoctor11;
    @FXML
    private TextField nameP;
    @FXML
    private TextField userLog;
    @FXML
    private TextArea reason;
    @FXML
    private TextField numberP;
    @FXML
    private TextField email;
    @FXML
    private RadioButton Attended;
    @FXML
    private RadioButton Scheduled;
    @FXML
    private RadioButton Cancelled;
    @FXML
    private RadioButton Absent;
    @FXML
    private TabPane tabPaneMantWorkers112;
    @FXML
    private Tab tabDoc1;
    @FXML
    private TextField textFieldSearchDoc_Name11;
    @FXML
    private TextField textFieldSearchDoc_Pusername11;
    @FXML
    private TextField textFieldSearchDoc_Code11;
    @FXML
    private TextField textFieldSearchDoc_Folio11;
    @FXML
    private TextField textFieldSearchDoc_License11;
    @FXML
    private TextField textFieldSearchDoc_State11;
    @FXML
    private TableView<DoctorDto> tableViewDoctorsDiary1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocCode11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocFolio11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocName11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocId11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocLicense11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocIniWork11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocFinishWork11;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocBreaks11;
    @FXML
    private TextField namePatMainField1;
    @FXML
    private TextField firstNamePatMainField1;
    @FXML
    private TextField lastNamePatMainField1;
    @FXML
    private TextField identPatMainField1;
    @FXML
    private RadioButton radioBtnMale1;
    @FXML
    private RadioButton radioBtnFemale1;
    @FXML
    private TextField emailPatMainField1;
    @FXML
    private JFXDatePicker datePickerBirthdayPat1;
    @FXML
    private BorderPane CreateAppointment;
    @FXML
    private AnchorPane rootDocDiary;
    @FXML
    private Text textMainDoctor12;
    @FXML
    private JFXDatePicker DayPicker;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocSpaces11;
    @FXML
    private Text textMainDoctor121;
    @FXML
    private TextField code;
    @FXML
    ComboBox<String> spaces = new ComboBox();
    @FXML
    private Button btnAgendar;
    @FXML
    private BorderPane OptionsViewDiary;
    @FXML
    private BorderPane OptionsMantPatient;
    @FXML
    private BorderPane OptionsSelectPatient;
    @FXML
    private BorderPane OptionsAppoinmentInfo;

    private ToggleGroup Tou = new ToggleGroup();
    private ToggleGroup gender = new ToggleGroup();
    private AppointmentDto appointmentDto = new AppointmentDto();
    private AppointmentDto appointmentDtoModi = new AppointmentDto();
    private UserDto userDto = new UserDto();
    private DoctorDto doctorDto = new DoctorDto();
    private PatientDto patientDto = new PatientDto();
    private DiaryDto diaryDto = new DiaryDto();
    private GridPane DiaryPane;
    private SpaceDto spacesDto = new SpaceDto();
    private List<Label> citasAgregadasList = new ArrayList<>();
    private List<Label> citasAgregadaDBsList = new ArrayList<>();
    private List<Label> citasPosiblesDBsList = new ArrayList<>();
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private List<LocalTime> horasAgregadas = new ArrayList<>();
    private List<LocalTime> horasModificadas = new ArrayList<>();
    private String matrizAgenda[][] = new String[15][8];
    private List<DoctorDto> doctorList = new ArrayList<>();
    private ObservableList<DoctorDto> doctorObservableList;
    private List<PatientDto> patientList = new ArrayList<>();
    private ObservableList<PatientDto> patientObservableList;
    private boolean modificarCita = false;
    private int movespace = 0;
    private String[] mint;
    private int HoraSelecionada = 0;
    private List<Integer> horasTotales = new ArrayList<>();
    private boolean canupdate = false;
    @FXML
    private Button UpdateEmailAppointment;
    @FXML
    private Button UpdateTelephoneAppointment;
    @FXML
    private Button btnRecordatorio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAgendar.setDisable(true);
        UpdateEmailAppointment.setDisable(true);
        UpdateTelephoneAppointment.setDisable(true);
        MenuView.toFront();
        CreateAppointment.toFront();
        this.tableColPatIdentif1.setCellValueFactory(new PropertyValueFactory("PtIdentification"));
        this.tableColPatName1.setCellValueFactory(new PropertyValueFactory("PtName"));
        this.tableColPatPsurname1.setCellValueFactory(new PropertyValueFactory("PtPlastname"));
        this.tableColPatSsurname1.setCellValueFactory(new PropertyValueFactory("PtSlastname"));
        this.tableColPatGender1.setCellValueFactory(new PropertyValueFactory("PtGender"));
        this.tableColPatEmail1.setCellValueFactory(new PropertyValueFactory("PtEmail"));
        this.tableColId1.setCellValueFactory(new PropertyValueFactory("PtId"));

        this.tableColDocBreaks11.setCellValueFactory(new PropertyValueFactory("DrBreak"));
        this.tableColDocFinishWork11.setCellValueFactory(new PropertyValueFactory("DrFinisworking"));
        this.tableColDocLicense11.setCellValueFactory(new PropertyValueFactory("DrLicense"));
        this.tableColDocId11.setCellValueFactory(new PropertyValueFactory("DoctorPsurname"));
        this.tableColDocName11.setCellValueFactory(new PropertyValueFactory("DoctorName"));
        this.tableColDocFolio11.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork11.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode11.setCellValueFactory(new PropertyValueFactory("DrCode"));

        Absent.setToggleGroup(Tou);
        Cancelled.setToggleGroup(Tou);
        Attended.setToggleGroup(Tou);
        Scheduled.setToggleGroup(Tou);

        this.radioBtnFemale1.setToggleGroup(gender);
        this.radioBtnMale1.setToggleGroup(gender);

        ObservableList<String> items = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4"
        );
        spaces.setValue("1");
        spaces.setItems(items);
        fillTablePatient();
        fillTableDoctors();

    }

    private Integer[] getYears(int startYear, int endYear) {
        return IntStream.rangeClosed(startYear, endYear).boxed().toArray(Integer[]::new);
    }

    private boolean fillAppoiment() {
        Respuesta r = null;
        AppointmentService service = new AppointmentService();
        this.userDto = (UserDto) AppContext.getInstance().get("Usuario");
        if (userDto != null && patientDto != null && !code.getText().isEmpty()) {
            appointmentDto.setAtCode(code.getText());
            appointmentDto.setAtPatient(patientDto);
            appointmentDto.setAtUserregister(userDto);
            appointmentDto.setAtReason(reason.getText());
            Long numLong = Long.parseLong(numberP.getText());
            appointmentDto.setAtTelephone(numLong);
            appointmentDto.setAtEmail(email.getText());
            if (Scheduled.isSelected()) {
                appointmentDto.setAtState("Programada");
            } else if (Attended.isSelected()) {
                appointmentDto.setAtState("Atendida");
            } else if (Cancelled.isSelected()) {
                appointmentDto.setAtState("Cancelada");
            } else {
                appointmentDto.setAtState("Ausente");
            }
            r = service.saveAppointment(appointmentDto);
            if (r.getEstado()) {
                appointmentDto = (AppointmentDto) r.getResultado("Appointments");
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Cita Registrada");
                return true;
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Cita No se pudo Registrar");
                return false;
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Complete la informacion");
            return false;
        }
    }

    @FXML
    private void ContinueDetail(ActionEvent event) {
        DiaryService servicediary = new DiaryService();
        if (!modificarCita) {
            List<SpaceDto> espaciosReservados = new ArrayList<>();
            Respuesta respuesta = null;
            if (fillAppoiment() && doctorDto != null && DayPicker.getValue() != null) {
                int espacios = 1;
                SpaceService service = new SpaceService();
                try {
                    espacios = Integer.parseInt(spaces.getValue());
                } catch (NumberFormatException e) {
                }
                for (int i = 0; i < espacios; i++) {
                    spacesDto = new SpaceDto();
                    spacesDto.setSeId(0);
                    spacesDto.setSeAppointment(appointmentDto);
                    String horaFormateada = horasAgregadas.get(i).format(timeFormatter);
                    spacesDto.setSeHour(horaFormateada);
                    respuesta = service.saveSpace(spacesDto);
                }
                espaciosReservados = service.getSpace();
                espaciosReservados = espaciosReservados.stream().filter(x -> x.getSeAppointment().getAtId() == appointmentDto.getAtId()).toList();
                for (int i = 0; i < espacios; i++) {
                    diaryDto.setDyId(0);
                    diaryDto.setDyDoctor(doctorDto);
                    diaryDto.setDyDate(DayPicker.getValue());
                    diaryDto.setDySpace(espaciosReservados.get(i));
                    respuesta = servicediary.saveDiary(diaryDto);
                    if (respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "diario Registrada");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "diario No se pudo Registrar");
                    }
                }
                lookday(event);
                appointmentDto = new AppointmentDto();
                spacesDto = new SpaceDto();
                respuesta = servicediary.emailDiary(diaryDto);
                diaryDto = new DiaryDto();
            }

        } else {
            AppointmentService service = new AppointmentService();
            Respuesta respuesta = null;

            appointmentDtoModi.setAtReason(reason.getText());
            Long numLong = Long.parseLong(numberP.getText());
            appointmentDtoModi.setAtTelephone(numLong);
            appointmentDtoModi.setAtEmail(email.getText());
            if (Scheduled.isSelected()) {
                appointmentDtoModi.setAtState("Programada");
            } else if (Attended.isSelected()) {
                appointmentDtoModi.setAtState("Atendida");
            } else if (Cancelled.isSelected()) {
                appointmentDtoModi.setAtState("Cancelada");
            } else {
                appointmentDtoModi.setAtState("Ausente");
            }
            respuesta = service.saveAppointment(appointmentDtoModi);
            if (respuesta.getEstado()) {
                lookday(event);
                cleanUpAppointment(event);
                modificarCita = false;
                nameP.setDisable(false);
                userLog.setDisable(false);
                code.setDisable(false);
                OptionsViewDiary.toFront();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Cita modificada");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Cita No modificada");
            }
        }
    }

    @FXML
    private void backDiary(MouseEvent event) {
        FlowController.getInstance().goMain("ViewMaintenanceOptions");
    }

    @FXML
    private void UpdatePatient(ActionEvent event) {
        PatientService patient = new PatientService();
        Respuesta r = null;
        r = patient.savePatient(bindNewPatient());
        fillTablePatient();
        patientDto = new PatientDto();

        if (r.getEstado()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", getStage(), "Paciente Guardado");
            //limpiar los campos 
            OptionsSelectPatient.toFront();
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", getStage(), "Error al guardar paciente");
        }
        patientDto = new PatientDto();
    }

    private void cleanUpPatient() {
        namePatMainField1.clear();
        firstNamePatMainField1.clear();
        lastNamePatMainField1.clear();
        emailPatMainField1.clear();
        datePickerBirthdayPat1.setValue(null);
        identPatMainField1.clear();
        gender.selectToggle(null);
    }

    PatientDto bindNewPatient() {
        LocalDate localDate = datePickerBirthdayPat1.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String genere = "";
        patientDto.setPtBirthdate(date);
        patientDto.setPtEmail(emailPatMainField1.getText());
        patientDto.setPtId(patientDto.getPtId());
        if (radioBtnMale1.isSelected()) {
            genere = "M";
        } else {
            genere = "F";
        }
        patientDto.setPtGender(genere);
        patientDto.setPtIdentification(identPatMainField1.getText());
        patientDto.setPtName(namePatMainField1.getText());
        patientDto.setPtPlastname(firstNamePatMainField1.getText());
        patientDto.setPtSlastname(lastNamePatMainField1.getText());
        return patientDto;
    }

    @FXML
    private void newPat(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nuevo Paciente");
        alert.setHeaderText(null);
        alert.setContentText("¿Quieres crear un nuevo paciente?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            OptionsMantPatient.toFront();
        } else {
        }
    }

    private void filterDoctorsByDay(ActionEvent event) {

        DoctorService service = new DoctorService();
        List<DoctorDto> listDoct = new ArrayList<>();

    }

    private void fillTablePatient() {

        PatientService service = new PatientService();
        patientList = service.getPatients();
        if (patientList.isEmpty()) {
        } else {
            patientObservableList = FXCollections.observableArrayList(patientList);
        }

        this.tableViewPatient1.refresh();
        this.tableViewPatient1.setItems(patientObservableList);
    }

    private void fillTableDoctors() {
        DoctorService service = new DoctorService();
        doctorList = service.getDoctor();
        if (doctorList == null) {
        } else {
            doctorObservableList = FXCollections.observableArrayList(doctorList);
        }
        this.tableViewDoctorsDiary1.refresh();
        this.tableViewDoctorsDiary1.setItems(doctorObservableList);
    }

    @FXML
    private void patientClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            this.userDto = (UserDto) AppContext.getInstance().get("Usuario");
            patientDto = tableViewPatient1.getSelectionModel().getSelectedItem();
            if (patientDto != null) {
                UpdateEmailAppointment.setDisable(false);
                UpdateTelephoneAppointment.setDisable(false);
                nameP.setText(patientDto.getPtName());
                userLog.setText(userDto.getUsName());
                OptionsAppoinmentInfo.toFront();
            }
        }
    }

    @FXML
    private void searchPat_Name(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Name1.textProperty().addListener((observable, value, newValue) -> {
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
        sorted.comparatorProperty().bind(tableViewPatient1.comparatorProperty());
        tableViewPatient1.setItems(sorted);
    }

    @FXML
    private void searchPat_Pusername(KeyEvent event) {
        FilteredList<PatientDto> filteredPatient = new FilteredList<>(patientObservableList, f -> true);
        textFieldSearchPat_Pusername1.textProperty().addListener((observable, value, newValue) -> {
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
        textFieldSearchPat_Identification1.textProperty().addListener((observable, value, newValue) -> {
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
        textFieldSearchPat_Gender1.textProperty().addListener((observable, value, newValue) -> {
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
        textFieldSearchPat_Susername1.textProperty().addListener((observable, value, newValue) -> {
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
    private void doctorDiaryClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            doctorDto = tableViewDoctorsDiary1.getSelectionModel().getSelectedItem();
            textMainDoctor12.setText(doctorDto.getDoctorName());
            OptionsViewDiary.toFront();
        }
    }

    public void eventAgendDoct() {
        String horaInicio = doctorDto.getDrIniworking();
        String horaFin = doctorDto.getDrFinisworking();
        String[] partesInicio = horaInicio.split(":");
        String[] partesFin = horaFin.split(":");

        int horaInicioInt = Integer.parseInt(partesInicio[0]);
        int horaFinInt = Integer.parseInt(partesFin[0]);
        DiaryPane = createWeekCalendarWithHeaders(horaInicioInt, horaFinInt, doctorDto.getDrSpaces());
        DiaryPane.setPrefSize(rootDocDiary.getPrefWidth() / 2 + 500, rootDocDiary.getPrefHeight() / 2 + 180);
        DiaryPane.setGridLinesVisible(true);
        DiaryPane.getChildren().removeAll(citasAgregadasList);
        citasAgregadasList.clear();
        AnchorPane.setTopAnchor(DiaryPane, (rootDocDiary.getHeight() - DiaryPane.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(DiaryPane, (rootDocDiary.getWidth() - DiaryPane.getPrefWidth()) / 2);

        DiaryPane.setOnDragOver(event -> {
            if (event.getGestureSource() != DiaryPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        DiaryPane.setOnDragDropped(event -> {

            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                double dropX = event.getX();
                double dropY = event.getY();

                int dropColumn = (int) (dropX / (DiaryPane.getWidth() / DiaryPane.getColumnConstraints().size()));
                int dropRow = (int) (dropY / (DiaryPane.getHeight() / DiaryPane.getRowConstraints().size()));
                HoraSelecionada = horasTotales.get(dropRow - 1);
                int citasAgregadas = 0;
                while (citasAgregadas < movespace) {
                    if (isCellLabelEmpty(DiaryPane, dropRow, dropColumn)) {
                        ActionEvent events = new ActionEvent();
                        lookday(events);
                        movespace = 0;
                        return;
                    }
                    citasAgregadas++;
                    if (dropColumn + 1 >= DiaryPane.getColumnConstraints().size()) {
                        if (dropRow >= DiaryPane.getRowConstraints().size()) {
                            break;
                        }
                        dropColumn = 1;
                        dropRow++;
                    } else {
                        dropColumn++;
                    }
                }
                citasAgregadas = 1;
                dropColumn = (int) (dropX / (DiaryPane.getWidth() / DiaryPane.getColumnConstraints().size()));
                dropRow = (int) (dropY / (DiaryPane.getHeight() / DiaryPane.getRowConstraints().size()));
                LocalTime horaActual = null;
                Label draggedLabel = (Label) event.getGestureSource();
                SpaceDto referente = (SpaceDto) draggedLabel.getUserData();
                DiaryPane.getChildren().remove(draggedLabel);
                DiaryPane.add(draggedLabel, dropColumn, dropRow);
                if (citasAgregadas <= mint.length) {
                    horaActual = LocalTime.of(HoraSelecionada, Integer.parseInt(mint[dropColumn - 1]));
                    horasModificadas.add(horaActual);
                }
                citasPosiblesDBsList.add(draggedLabel);

                while (citasAgregadas < movespace) {
                    if (dropColumn + 1 >= DiaryPane.getColumnConstraints().size()) {
                        if (dropRow >= DiaryPane.getRowConstraints().size()) {
                            break;
                        }
                        dropColumn = 1;
                        dropRow++;
                        HoraSelecionada++;
                    } else {
                        dropColumn++;
                    }
                    Label label = new Label(draggedLabel.getText());
                    label.setStyle(draggedLabel.getStyle());
                    GridPane.setColumnSpan(label, 1);
                    GridPane.setRowSpan(label, 1);
                    GridPane.setColumnIndex(label, dropColumn);
                    GridPane.setRowIndex(label, dropRow);

                    DiaryPane.getChildren().add(label);
                    citasPosiblesDBsList.add(label);
                    horaActual = null;
                    if (citasAgregadas <= mint.length) {
                        horaActual = LocalTime.of(HoraSelecionada, Integer.parseInt(mint[dropColumn - 1]));
                        horasModificadas.add(horaActual);
                    }
                    citasAgregadas++;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modificar Cita");
                alert.setHeaderText(null);
                alert.setContentText("¿Quieres modificar esta cita?");
                ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
                if (result == ButtonType.OK) {
                    SpaceService service = new SpaceService();
                    List<SpaceDto> actualizados = service.getSpace();
                    actualizados = actualizados.stream().filter(x -> x.getSeAppointment().getAtId() == referente.getSeAppointment().getAtId()).toList();
                    int k = 0;
                    for (SpaceDto p : actualizados) {
                        spacesDto = p;
                        spacesDto.setSeHour(horasModificadas.get(k).format(timeFormatter));
                        Respuesta respuesta = service.saveSpace(spacesDto);
                        k++;
                    }
                    ActionEvent events = new ActionEvent();
                    lookday(events);
                } else {
                    ActionEvent events = new ActionEvent();
                    lookday(events);
                }
                movespace = 0;
                horasModificadas.clear();
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        rootDocDiary.heightProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setTopAnchor(DiaryPane, (newVal.doubleValue() - DiaryPane.getPrefHeight()) / 2));

        rootDocDiary.widthProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setLeftAnchor(DiaryPane, (newVal.doubleValue() - DiaryPane.getPrefWidth()) / 2));

        rootDocDiary.getChildren().add(DiaryPane);
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }

    public GridPane createWeekCalendarWithHeaders(int iniHora, int finHora, int v) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        mint = new String[v];
        int doctoSpaces = v;

        if (doctoSpaces == 2) {
            mint[0] = "00";
            mint[1] = "30";
        } else if (doctoSpaces == 3) {
            mint[0] = "00";
            mint[1] = "20";
            mint[2] = "40";
        } else {
            mint[0] = "00";
            mint[1] = "15";
            mint[2] = "30";
            mint[3] = "45";
        }
        for (int i = 0; i < mint.length; i++) {
            Label dayLabel = new Label(mint[i]);
            dayLabel.setAlignment(Pos.CENTER);
            dayLabel.setStyle("-fx-font-weight: bold");

            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);

            gridPane.add(dayLabel, i + 1, 0);
        }

        for (int i = iniHora; i <= finHora; i++) {
            Label hourLabel = new Label(String.format("%02d:00", i));
            hourLabel.setAlignment(Pos.CENTER);
            hourLabel.setStyle("-fx-font-weight: bold");

            hourLabel.setMaxWidth(Double.MAX_VALUE);
            hourLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(hourLabel, true);
            GridPane.setFillHeight(hourLabel, true);

            gridPane.add(hourLabel, 0, i - iniHora + 1);
        }

        for (int day = 1; day <= mint.length; day++) {
            for (int hour = iniHora; hour <= finHora; hour++) {
                Label cellLabel = new Label();
                cellLabel.setStyle("-fx-font-size: 10");

                cellLabel.setMaxWidth(Double.MAX_VALUE);
                cellLabel.setMaxHeight(Double.MAX_VALUE);
                cellLabel.setAlignment(Pos.TOP_LEFT);
                GridPane.setFillWidth(cellLabel, true);
                GridPane.setFillHeight(cellLabel, true);

                final int finalHour = hour;
                horasTotales.add(hour);
                cellLabel.setOnMouseClicked(event -> {
                    try {
                        handleCellClick(gridPane, cellLabel, mint, finalHour);
                    } catch (ParseException ex) {
                        Logger.getLogger(ViewDiariesOptionsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                gridPane.add(cellLabel, day, hour - iniHora + 1);
            }
        }

        for (int i = 0; i < mint.length + 1; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
            columnConstraints.setPercentWidth(80.0 / (mint.length + 1));
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < calcularNumeroHoras(iniHora, finHora) + 1; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);
            rowConstraints.setPercentHeight(100.0 / (15 + 1));
            gridPane.getRowConstraints().add(rowConstraints);
        }

        return gridPane;
    }

    public static int calcularNumeroHoras(int horaInicio, int horaFin) {

        horaInicio = (horaInicio + 24) % 24;
        horaFin = (horaFin + 24) % 24;

        int diferenciaHoras = horaFin - horaInicio;

        if (diferenciaHoras < 0) {
            diferenciaHoras += 24;
        }

        return diferenciaHoras;
    }

    private boolean isCellLabelEmpty(GridPane gridPane, int rowIndex, int columnIdx) {
        for (Node child : gridPane.getChildren()) {
            if (child instanceof Label
                    && GridPane.getRowIndex(child) == rowIndex
                    && GridPane.getColumnIndex(child) == columnIdx) {
                Label label = (Label) child;
                if (label.getText() == null || !label.getText().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void handleCellClick(GridPane gridPane, Label cellLabel, String min[], int hour) throws ParseException {
        int maxCitas = Integer.parseInt(spaces.getValue());

        gridPane.getChildren().removeAll(citasAgregadasList);
        citasAgregadasList.clear();

        horasAgregadas = new ArrayList<>();

        int citasAgregadas = 0;
        int startColumn = GridPane.getColumnIndex(cellLabel);
        int columnIdx = startColumn;
        int rowIndex = GridPane.getRowIndex(cellLabel);

        while (citasAgregadas < maxCitas) {
            if (isCellLabelEmpty(gridPane, rowIndex, columnIdx)) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error", getStage(), "No se pueden registrar los campos");
                return;
            }
            citasAgregadas++;
            if (columnIdx + 1 >= gridPane.getColumnConstraints().size()) {
                if (rowIndex >= gridPane.getRowConstraints().size()) {
                    break;
                }
                columnIdx = 1;
                rowIndex++;
            } else {
                columnIdx++;
            }
        }

        citasAgregadas = 0;
        startColumn = GridPane.getColumnIndex(cellLabel);
        columnIdx = startColumn;
        rowIndex = GridPane.getRowIndex(cellLabel);

        while (citasAgregadas < maxCitas) {
            Label label = new Label("Cita");
            label.setStyle("-fx-font-size: 15");
            GridPane.setColumnSpan(label, 1);
            GridPane.setRowSpan(label, 1);
            GridPane.setColumnIndex(label, columnIdx);
            GridPane.setRowIndex(label, rowIndex);

            gridPane.getChildren().add(label);
            citasAgregadasList.add(label);

            LocalTime horaActual = null;
            if (citasAgregadas <= min.length) {
                horaActual = LocalTime.of(hour, Integer.parseInt(min[columnIdx - 1]));
                horasAgregadas.add(horaActual);
            }

            citasAgregadas++;

            if (columnIdx + 1 >= gridPane.getColumnConstraints().size()) {
                if (rowIndex >= gridPane.getRowConstraints().size()) {
                    break;
                }
                columnIdx = 1;
                rowIndex++;
                hour++;
            } else {
                columnIdx++;
            }
        }
        btnAgendar.setDisable(false);
    }

    @Override
    public void initialize() {
    }

    public Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }

    public int findColumna(DiaryDto p) {
        for (int i = 1; i <= doctorDto.getDrSpaces(); i++) {
            Node node = getNodeFromGridPane(DiaryPane, 0, i);

            if (node instanceof Label) {
                Label label = (Label) node;
                String labelText = label.getText();
                if (p.getDySpace().getSeHour().substring(3, 5).equals(labelText)) {
                    return i;
                }
            }
        }
        return 0;
    }

    public int findFila(DiaryDto p, int iniHora, int finHora) {
        for (int i = iniHora; i <= finHora; i++) {
            Node node = getNodeFromGridPane(DiaryPane, i - iniHora + 1, 0);

            if (node instanceof Label) {
                Label label = (Label) node;
                String labelText = label.getText().substring(0, 2);
                if (p.getDySpace().getSeHour().substring(0, 2).equals(labelText)) {
                    return i - iniHora + 1;
                }
            }
        }
        return 0;
    }
    Predicate<DiaryDto> pDoctor = x -> x.getDyDoctor().getDoctorName().equals(doctorDto.getDoctorName());
    Predicate<DiaryDto> pCancelada = x -> !x.getDySpace().getSeAppointment().getAtState().equals("Cancelada");

    @FXML
    private void lookday(ActionEvent event) {
        if (DiaryPane != null) {
            DiaryPane.getChildren().removeAll(citasAgregadasList);
            citasAgregadasList.clear();
            DiaryPane.getChildren().removeAll(citasAgregadaDBsList);
            citasAgregadaDBsList.clear();
            DiaryPane.getChildren().removeAll(citasPosiblesDBsList);
            citasPosiblesDBsList.clear();
        }
        String horaInicio = doctorDto.getDrIniworking();
        String horaFin = doctorDto.getDrFinisworking();
        String[] partesInicio = horaInicio.split(":");
        String[] partesFin = horaFin.split(":");
        int iniHora = Integer.parseInt(partesInicio[0]);
        int finHora = Integer.parseInt(partesFin[0]);

        int fila = 0;
        int columna = 0;
        if (DayPicker.getValue() == null) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error", getStage(), "Debes Seleccionar un dia");
        } else {
            DiaryService diario = new DiaryService();
            List<DiaryDto> AgendaCompleta = diario.getDiary();
            eventAgendDoct();
            if (AgendaCompleta != null) {
                AgendaCompleta = AgendaCompleta.stream().filter(pCancelada.and(pDoctor.and(x -> x.getDyDate().equals(DayPicker.getValue())))).toList();

                for (int i = 0; i < AgendaCompleta.size(); i++) {

                    fila = findFila(AgendaCompleta.get(i), iniHora, finHora);
                    columna = findColumna(AgendaCompleta.get(i));

                    Label label = new Label("Cita / " + AgendaCompleta.get(i).getDySpace().getSeAppointment().getAtState());
                    CargarColor(label, AgendaCompleta.get(i).getDySpace().getSeAppointment().getAtState());
                    label.setUserData(AgendaCompleta.get(i).getDySpace());

                    label.setOnMouseClicked(eventClicked -> {
                        SpaceDto spaceAux = (SpaceDto) label.getUserData();
                        ModificarCita(spaceAux.getSeAppointment());
                    });

                    label.setOnDragDetected(eventx -> {
                        Dragboard db = label.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(label.getText());

                        DiaryPane.getChildren().removeAll(citasPosiblesDBsList);
                        SpaceDto spaceAux = (SpaceDto) label.getUserData();
                        AppointmentDto aux = spaceAux.getSeAppointment();
                        String targetCode = aux.getAtCode();
                        Iterator<Label> iterator = citasAgregadaDBsList.iterator();
                        while (iterator.hasNext()) {
                            Label currentLabel = iterator.next();
                            SpaceDto currentAppointment = (SpaceDto) currentLabel.getUserData();
                            if (targetCode.equals(currentAppointment.getSeAppointment().getAtCode())) {
                                movespace++;
                                DiaryPane.getChildren().remove(currentLabel);
                                iterator.remove();
                            }
                        }
                        db.setContent(content);
                        event.consume();
                    });
                    label.setOnDragOver(eventz -> {
                        if (eventz.getGestureSource() != label && eventz.getDragboard().hasString()) {
                            eventz.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }
                        event.consume();
                    });
                    label.setOnDragDropped(eventf -> {
                        Dragboard db = eventf.getDragboard();
                        boolean success = false;
                        if (db.hasString()) {
                            ActionEvent events = new ActionEvent();
                            lookday(events);
                            movespace = 0;
                            success = true;
                        }
                        eventf.setDropCompleted(success);
                        eventf.consume();
                    });
                    label.setOnDragDone(DragEvent::consume);
                    DiaryPane.setColumnSpan(label, 1);
                    DiaryPane.setRowSpan(label, 1);
                    DiaryPane.setColumnIndex(label, columna);
                    DiaryPane.setRowIndex(label, fila);
                    DiaryPane.getChildren().add(label);
                    citasAgregadaDBsList.add(label);
                }
            }
        }
    }

    public void ModificarCita(AppointmentDto cita) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar Cita");
        alert.setHeaderText(null);
        alert.setContentText("¿Quieres modificar esta cita?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        if (result == ButtonType.OK) {
            appointmentDtoModi = cita;
            modificarCita = true;
            nameP.setText(appointmentDtoModi.getAtPatient().getPtName());
            userLog.setText(appointmentDtoModi.getAtUserregister().getUsName());
            numberP.setText(String.valueOf(appointmentDtoModi.getAtTelephone()));
            email.setText(appointmentDtoModi.getAtEmail());
            code.setText(appointmentDtoModi.getAtCode());
            reason.setText(appointmentDtoModi.getAtReason());
            switch (appointmentDtoModi.getAtState()) {
                case "Programada":
                    Scheduled.setSelected(true);
                    break;
                case "Atendida":
                    Attended.setSelected(true);
                    break;
                case "Ausente":
                    Absent.setSelected(true);
                    break;
                case "Cancelada":
                    Cancelled.setSelected(true);
                    break;
            }
            nameP.setDisable(true);
            userLog.setDisable(true);
            code.setDisable(true);
            OptionsAppoinmentInfo.toFront();
        } else {
        }
    }

    public void CargarColor(Label label, String estado) {
        switch (estado) {
            case "Programada":
                label.setStyle("-fx-font-size: 15; -fx-background-color: yellow;");
                break;
            case "Atendida":
                label.setStyle("-fx-font-size: 15; -fx-background-color: green;");
                break;
            case "Ausente":
                label.setStyle("-fx-font-size: 15; -fx-background-color: red;");
                break;
        }
    }

    @FXML
    private void back(ActionEvent event) {
        CreateAppointment.toFront();
    }

    private void openDiary(ActionEvent event) {
        OptionsViewDiary.toFront();
    }

    @FXML
    private void selectPatient(MouseEvent event) {
        OptionsSelectPatient.toFront();
    }

    @FXML
    private void openAppoinment(ActionEvent event) {
        modificarCita = false;
        OptionsAppoinmentInfo.toFront();
    }

    @FXML
    private void backAppointment(ActionEvent event) {
        cleanUpAppointment(event);
        modificarCita = false;
        nameP.setDisable(false);
        userLog.setDisable(false);
        code.setDisable(false);
        OptionsViewDiary.toFront();
    }

    @FXML
    private void cleanUpAppointment(ActionEvent event) {
        nameP.clear();
        numberP.clear();
        userLog.clear();
        email.clear();
        code.clear();
        reason.clear();
        Tou.selectToggle(null);
    }

    @FXML
    private void backDiary(ActionEvent event) {
        CreateAppointment.toFront();
    }

    @FXML
    private void UpdateEmailAppointment(ActionEvent event) {
        if (!email.getText().isEmpty()) {
            PatientService patient = new PatientService();
            patientDto.setPtEmail(email.getText());
            Respuesta respuesta = patient.savePatient(patientDto);
            if (respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar paciente", getStage(), "Paciente Actualizar");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar paciente", getStage(), "Error al Actualizar paciente");
            }
        }
    }

    @FXML
    private void UpdateTelephoneAppointment(ActionEvent event) {
        if (!numberP.getText().isEmpty()) {
            PatientService patient = new PatientService();
            patientDto.setPtTelephone(numberP.getText());
            Respuesta respuesta = patient.savePatient(patientDto);
            if (respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar paciente", getStage(), "Paciente Actualizar");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar paciente", getStage(), "Error al Actualizar paciente");
            }
        }
    }

    @FXML
    private void Recordatorio(ActionEvent event) {
    }
}
