/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.PatientDto;
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
public class PatientService {

    public Respuesta getPatientId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModulePatient/patient", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            PatientDto patientDto = (PatientDto) request.readEntity(PatientDto.class);
            return new Respuesta(true, "", "", "Patient", patientDto);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Error obteniendo el paciente [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el paciente.", "getpaciente " + ex.getMessage());
        }
    }

    public Respuesta savePatient(PatientDto patientDto) {
        try {
            Request request = new Request("ModulePatient/patient");
            request.post(patientDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            PatientDto patientsDto = (PatientDto) request.readEntity(PatientDto.class);
            return new Respuesta(true, "", "", "Patient", patientsDto);
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, "Error guardando el paciente.", ex);
            return new Respuesta(false, "Error guardando el paciente.", "guardarpaciente" + ex.getMessage());
        }
    }

    public Respuesta deletePatient(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModulePatient/patient", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, "Error eliminando el paciente.", ex);
            return new Respuesta(false, "Error eliminando el paciente.", "eliminarpaciente " + ex.getMessage());
        }
    }

    public List<PatientDto> getPatients() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModulePatient/patients", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<PatientDto>) request.readEntity(new GenericType<List<PatientDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
