/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.model.EmailDto;
import cr.ac.una.clinicaws.model.ExcelDto;
import cr.ac.una.clinicaws.model.Parameters;
import cr.ac.una.clinicaws.model.ParametersDto;
import cr.ac.una.clinicaws.model.ParametersSqlDto;
import cr.ac.una.clinicaws.service.EmailService;
import cr.ac.una.clinicaws.service.GenericSql;
import cr.ac.una.clinicaws.service.ParametersService;
import cr.ac.una.clinicaws.service.ParametersSqlService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
@Path("/ModuleSql")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Sql", description = "Operations on employees")
public class ModuleSql {

    @EJB
    GenericSql ServiceSql;
    @EJB
    ParametersService serviceParameters;
    @EJB
    EmailService serviceemail;
    @EJB
    ParametersSqlService serviceParametersSql;
    
    
    private static final Logger LOG = Logger.getLogger(GenericSql.class.getName());

    @POST
    @Path("/sql")
    public Response getUser(ExcelDto excelDto) {

        try {
            Respuesta res = ServiceSql.VerificarSQL(excelDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            excelDto.getParametersDto().setPsDateSig(DatePlus(excelDto.getParametersDto().getPsTime(), excelDto.getParametersDto().getPsDateInit()));
            res = serviceParameters.saveParameters(excelDto.getParametersDto());

            res = serviceParameters.getParametersByTitule(excelDto.getParametersDto().getPsTitule());
            Parameters parametro = new Parameters((ParametersDto) res.getResultado("Parameters"));

            for (EmailDto p : excelDto.getEmailDto()) {
                p.setElIdsql(parametro);
                res = serviceemail.saveEmail(p);
            }
            for (ParametersSqlDto p : excelDto.getParametersSqlDto()) {
                p.setPsqlIdparam(parametro);
                res = serviceParametersSql.saveParametersSql(p);
            }

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSql.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error realizando la consulta").build();
        }
    }

    @GET
    @Path("/sql")
    public Response getParameters() {
        try {
            Respuesta res = serviceParameters.getParameter();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ParametersDto>>((List< ParametersDto>) res.getResultado("Parameters")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSql.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la agenda").build();
        }
    }
    
    @GET
    @Path("/sql/{id}")
    public Response getParametersSql(@PathParam("id") Integer id) {
        try {
            Respuesta res = serviceParametersSql.getParametersByFrequency(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ParametersSqlDto>>((List< ParametersSqlDto>) res.getResultado("Parameters")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSql.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la agenda").build();
        }
    } 
    
    

    public LocalDate DatePlus(String date, LocalDate dateUpdate) {

        switch (date) {
            case "Daily":
                return dateUpdate.plusDays(1);
            case "Weekly":
                return dateUpdate.plusWeeks(1);
            case "Monthly":
                return dateUpdate.plusMonths(1);
        }
        return null;
    }
    @GET
    @Path("/sqlEmail/{id}")
    public Response getEmails(@PathParam("id") Integer id) {
        try {
            Respuesta res = serviceemail.getSqlBySql(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<EmailDto>>((List<EmailDto>) res.getResultado("Email")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSql.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la agenda").build();
        }
    }
    
    @POST
    @Path("/Parameter")
    public Response saveParameter(ParametersDto parametersDto) {

        try {
            Respuesta res = serviceParameters.saveParameters(parametersDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Parameters")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSql.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error realizando la consulta").build();
        }
    }
    
    @POST
    @Path("/ParameterSql")
    public Response saveParameterSql(ParametersSqlDto parametersDto) {

        try {
            Respuesta res = serviceParametersSql.saveParametersSql(parametersDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Parameters")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleSql.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error realizando la consulta").build();
        }
    }

}
