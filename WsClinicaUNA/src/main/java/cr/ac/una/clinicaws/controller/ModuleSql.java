/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.EmailDto;
import cr.ac.una.clinicaws.model.ExcelDto;
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
import jakarta.ws.rs.POST;
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
    private static final Logger LOG = Logger.getLogger(GenericSql.class.getName());

    @POST
    @Path("/sql")
    public Response getUser(ExcelDto excelDto) {
        try {

            Respuesta res = serviceParameters.saveParameters(excelDto.getParametersDto());
            
            res = serviceParameters.getParametersByTitule(excelDto.getParametersDto().getPsTitule());
            Parameters parametro = new Parameters((ParametersDto) res.getResultado("Parameters"));
            excelDto.getSqlDto().setSqlParam(parametro);
            
            res = serviceSql.saveSql(excelDto.getSqlDto());
            
            res = serviceSql.getSqlByParam(excelDto.getSqlDto().getSqlParam().getPsId());
            SqlDto sqlDto = (SqlDto) res.getResultado("Sql");
            Sql sql = new Sql(sqlDto);
            for (EmailDto p : excelDto.getEmailDto()) {
                p.setElIdsql(sql);
                 res = serviceemail.saveEmail(p);
            }
            res= ServiceSql.getSQL(excelDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error realizando la consulta").build();
        }
    }
}
