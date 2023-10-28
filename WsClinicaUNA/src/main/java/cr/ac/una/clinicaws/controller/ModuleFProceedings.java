/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.FProceedingsDto;
import cr.ac.una.clinicaws.service.FProceedingsService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import cr.ac.una.clinicaws.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
 *
 */
@Path("/ModuleFProceedings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "FProceedings", description = "Operations on FProceedings")
//@Secure
public class ModuleFProceedings {
    
      @EJB
    FProceedingsService fProceedingsService;

    @GET
    @Path("/fProceedings/{id}")
    public Response getFProceedings(@PathParam("id") Long id) {
        try {
            Respuesta res = fProceedingsService.getFProceedings(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("FProceedings")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo FProceedings").build();
        }
    }


    @POST
    @Path("/fProceedings")
    public Response saveFProceedings(FProceedingsDto fProceedingsDto) {
        try {
            Respuesta res = fProceedingsService.saveFProceedings(fProceedingsDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("FProceedings")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el FProceedings").build();
        }
    }

    @DELETE
    @Path("/fProceedings/{id}")
    public Response deleteFProceedings(@PathParam("id") Integer id) {
        try {
            Respuesta res = fProceedingsService.deleteFProceedings(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el FProceedings").build();
        }
    }


    @GET
    @Path("/fProceedings")
    public Response getFProceedings() {
        try {
            Respuesta res = fProceedingsService.getFProceedings();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<FProceedingsDto>>((List< FProceedingsDto>) res.getResultado("FProceedings")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDoctor.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el FProceedings").build();
        }
    }
    
}
