/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.PatientDto;
import cr.ac.una.clinicaws.model.UsersDto;
import cr.ac.una.clinicaws.service.PatientService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
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
 * @author jomav
 */
@Path("/ModulePatient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Patient", description = "Operations on Patient")

//@Secure
public class ModulePatient {

    @EJB
    PatientService patientService;
    @GET
    @Path("/patient/{id}")
    public Response getPatient(@PathParam("id") Integer id) {
        try {
            Respuesta res = patientService.getPatient(id);
            if (!res.getEstado()) { 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Patient")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePatient.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Patient").build();
        }
    }

    @POST
    @Path("/patient")
    public Response savePatient(PatientDto patientDto) {
        try {
            Respuesta res = patientService.savePatient(patientDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Patient")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Patient").build();
        }
    }

    @DELETE
    @Path("/patient/{id}")
    public Response deletePatient(@PathParam("id") Integer id) {
        try {
            Respuesta res = patientService.deletePatient(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePatient.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Patient").build();
        }
    }

    @GET
    @Path("/patients")
    public Response getPatient() {
        try {
            Respuesta res = patientService.getPatient();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<UsersDto>>((List< UsersDto>) res.getResultado("Patient")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePatient.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Patients").build();
        }
    }
}
