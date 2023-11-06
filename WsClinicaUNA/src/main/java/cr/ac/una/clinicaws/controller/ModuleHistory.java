/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.model.HistoryDto;
import cr.ac.una.clinicaws.service.DiaryService;
import cr.ac.una.clinicaws.service.HistoryService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Email;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
@Path("/ModuleHistory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "History", description = "Operations on History")
//@Secure
public class ModuleHistory {
    
    @EJB
    HistoryService historyService;

    @GET
    @Path("/history/{id}")
    public Response getHistory(@PathParam("id") Integer id) {
        try {
            Respuesta res = historyService.getHistory(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("historytime")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la historial").build();
        }
    }

    @POST
    @Path("/history")
    public Response saveHistory(HistoryDto historyDto) {
        try {
            Respuesta res = historyService.saveHistory(historyDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("historytime")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la historial").build();
        }
    }

    @DELETE
    @Path("/history/{id}")
    public Response deleteHistory(@PathParam("id") Integer id) {
        try {
            Respuesta res = historyService.deleteHistory(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la historial").build();
        }
    }

    @GET
    @Path("/history")
    public Response getHistorys() {
        try {
            Respuesta res = historyService.getHistorys();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<HistoryDto>>((List< HistoryDto>) res.getResultado("historytime")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la historial").build();
        }
    }
    
    @GET
    @Path("/historys/{id}")
    public Response getHistoryByDoctor(@PathParam("id") Integer id) {
        try {
            Respuesta res = historyService.getHistoryByDoctor(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<HistoryDto>>((List< HistoryDto>) res.getResultado("historytime")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la historial").build();
        }
    }
        
    @GET
    @Path("/historysRange/{date}/{id}")
    public Response getHistoryByRange(@PathParam("date") String date,@PathParam("id") Integer id) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            Respuesta res = historyService.getHistoryByDate(parsedDate,id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("historytime")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la historial").build();
        }
    }
}
