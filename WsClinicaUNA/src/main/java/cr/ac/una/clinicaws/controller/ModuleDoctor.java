/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DoctorDto;
import cr.ac.una.clinicaws.service.DoctorService;
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
@Path("/ModuleDoctor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Doctor", description = "Operations on Doctor")
//@Secure
public class ModuleDoctor {
    @EJB
    DoctorService doctorService;

    @GET
    @Path("/doctor/{id}")
    public Response getDoctor(@PathParam("id") Long id) {
        try {
            Respuesta res = doctorService.getDoctor(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Doctors")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Doctor").build();
        }
    }


    @GET
    @Path("/doctorByUser/{id}")
    public Response getDoctorByUser(@PathParam("id") Long id) {
        try {
            Respuesta res = doctorService.getDoctorByUser(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Doctors")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Doctor").build();
        }
    }
    
    @POST
    @Path("/doctor")
    public Response saveDoctor(DoctorDto doctorDto) {
        try {
            Respuesta res = doctorService.saveDoctor(doctorDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Doctors")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Doctor").build();
        }
    }

    @DELETE
    @Path("/doctor/{id}")
    public Response deleteDoctor(@PathParam("id") Integer id) {
        try {
            Respuesta res = doctorService.deleteDoctor(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el doctor").build();
        }
    }


    @GET
    @Path("/doctor")
    public Response getDoctors() {
        try {
            Respuesta res = doctorService.getDoctors();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<DoctorDto>>((List< DoctorDto>) res.getResultado("Doctors")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el doctor").build();
        }
    }
}
