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

    public boolean isActive(String user, String password) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("user", user);
            parametros.put("pass", password);

            Request request = new Request("ModuleUser/userIsAct", "/{user}/{pass}", parametros);

            request.get();

            if (request.isError()) {
                return false;
            }

            return (boolean) request.readEntity(Boolean.class);

        } catch (Exception ex) {
            // Log an error if an exception occurs
            Logger.getLogger(UserService.class.getName())
                    .log(Level.SEVERE, "Error verificando si esta activo [" + user + "]", ex);
            return false;
        }
    }

        public boolean isAdmin(String user, String password) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("user", user);
            parametros.put("pass", password);

            Request request = new Request("ModuleUser/userIsAdmin", "/{user}/{pass}", parametros);

            request.get();

            if (request.isError()) {
                return false;
            }

            return (boolean) request.readEntity(Boolean.class);

        } catch (Exception ex) {
            // Log an error if an exception occurs
            Logger.getLogger(UserService.class.getName())
                    .log(Level.SEVERE, "Error verificando si es admin [" + user + "]", ex);
            return false;
        }
    }
    
    
    
    
    
    public boolean isTempPass(String user, String password) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("user", user);
            parametros.put("pass", password);

            Request request = new Request("ModuleUser/userIsTemp", "/{user}/{pass}", parametros);

            request.get();

            if (request.isError()) {
                return false;
            }

            return (boolean) request.readEntity(Boolean.class);

        } catch (Exception ex) {
            // Log an error if an exception occurs
            Logger.getLogger(UserService.class.getName())
                    .log(Level.SEVERE, "Error verificando si la contraseña es temporal para el usuario [" + user + "]", ex);
            return false;
        }
    }

    public Respuesta getUserId(Integer id) {
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

    public Respuesta getUserName(String usUsername) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("usUsername", usUsername);
            Request request = new Request("ModuleUser/userName", "/{usUsername}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            UserDto userDto = (UserDto) request.readEntity(UserDto.class);
            return new Respuesta(true, "", "", "User", userDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usUsername + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta resetAccontPassword(String email, String password) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Email", email);
            parametros.put("Password", password);
            Request request = new Request("ModuleUser/user", "/{Email}/{Password}", parametros);
            request.post(email);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            UserDto userDto = (UserDto) request.readEntity(UserDto.class);
            return new Respuesta(true, "", "", "", userDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + email + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta ResetTemp(String email, String temPassword) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Email", email);
            parametros.put("temPassword", temPassword);
            Request request = new Request("ModuleUser/userTempPas", "/{Email}/{temPassword}", parametros);
            request.post(email);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            UserDto userDto = (UserDto) request.readEntity(UserDto.class);
            return new Respuesta(true, "", "", "", userDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + email + "]", ex);
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

    public Respuesta renovarToken() {
        try {
            Request request = new Request("ModuleUser/renovar");
            request.getRenewal();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            String token = (String) request.readEntity(String.class);
            return new Respuesta(true, "", "", "Token", token);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el token", ex);
            return new Respuesta(false, "Error renovando el token.", "renovarToken " + ex.getMessage());
        }
    }

    
}
