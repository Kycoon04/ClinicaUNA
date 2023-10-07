/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.UserDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilan
 */
public class UserService {
 
    public Respuesta getUser(String user, String password) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Usuario", user);
            parametros.put("Clave", password);
            Request request = new Request("ModuleUser/user", "/{Usuario}/{Clave}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            UserDto userDto = (UserDto) request.readEntity(UserDto.class);
            return new Respuesta(true, "", "", "User", userDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + user + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUserId(Long id) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleUser/user", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            UserDto userDto = (UserDto) request.readEntity(UserDto.class);
            return new Respuesta(true, "", "", "User", userDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }


    public Respuesta saveUser(UserDto userDto) {
        try {
            Request request = new Request("ModuleUser/user");
            request.post(userDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            UserDto userDtoEn = (UserDto) request.readEntity(UserDto.class);
            return new Respuesta(true, "", "", "User", userDtoEn);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            return new Respuesta(false, "Error guardando el usuario.", "guardarusuario" + ex.getMessage());
        }
    }

    public Respuesta deleteUser(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleUser/user", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error eliminando el usuario.", ex);
            return new Respuesta(false, "Error eliminando el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

    public List<UserDto> getUsers() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleUser/users", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<UserDto>) request.readEntity(new GenericType<List<UserDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
