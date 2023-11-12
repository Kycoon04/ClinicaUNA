/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.service.GenericSql;
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

    @GET
    @Path("/user/{ConsultaSql}/{Titulo}/{Correo}")
    public Response getUser(@PathParam("ConsultaSql") String consulta, @PathParam("Titulo") String titulo,@PathParam("Correo") String Correo) {
        try {
            Respuesta res = ServiceSql.getSQL(consulta,titulo,Correo);
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
