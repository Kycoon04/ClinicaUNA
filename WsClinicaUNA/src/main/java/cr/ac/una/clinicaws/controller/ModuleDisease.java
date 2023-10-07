/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DiseaseDto;
import cr.ac.una.clinicaws.service.DiaryService;
import cr.ac.una.clinicaws.service.DiseaseService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import cr.ac.una.clinicaws.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
@Path("/ModuleDiseases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Disease", description = "Operations on Diseases")
//@Secure
public class ModuleDisease {

    @EJB
    DiseaseService diseaseService;


    @GET
    @Path("/disease/{id}")
    public Response getDisease(@PathParam("id") Long id) {
        try {
            Respuesta res = diseaseService.getDisease(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Diseases")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDisease.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la enfermedad").build();
        }
    }

    @POST
    @Path("/disease")
    public Response saveDisease(DiseaseDto diseaseDto) {
        try {
            Respuesta res = diseaseService.saveDisease(diseaseDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Diseases")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDisease.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la enfermedad").build();
        }
    }

    @DELETE
    @Path("/disease/{id}")
    public Response deleteDisease(@PathParam("id") Integer id) {
        try {
            Respuesta res = diseaseService.deleteDisease(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDisease.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la enfermedad").build();
        }
    }

    @GET
    @Path("/disease")
    public Response getDiseases() {
        try {
            Respuesta res = diseaseService.getDiseases();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<DiseaseDto>>((List< DiseaseDto>) res.getResultado("Diseases")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDisease.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la enfermedad").build();
        }
    }
}
