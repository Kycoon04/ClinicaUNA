/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.PatientDto;
import cr.ac.una.clinicauna.model.ProceedingsDto;
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
public class ProceedingsService {
      public Respuesta getProcedingsId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleProceedings/proceedings", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProceedingsDto procedingsDto = (ProceedingsDto) request.readEntity(ProceedingsDto.class);
            return new Respuesta(true, "", "", "Proceedings", procedingsDto);
        } catch (Exception ex) {
            Logger.getLogger(ProceedingsService.class.getName()).log(Level.SEVERE, "Error obteniendo las Actas [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo las Actas .", "getActas " + ex.getMessage());
        }
    }
      
    public Respuesta getProcedingsIdPatient(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleProceedings/proceedingsPat", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProceedingsDto procedingsDto = (ProceedingsDto) request.readEntity(ProceedingsDto.class);
            return new Respuesta(true, "", "", "Proceedings", procedingsDto);
        } catch (Exception ex) {
            Logger.getLogger(ProceedingsService.class.getName()).log(Level.SEVERE, "Error obteniendo las Actas [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo las Actas .", "getActas " + ex.getMessage());
        }
    }

    public Respuesta saveProcedings(ProceedingsDto proceedingsDto) {
        try {
            Request request = new Request("ModuleProceedings/proceedings");
            request.post(proceedingsDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProceedingsDto procedingsDto = (ProceedingsDto) request.readEntity(ProceedingsDto.class);
            return new Respuesta(true, "", "", "Proceedings", procedingsDto);
        } catch (Exception ex) {
            Logger.getLogger(ProceedingsService.class.getName()).log(Level.SEVERE, "Error guardando las Actas.", ex);
            return new Respuesta(false, "Error guardando Actas.", "guardarActas" + ex.getMessage());
        }
    }

    public Respuesta deleteProcedings(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleProceedings/proceedings", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProceedingsService.class.getName()).log(Level.SEVERE, "Error eliminando el paciente.", ex);
            return new Respuesta(false, "Error eliminando el paciente.", "eliminarpaciente " + ex.getMessage());
        }
    }

    public List<ProceedingsDto> getProcedings() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleProceedings/proceedings", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<ProceedingsDto>) request.readEntity(new GenericType<List<ProceedingsDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
