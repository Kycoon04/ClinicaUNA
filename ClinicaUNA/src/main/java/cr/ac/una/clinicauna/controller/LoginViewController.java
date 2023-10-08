package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
import cr.ac.una.clinicauna.model.SpaceDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.AppointmentService;
import cr.ac.una.clinicauna.service.DiaryService;
import cr.ac.una.clinicauna.service.DiseaseService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.ProceedingsService;
import cr.ac.una.clinicauna.service.SpaceService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 *
 * @author jomav
 */
public class LoginViewController extends Controller implements Initializable {

    UserDto userDto;
    private List<UserDto> usersList;

    DoctorDto doctorDto;
    private List<DoctorDto> doctorList;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private BorderPane RegisterView;
    @FXML
    private TextField userRegisField;
    @FXML
    private TextField surname1RegisField;
    @FXML
    private TextField surname2RegisField;
    @FXML
    private TextField usernameRegisField;
    @FXML
    private TextField emailRegisField;
    @FXML
    private TextField landlineRegisField;
    @FXML
    private TextField idRegisField;
    @FXML
    private TextField phoneNumberRegisField;
    @FXML
    private TextField passwordRegisField;
    @FXML
    private TextField password2RegisField;
    @FXML
    private BorderPane loginView;

    DiaryDto diaryDto;
    private List<DiaryDto> diaryList;

    DiseaseDto diseaseDto;
    private List<DiseaseDto> diseaseList;

    
    ProceedingsDto proceedingsDto;
    private List<ProceedingsDto> ProceedingsList;
    @FXML
    private VBox VboxChangeIdioms;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login("dfg", "12");
    }

    @Override
    public void initialize() {

    }

    private void saveUser(UserDto userDto) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.saveUser(userDto);
            this.userDto = (UserDto) respuesta.getResultado("User");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), "Ocurrió un error al guardar el usuario.");
        }
    }

    private void getUser(Long id) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.getUserId(id);
            this.userDto = (UserDto) respuesta.getResultado("User");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el empleado.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleado", getStage(), "Ocurrio un error consultando el empleado.");
        }
    }

    private void login(String name, String password) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.getUser(name, password);
            if (respuesta.getEstado()) {
                this.userDto = (UserDto) respuesta.getResultado("User");
                AppContext.getInstance().set("Token", userDto.getToken());
                AppContext.getInstance().set("Usuario", userDto);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación Usuario", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el usuarios.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleado", getStage(), "Ocurrio un error consultando el usuarios.");
        }
    }

    private void getUsers() {
        try {
            UserService service = new UserService();
            usersList = service.getUsers();
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando  usuarios.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar usuarios", getStage(), "Ocurrio un error consultando usuarios.");
        }
    }

    private void deleteUser(Integer id) {
        try {
            if (id != null && id > 0) {
                UserService service = new UserService();
                Respuesta respuesta = service.deleteUser(id);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar usuario", getStage(), "usuario eliminado correctamente.");
                }

            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), "Debe cargar el usuario que desea eliminar.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error eliminando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar empleado", getStage(), "Ocurrio un error eliminando el empleado.");
        }
    }

    // doctor 
    private void saveDoctor(DoctorDto doctorDto) {
        try {
            DoctorService service = new DoctorService();
            Respuesta respuesta = service.saveDoctor(doctorDto);
            this.userDto = (UserDto) respuesta.getResultado("Doctor");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando el Doctor.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Doctor", getStage(), "Ocurrió un error al guardar el Doctor.");
        }
    }

    private void getDoctor(int id) {
        try {
            DoctorService service = new DoctorService();
            Respuesta respuesta = service.getDoctor(id);
            this.doctorDto = (DoctorDto) respuesta.getResultado("Doctor");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el Doctor.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Doctor", getStage(), "Ocurrio un error consultando el Doctor.");
        }
    }

    private void getDoctors() {
        try {
            DoctorService service = new DoctorService();
            doctorList = service.getDoctor();
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando  Doctores.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Doctores", getStage(), "Ocurrio un error consultando Doctores.");
        }
    }

    private void deleteDoctor(Integer id) {
        System.out.println(id);
        try {
            if (id != null && id > 0) {
                DoctorService service = new DoctorService();
                Respuesta respuesta = service.deleteDoctor(id);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Doctor", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Doctor", getStage(), "Doctor eliminado correctamente.");
                }

            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Doctor", getStage(), "Debe cargar el Doctor que desea eliminar.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Doctor.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Doctor", getStage(), "Ocurrio un error eliminando el Doctor.");
        }
    }

    //agenda
    private void saveDiary(DiaryDto diaryDto) {
        try {
            DiaryService service = new DiaryService();
            Respuesta respuesta = service.saveDiary(diaryDto);
            this.diaryDto = (DiaryDto) respuesta.getResultado("Diary");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando Agenda.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Agenda", getStage(), "Ocurrió un error al guardar Agenda.");
        }
    }

    private void getDiary(int id) {
        try {
            DiaryService service = new DiaryService();
            Respuesta respuesta = service.getDiaryId(id);
            this.diaryDto = (DiaryDto) respuesta.getResultado("Diary");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando Agenda.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Agenda", getStage(), "Ocurrio un error consultandoAgenda.");
        }
    }

    private void getDiary() {
        try {
            DiaryService service = new DiaryService();
            diaryList = service.getDiary();
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando  Agendas.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Agendas", getStage(), "Ocurrio un error consultando Agendas.");
        }
    }

    private void deleteDiary(Integer id) {
        System.out.println(id);
        try {
            if (id != null && id > 0) {
                DiaryService service = new DiaryService();
                Respuesta respuesta = service.deleteDiary(id);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Agenda", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Agenda", getStage(), "Agenda eliminado correctamente.");
                }

            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Agenda", getStage(), "Debe cargar la Agenda que desea eliminar.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error eliminando la Agenda.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Agenda", getStage(), "Ocurrio un error eliminando  Agenda.");
        }
    }

    
    //enfermedad
    private void saveDisease(DiseaseDto diseaseDto) {
        try {
            DiseaseService service = new DiseaseService();
            Respuesta respuesta = service.saveDisease(diseaseDto);
            this.diseaseDto = (DiseaseDto) respuesta.getResultado("Disease");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando enfermedad.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar enfermedad", getStage(), "Ocurrió un error al guardar enfermedad.");
        }
    }

    private void getDisease(int id) {
        try {
            DiseaseService service = new DiseaseService();
            Respuesta respuesta = service.getDiseaseId(id);
            this.diseaseDto = (DiseaseDto) respuesta.getResultado("Disease");
            System.out.println(diseaseDto.getDsName());
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando enfermedad.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar enfermedad", getStage(), "Ocurrio un error consultandoe nfermedad.");
        }
    }

     private void getDiseases() {
        try {
          DiseaseService service = new DiseaseService();
            diseaseList = service.getDisease();
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando  enfermedades.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar enfermedades", getStage(), "Ocurrio un error consultando enfermedades.");
        }
    }
     
    private void deleteDisease(Integer id) {
        System.out.println(id);
        try {
            if (id != null && id > 0) {
                DiaryService service = new DiaryService();
                Respuesta respuesta = service.deleteDiary(id);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar enfermedades", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar enfermedades", getStage(), "Agenda enfermedades correctamente.");
                }

            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar enfermedades", getStage(), "Debe cargar la enfermedades que desea eliminar.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error eliminando la enfermedades.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar enfermedades", getStage(), "Ocurrio un error eliminando  enfermedades.");
        }
    }

    
    //proceedings
    private void saveProceedings(ProceedingsDto proceedingsDto) {
        try {
            ProceedingsService service = new ProceedingsService();
            Respuesta respuesta = service.saveProcedings(proceedingsDto);
            this.proceedingsDto = (ProceedingsDto) respuesta.getResultado("Proceedings");
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando Acta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Acta", getStage(), "Ocurrió un error al guardar Acta.");
        }
    }

    private void getProceedings(int id) {
        try {
         ProceedingsService service = new ProceedingsService();
            Respuesta respuesta = service.getProcedingsId(id);
            this.proceedingsDto = (ProceedingsDto) respuesta.getResultado("Proceedings");
            System.out.println(proceedingsDto.getPsPatient().getPtName());
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando Acta.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Acta", getStage(), "Ocurrio un error consultando Acta.");
        }
    }

    private void deleteProceedings(Integer id) {
        System.out.println(id);
        try {
            if (id != null && id > 0) {
               ProceedingsService service = new ProceedingsService();
                Respuesta respuesta = service.deleteProcedings(id);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Acta", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Acta", getStage(), "Acta eliminado correctamente.");
                }

            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Acta", getStage(), "Debe cargar el Acta que desea eliminar.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Acta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Acta", getStage(), "Ocurrio un error eliminando Acta.");
        }
    }
    
    
       private void getProceedings() {
        try {
         ProceedingsService service = new ProceedingsService();
            ProceedingsList = service.getProcedings();
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando  Acta.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Acta", getStage(), "Ocurrio un error consultando Acta.");
        }
    }
     
       
       

    @FXML
    private void PasswordForget(MouseEvent event) {
    }

    @FXML
    private void AcceptLogin(ActionEvent event) {
    }

    @FXML
    private void Register(ActionEvent event) {
        RegisterView.toFront();
    }

    @FXML
    private void AcceptLoginEnter(KeyEvent event) {
    }

    @FXML
    private void Back(ActionEvent event) {
        loginView.toFront();
    }

    @FXML
    private void ConfirmRegister(ActionEvent event) {
    }

    @FXML
    private void changeIdiomSpanish(MouseEvent event) {
    }

    @FXML
    private void changeIdiomEnglish(MouseEvent event) {
    }

    @FXML
    private void changeIdiomFrench(MouseEvent event) {
    }

    @FXML
    private void changeIdiomJapanese(MouseEvent event) {
    }

    @FXML
    private void openChangeIdiom(MouseEvent event) {
        if(!VboxChangeIdioms.isVisible()){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(VboxChangeIdioms);
            slide.setToY(0);
            slide.play();
            VboxChangeIdioms.setTranslateY(-176);
            slide.setOnFinished((ActionEvent e) -> {
                VboxChangeIdioms.setVisible(true);
            });
        }else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(VboxChangeIdioms);
            slide.setToY(-176);
            slide.play();
            VboxChangeIdioms.setTranslateY(0);
            slide.setOnFinished((ActionEvent e)->{
                 VboxChangeIdioms.setVisible(false);
            });
        }
    }
}

 
