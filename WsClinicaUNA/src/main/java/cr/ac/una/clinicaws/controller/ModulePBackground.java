/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.PersonalbackgroundDto;
import cr.ac.una.clinicaws.model.UsersDto;
import cr.ac.una.clinicaws.service.PBackgroundService;
import cr.ac.una.clinicaws.service.UserService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.JwTokenHelper;
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

@Path("/ModulePBackground")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Personal Background", description = "Operations on Personal Background")

//@Secure
public class ModulePBackground {

    @EJB
    PBackgroundService pBackgroundService;

    @GET
    @Path("/pBackground/{id}")
    public Response getPBackground(@PathParam("id") Integer id) {
        try {
            Respuesta res = pBackgroundService.getPBackground(id);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("PBackground")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePBackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Personal Background").build();
        }
    }
    
    @POST
    @Path("/pBackground")
    public Response savePBackground(PersonalbackgroundDto personalbackgroundDto) {
        try {
            Respuesta res = pBackgroundService.savePBackground(personalbackgroundDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("PBackground")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePBackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Personal Background").build();
        }
    }
    @DELETE
    @Path("/pBackground/{id}")
    public Response deletePBackground(@PathParam("id") Integer id) {
        try {
            Respuesta res = pBackgroundService.deletePBackground(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePBackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Personal Background").build();
        }
    }
    
    @GET
    @Path("/pBackgrounds")
    public Response getPBackgrounds() {
        try {
            Respuesta res = pBackgroundService.getPBackground();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<PersonalbackgroundDto>>((List<PersonalbackgroundDto>) res.getResultado("PBackground")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModulePBackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Personal Backgrounds").build();
        }
    }
}
