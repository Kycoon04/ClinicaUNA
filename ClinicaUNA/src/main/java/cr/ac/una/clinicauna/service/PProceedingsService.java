/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.FamilybackgroundDto;
import cr.ac.una.clinicauna.model.PProceedingsDto;
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
public class PProceedingsService {
        
     public Respuesta getPProceedingsId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModulePProceeding/pProceeding", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            PProceedingsDto pProceedingsDtos = (PProceedingsDto) request.readEntity(PProceedingsDto.class);
            return new Respuesta(true, "", "", "PProceeding", pProceedingsDtos);
        } catch (Exception ex) {
            Logger.getLogger(PProceedingsService.class.getName()).log(Level.SEVERE, "Error obteniendo  [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo ", "getPProceeding " + ex.getMessage());
        }
    }
    
    public Respuesta savePProceedings(PProceedingsDto pProceedingsDto) {
        try {
            Request request = new Request("ModulePProceeding/pProceeding");
            request.post(pProceedingsDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            PProceedingsDto pProceedingsDtos = (PProceedingsDto) request.readEntity(PProceedingsDto.class);
            return new Respuesta(true, "", "", "PProceeding", pProceedingsDtos);
        } catch (Exception ex) {
            Logger.getLogger(PProceedingsService.class.getName()).log(Level.SEVERE, "Error obteniendo  ", ex);
            return new Respuesta(false, "Error obteniendo  .", "guardar" + ex.getMessage());
        }
    }

    public Respuesta deletePProceedings(int id) {
        try {
      
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModulePProceeding/pProceeding", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(PProceedingsService.class.getName()).log(Level.SEVERE, "Error obteniendo  ", ex);
            return new Respuesta(false, "Error obteniendo  ", "eliminar " + ex.getMessage());
        }
    }

    public List<PProceedingsDto> getFamilybackground(){
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModulePProceeding/pProceedings", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<PProceedingsDto>) request.readEntity(new GenericType<List<PProceedingsDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
