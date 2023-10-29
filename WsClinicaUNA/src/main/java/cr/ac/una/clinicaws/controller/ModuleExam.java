/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.ExamDto;
import cr.ac.una.clinicaws.service.ExamService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import cr.ac.una.clinicaws.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dilan
 */
@Path("/ModuleExam")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Exam", description = "Operations on Exam")
//@Secure
public class ModuleExam {
      @EJB
    ExamService examService;

    @GET
    @Path("/exam/{id}")
    public Response getDoctor(@PathParam("id") Long id) {
        try {
            Respuesta res = examService.getExam(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Exam")).build();
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Examen").build();
        }
    }


    @POST
    @Path("/exam")
    public Response saveExam(ExamDto examDto) {
        try {
            Respuesta res = examService.saveExam(examDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Exam")).build();
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Examen").build();
        }
    }

    @DELETE
    @Path("/exam/{id}")
    public Response deleteDoctor(@PathParam("id") Integer id) {
        try {
            Respuesta res = examService.deleteExam(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Examen").build();
        }
    }


    @GET
    @Path("/exam")
    public Response getDoctors() {
        try {
            Respuesta res = examService.getExam();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ExamDto>>((List< ExamDto>) res.getResultado("Exam")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Examen").build();
        }
    }
    
    
    @GET
    @Path("/examPat/{id}")
    public Response getDoctorsPati(@PathParam("id") long PatientId) {
        try {
   
            Respuesta res = examService.getExamsByPatientId(PatientId); 
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            List<ExamDto> examDtoList = (List<ExamDto>) res.getResultado("Exams");

            return Response.ok(new GenericEntity<List<ExamDto>>(examDtoList) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ExamService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Examen").build();
        }
    }

}
