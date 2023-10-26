/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.SpaceDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.AppointmentService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.PatientService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
 * @author dilan
 */
public class ViewDiariesOptionsController extends Controller implements Initializable {

    @FXML
    private BorderPane MenuView;
    @FXML
    private BorderPane OptionsMainDiary;
    @FXML
    private TabPane tabPaneMantWorkers11;
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
    private TextField textFieldSearchDoc_Name1;
    @FXML
    private TextField textFieldSearchDoc_Pusername1;
    @FXML
    private TextField textFieldSearchDoc_Code1;
    @FXML
    private TextField textFieldSearchDoc_Folio1;
    @FXML
    private TextField textFieldSearchDoc_License1;
    @FXML
    private TextField textFieldSearchDoc_State1;
    @FXML
    private TableView<DoctorDto> tableViewDoctorsDiary;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocCode1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocFolio1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocName1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocId1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocLicense1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocIniWork1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocFinishWork1;
    @FXML
    private TableColumn<DoctorDto, String> tableColDocBreaks1;
    @FXML
    private Text textMainDoctor1;
    @FXML
    private GridPane grid;
    @FXML
    private RadioButton amRadio;
    @FXML
    private RadioButton pmRadio;
    private String matrizAgenda[][] = new String[15][8];

    List<DoctorDto> doctorList = new ArrayList<>();
    private ObservableList<DoctorDto> doctorObservableList;

    List<PatientDto> patientList = new ArrayList<>();
    private ObservableList<PatientDto> patientObservableList;
    @FXML
    private Tab tabPatient;
    @FXML
    private Tab tabDoc;
    @FXML
    private Tab tabDiary;
    @FXML
    private Text textMainDoctor11;

    private UserDto userDto = new UserDto();
    private DoctorDto doctorDto = new DoctorDto();
    private PatientDto patientDto = new PatientDto();
    private DiseaseDto diseaseDto = new DiseaseDto();

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
    ToggleGroup Hour = new ToggleGroup();
    ToggleGroup Tou = new ToggleGroup();
    @FXML
    ComboBox<String> spaces = new ComboBox();

    @FXML
    private BorderPane OptionsMainDiary1;
    @FXML
    private TabPane tabPaneMantWorkers112;
    @FXML
    private Tab tabDiary2;
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
    private Tab newPatientAppoiment;
    @FXML
    private BorderPane CreateAppointment;
    @FXML
    private Tab tabDiary21;
    @FXML
    private AnchorPane rootCalendar;
    private YearMonth currentYearMonth;
    private JFXTimePicker iniHour;
    private JFXTimePicker endHour;
    @FXML
    private AnchorPane rootDocDiary;
    @FXML
    private Text textMainDoctor12;
    @FXML
    private RadioButton amRadio1;
    @FXML
    private RadioButton pmRadio1;

    @FXML
    private TableColumn<DoctorDto, String> tableColDocSpaces11;
    @FXML
    private ComboBox<Integer> yearPicker;
    private List<Label> citasAgregadasList = new ArrayList<>();
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuView.toFront();
        this.tableColPatIdentif1.setCellValueFactory(new PropertyValueFactory("PtIdentification"));
        this.tableColPatName1.setCellValueFactory(new PropertyValueFactory("PtName"));
        this.tableColPatPsurname1.setCellValueFactory(new PropertyValueFactory("PtPlastname"));
        this.tableColPatSsurname1.setCellValueFactory(new PropertyValueFactory("PtSlastname"));
        this.tableColPatGender1.setCellValueFactory(new PropertyValueFactory("PtGender"));
        this.tableColPatEmail1.setCellValueFactory(new PropertyValueFactory("PtEmail"));
        this.tableColId1.setCellValueFactory(new PropertyValueFactory("PtId"));

        this.tableColDocBreaks1.setCellValueFactory(new PropertyValueFactory("DrBreak"));
        this.tableColDocFinishWork1.setCellValueFactory(new PropertyValueFactory("DrFinisworking"));
        this.tableColDocLicense1.setCellValueFactory(new PropertyValueFactory("DrLicense"));
        this.tableColDocId1.setCellValueFactory(new PropertyValueFactory("DoctorPsurname"));
        this.tableColDocName1.setCellValueFactory(new PropertyValueFactory("DoctorName"));
        this.tableColDocFolio1.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork1.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode1.setCellValueFactory(new PropertyValueFactory("DrCode"));

        this.tableColDocBreaks11.setCellValueFactory(new PropertyValueFactory("DrBreak"));
        this.tableColDocFinishWork11.setCellValueFactory(new PropertyValueFactory("DrFinisworking"));
        this.tableColDocLicense11.setCellValueFactory(new PropertyValueFactory("DrLicense"));
        this.tableColDocId11.setCellValueFactory(new PropertyValueFactory("DoctorPsurname"));
        this.tableColDocName11.setCellValueFactory(new PropertyValueFactory("DoctorName"));
        this.tableColDocFolio11.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork11.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode11.setCellValueFactory(new PropertyValueFactory("DrCode"));
        // this.tableColDocSpaces11.setCellFactory(new PropertyValueFactory("DrSpaces"));
        yearPicker.getItems().addAll(getYears(Year.now().getValue(), 2100));
        Absent.setToggleGroup(Tou);
        Cancelled.setToggleGroup(Tou);
        Attended.setToggleGroup(Tou);
        Scheduled.setToggleGroup(Tou);
        amRadio1.setToggleGroup(Hour);
        pmRadio1.setToggleGroup(Hour);

        ObservableList<String> items = FXCollections.observableArrayList(
                "2",
                "3",
                "4"
        );

        spaces.setItems(items);

        calendarsProperties();
        fillTablePatient();
        fillTableDoctors();

    }

    private Integer[] getYears(int startYear, int endYear) {
        return IntStream.rangeClosed(startYear, endYear).boxed().toArray(Integer[]::new);
    }

    private void calendarsProperties() {
        currentYearMonth = YearMonth.now();

        GridPane calendarPane = createCalendar(currentYearMonth);
        calendarPane.setPrefSize(rootCalendar.getPrefWidth() / 2 + 400, rootCalendar.getPrefHeight() / 2 + 180);
        calendarPane.setGridLinesVisible(true);

        AnchorPane.setTopAnchor(calendarPane, (rootCalendar.getHeight() - calendarPane.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(calendarPane, (rootCalendar.getWidth() - calendarPane.getPrefWidth()) / 2);

        rootCalendar.heightProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setTopAnchor(calendarPane, (newVal.doubleValue() - calendarPane.getPrefHeight()) / 2));

        rootCalendar.widthProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setLeftAnchor(calendarPane, (newVal.doubleValue() - calendarPane.getPrefWidth()) / 2));

        rootCalendar.getChildren().add(calendarPane);

    }

    private GridPane createCalendar(YearMonth yearMonth) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        LocalDate firstDayOfMonth = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        int daysInMonth = yearMonth.lengthOfMonth();

        String[] dayNames = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(dayNames[i]);
            dayLabel.setAlignment(Pos.CENTER);
            dayLabel.setStyle("-fx-font-weight: bold");

            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);

            gridPane.add(dayLabel, i, 0);
        }

        int row = 1;
        int col = firstDayOfMonth.getDayOfWeek().getValue() % 7;

        final Label[] selectedLabel = {null}; // Usamos un array para almacenar la etiqueta seleccionada

        for (int day = 1; day <= daysInMonth; day++) {
            final int finalDay = day;

            Label dayLabel = new Label(Integer.toString(day));
            dayLabel.setStyle("-fx-font-size: 14");

            final int finalRow = row;
            final int finalCol = col;

            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.TOP_LEFT);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);

            dayLabel.setOnMouseClicked(event -> {
                if (selectedLabel[0] != null) {
                    selectedLabel[0].setStyle(""); // Restablece el estilo de la etiqueta anteriormente seleccionada
                }

                if (!dayLabel.getStyle().contains("green")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE");
                    String dayOfWeek = firstDayOfMonth.plusDays(finalDay - 1).format(formatter);

                    System.out.println("Día clickeado: " + finalDay + " (" + dayOfWeek + ")");
                    dayLabel.setStyle("-fx-background-color: green;");
                    selectedLabel[0] = dayLabel; // Almacena la etiqueta recién seleccionada
                }
            });

            gridPane.add(dayLabel, col, row);

            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
        for (int i = 0; i < 7; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i <= row; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        return gridPane;
    }

    private void fillAppoiment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        Respuesta r = null;
        AppointmentService service = new AppointmentService();
        this.userDto = (UserDto) AppContext.getInstance().get("Usuario");
        if (userDto != null && patientDto != null) {
            appointmentDto.setAtPatient(patientDto);
            appointmentDto.setAtUserregister(userDto);
            appointmentDto.setAtReason(reason.getText());
            Long numLong = Long.parseLong(numberP.getText());
            appointmentDto.setAtTelephone(numLong);
            appointmentDto.setAtEmail(email.getText());
            System.out.println(email.getText());
            if (Scheduled.isSelected()) {
                appointmentDto.setAtState("Programada");
            } else if (Attended.isSelected()) {
                appointmentDto.setAtState("Atendida");
            } else if (Cancelled.isSelected()) {
                appointmentDto.setAtState("Cancelada");
            } else {
                appointmentDto.setAtState("Ausente");
            }

            System.out.println(appointmentDto.getAtPatient().getPtName() + " " + appointmentDto.getAtUserregister().getUsName());
            r = service.saveAppointment(appointmentDto);
        }
        if (r.getEstado()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "Registrada");
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "", getStage(), "No se pudo Registrar");
        }
    }

    @FXML
    private void ContinueDetail(ActionEvent event) {
        fillAppoiment();
        SpaceDto spacesDto = new SpaceDto();

        for (int i = 0; i <= doctorDto.getDrSpaces(); i++) {
            spacesDto.setSeId(0);
            //spacesDto.setSeHour(seHour);
        }
    }

    @FXML
    private void openAppoimentRegister(MouseEvent event) {
        OptionsMainDiary1.toFront();
    }

    @FXML
    private void openDiaryV(MouseEvent event) {
        OptionsMainDiary.toFront();
    }

    @FXML
    private void backDiary(MouseEvent event) {
        FlowController.getInstance().goMain("ViewMaintenanceOptions");
    }

    @FXML
    private void completeAppointment(MouseEvent event) {
        CreateAppointment.toFront();
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
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", getStage(), "Paciente Guardado");
            //limpiar los campos 
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", getStage(), "Error al guardar paciente");
        }
        patientDto = new PatientDto();
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
            OptionsMainDiary1.toFront();
        } else {
        }
    }

    public static Predicate<DoctorDto> filterByTimeRange(LocalTime startTimeParam, LocalTime endTimeParam) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return doctorDto -> {
            LocalTime doctorStartTime = LocalTime.parse(doctorDto.getDrIniworking(), formatter);
            LocalTime doctorEndTime = LocalTime.parse(doctorDto.getDrFinisworking(), formatter);
            // debbug
            System.out.println("Doctor: " + doctorDto.getDoctorName());
            System.out.println("Doctor Start Time: " + doctorStartTime);
            System.out.println("Doctor End Time: " + doctorEndTime);
            System.out.println("Start Time Param: " + startTimeParam);
            System.out.println("End Time Param: " + endTimeParam);

            boolean startInRange = !startTimeParam.isBefore(doctorStartTime);
            boolean endInRange = !endTimeParam.isAfter(doctorEndTime);

            boolean result = startInRange && endInRange;

            System.out.println("Result: " + result);
            System.out.println("");

            return result;
        };
    }

    @FXML
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
        if (doctorList.isEmpty()) {
            System.out.println("fdf");
        } else {
            doctorObservableList = FXCollections.observableArrayList(doctorList);
        }
        this.tableViewDoctorsDiary1.refresh();
        this.tableViewDoctorsDiary1.setItems(doctorObservableList);
        this.tableViewDoctorsDiary.refresh();
        this.tableViewDoctorsDiary.setItems(doctorObservableList);
    }

    @FXML
    private void patientClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            this.userDto = (UserDto) AppContext.getInstance().get("Usuario");
            patientDto = tableViewPatient1.getSelectionModel().getSelectedItem();
            if (patientDto != null) {
                nameP.setText(patientDto.getPtName());
                userLog.setText(userDto.getUsName());
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

    /*
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
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Paciente Eliminado Correctamente");
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar ", getStage(), "Error al eliminar paciente");
                    }
                }
            }
        }
        if (event.getClickCount() == 2) {
            patientDto = tableViewPatient.getSelectionModel().getSelectedItem();
            if (patientDto != null) {
                fillPatient(patientDto);
            }
        }
    }
     */
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
            eventAgendDoct();
            textMainDoctor12.setText(doctorDto.getDoctorName());
        }
    }

    public void eventAgendDoct() {
        GridPane DiaryPane = createWeekCalendarWithHeaders(doctorDto.getDrSpaces());
        DiaryPane.setPrefSize(rootDocDiary.getPrefWidth() / 2 + 500, rootDocDiary.getPrefHeight() / 2 + 180);
        DiaryPane.setGridLinesVisible(true);

        AnchorPane.setTopAnchor(DiaryPane, (rootDocDiary.getHeight() - DiaryPane.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(DiaryPane, (rootDocDiary.getWidth() - DiaryPane.getPrefWidth()) / 2);

        rootDocDiary.heightProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setTopAnchor(DiaryPane, (newVal.doubleValue() - DiaryPane.getPrefHeight()) / 2));

        rootDocDiary.widthProperty().addListener((obs, oldVal, newVal)
                -> AnchorPane.setLeftAnchor(DiaryPane, (newVal.doubleValue() - DiaryPane.getPrefWidth()) / 2));

        rootDocDiary.getChildren().add(DiaryPane);
    }

    @FXML
    private void updateAgenda(ActionEvent event) {

    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }

    public GridPane createWeekCalendarWithHeaders(int v) {
        final int op = 0;
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        String[] DAY_NAMES = new String[v];
        int doctoSpaces = v;

        if (doctoSpaces == 2) {
            DAY_NAMES[0] = "00";
            DAY_NAMES[1] = "30";
        } else if (doctoSpaces == 3) {
            DAY_NAMES[0] = "00";
            DAY_NAMES[1] = "20";
            DAY_NAMES[2] = "40";
        } else {
            DAY_NAMES[0] = "00";
            DAY_NAMES[1] = "15";
            DAY_NAMES[2] = "30";
            DAY_NAMES[3] = "45";
        }

        for (int i = 0; i < DAY_NAMES.length; i++) {
            Label dayLabel = new Label(DAY_NAMES[i]);
            dayLabel.setAlignment(Pos.CENTER);
            dayLabel.setStyle("-fx-font-weight: bold");

            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);

            gridPane.add(dayLabel, i + 1, 0);
        }

        for (int i = 0; i < 13; i++) {
            Label hourLabel = new Label(String.format("%02d", i));
            hourLabel.setAlignment(Pos.CENTER);
            hourLabel.setStyle("-fx-font-weight: bold");

            hourLabel.setMaxWidth(Double.MAX_VALUE);
            hourLabel.setMaxHeight(Double.MAX_VALUE);
            GridPane.setFillWidth(hourLabel, true);
            GridPane.setFillHeight(hourLabel, true);

            gridPane.add(hourLabel, 0, i + 1);
        }

        for (int day = 1; day <= DAY_NAMES.length + 1; day++) {

            for (int hour = 0; hour < 13; hour++) {
                Label cellLabel = new Label();
                cellLabel.setStyle("-fx-font-size: 10");

                cellLabel.setMaxWidth(Double.MAX_VALUE);
                cellLabel.setMaxHeight(Double.MAX_VALUE);
                cellLabel.setAlignment(Pos.TOP_LEFT);
                GridPane.setFillWidth(cellLabel, true);
                GridPane.setFillHeight(cellLabel, true);

                final int minute = day;
                final int finalHour = hour;

                cellLabel.setOnMouseClicked(event -> handleCellClick(gridPane, cellLabel, DAY_NAMES, finalHour));

                gridPane.add(cellLabel, day, hour + 1);
            }
        }

        for (int i = 0; i < DAY_NAMES.length + 1; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setFillWidth(true);
            columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
            columnConstraints.setPercentWidth(9.5);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < 13; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setFillHeight(true);
            rowConstraints.setVgrow(javafx.scene.layout.Priority.ALWAYS);
            rowConstraints.setPercentHeight(6.25);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        return gridPane;
    }

    private void handleCellClick(GridPane gridPane, Label cellLabel, String min[], int hour) {
        int maxCitas = Integer.parseInt(spaces.getValue());

        if (!cellLabel.getStyle().contains("green")) {
           
            int citasAgregadas = 0;
            while (citasAgregadas < maxCitas) {
                cellLabel.setStyle("-fx-background-color: green;");
                Label label = new Label("Cita");
                label.setStyle("-fx-font-size: 10");
                GridPane.setColumnSpan(label, 1);
                GridPane.setRowSpan(label, 1);
                GridPane.setColumnIndex(label, GridPane.getColumnIndex(cellLabel) + citasAgregadas);
                GridPane.setRowIndex(label, GridPane.getRowIndex(cellLabel));
                gridPane.getChildren().add(label);
                citasAgregadasList.add(label);  

                citasAgregadas++;
            }
            for (int j = 0; j < min.length; j++) {
                System.out.println(min[j]);
                LocalTime horaActual = LocalTime.of(hour, Integer.parseInt(min[j]));
                String horaFormateada = horaActual.format(timeFormatter);
                System.out.println("Hora agregada: " + horaFormateada);
            }
        }
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @FXML
    private void selectDiary(MouseEvent event) {
    }

}
