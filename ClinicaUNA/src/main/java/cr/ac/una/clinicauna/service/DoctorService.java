/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.DoctorDto;
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
public class DoctorService {
    
     public Respuesta getDoctor(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDoctor/doctor", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DoctorDto doctorDto = (DoctorDto) request.readEntity(DoctorDto.class);
            System.out.println(doctorDto.drUser);
            return new Respuesta(true, "", "", "Doctor", doctorDto);
        } catch (Exception ex) {
            Logger.getLogger(DoctorService.class.getName()).log(Level.SEVERE, "Error obteniendo Doctor [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo Doctor", "getDoctor " + ex.getMessage());
        }
    }
     
     public Respuesta getDoctorUser(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDoctor/doctorByUser", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DoctorDto doctorDto = (DoctorDto) request.readEntity(DoctorDto.class);
            System.out.println(doctorDto.drUser);
            return new Respuesta(true, "", "", "Doctor", doctorDto);
        } catch (Exception ex) {
            Logger.getLogger(DoctorService.class.getName()).log(Level.SEVERE, "Error obteniendo Doctor [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo Doctor", "getDoctor " + ex.getMessage());
        }
    }


    public Respuesta saveDoctor(DoctorDto doctorDto) {
        try {
            Request request = new Request("ModuleDoctor/doctor");
            request.post(doctorDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DoctorDto doctorDtos = (DoctorDto) request.readEntity(DoctorDto.class);
            return new Respuesta(true, "", "", "Doctor", doctorDtos);
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, "Error obteniendo Doctor ", ex);
            return new Respuesta(false, "Error obteniendo Doctor .", "guardarDoctor" + ex.getMessage());
        }
    }

    public Respuesta deleteDoctor(int id) {
        try {
            System.out.println("sdffsf"+id);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDoctor/doctor", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, "Error obteniendo Doctor ", ex);
            return new Respuesta(false, "Error obteniendo Doctor ", "eliminarDoctor " + ex.getMessage());
        }
    }

    public List<DoctorDto> getDoctor() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleDoctor/doctor", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<DoctorDto>) request.readEntity(new GenericType<List<DoctorDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
