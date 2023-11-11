/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.service.DiaryService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Email;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilan
 */
@Path("/ModuleDiary")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Diary", description = "Operations on Diary")
//@Secure
public class ModuleDiary {

    @EJB
    DiaryService diaryService;

    @GET
    @Path("/diary/{id}")
    public Response getDiary(@PathParam("id") Long id) {
        try {
            Respuesta res = diaryService.getDiary(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Diaries")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la agenda").build();
        }
    }

    @POST
    @Path("/diary")
    public Response saveDiary(DiaryDto diaryDto) {
        try {
            Respuesta res = diaryService.saveDiary(diaryDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Diaries")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la agenda").build();
        }
    }

    @POST
    @Path("/diaryemail")
    public Response sentemail(DiaryDto diaryDto) {
        try {
            Email email = new Email();
            email.setDestinationMail(diaryDto.getDySpace().getSeAppointment().getAtEmail());
            email.enviarConfirmacion2(diaryDto.getDyDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), diaryDto.getDySpace().getSeAppointment().getAtPatient().getPtName(),diaryDto.getDySpace().getSeAppointment().getAtUserregister().getUsLenguage());
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la agenda").build();
        }
    }

    @POST
    @Path("/diaryEmailRecordatorio")
    public Response sentEmailRecordatorio(DiaryDto diaryDto) {
        try {
            Email email = new Email();
            email.setDestinationMail(diaryDto.getDySpace().getSeAppointment().getAtEmail());
            email.enviarRecordatorio(diaryDto.getDyDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), diaryDto.getDySpace().getSeAppointment().getAtPatient().getPtName());
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la agenda").build();
        }
    }

    @DELETE
    @Path("/diary/{id}")
    public Response deleteDiary(@PathParam("id") Integer id) {
        try {
            Respuesta res = diaryService.deleteDiary(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la agenda").build();
        }
    }

    @GET
    @Path("/diary")
    public Response getAppointments() {
        try {
            Respuesta res = diaryService.getDiaries();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<DiaryDto>>((List< DiaryDto>) res.getResultado("Diaries")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleDiary.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la agenda").build();
        }
    }

    public void setDiaryService(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    public DiaryService getDiaryService() {
        return diaryService;
    }
}
