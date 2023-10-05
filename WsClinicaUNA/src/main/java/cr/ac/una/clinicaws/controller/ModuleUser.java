package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.UsersDto;
import cr.ac.una.clinicaws.service.UserService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import cr.ac.una.clinicaws.util.Secure;
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
 * @author jomav
 */
@Path("/ModuleUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Users", description = "Operations on employees")
//@Secure
public class ModuleUser {
    @EJB
    UserService userService;
    
    @GET
    @Path("/user/{id}")
    public Response getUser(@PathParam("id") Long id) {
        try {
            Respuesta res = userService.getEmpleado(id);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Users")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el empleado").build();
        }
    }
    
    /*
    @GET
    @Path("/userName/{Usuario}")
    public Response getUserByNamePass(@PathParam("user") String user) {
        try {
            Respuesta res = userService.getUserByName(user);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            UsersDto usersDto= (UsersDto) res.getResultado("User");
      
            return Response.ok(res.getResultado("User")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error validating user").build();
        }
    }*/
}
