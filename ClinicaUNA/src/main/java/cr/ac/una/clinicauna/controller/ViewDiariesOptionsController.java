/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.HistoryDto;
import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.ReportDto;
import cr.ac.una.clinicauna.model.SpaceDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.AppointmentService;
import cr.ac.una.clinicauna.service.DiaryService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.HistoryService;
import cr.ac.una.clinicauna.service.PatientService;
import cr.ac.una.clinicauna.service.ReportService;
import cr.ac.una.clinicauna.service.SpaceService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private GridPane DiaryPaneModi;
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
    int iniHora;
    int finHora;
    int spacesfree;
    private int HoraSelecionada = 0;
    private List<Integer> horasTotales = new ArrayList<>();
    private boolean canupdate = false;
    UserDto usrIdiom = (UserDto) AppContext.getInstance().get("Usuario");
    boolean flagBtnAttenControl = false;
    private List<DiaryDto> agendadosNuevo;
    private List<DiaryDto> agendadosViejos;
    private boolean modifiSpace = false;
    List<DiaryDto> agendadostotal;
    @FXML
    private Button UpdateEmailAppointment;
    @FXML
    private Button UpdateTelephoneAppointment;
    @FXML
    private Button btnRecordatorio;
    @FXML
    private BorderPane AttentionControlView;
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
    private Button btnAttentionControl;
    @FXML
    private JFXDatePicker DatePickerAppointment;
    @FXML
    private Button btnChangeAppointment;
    @FXML
    private BorderPane OptionsViewDiary1;
    @FXML
    private Text textMainDoctor1211;
    @FXML
    private AnchorPane rootDocDiary1;
    @FXML
    private JFXDatePicker datePickerConsultDate;
    @FXML
    private JFXTimePicker timePickerConsultTime;
    @FXML
    private TextArea textAreaRep_NotesNursing;
    @FXML
    private TextArea textAreaRep_CarePlan;
    @FXML
    private TextArea textAreaRep_PhysicalExam;
    @FXML
    private TextArea textAreaRep_Treatments;
    @FXML
    private ComboBox<String> spacesEdit = new ComboBox();
    ReportDto reportDto = new ReportDto();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAgendar.setDisable(true);
        btnRecordatorio.setDisable(true);
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
        spacesEdit.setItems(items);
        spacesEdit.setValue("0");
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
            appointmentDto.setFechaRegistro(LocalDate.now());
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
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Cita Registrada");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Registered Appointment");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Rendez-vous enregistré");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "登録済みの予約");
                }
                return true;
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error registrando cita");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error registering appointment");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de l'enregistrement du rendez-vous");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "予定の登録エラー");
                }
                return false;
            }
        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Informacion incompleta");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "incomplete information");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "information incomplète");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "情報を完成させる");
            }
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
                }
                if (respuesta.getEstado()) {
                    if (usrIdiom.getUsLenguage().equals("Spanish")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Agenda Registrada");
                    } else if (usrIdiom.getUsLenguage().equals("English")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Registered Agenda");
                    } else if (usrIdiom.getUsLenguage().equals("French")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Ordre du jour enregistré");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "登録済みの議題");
                    }
                } else {
                    if (usrIdiom.getUsLenguage().equals("Spanish")) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error al registrar agenda");
                    } else if (usrIdiom.getUsLenguage().equals("English")) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error when registering agenda");
                    } else if (usrIdiom.getUsLenguage().equals("French")) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de l'enregistrement de l'agenda");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "議題登録時のエラー");
                    }
                }
                lookday(event);
                appointmentDto = new AppointmentDto();
                spacesDto = new SpaceDto();
                respuesta = servicediary.emailDiary(diaryDto);
                diaryDto = new DiaryDto();
            }

        } else {
            List<SpaceDto> espaciosReservados = new ArrayList<>();
            AppointmentService service = new AppointmentService();
            DiaryService serviceDiary = new DiaryService();
            SpaceService serviceSpace = new SpaceService();
            Respuesta respuesta = null;
            boolean choque = false;
            agendadosViejos = agendadostotal.stream().filter(x -> x.getDyDate().equals(DatePickerAppointment.getValue())).toList();
            for (DiaryDto p : agendadosViejos) {
                for (int j = 0; j < agendadosNuevo.size(); j++) {
                    if (p.getDySpace().getSeHour().equals(agendadosNuevo.get(j).getDySpace().getSeHour())) {
                        choque = true;
                    }
                }
            }
            if (!choque) {
                if (agendadosNuevo.size() >= Integer.parseInt(spacesEdit.getValue())) {
                    int k = 0;
                    for (DiaryDto p : agendadosNuevo) {
                        p.setDyDate(DatePickerAppointment.getValue());
                        spacesDto = p.getDySpace();
                        if (modifiSpace) {
                            if (k < Integer.parseInt(spacesEdit.getValue())) {
                                respuesta = serviceSpace.saveSpace(spacesDto);
                                respuesta = serviceDiary.saveDiary(p);
                            } else {
                                respuesta = serviceDiary.deleteDiary(p.getDyId());
                                respuesta = serviceSpace.deleteSpace(spacesDto.getSeId());
                            }
                        } else {
                            respuesta = serviceDiary.saveDiary(p);
                        }
                        k++;
                    }
                }
                if (agendadosNuevo.size() < horasAgregadas.size()) {
                    for (int i = 0; i < horasAgregadas.size(); i++) {
                        spacesDto = new SpaceDto();
                        if (i < agendadosNuevo.size()) {
                            String horaFormateada = horasAgregadas.get(i).format(timeFormatter);
                            agendadosNuevo.get(i).getDySpace().setSeHour(horaFormateada);
                            respuesta = serviceSpace.saveSpace(agendadosNuevo.get(i).getDySpace());
                        } else {
                            spacesDto.setSeId(0);
                            spacesDto.setSeAppointment(agendadosNuevo.get(0).getDySpace().getSeAppointment());
                            String horaFormateada = horasAgregadas.get(i).format(timeFormatter);
                            spacesDto.setSeHour(horaFormateada);
                            respuesta = serviceSpace.saveSpace(spacesDto);
                        }
                    }
                    int espacios = 1;
                    try {
                        espacios = Integer.parseInt(spacesEdit.getValue());
                    } catch (NumberFormatException e) {
                    }
                    espaciosReservados = serviceSpace.getSpace();
                    espaciosReservados = espaciosReservados.stream().filter(x -> x.getSeAppointment().getAtId() == agendadosNuevo.get(0).getDySpace().getSeAppointment().getAtId()).toList();

                    for (int i = 0; i < espaciosReservados.size(); i++) {
                        for (int j = 0; j < agendadosNuevo.size(); j++) {
                            if (!agendadosNuevo.get(j).getDySpace().getSeId().equals(espaciosReservados.get(i).getSeId())) {
                                diaryDto.setDyId(0);
                                diaryDto.setDyDoctor(agendadosNuevo.get(0).getDyDoctor());
                                diaryDto.setDyDate(DatePickerAppointment.getValue());
                                diaryDto.setDySpace(espaciosReservados.get(i));
                                respuesta = servicediary.saveDiary(diaryDto);
                            } else {
                                agendadosNuevo.get(j).setDyDate(DatePickerAppointment.getValue());
                                respuesta = servicediary.saveDiary(agendadosNuevo.get(j));
                            }
                        }
                    }
                }

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
                    if (usrIdiom.getUsLenguage().equals("Spanish")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Cita actualizada");
                    } else if (usrIdiom.getUsLenguage().equals("English")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Updated quote");
                    } else if (usrIdiom.getUsLenguage().equals("French")) {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Devis mis à jour");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "見積書を更新しました");
                    }
                } else {
                    if (usrIdiom.getUsLenguage().equals("Spanish")) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error actualizando la cita");
                    } else if (usrIdiom.getUsLenguage().equals("English")) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error updating appointment");
                    } else if (usrIdiom.getUsLenguage().equals("French")) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de la mise à jour du rendez-vous");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "予定の更新中にエラーが発生しました");
                    }
                }
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Los campos para ese día están ocupados");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "The fields for that day are occupied");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Les champs pour ce jour sont occupés");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "その日のフィールドは使用中です");
                }
            }
        }
    }

    @FXML
    private void UpdatePatient(ActionEvent event) {
        PatientService patient = new PatientService();
        Respuesta r = null;
        r = patient.savePatient(bindNewPatient());
        fillTablePatient();
        patientDto = new PatientDto();

        if (r.getEstado()) {
            OptionsSelectPatient.toFront();
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "paciente actualizada");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "patient quote");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Devis mis à jour");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "見積書を更新しました");
            }
        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error actualizando la paciente");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error updating patient");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de la mise à jour du rendez-vous");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "予定の更新中にエラーが発生しました");
            }
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
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            alert.setContentText("¿Quieres crear un nuevo paciente?");
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            alert.setContentText("Do you want to create a new patient?");
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            alert.setContentText("Voulez-vous créer un nouveau patient?");
        } else {
            alert.setContentText("新しい患者を作成しますか？");
        }
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
                numberP.setText(patientDto.getPtTelephone());
                email.setText(patientDto.getPtEmail());
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

    private int getColumnFromX(double x, GridPane gridPane) {
        double widthSoFar = 0.0;
        int col;
        double totalWidth = gridPane.getWidth();
        for (col = 0; col < gridPane.getColumnConstraints().size(); col++) {
            double columnPercentage = gridPane.getColumnConstraints().get(col).getPercentWidth();
            double columnWidth = totalWidth * (columnPercentage / 80.0);
            widthSoFar += columnWidth;
            if (x < widthSoFar) {
                break;
            }
        }
        return col;
    }

    private int getRowFromY(double y, GridPane gridPane) {
        double heightSoFar = 0.0;
        int row;
        double totalHeight = gridPane.getHeight();
        for (row = 0; row < gridPane.getRowConstraints().size(); row++) {
            double rowPercentage = gridPane.getRowConstraints().get(row).getPercentHeight();
            double rowHeight = totalHeight * (rowPercentage / 80.0);
            heightSoFar += rowHeight;
            if (y < heightSoFar) {
                break;
            }
        }
        return row;
    }

    public void eventAgendDoct() {
        rootDocDiary.getChildren().removeIf(node -> node instanceof GridPane);
        DiaryPane = createWeekCalendarWithHeaders(1, 2, doctorDto.getDrSpaces(), true);
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
                //si mueve de derecha a izquierda creo que ya mueve mejor entre mas le sume mas fino va 
                //si mueve de arriba a abajo tambien 
                //el problema esta cuando quiere pasar las citas en una casilla diagonal

                double dropX = event.getX() + 30;
                double dropY = event.getY() + 5;

                int dropColumn = getColumnFromX(dropX, DiaryPane);
                int dropRow = getRowFromY(dropY, DiaryPane);

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
                DiaryDto referente = (DiaryDto) draggedLabel.getUserData();
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
                Respuesta respuesta = new Respuesta();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modificar Cita");
                alert.setHeaderText(null);
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    alert.setContentText("¿Quieres modificar esta cita?");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    alert.setContentText("Do you want to modify this appointment?");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    alert.setContentText("Voulez-vous modifier ce rendez-vous?");
                } else {
                    alert.setContentText("この予定を変更しますか？");
                }
                ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
                if (result == ButtonType.OK) {
                    SpaceService service = new SpaceService();
                    List<SpaceDto> actualizados = service.getSpace();
                    actualizados = actualizados.stream().filter(x -> x.getSeAppointment().getAtId() == referente.getDySpace().getSeAppointment().getAtId()).toList();
                    int k = 0;
                    DatePickerAppointment.setVisible(true);
                    btnAttentionControl.setVisible(true);
                    for (SpaceDto p : actualizados) {
                        spacesDto = p;
                        spacesDto.setSeHour(horasModificadas.get(k).format(timeFormatter));
                        respuesta = service.saveSpace(spacesDto);
                        k++;
                    }
                    if (respuesta.getEstado()) {
                        if (usrIdiom.getUsLenguage().equals("Spanish")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Cita actualizada");
                        } else if (usrIdiom.getUsLenguage().equals("English")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Updated quote");
                        } else if (usrIdiom.getUsLenguage().equals("French")) {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Devis mis à jour");
                        } else {
                            new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "見積書を更新しました");
                        }
                    } else {
                        if (usrIdiom.getUsLenguage().equals("Spanish")) {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error actualizando la cita");
                        } else if (usrIdiom.getUsLenguage().equals("English")) {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error updating appointment");
                        } else if (usrIdiom.getUsLenguage().equals("French")) {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de la mise à jour du rendez-vous");
                        } else {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "予定の更新中にエラーが発生しました");
                        }
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

    public GridPane createWeekCalendarWithHeaders(int b, int a, int v, boolean type) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        HistoryService serviceHistorial = new HistoryService();
        List<HistoryDto> lista = serviceHistorial.getHistorysByDoctor(doctorDto.getDrId());
        DiaryService diario = new DiaryService();

        List<DiaryDto> listadiario = diario.getDiary();
        listadiario = listadiario.stream().filter(x -> x.getDyDate().equals(DayPicker.getValue())).toList();
        HistoryDto filteredList;

        if (listadiario.isEmpty()) {
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(DayPicker.getValue().toString(), doctorDto.getDrId()).getResultado("history");
            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
            iniHora = Integer.parseInt(partesInicio[0]);
            finHora = Integer.parseInt(partesFin[0]);
            mint = new String[filteredList.getHtSpaces()];

        } else {
            AppointmentService prueba = new AppointmentService();
            AppointmentDto as = (AppointmentDto) prueba.getAppointmentId(listadiario.get(0).getDySpace().getSeAppointment().getAtId()).getResultado("Appointments");
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(as.getFechaRegistro().toString(), doctorDto.getDrId()).getResultado("history");
            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
            iniHora = Integer.parseInt(partesInicio[0]);
            finHora = Integer.parseInt(partesFin[0]);
            mint = new String[filteredList.getHtSpaces()];
        }
        spacesfree = filteredList.getHtSpaces();
        if (filteredList.getHtSpaces() == 2) {
            mint[0] = "00";
            mint[1] = "30";
        } else if (filteredList.getHtSpaces() == 3) {
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
            dayLabel.setStyle("-fx-font-weight: bold; "
                    + "-fx-background-color: #CC9900; ");

            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);
            gridPane.add(dayLabel, i + 1, 0);
        }

        Label dayLabel = new Label("Agenda");
        dayLabel.setAlignment(Pos.CENTER);
        dayLabel.setStyle("-fx-font-weight: bold; "
                + "-fx-background-color: #CC9900; ");

        dayLabel.setMaxWidth(Double.MAX_VALUE);
        dayLabel.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillWidth(dayLabel, true);
        GridPane.setFillHeight(dayLabel, true);
        gridPane.add(dayLabel, 0, 0);

        for (int i = iniHora; i <= finHora; i++) {
            Label hourLabel = new Label(String.format("%02d:00", i));
            hourLabel.setAlignment(Pos.CENTER);
            hourLabel.setStyle("-fx-font-weight: bold; "
                    + "-fx-background-color: #CC9900; ");

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
                        if (type) {
                            handleCellClick(gridPane, cellLabel, mint, finalHour);
                        } else {
                            handleCellClickModi(gridPane, cellLabel, mint, finalHour);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ViewDiariesOptionsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                gridPane.add(cellLabel, day, hour - iniHora + 1);
            }
        }

        for (int col = 0; col <= mint.length; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(80.0 / (mint.length + 1));
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        int totalRows = finHora - iniHora + 2;
        for (int row = 0; row < totalRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(80.0 / totalRows);
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
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "No se pueden registrar los campos");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Fields cannot be registered");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Les champs ne peuvent pas être enregistrés");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "見積書を更新しました");
                }
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
            Label label = new Label("Reservar campos");
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-font-size: 15;"
                    + "-fx-background-color: #073763;"
                    + "-fx-background-radius: 10;"
                    + "-fx-text-fill: #FFFFFF;");
            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);
            GridPane.setMargin(label, new Insets(5, 5, 5, 5));
            GridPane.setColumnSpan(label, 1);
            GridPane.setRowSpan(label, 1);
            GridPane.setColumnIndex(label, columnIdx);
            GridPane.setRowIndex(label, rowIndex);

            gridPane.getChildren().add(label);
            citasAgregadasList.add(label);

            LocalTime horaActual = null;
            if (citasAgregadas <= min.length) {
                horaActual = LocalTime.of(hour, Integer.parseInt(mint[columnIdx - 1]));
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

    private void handleCellClickModi(GridPane gridPane, Label cellLabel, String min[], int hour) throws ParseException {
        int maxCitas = Integer.parseInt(spacesEdit.getValue());

        gridPane.getChildren().removeAll(citasAgregadasList);
        citasAgregadasList.clear();

        horasAgregadas = new ArrayList<>();

        int citasAgregadas = 0;
        int startColumn = GridPane.getColumnIndex(cellLabel);
        int columnIdx = startColumn;
        int rowIndex = GridPane.getRowIndex(cellLabel);

        while (citasAgregadas < maxCitas) {
            if (isCellLabelEmpty(gridPane, rowIndex, columnIdx)) {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "No se pueden registrar los campos");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Fields cannot be registered");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Les champs ne peuvent pas être enregistrés");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "見積書を更新しました");
                }
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
            Label label = new Label(diaryDto.getDySpace().getSeAppointment().getAtPatient().getPtName() + " / " + diaryDto.getDySpace().getSeAppointment().getAtState());
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

    public int findColumna(GridPane gridPane, DiaryDto p) {
        for (int i = 1; i <= spacesfree; i++) {
            Node node = getNodeFromGridPane(gridPane, 0, i);

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

    public int findFila(GridPane gridPane, DiaryDto p, int iniHora, int finHora) {
        for (int i = iniHora; i <= finHora; i++) {
            Node node = getNodeFromGridPane(gridPane, i - iniHora + 1, 0);

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
        DiaryPane = null;
        btnRecordatorio.setDisable(false);
        if (DiaryPane != null) {
            DiaryPane.getChildren().removeAll(citasAgregadasList);
            citasAgregadasList.clear();
            DiaryPane.getChildren().removeAll(citasAgregadaDBsList);
            citasAgregadaDBsList.clear();
            DiaryPane.getChildren().removeAll(citasPosiblesDBsList);
            citasPosiblesDBsList.clear();
        } else {
            eventAgendDoct();
        }
        HistoryService serviceHistorial = new HistoryService();
        List<HistoryDto> lista = serviceHistorial.getHistorysByDoctor(doctorDto.getDrId());
        DiaryService diario = new DiaryService();

        List<DiaryDto> listadiario = diario.getDiary();
        listadiario = listadiario.stream().filter(x -> x.getDyDate().equals(DayPicker.getValue())).toList();
        HistoryDto filteredList;

        if (listadiario.isEmpty()) {
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(DayPicker.getValue().toString(), doctorDto.getDrId()).getResultado("history");

            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");

        } else {
            AppointmentService prueba = new AppointmentService();
            AppointmentDto as = (AppointmentDto) prueba.getAppointmentId(listadiario.get(0).getDySpace().getSeAppointment().getAtId()).getResultado("Appointments");
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(as.getFechaRegistro().toString(), doctorDto.getDrId()).getResultado("history");

            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
        }
        int fila = 0;
        int columna = 0;
        if (DayPicker.getValue() == null) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Seleccione un dia ");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Select a day");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Sélectionnez un jour");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "日を選択してください");
            }
        } else {
            List<DiaryDto> AgendaCompleta = diario.getDiary();

            if (AgendaCompleta != null) {
                AgendaCompleta = AgendaCompleta.stream().filter(pCancelada.and(pDoctor.and(x -> x.getDyDate().equals(DayPicker.getValue())))).toList();

                for (int i = 0; i < AgendaCompleta.size(); i++) {

                    fila = findFila(DiaryPane, AgendaCompleta.get(i), iniHora, finHora);
                    columna = findColumna(DiaryPane, AgendaCompleta.get(i));
                    Label label = new Label("Cita / " + AgendaCompleta.get(i).getDySpace().getSeAppointment().getAtState());
                    CargarColor(label, AgendaCompleta.get(i).getDySpace().getSeAppointment().getAtState());
                    label.setUserData(AgendaCompleta.get(i));
                    label.setAlignment(Pos.CENTER);
                    label.setMaxWidth(Double.MAX_VALUE);
                    label.setMaxHeight(Double.MAX_VALUE);
                    GridPane.setMargin(label, new Insets(5, 5, 5, 5));
                    label.setOnMouseClicked(eventClicked -> {
                        DiaryDto DiaryAux = (DiaryDto) label.getUserData();
                        ModificarCita(DiaryAux.getDySpace().getSeAppointment());
                        diaryDto = DiaryAux;

                        DiaryService serviceDiary = new DiaryService();
                        agendadostotal = serviceDiary.getDiary();
                        agendadosNuevo = agendadostotal.stream().filter(x -> x.getDySpace().getSeAppointment().getAtId() == diaryDto.getDySpace().getSeAppointment().getAtId()).toList();
                        agendadosViejos = agendadostotal.stream().filter(x -> x.getDyDate().equals(DatePickerAppointment.getValue())).toList();
                    });

                    label.setOnDragDetected(eventx -> {
                        Dragboard db = label.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(label.getText());

                        DiaryPane.getChildren().removeAll(citasPosiblesDBsList);
                        DiaryDto DiaryAux = (DiaryDto) label.getUserData();
                        AppointmentDto aux = DiaryAux.getDySpace().getSeAppointment();
                        String targetCode = aux.getAtCode();
                        Iterator<Label> iterator = citasAgregadaDBsList.iterator();
                        while (iterator.hasNext()) {
                            Label currentLabel = iterator.next();
                            DiaryDto currentAppointment = (DiaryDto) currentLabel.getUserData();
                            if (targetCode.equals(currentAppointment.getDySpace().getSeAppointment().getAtCode())) {
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
                    DiaryPane.setColumnIndex(label, columna);
                    DiaryPane.setRowIndex(label, fila);
                    DiaryPane.getChildren().add(label);
                    citasAgregadaDBsList.add(label);
                }
            }
        }
    }

    public void loadReport(ReportDto report) {
        textFieldRep_Pressure.setText(String.valueOf(reportDto.getRtPressure()));
        textFieldRep_HeartRate.setText(String.valueOf(reportDto.getRtHeartRate()));
        textFieldRep_Height.setText(String.valueOf(reportDto.getRtHeight()));
        textFieldRep_Weight.setText(String.valueOf(reportDto.getRtWeight()));
        textFieldRep_Temperature.setText(String.valueOf(reportDto.getRtTemperature()));
        textFieldRep_BodyMass.setText(String.valueOf(reportDto.getRtBodyMass()));
        textAreaRep_Reason.setText(reportDto.getRtDoctorReason());
        textAreaRep_NotesNursing.setText(reportDto.getRtNotesNursing());
        textAreaRep_CarePlan.setText(reportDto.getRtCarePlan());
        textAreaRep_PhysicalExam.setText(reportDto.getRtFisicExamen());
        textAreaRep_Treatments.setText(reportDto.getRtTreatmentExamen());
        textAreaRep_Notes.setText(reportDto.getRtObservations());
        datePickerConsultDate.setValue(reportDto.getRtDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Instant instant = reportDto.getRtDate().toInstant();
        LocalTime localTime = instant.atZone(ZoneId.systemDefault()).toLocalTime();
        timePickerConsultTime.setValue(localTime);

    }

    public void ModificarCita(AppointmentDto cita) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar Cita");
        alert.setHeaderText(null);
        ReportService serviceRepor = new ReportService();
        if (usrIdiom.getUsLenguage().equals("Spanish")) {
            alert.setContentText("¿Quieres modificar esta cita?");
        } else if (usrIdiom.getUsLenguage().equals("English")) {
            alert.setContentText("Do you want to modify this appointment?");
        } else if (usrIdiom.getUsLenguage().equals("French")) {
            alert.setContentText("Voulez-vous modifier ce rendez-vous?");
        } else {
            alert.setContentText("この予定を変更しますか？");
        }
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
            btnAttentionControl.setVisible(true);
            DatePickerAppointment.setVisible(true);
            Respuesta res = serviceRepor.getReportByIdAppoinment(appointmentDtoModi.getAtId());
            reportDto = (ReportDto) res.getResultado("Report");
            if (reportDto != null) {
                loadReport(reportDto);
            }

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
        DatePickerAppointment.setValue(null);
    }

    public void CargarColor(Label label, String estado) {
        switch (estado) {
            case "Programada":
                label.setStyle("-fx-font-size: 15; -fx-background-color: yellow; -fx-background-radius: 10; -fx-text-fill: #FFFFFF; -fx-text-fill: #000000");
                break;
            case "Atendida":
                label.setStyle("-fx-font-size: 15; -fx-background-color: green; -fx-background-radius: 10; -fx-text-fill: #FFFFFF; -fx-text-fill: #000000");
                break;
            case "Ausente":
                label.setStyle("-fx-font-size: 15; -fx-background-color: red; -fx-background-radius: 10; -fx-text-fill: #FFFFFF; -fx-text-fill: #000000");
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
        btnAttentionControl.setVisible(false);
        DatePickerAppointment.setVisible(false);
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
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Email actualizado");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Updated Email");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Email mis à jour");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "更新された電話機");
                }
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error al actualizar Email");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error updating Email");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de la mise à jour du Email");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "電話の更新エラー");
                }
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
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Telefono actualizado");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Updated phone");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Téléphone mis à jour");
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "更新された電話機");
                }
            } else {
                if (usrIdiom.getUsLenguage().equals("Spanish")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error al actualizar teléfono");
                } else if (usrIdiom.getUsLenguage().equals("English")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error updating phone");
                } else if (usrIdiom.getUsLenguage().equals("French")) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de la mise à jour du téléphone");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "電話の更新エラー");
                }
            }
        }
    }

    @FXML
    private void Recordatorio(ActionEvent event) {
        DiaryService diario = new DiaryService();
        List<DiaryDto> lista = new ArrayList<>();
        Respuesta respuesta = new Respuesta();
        lista = diario.getDiary();

        List<DiaryDto> filteredList = lista.stream()
                .filter(distinctByKey(x -> x.getDySpace().getSeAppointment().getAtId()))
                .collect(Collectors.toList());

        filteredList = filteredList.stream()
                .filter(x -> DayPicker.getValue().equals(x.getDyDate()))
                .collect(Collectors.toList());

        for (DiaryDto p : filteredList) {
            respuesta = diario.emailDiaryRecordatorio(p);
        }
        if (respuesta.getEstado()) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Aviso", getStage(), "Recordatorios enviados");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Warning", getStage(), "Reminders sent");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Avertissement", getStage(), "Rappels envoyés");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "リマインダーが送信されました", getStage(), "保存されたユーザー");
            }
        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Aviso", getStage(), "Error al enviar recordatorio");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Warning", getStage(), "Error sending reminder");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Avertissement", getStage(), "Erreur lors de l'envoi du rappel");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "リマインダーが送信されました", getStage(), "保存時のエラー");
            }
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @FXML
    private void openAttentionControl(ActionEvent event) {
        AttentionControlView.toFront();
    }

    private void backAttentionControl(ActionEvent event) {
        btnAttentionControl.setVisible(true);
        OptionsAppoinmentInfo.toFront();
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

    ReportDto bindNewReport() {

        reportDto.setRtId(reportDto.getRtId());
        reportDto.setRtAppointment(appointmentDtoModi);
        reportDto.setRtPressure(Double.parseDouble(textFieldRep_Pressure.getText()));
        reportDto.setRtHeartRate(Double.parseDouble(textFieldRep_HeartRate.getText()));

        reportDto.setRtHeight(Double.parseDouble(textFieldRep_Height.getText()));
        reportDto.setRtWeight(Double.parseDouble(textFieldRep_Weight.getText()));
        reportDto.setRtTemperature(Double.parseDouble(textFieldRep_Temperature.getText()));
        reportDto.setRtBodyMass(Double.parseDouble(textFieldRep_BodyMass.getText()));
        reportDto.setRtDoctorReason(textAreaRep_Reason.getText());
        reportDto.setRtNotesNursing(textAreaRep_NotesNursing.getText());
        reportDto.setRtCarePlan(textAreaRep_CarePlan.getText());
        reportDto.setRtFisicExamen(textAreaRep_PhysicalExam.getText());
        reportDto.setRtTreatmentExamen(textAreaRep_Treatments.getText());
        reportDto.setRtObservations(textAreaRep_Notes.getText());
        LocalDate localDate = datePickerConsultDate.getValue();
        //Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDateTime localDateTime = LocalDateTime.of(localDate, timePickerConsultTime.getValue());
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        reportDto.setRtDate(date);

        return reportDto;
    }

    @FXML
    private void updateReportAp(ActionEvent event) {

        ReportService service = new ReportService();
        Respuesta response = null;

        if (reportDto != null) {
            response = service.saveReport(bindNewReport());
            reportDto = new ReportDto();
        }
        if (response.getEstado()) {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Control de Atención", getStage(), "Control de Atención guardada");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Save Attention Control", getStage(), "Saved Attention Control");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Enregistrer le contrôle de l'attention", getStage(), "Contrôle d'attention enregistré");
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "アテンションコントロールの保存", getStage(), "保存されたアテンション コントロール");
            }
            //cleanAttentionControl();

        } else {
            if (usrIdiom.getUsLenguage().equals("Spanish")) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Control de Atención", getStage(), "Error al guardar el Control de Atención");
            } else if (usrIdiom.getUsLenguage().equals("English")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Save Attention Control", getStage(), "Error saving Attention Control");
            } else if (usrIdiom.getUsLenguage().equals("French")) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Enregistrer le contrôle de l'attention", getStage(), "Erreur lors de l'enregistrement du contrôle d'attention");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "アテンションコントロールの保存", getStage(), "アテンション コントロールの保存中にエラーが発生しました");
            }
        }

    }

    @FXML
    private void ChangeAppointment(ActionEvent event) {
        eventAgendDoctModi();
        lookdayModi(event);
        spacesEdit.setValue(String.valueOf(agendadosNuevo.size()));
        OptionsViewDiary1.toFront();
    }

    @FXML
    private void SaveChangeSpace(ActionEvent event) {
        modifiSpace = true;
        agendadosViejos = agendadostotal.stream().filter(x -> x.getDyDate().equals(DatePickerAppointment.getValue())).toList();
        int k = 0;
        for (DiaryDto p : agendadosNuevo) {
            if (k < horasAgregadas.size()) {
                p.getDySpace().setSeHour(horasAgregadas.get(k).format(timeFormatter));
            }
            k++;
        }
        OptionsAppoinmentInfo.toFront();
        DiaryPaneModi.getChildren().removeAll(citasAgregadasList);
        DiaryPane.getChildren().removeAll(citasAgregadasList);
        citasAgregadasList.clear();
        DiaryPaneModi.getChildren().removeAll(citasAgregadaDBsList);
        DiaryPane.getChildren().removeAll(citasAgregadaDBsList);
        citasAgregadaDBsList.clear();
        DiaryPaneModi.getChildren().removeAll(citasPosiblesDBsList);
        DiaryPane.getChildren().removeAll(citasPosiblesDBsList);
        citasPosiblesDBsList.clear();
    }

    @FXML
    private void Volver(ActionEvent event) {
        OptionsAppoinmentInfo.toFront();
        DiaryPaneModi.getChildren().removeAll(citasAgregadasList);
        DiaryPane.getChildren().removeAll(citasAgregadasList);
        citasAgregadasList.clear();
        DiaryPaneModi.getChildren().removeAll(citasAgregadaDBsList);
        DiaryPane.getChildren().removeAll(citasAgregadaDBsList);
        citasAgregadaDBsList.clear();
        DiaryPaneModi.getChildren().removeAll(citasPosiblesDBsList);
        DiaryPane.getChildren().removeAll(citasPosiblesDBsList);
        citasPosiblesDBsList.clear();
    }

    public void eventAgendDoctModi() {
        rootDocDiary1.getChildren().removeIf(node -> node instanceof GridPane);
        String horaInicio = doctorDto.getDrIniworking();
        String horaFin = doctorDto.getDrFinisworking();
        String[] partesInicio = horaInicio.split(":");
        String[] partesFin = horaFin.split(":");

        int horaInicioInt = Integer.parseInt(partesInicio[0]);
        int horaFinInt = Integer.parseInt(partesFin[0]);
        DiaryPaneModi = createWeekCalendarWithHeadersModi(horaInicioInt, horaFinInt, doctorDto.getDrSpaces(), false);
        DiaryPaneModi.setPrefSize(rootDocDiary1.getPrefWidth() / 2 + 500, rootDocDiary1.getPrefHeight() / 2 + 180);
        DiaryPaneModi.setGridLinesVisible(true);
        DiaryPaneModi.getChildren().removeAll(citasAgregadasList);
        citasAgregadasList.clear();
        AnchorPane.setTopAnchor(DiaryPaneModi, (rootDocDiary1.getHeight() - DiaryPaneModi.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(DiaryPaneModi, (rootDocDiary1.getWidth() - DiaryPaneModi.getPrefWidth()) / 2);

        rootDocDiary1.heightProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setTopAnchor(DiaryPaneModi, (newVal.doubleValue() - DiaryPaneModi.getPrefHeight()) / 2));

        rootDocDiary1.widthProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setLeftAnchor(DiaryPaneModi, (newVal.doubleValue() - DiaryPaneModi.getPrefWidth()) / 2));

        rootDocDiary1.getChildren().add(DiaryPaneModi);
    }

    private void lookdayModi(ActionEvent event) {
        HistoryService serviceHistorial = new HistoryService();
        List<HistoryDto> lista = serviceHistorial.getHistorysByDoctor(doctorDto.getDrId());
        DiaryService diario = new DiaryService();

        List<DiaryDto> listadiario = diario.getDiary();
        listadiario = listadiario.stream().filter(x -> x.getDyDate().equals(DayPicker.getValue())).toList();
        HistoryDto filteredList;

        if (listadiario.isEmpty()) {
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(DayPicker.getValue().toString(), doctorDto.getDrId()).getResultado("history");

            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");

        } else {
            AppointmentService prueba = new AppointmentService();
            AppointmentDto as = (AppointmentDto) prueba.getAppointmentId(listadiario.get(0).getDySpace().getSeAppointment().getAtId()).getResultado("Appointments");
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(as.getFechaRegistro().toString(), doctorDto.getDrId()).getResultado("history");

            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
        }

        int fila = 0;
        int columna = 0;
        List<DiaryDto> AgendaCompleta = diario.getDiary();

        if (AgendaCompleta != null) {
            AgendaCompleta = AgendaCompleta.stream().filter(pCancelada.and(pDoctor.and(x -> x.getDyDate().equals(DatePickerAppointment.getValue())))).toList();

            for (int i = 0; i < AgendaCompleta.size(); i++) {

                fila = findFila(DiaryPaneModi, AgendaCompleta.get(i), iniHora, finHora);
                columna = findColumna(DiaryPaneModi, AgendaCompleta.get(i));

                Label label = new Label("Cita / " + AgendaCompleta.get(i).getDySpace().getSeAppointment().getAtState());
                CargarColor(label, AgendaCompleta.get(i).getDySpace().getSeAppointment().getAtState());
                label.setUserData(AgendaCompleta.get(i));
                label.setAlignment(Pos.CENTER);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setMaxHeight(Double.MAX_VALUE);
                GridPane.setMargin(label, new Insets(5, 5, 5, 5));
                label.setOnMouseClicked(eventClicked -> {
                    DiaryDto DiaryAux = (DiaryDto) label.getUserData();
                    ModificarCita(DiaryAux.getDySpace().getSeAppointment());
                    diaryDto = DiaryAux;
                    DatePickerAppointment.setValue(DiaryAux.dyDate);
                });

                label.setOnDragDetected(eventx -> {
                    Dragboard db = label.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(label.getText());

                    DiaryPaneModi.getChildren().removeAll(citasPosiblesDBsList);
                    DiaryDto DiaryAux = (DiaryDto) label.getUserData();
                    AppointmentDto aux = DiaryAux.getDySpace().getSeAppointment();
                    String targetCode = aux.getAtCode();
                    Iterator<Label> iterator = citasAgregadaDBsList.iterator();
                    while (iterator.hasNext()) {
                        Label currentLabel = iterator.next();
                        SpaceDto currentAppointment = (SpaceDto) currentLabel.getUserData();
                        if (targetCode.equals(currentAppointment.getSeAppointment().getAtCode())) {
                            movespace++;
                            DiaryPaneModi.getChildren().remove(currentLabel);
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
                DiaryPaneModi.setColumnIndex(label, columna);
                DiaryPaneModi.setRowIndex(label, fila);
                DiaryPaneModi.getChildren().add(label);
                citasAgregadaDBsList.add(label);
            }
        }
    }

    @FXML
    private void backCreateAppointment(MouseEvent event) {
        btnAttentionControl.setVisible(true);
        FlowController.getInstance().goMain("ViewMaintenanceOptions");
    }

    @FXML
    private void backReport(ActionEvent event) {
        OptionsAppoinmentInfo.toFront();
    }

    @FXML
    private void cleanUpAttentionControl(ActionEvent event) {
        cleanAttentionControl();
    }

    private void cleanAttentionControl() {
        textFieldRep_Pressure.clear();
        textFieldRep_HeartRate.clear();
        textFieldRep_Height.clear();
        textFieldRep_Weight.clear();
        textFieldRep_Temperature.clear();
        textFieldRep_BodyMass.clear();
        textAreaRep_Reason.clear();
        textAreaRep_Notes.clear();
        datePickerConsultDate.setValue(null);
        timePickerConsultTime.setValue(null);
        textAreaRep_NotesNursing.clear();
        textAreaRep_CarePlan.clear();
        textAreaRep_PhysicalExam.clear();
        textAreaRep_Treatments.clear();
    }

    public GridPane createWeekCalendarWithHeadersModi(int b, int a, int v, boolean type) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        HistoryService serviceHistorial = new HistoryService();
        List<HistoryDto> lista = serviceHistorial.getHistorysByDoctor(doctorDto.getDrId());
        DiaryService diario = new DiaryService();

        List<DiaryDto> listadiario = diario.getDiary();
        listadiario = listadiario.stream().filter(x -> x.getDyDate().equals(DatePickerAppointment.getValue())).toList();
        HistoryDto filteredList;

        if (listadiario.isEmpty()) {
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(DatePickerAppointment.getValue().toString(), doctorDto.getDrId()).getResultado("history");
            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
            iniHora = Integer.parseInt(partesInicio[0]);
            finHora = Integer.parseInt(partesFin[0]);
            mint = new String[filteredList.getHtSpaces()];

        } else {
            AppointmentService prueba = new AppointmentService();
            AppointmentDto as = (AppointmentDto) prueba.getAppointmentId(listadiario.get(0).getDySpace().getSeAppointment().getAtId()).getResultado("Appointments");
            filteredList = (HistoryDto) serviceHistorial.getHistorysByDate(as.getFechaRegistro().toString(), doctorDto.getDrId()).getResultado("history");
            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
            iniHora = Integer.parseInt(partesInicio[0]);
            finHora = Integer.parseInt(partesFin[0]);
            mint = new String[filteredList.getHtSpaces()];
        }
        spacesfree = filteredList.getHtSpaces();
        if (filteredList.getHtSpaces() == 2) {
            mint[0] = "00";
            mint[1] = "30";
        } else if (filteredList.getHtSpaces() == 3) {
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
            dayLabel.setStyle("-fx-font-weight: bold; "
                    + "-fx-background-color: #CC9900; ");

            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);
            gridPane.add(dayLabel, i + 1, 0);
        }

        Label dayLabel = new Label("Agenda");
        dayLabel.setAlignment(Pos.CENTER);
        dayLabel.setStyle("-fx-font-weight: bold; "
                + "-fx-background-color: #CC9900; ");

        dayLabel.setMaxWidth(Double.MAX_VALUE);
        dayLabel.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillWidth(dayLabel, true);
        GridPane.setFillHeight(dayLabel, true);
        gridPane.add(dayLabel, 0, 0);

        for (int i = iniHora; i <= finHora; i++) {
            Label hourLabel = new Label(String.format("%02d:00", i));
            hourLabel.setAlignment(Pos.CENTER);
            hourLabel.setStyle("-fx-font-weight: bold; "
                    + "-fx-background-color: #CC9900; ");

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
                        if (type) {
                            handleCellClick(gridPane, cellLabel, mint, finalHour);
                        } else {
                            handleCellClickModi(gridPane, cellLabel, mint, finalHour);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ViewDiariesOptionsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                gridPane.add(cellLabel, day, hour - iniHora + 1);
            }
        }

        for (int col = 0; col <= mint.length; col++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(80.0 / (mint.length + 1));
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        int totalRows = finHora - iniHora + 2;
        for (int row = 0; row < totalRows; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(80.0 / totalRows);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        return gridPane;
    }
}
