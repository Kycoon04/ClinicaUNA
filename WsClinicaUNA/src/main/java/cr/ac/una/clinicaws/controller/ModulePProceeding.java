/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.PProceedingsDto;
import cr.ac.una.clinicaws.service.PProceedingsService;
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
 * @author jomav
 */
@Path("/ModulePProceeding")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "PProceeding", description = "Operations on PProceeding")

@Secure
public class ModulePProceeding {

    @EJB
    PProceedingsService  pProceedingsService;

    @GET
    @Path("/pProceeding/{id}")
    public Response getPProceeding(@PathParam("id") Integer id) {
        try {
            Respuesta res = pProceedingsService.getPProceeding(id);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("PProceeding")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePProceeding.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el PProceeding").build();
        }
    }

    @POST
    @Path("/pProceeding")
    public Response savePProceeding(PProceedingsDto pProceedingsDto) {
        try {
            Respuesta res =  pProceedingsService.savePProceedings(pProceedingsDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("PProceeding")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePProceeding.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el PProceeding").build();
        }
    }

    @DELETE
    @Path("/pProceeding/{id}")
    public Response deletePProceeding(@PathParam("id") Integer id) {
        try {
            Respuesta res = pProceedingsService.deletePProceedings(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePProceeding.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el PProceeding").build();
        }
    }
    @GET
    @Path("/pProceedings")
    public Response getPProceedings() {
        try {
            Respuesta res = pProceedingsService.gePProceedings();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<PProceedingsDto>>((List<PProceedingsDto>) res.getResultado("PProceeding")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los PProceeding").build();
        }
    }
}
