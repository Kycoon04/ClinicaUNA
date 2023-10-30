/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.DoctorDto;
import cr.ac.una.clinicauna.model.FamilybackgroundDto;
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
public class FamilybackgroundService {
        
     public Respuesta getFamilybackground(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleFamilybackground/familybackground", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            FamilybackgroundDto familybackgroundDto = (FamilybackgroundDto) request.readEntity(FamilybackgroundDto.class);
            return new Respuesta(true, "", "", "Familybackground", familybackgroundDto);
        } catch (Exception ex) {
            Logger.getLogger(FamilybackgroundService.class.getName()).log(Level.SEVERE, "Error obteniendo  [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo ", "getFamilybackground " + ex.getMessage());
        }
    }
    
       public Respuesta getFamilybackgroundCode(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleFamilybackground/familybackgroundCode", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            FamilybackgroundDto familybackgroundDto = (FamilybackgroundDto) request.readEntity(FamilybackgroundDto.class);
            return new Respuesta(true, "", "", "Familybackground", familybackgroundDto);
        } catch (Exception ex) {
            Logger.getLogger(FamilybackgroundService.class.getName()).log(Level.SEVERE, "Error obteniendo  [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo ", "getFamilybackground " + ex.getMessage());
        }
    }
       
    public Respuesta saveFamilybackground(FamilybackgroundDto familybackgroundDto) {
        try {
            Request request = new Request("ModuleFamilybackground/familybackground");
            request.post(familybackgroundDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            FamilybackgroundDto familybackgroundDtos = (FamilybackgroundDto) request.readEntity(FamilybackgroundDto.class);
            return new Respuesta(true, "", "", "Familybackground", familybackgroundDtos);
        } catch (Exception ex) {
            Logger.getLogger(FamilybackgroundService.class.getName()).log(Level.SEVERE, "Error obteniendo  ", ex);
            return new Respuesta(false, "Error obteniendo  .", "guardar" + ex.getMessage());
        }
    }

    public Respuesta deleteFamilybackground(int id) {
        try {
            System.out.println("sdffsf"+id);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleFamilybackground/familybackground", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(FamilybackgroundService.class.getName()).log(Level.SEVERE, "Error obteniendo Doctor ", ex);
            return new Respuesta(false, "Error obteniendo Doctor ", "eliminarDoctor " + ex.getMessage());
        }
    }

    public List<FamilybackgroundDto> getFamilybackground(){
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleFamilybackground/familybackground", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<FamilybackgroundDto>) request.readEntity(new GenericType<List<FamilybackgroundDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
