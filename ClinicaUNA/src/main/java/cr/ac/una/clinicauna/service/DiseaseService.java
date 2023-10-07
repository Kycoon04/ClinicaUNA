/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;


import cr.ac.una.clinicauna.model.DiseaseDto;
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
public class DiseaseService {
    
      public Respuesta getDiseaseId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDiseases/disease", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DiseaseDto diseaseDto = (DiseaseDto) request.readEntity(DiseaseDto.class);
            System.out.println(diseaseDto.getDsId());
            return new Respuesta(true, "", "", "Disease",diseaseDto);
        } catch (Exception ex) {
            Logger.getLogger(DiseaseService.class.getName()).log(Level.SEVERE, "Error obteniendo enfermedad [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo enfermedad.", " " + ex.getMessage());
        }
    }


    public Respuesta saveDisease(DiseaseDto diseaseDto) {
        try {
            Request request = new Request("ModuleDiseases/disease");
            request.post(diseaseDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DiseaseService diseaseDtos = (DiseaseService) request.readEntity(DiseaseService.class);
            return new Respuesta(true, "", "", "Disease", diseaseDtos);
        } catch (Exception ex) {
            Logger.getLogger(DiseaseService.class.getName()).log(Level.SEVERE, "Error guardando enfermedad.", ex);
            return new Respuesta(false, "Error guardando enfermedad.", "guardarenfermedad" + ex.getMessage());
        }
    }

    public Respuesta deleteDisease(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDiseases/disease", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(DiseaseService.class.getName()).log(Level.SEVERE, "Error eliminando agenda.", ex);
            return new Respuesta(false, "Error eliminando agenda.", "eliminareagenda " + ex.getMessage());
        }
    }

    public List<DiseaseService> getDisease() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleDiseases/disease", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<DiseaseService>) request.readEntity(new GenericType<List<DiseaseService>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
}
