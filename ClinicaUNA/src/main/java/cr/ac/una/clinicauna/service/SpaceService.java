/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.SpaceDto;
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
 * @author jomav
 */
public class SpaceService {
    
    public Respuesta getSpaceId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleSpace/space", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            SpaceDto spaceDto = (SpaceDto) request.readEntity(SpaceDto.class);
            return new Respuesta(true, "", "", "Space",spaceDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el espacio [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el espacio.", "getespacio " + ex.getMessage());
        }
    }


    public Respuesta saveSpace(SpaceDto userDto) {
        try {
            Request request = new Request("ModuleSpace/space");
            request.post(userDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            SpaceDto spaceDto = (SpaceDto) request.readEntity(SpaceDto.class);
            return new Respuesta(true, "", "", "User", spaceDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error guardando el espacio.", ex);
            return new Respuesta(false, "Error guardando el espacio.", "guardarespacio" + ex.getMessage());
        }
    }

    public Respuesta deleteSpace(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleSpace/space", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error eliminando el espacio.", ex);
            return new Respuesta(false, "Error eliminando el espacio.", "eliminarespacio " + ex.getMessage());
        }
    }

    public List<SpaceDto> getSpace() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleSpace/space", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<SpaceDto>) request.readEntity(new GenericType<List<SpaceDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
