/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private TextField textFieldSearch_Rol;
    @FXML
    private TextField textFieldSearch_Susername;
    @FXML
    private TextField textFieldSearch_State;
    @FXML
    private TableView<UserDto> tableViewUser;
    @FXML
    private TableColumn<UserDto, String> tableColAct;
    @FXML
    private TableColumn<UserDto, String> tableColIdentif;
    @FXML
    private TableColumn<UserDto, String> tableColName;
    @FXML
    private TableColumn<UserDto, String> tableColPsurname;
    @FXML
    private TableColumn<UserDto, String> tableColSsurname;
    @FXML
    private TableColumn<UserDto, String> tableColUserName;
    @FXML
    private TableColumn<UserDto, String> tableColEmail;
    @FXML
    private TableColumn<UserDto, String> tableColAdmi;
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
    @FXML
    private Button BtndeleteDoctor;
    @FXML
    private JFXTimePicker timepickerFinWork;
    @FXML
    private Button btnMantUsers;
    @FXML
    private Button btnMantDoctors;
    List<UserDto> userList = new ArrayList<>();
    private ObservableList<UserDto> userObservableList;
    List<DoctorDto> doctorList = new ArrayList<>();
    private ObservableList<DoctorDto> doctorObservableList;
    @FXML
    private BorderPane MenuView;

    UserDto userDto = new UserDto();
    DoctorDto doctorDto = new DoctorDto();
    private TextField freeTimeMainField1;
    @FXML
    private TextField usernameMainField1;
    private TextField textFieldSearch_Ident;
    @FXML
    private TextField textFieldSearch_Usuario;
    @FXML
    private TextField textFieldSearchDoc_State;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OptionsMenuView.toFront();
        this.tableColAct.setCellValueFactory(new PropertyValueFactory("UsState"));
        this.tableColIdentif.setCellValueFactory(new PropertyValueFactory("UsIdentification"));
        this.tableColName.setCellValueFactory(new PropertyValueFactory("UsName"));
        this.tableColPsurname.setCellValueFactory(new PropertyValueFactory("UsPlastname"));
        this.tableColSsurname.setCellValueFactory(new PropertyValueFactory("UsSlastname"));
        this.tableColUserName.setCellValueFactory(new PropertyValueFactory("UsUsername"));
        this.tableColEmail.setCellValueFactory(new PropertyValueFactory("UsEmail"));
        this.tableColAdmi.setCellValueFactory(new PropertyValueFactory("UsType"));

        this.tableColDocBreaks.setCellValueFactory(new PropertyValueFactory("DrBreak"));
        this.tableColDocFinishWork.setCellValueFactory(new PropertyValueFactory("DrFinisworking"));
        this.tableColDocLicense.setCellValueFactory(new PropertyValueFactory("DrLicense"));
        this.tableColDocId.setCellValueFactory(new PropertyValueFactory("DoctorPsurname"));
        this.tableColDocName.setCellValueFactory(new PropertyValueFactory("DoctorName"));
        this.tableColDocFolio.setCellValueFactory(new PropertyValueFactory("DrFol"));
        this.tableColDocIniWork.setCellValueFactory(new PropertyValueFactory("DrIniworking"));
        this.tableColDocCode.setCellValueFactory(new PropertyValueFactory("DrCode"));

        fillTableUsers();
        fillTableDoctors();
    }

    private void fillTableUsers() {
        UserService service = new UserService();
        userList = service.getUsers();
        if (userList.isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "No hay ningun Usuario", getStage(), "");
        } else {
            userObservableList = FXCollections.observableArrayList(userList);
        }
        this.tableViewUser.refresh();
        this.tableViewUser.setItems(userObservableList);
    }
//jfoenix + cambiar tabla

    private void fillTableDoctors() {
        DoctorService service = new DoctorService();
        doctorList = service.getDoctor();
        if (doctorList.isEmpty()) {
        } else {
            doctorObservableList = FXCollections.observableArrayList(doctorList);
        }
        this.tableViewDoctors.refresh();
        this.tableViewDoctors.setItems(doctorObservableList);
    }

    @FXML
    private void UpdateWorkerEnter(KeyEvent event) {
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
        if (userDto != null) {
            UserDto us = new UserDto();
            //  UserService service = new UserService();
            //  service.saveUser(bindNewUser());
        }
    }

//    UserDto bindNewUser() {
//       /*
//        UserDto user = new UserDto();
//        user.setUsId(userDto.getUsId());
//        user.setUsName(userMainField.getText());
//        user.setUsPlastname(psurnameMainField.getText());
//        user.setUsSlastname(ssurnameMainField.getText());
//        user.setUsUsername(usernameMainField.getText());
//        user.setUsEmail(emailMainField.getText());
//        user.setUsState(userDto.getUsState());
//        user.setUsType("Default");
//        user.setUsIdentification(identMainField.getText());
//    */
//        return user;
//    }
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

    @FXML
    private void exit(ActionEvent event) {
        FlowController.getInstance().goMain("LoginView");
    }

    @FXML
    private void searchUser_Name(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);

        textFieldSearch_Name.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsName().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    private void filteredUsers(FilteredList<UserDto> list) {
        SortedList<UserDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewUser.comparatorProperty());
        tableViewUser.setItems(sorted);
    }

    @FXML
    private void searchUser_Pusername(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Pusername.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsPlastname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_username(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Usuario.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsUsername().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_Rol(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Rol.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsType().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_Susername(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_Susername.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsSlastname().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void searchUser_State(KeyEvent event) {
        FilteredList<UserDto> filteredUser = new FilteredList<>(userObservableList, f -> true);
        textFieldSearch_State.textProperty().addListener((observable, value, newValue) -> {
            filteredUser.setPredicate(UserDto -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();
                if (UserDto.getUsState().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        filteredUsers(filteredUser);
    }

    @FXML
    private void userClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            userDto = tableViewUser.getSelectionModel().getSelectedItem();
            fillUser(userDto);
            System.out.println(userDto.getUsName());
        }
    }

    private void fillUser(UserDto user) {
        userMainField.setText(user.getUsName());
        psurnameMainField.setText(user.getUsPlastname());
        ssurnameMainField.setText(user.getUsSlastname());
        usernameMainField.setText(user.getUsUsername());
        emailMainField.setText(user.getUsUsername());
        identMainField.setText(user.getUsIdentification());
    }

    private void fillDoctors(DoctorDto doctorDto) {
        codeDocMainField.setText(doctorDto.getDrCode() + "");
        licenseDocMainField.setText(doctorDto.getDrLicense() + "");
        folioDocMainField.setText(doctorDto.getDrFol() + "");
        freeTimeMainField1.setText(doctorDto.getDrBreak());

        LocalTime defaultTime = LocalTime.of(12, 0);

        timepickerIniWork.setValue(defaultTime);
        timepickerFinWork.setValue(defaultTime);
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

    private void filteredDoctors(FilteredList<DoctorDto> list) {
        SortedList<DoctorDto> sorted = new SortedList<>(list);
        sorted.comparatorProperty().bind(tableViewDoctors.comparatorProperty());
        tableViewDoctors.setItems(sorted);
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
    private void searchDoctor_Folio(KeyEvent event){
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
    private void doctorClicked(MouseEvent event) {

        if (event.getClickCount() == 2) {
            doctorDto = tableViewDoctors.getSelectionModel().getSelectedItem();
            fillDoctors(doctorDto);
            System.out.println(userDto.getUsName());
        }

    }

    @FXML
    private void searchDoctor_State(KeyEvent event) {
    
    }

}
