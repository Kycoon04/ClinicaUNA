/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.ProceedingsDto;
import cr.ac.una.clinicaws.service.ProceedingsService;
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
@Path("/ModuleProceedings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Proceedings", description = "Operations on Proceedings")
//@Secure
public class ModuleProceedings {

    @EJB
    ProceedingsService proceedingsService;

    @GET
    @Path("/proceedings/{id}")
    public Response getProceedings(@PathParam("id") Integer id) {
        try {
            Respuesta res = proceedingsService.getProceedings(id);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Proceedings")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleProceedings.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Proceedings").build();
        }
    }

    @POST
    @Path("/proceedings")
    public Response saveProceedings(ProceedingsDto proceedingsDto) {
        try {
            Respuesta res = proceedingsService.saveProceedings(proceedingsDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Proceedings")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleProceedings.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Proceedings").build();
        }
    }

    @DELETE
    @Path("/proceedings/{id}")
    public Response deleteProceedings(@PathParam("id") Integer id) {
        try {
            Respuesta res = proceedingsService.deleteProceedings(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleProceedings.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Proceedings").build();
        }
    }
    @GET
    @Path("/proceedings")
    public Response getProceedings() {
        try {
            Respuesta res = proceedingsService.getProceedings();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProceedingsDto>>((List<ProceedingsDto>) res.getResultado("Proceedings")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleProceedings.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Proceedings").build();
        }
    }
}
