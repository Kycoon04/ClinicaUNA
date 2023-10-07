/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.FamilybackgroundDto;
import cr.ac.una.clinicaws.service.FamilybackgroundService;
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
@Path("/ModuleFamilybackground")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Familybackground", description = "Operations on Familybackground")
@Secure
public class ModuleFamilybackground {
    
      @EJB
    FamilybackgroundService familybackgroundService;

    @GET
    @Path("/familybackground/{id}")
    public Response getFamilybackground(@PathParam("id") Long id) {
        try {
            Respuesta res = familybackgroundService.getFamilybackground(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Familybackground")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleFamilybackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("antecedentes heredo familiares").build();
        }
    }


    @POST
    @Path("/familybackground")
    public Response saveFamilybackground(FamilybackgroundDto familybackgroundDto) {
        try {
            Respuesta res = familybackgroundService.saveFamilybackground(familybackgroundDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Familybackground")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleFamilybackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando antecedentes heredo familiares").build();
        }
    }

    @DELETE
    @Path("/familybackground/{id}")
    public Response deleteFamilybackground(@PathParam("id") Integer id) {
        try {
            Respuesta res = familybackgroundService.deleteFamilybackground(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleFamilybackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo  antecedentes heredo familiares").build();
        }
    }


    @GET
    @Path("/familybackground")
    public Response getFamilybackground() {
        try {
            Respuesta res = familybackgroundService.getFamilybackgrounds();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<FamilybackgroundDto>>((List< FamilybackgroundDto>) res.getResultado("Familybackground")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleFamilybackground.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo antecedentes heredo familiares").build();
        }
    }
    
    
}
