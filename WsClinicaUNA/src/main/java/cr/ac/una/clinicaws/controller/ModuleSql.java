/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.EmailDto;
import cr.ac.una.clinicaws.model.Parameters;
import cr.ac.una.clinicaws.model.ParametersDto;
import cr.ac.una.clinicaws.model.Sql;
import cr.ac.una.clinicaws.model.SqlDto;
import cr.ac.una.clinicaws.service.EmailService;
import cr.ac.una.clinicaws.service.GenericSql;
import cr.ac.una.clinicaws.service.ParametersService;
import cr.ac.una.clinicaws.service.SqlService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    SqlService serviceSql;
    @EJB
    EmailService serviceemail;

    @GET
    @Path("/user/{ConsultaSql}/{Titulo}/{Correo}")
    public Response getUser(@PathParam("ConsultaSql") String consulta, @PathParam("Titulo") String titulo, @PathParam("Correo") String Correo) {
        try {
            /* Parameters parametroDto = new Parameters(); //viene de parametro
            parametroDto.setPsId(888);
            //Respuesta res = serviceParameters.saveParameters(parametroDto);
            SqlDto sqlDto = new SqlDto();
            sqlDto.setSqlQuery(consulta);
            sqlDto.setSqlId(0);
            sqlDto.setSqlParam(parametroDto);
            res = serviceSql.saveSql(sqlDto);
            Sql sqlDto = new Sql();
            sqlDto.setSqlId(889);                   ESTO NO SIRVE,ES ORDEN MENTAL MIO QUE LO NECESITO AQUI, LUEGO LO BORRO
            EmailDto emailDto = new EmailDto();
            emailDto.setElId(0);
            emailDto.setElIdsql(sqlDto);
            emailDto.setElEmail("jomaval4@gmail.com");
            Respuesta res = serviceemail.saveEmail(emailDto);
            
            res = serviceemail.getSqlBySql(889);
            List<EmailDto> ListemailDto = (List<EmailDto>) res.getResultado("Email"); */
            Respuesta res = new Respuesta();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Lista")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error realizando la consulta").build();
        }
    }
}
