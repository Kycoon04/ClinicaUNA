package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.service.DiaryService;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/ModuleJasperReports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "JasperReports", description = "Operations on Diary")
//@Secure
public class ModuleJasperReports {
    
    @EJB
    DiaryService diaryService;
    
    @GET
    @Path("/JasperReports/{Date}")
    public Response getReportDiary(@PathParam("date") Date date) {
        /*
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
        */
        return null;
    }
}
