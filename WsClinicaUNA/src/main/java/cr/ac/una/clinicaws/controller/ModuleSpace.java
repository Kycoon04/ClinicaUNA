/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.SpaceDto;
import cr.ac.una.clinicaws.service.SpaceService;
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
@Path("/ModuleSpace")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Space", description = "Operations on Space")
@Secure
public class ModuleSpace {
    
    @EJB
    SpaceService spaceService;

    @GET
    @Path("/space/{id}")
    public Response getSpace(@PathParam("id") Integer id) {
        try {
            Respuesta res = spaceService.getSpace(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Space")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSpace.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el space").build();
        }
    }

    @POST
    @Path("/space")
    public Response savSpace(SpaceDto spaceDto) {
        try {
            Respuesta res = spaceService.saveSpace(spaceDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Space")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Space").build();
        }
    }

    @DELETE
    @Path("/space/{id}")
    public Response deleteSpace(@PathParam("id") Integer id) {
        try {
            Respuesta res = spaceService.deleteSpace(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Space").build();
        }
    }

    @GET
    @Path("/space")
    public Response getSpace() {
        try {
            Respuesta res = spaceService.getSpace();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<SpaceDto>>((List<SpaceDto>) res.getResultado("Space")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Spaces").build();
        }
    }
}
