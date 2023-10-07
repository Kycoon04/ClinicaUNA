package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.SpaceDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.AppointmentService;
import cr.ac.una.clinicauna.service.DoctorService;
import cr.ac.una.clinicauna.service.SpaceService;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //login("dfg","12");
       // getDoctor(307);
        
        
        
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
}
