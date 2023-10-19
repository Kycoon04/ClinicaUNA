/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.PatientDto;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private Map<Node, Posicion> mapaPosiciones = new HashMap<>();
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

    UserDto userDto = new UserDto();
    DoctorDto doctorDto = new DoctorDto();
    PatientDto patientDto = new PatientDto();
    DiseaseDto diseaseDto = new DiseaseDto();
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
    ToggleGroup Tou = new ToggleGroup();
    @FXML
    private BorderPane OptionsMainDiary1;
    @FXML
    private TabPane tabPaneMantWorkers111;
    @FXML
    private BorderPane OptionsMainPatientView;
    @FXML
    private TabPane tabPaneMantWorkers2;
    @FXML
    private Tab tabMantPatient;
    @FXML
    private TextField namePatMainField;
    @FXML
    private TextField firstNamePatMainField;
    @FXML
    private TextField lastNamePatMainField;
    @FXML
    private TextField identPatMainField;
    @FXML
    private RadioButton radioBtnMale;
    @FXML
    private RadioButton radioBtnFemale;
    @FXML
    private TextField emailPatMainField;
    @FXML
    private JFXDatePicker datePickerBirthdayPat;
    @FXML
    private TabPane tabPaneMantWorkers112;
    @FXML
    private Tab tabDiary2;
    @FXML
    private Text textMainDoctor12;
    @FXML
    private GridPane grid1;
    @FXML
    private RadioButton amRadio1;
    @FXML
    private RadioButton pmRadio1;
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
    private TableView<?> tableViewDoctorsDiary1;
    @FXML
    private TableColumn<?, ?> tableColDocCode11;
    @FXML
    private TableColumn<?, ?> tableColDocFolio11;
    @FXML
    private TableColumn<?, ?> tableColDocName11;
    @FXML
    private TableColumn<?, ?> tableColDocId11;
    @FXML
    private TableColumn<?, ?> tableColDocLicense11;
    @FXML
    private TableColumn<?, ?> tableColDocIniWork11;
    @FXML
    private TableColumn<?, ?> tableColDocFinishWork11;
    @FXML
    private TableColumn<?, ?> tableColDocBreaks11;
    @FXML
    private Tab tabPatient1;
    @FXML
    private TextField textFieldSearchPat_Name11;
    @FXML
    private TextField textFieldSearchPat_Pusername11;
    @FXML
    private TextField textFieldSearchPat_Identification11;
    @FXML
    private TextField textFieldSearchPat_Gender11;
    @FXML
    private TextField textFieldSearchPat_Susername11;
    @FXML
    private TableColumn<?, ?> tableColId11;
    @FXML
    private TableColumn<?, ?> tableColPatIdentif11;
    @FXML
    private TableColumn<?, ?> tableColPatPsurname11;
    @FXML
    private TableColumn<?, ?> tableColPatSsurname11;
    @FXML
    private TableColumn<?, ?> tableColPatGender11;
    @FXML
    private Tab tabMantPatient1;
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
    private TableView<?> tableViewAppoiment;
    @FXML
    private TextArea reasonTxtField;

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
        Absent.setToggleGroup(Tou);
        Cancelled.setToggleGroup(Tou);
        Attended.setToggleGroup(Tou);
        Scheduled.setToggleGroup(Tou);

        agenda();
        selectDiary();
        fillTablePatient();
        fillTableDoctors();

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

    @FXML
    private void detailsView(MouseEvent event) {

    }

    private class Posicion {

        int fila;
        int columna;

        public Posicion(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }
    }

    private void agenda() {
        String[] diasSemana = {"LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO", "DOMINGO"};
        for (int i = 0; i < diasSemana.length; i++) {
            String dia = diasSemana[i];
            Label label = new Label(dia);
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-content-display: center;");
            label.setWrapText(false);
            int columna = i + 1;
            int fila = 0;
            if (columna < grid.getColumnConstraints().size()) {
                label.setPrefWidth(grid.getColumnConstraints().get(columna).getPercentWidth());
            }
            label.setPrefHeight(grid.getRowConstraints().get(fila).getPercentHeight());
            grid.add(label, columna, fila);
            matrizAgenda[0][columna] = dia;

            mapaPosiciones.put(label, new ViewDiariesOptionsController.Posicion(fila, columna));
        }

        String hora = null;
        int indice = 1;
        for (int i = 1; i < 13; i++) {
            hora = Integer.toString(i) + ":00";
            Label label = new Label(hora);

            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-content-display: center;");
            label.setWrapText(false);

            int fila = indice;
            int columna = 0;

            if (indice < grid.getRowConstraints().size()) {
                label.setPrefWidth(grid.getColumnConstraints().get(columna).getPercentWidth());
                label.setPrefHeight(grid.getRowConstraints().get(fila).getPercentHeight());
            }

            grid.add(label, columna, fila);
            matrizAgenda[indice][0] = hora;

            mapaPosiciones.put(label, new ViewDiariesOptionsController.Posicion(fila, columna));

            indice++;
        }
    }

    private void obtenerElementosGrid() {
        for (Node node : grid.getChildren()) {
            if (node instanceof HBox) {
                Integer columna = GridPane.getColumnIndex(node);
                Integer fila = GridPane.getRowIndex(node);

                if (columna != null && fila != null) {
                    HBox hbox = (HBox) node;
                    for (Node child : hbox.getChildren()) {
                        if (child instanceof Label) {
                            String contenido = ((Label) child).getText();
                            if (!contenido.isEmpty()) {
                                String hora = matrizAgenda[fila][0];
                                String dia = matrizAgenda[0][columna];

                                System.out.println("Elemento en columna " + columna + ", fila " + fila
                                        + ": Contenido = " + contenido + ", Hora = " + hora + ", Día = " + dia);
                            }
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void selectDiary() {
        grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();

                int columna = obtenerColumnaDesdeX(x);
                int fila = obtenerFilaDesdeY(y);

                if (columna >= 0 && columna < 8 && fila >= 0 && fila < 13) {

                    HBox hbox = new HBox();

                    for (int i = 0; i < 3; i++) {
                        Label nuevoLabel = new Label(" " + (i + 1));
                        hbox.getChildren().add(nuevoLabel);
                    }
                    grid.add(hbox, columna, fila);

                    String hora = obtenerHoraDesdeFila(fila);
                    String dia = obtenerDiaDesdeColumna(columna);

                    System.out.println("Nuevos eventos agregados en columna " + columna + ", fila " + fila
                            + ": Hora = " + hora + ", Día = " + dia);
                } else {
                    System.out.println("Clic fuera de los límites del GridPane");
                }
            }
        });
    }

    private int obtenerColumnaDesdeX(double x) {
        return (int) (x / 100);
    }

    private int obtenerFilaDesdeY(double y) {
        return (int) (y / 25);
    }

    private String obtenerHoraDesdeFila(int fila) {
        return "Hora" + fila;
    }

    private String obtenerDiaDesdeColumna(int columna) {
        return "Día" + columna;
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
        } else {
            doctorObservableList = FXCollections.observableArrayList(doctorList);
        }
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
    }

    @FXML
    private void updateAgenda(ActionEvent event) {
        obtenerElementosGrid();
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
