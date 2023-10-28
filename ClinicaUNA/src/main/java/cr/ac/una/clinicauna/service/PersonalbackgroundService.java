/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.DiseaseDto;
import cr.ac.una.clinicauna.model.PersonalbackgroundDto;
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
 * @author Dilan
 */
public class PersonalbackgroundService {
    
      public Respuesta getPersonalbackgroundId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModulePBackground/pBackground", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            PersonalbackgroundDto diseaseDto = (PersonalbackgroundDto) request.readEntity(PersonalbackgroundDto.class);
    
            return new Respuesta(true, "", "", "PersonalBackground",diseaseDto);
        } catch (Exception ex) {
            Logger.getLogger(PersonalbackgroundService.class.getName()).log(Level.SEVERE, "Error obteniendo  [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo .", " " + ex.getMessage());
        }
    }


    public Respuesta savePersonalbackground(PersonalbackgroundDto personalbackgroundDto) {
        try {
            Request request = new Request("ModulePBackground/pBackground");
            request.post(personalbackgroundDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            PersonalbackgroundDto personalbackgroundDtos = (PersonalbackgroundDto) request.readEntity(PersonalbackgroundDto.class);
            return new Respuesta(true, "", "", "PersonalBackground", personalbackgroundDtos);
        } catch (Exception ex) {
            Logger.getLogger(PersonalbackgroundService.class.getName()).log(Level.SEVERE, "Error guardando .", ex);
            return new Respuesta(false, "Error guardando .", "guardarPersonalBackground" + ex.getMessage());
        }
    }

    public Respuesta deletePersonalbackground(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModulePBackground/pBackground", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(PersonalbackgroundService.class.getName()).log(Level.SEVERE, "Error eliminando .", ex);
            return new Respuesta(false, "Error eliminando .", "eliminarpBackground " + ex.getMessage());
        }
    }

    public List<PersonalbackgroundDto> getPersonalbackgrounds() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModulePBackground/pBackground", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<PersonalbackgroundDto>) request.readEntity(new GenericType<List<PersonalbackgroundDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    
}
