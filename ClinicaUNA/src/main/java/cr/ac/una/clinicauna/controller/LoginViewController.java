/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.service.UserService;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jomav
 */
public class LoginViewController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    UserDto userDto;
    private List<UserDto> usersList;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //estos funcionan perfecto, sustituyan las variables y los prueban con sus bases de datos
        loadUserId(Long.valueOf("304"));
        loadUserNamePass("ssdfsf", "23");
        deleteUserId(Long.valueOf("304"));
        loadUsers(); 
         
        
        
        /*  Me falta el de guardar solamente , me da un error el nos constrains
        UserDto user = new UserDto();
        user.setUsName("Roberto");
        user.setUsPlastname("Sancho");
        user.setUsSlastname("Lopez");
        user.setUsIdentification("2342342");
        user.setUsType("Doctor");
        user.setUsLenguage("English");
        user.setUsState("A");
        user.setUsUsername("dili");
        user.setUsPassword("1234");
        user.setUsEmail("dio3sancho@gmail.com");
        user.setUsRecover("N");
        user.setUsTemppassword("");
        user.setUsId("");
        user.setUsCode("vergsdfsdf3r");
     */
    }

    private void saveUser(UserDto userDto) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.saveUser(userDto);
            if (respuesta.getEstado()) {
                this.userDto = (UserDto) respuesta.getResultado("User");
                System.out.println("Usuario guardado exitosamente. Nombre: " + userDto.getUsName());
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Usuario", getStage(), "Ocurrió un error al guardar el usuario.");
        }
    }

    private void loadUserId(Long id) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.getUserId(id);
            if (respuesta.getEstado()) {
                this.userDto = (UserDto) respuesta.getResultado("User");
                System.out.println(userDto.getUsName());
            } else {

                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar empleado", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el empleado.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleado", getStage(), "Ocurrio un error consultando el empleado.");
        }
    }

    private void loadUserNamePass(String name, String password) {
        try {
            UserService service = new UserService();
            Respuesta respuesta = service.getUser(name, password);
            if (respuesta.getEstado()) {
                this.userDto = (UserDto) respuesta.getResultado("User");
                System.out.println(userDto.getUsName());
            } else {

                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar usuarios", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando el usuarios.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Empleado", getStage(), "Ocurrio un error consultando el usuarios.");
        }
    }

      private void loadUsers() {
        try {
            UserService service = new UserService();
            usersList = service.getUsuarios();
            if (!usersList.isEmpty()) {
                //aca se obtienen los datos para cargarlos en algun lado
            } else {

            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error consultando  usuarios.", ex);

            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar usuarios", getStage(), "Ocurrio un error consultando usuarios.");
        }
    }
      
    private void deleteUserId(Long id) {
        try {
            if (this.userDto.getUsId() == null) {
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

    
    
    
    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
