/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.ExamDto;
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
public class ExamService {
    
    
    public Respuesta getExamById(Integer id) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleExam/exam", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ExamDto examDto = (ExamDto) request.readEntity(ExamDto.class);
            return new Respuesta(true, "", "", "Exam", examDto);
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error obteniendo el Examen [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Examen.", "getExamen " + ex.getMessage());
        }
    }
    
 public Respuesta getExamsByPatientId(int patientId) {
    try {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", patientId);
        Request request = new Request("ModuleExam/examPat", "/{id}", parametros);
        request.get();
        if (request.isError()) {
            return new Respuesta(false, request.getError(), "");
        }
        List<ExamDto> examDtoList = (List<ExamDto>) request.readEntity(new GenericType<List<ExamDto>>() {});

        return new Respuesta(true, "", "", "Exams", examDtoList);
    } catch (Exception ex) {
        Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error obteniendo los Exámenes para el paciente [" + patientId + "]", ex);
        return new Respuesta(false, "Error obteniendo los Exámenes para el paciente.", "getExamsByPatientId " + ex.getMessage());
    }
}


    public Respuesta saveExam(ExamDto examDto) {
        try {
            Request request = new Request("ModuleExam/exam");
            request.post(examDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ExamDto examsDto = (ExamDto) request.readEntity(ExamDto.class);
            return new Respuesta(true, "", "", "Exam", examsDto);
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error guardando el Examen.", ex);
            return new Respuesta(false, "Error guardando el Examen.", "guardarExamen" + ex.getMessage());
        }
    }

    public Respuesta deleteExam(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleExam/exam", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, "Error eliminando el examen.", ex);
            return new Respuesta(false, "Error eliminando el examen.", "eliminarExamen " + ex.getMessage());
        }
    }

    public List<ExamDto> getExams() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleExam/exam", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<ExamDto>) request.readEntity(new GenericType<List<ExamDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
}
