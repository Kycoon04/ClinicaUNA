package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.SpaceDto;
import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.AppointmentService;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login("Kycoon04","Valverdejo04");
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
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), "Debe cargar el usuario que desea eliminar.");
            } else {
                UserService service = new UserService();
                Respuesta respuesta = service.deleteUser(id);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar usuario", getStage(), "usuario eliminado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error eliminando el empleado.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar empleado", getStage(), "Ocurrio un error eliminando el empleado.");
        }
    }
}
