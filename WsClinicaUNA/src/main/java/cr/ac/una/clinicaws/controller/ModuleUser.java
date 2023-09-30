package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * @author jomav
 */
@Path("/ModuleUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Users", description = "Operations on employees")
@Secure
public class ModuleUser {
    
    @GET
    @Path("/empleado/{id}")
    public boolean getEmpleado(@PathParam("id") Long id) {
        try {
           // Respuesta res = empleadoService.getEmpleado(id);
           // if (!res.getEstado()) {
           //     return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
          //  }
            return true;
        } catch (Exception ex) {
          //  Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }
}
