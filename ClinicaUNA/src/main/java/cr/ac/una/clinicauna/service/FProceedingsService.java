/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.FProceedingsDto;
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
public class FProceedingsService {
    
      
    public Respuesta getFProceedingsSById(Integer id) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleFProceedings/fProceedings", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            FProceedingsDto examDto = (FProceedingsDto) request.readEntity(FProceedingsDto.class);
            return new Respuesta(true, "", "", "FProceedings", examDto);
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error obteniendo el FProceedings [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el FProceedings.", "getFProceedings " + ex.getMessage());
        }
    }

    public Respuesta savefProceedings(FProceedingsDto fProceedingsDto) {
        try {
            Request request = new Request("ModuleFProceedings/fProceedings");
            request.post(fProceedingsDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            FProceedingsDto fProceedingDto = (FProceedingsDto) request.readEntity(FProceedingsDto.class);
            return new Respuesta(true, "", "", "FProceedings", fProceedingDto);
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error guardando el FProceedings.", ex);
            return new Respuesta(false, "Error guardando el FProceedings.", "guardarFProceedings" + ex.getMessage());
        }
    }

    public Respuesta deleteFProceedings(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleFProceedings/fProceedings", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error eliminando el FProceedings.", ex);
            return new Respuesta(false, "Error eliminando el FProceedings.", "eliminarFProceedings " + ex.getMessage());
        }
    }

    public List<FProceedingsDto> getfProceedings() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleFProceedings/fProceedings", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<FProceedingsDto>) request.readEntity(new GenericType<List<FProceedingsDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    
}
