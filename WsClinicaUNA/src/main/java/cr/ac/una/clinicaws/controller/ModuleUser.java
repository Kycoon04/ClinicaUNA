package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.UsersDto;
import cr.ac.una.clinicaws.service.UserService;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.JwTokenHelper;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;
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
    @Context
    SecurityContext securityContext;

    @GET
    @Path("/renovar")
    public Response renovarToken() {
        try {
            String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (usuarioRequest != null && !usuarioRequest.isEmpty()) {
                return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuarioRequest)).build();
            } else {
                return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("No se pudo renovar el token.").build();
            }
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al renovar token").build();
        }
    }
    
    @GET
    @Path("/user/{id}")
    public Response getUser(@PathParam("id") Integer id) {
        try {
            Respuesta res = userService.getUser(id);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Users")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }
    
    @GET
    @Path("/userName/{usUsername}")
    public Response getUserByEmail(@PathParam("usUsername") String name) {
        try {
            Respuesta res = userService.getUserEmail(name);
            if (!res.getEstado()) {//retorna el codigo de respuesta del server 
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("User")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Path("/user/{Usuario}/{Clave}")
    public Response validateUser(@PathParam("Usuario") String usuario, @PathParam("Clave") String clave) {
        try {
            Respuesta res = userService.validateUser(usuario, clave);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            UsersDto empleadoDto = (UsersDto) res.getResultado("Users");
            empleadoDto.setToken(JwTokenHelper.getInstance().generatePrivateKey(usuario));
            return Response.ok(res.getResultado("Users")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al validar usuario").build();
        }
    }

    @POST
    @Path("/user")
    public Response saveUser(UsersDto empleadoDto) {
        try {
            Respuesta res = userService.saveUser(empleadoDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("Users")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el usuario").build();
        }
    }

    @DELETE
    @Path("/user/{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        try {
            Respuesta res = userService.deleteUser(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Path("/userActive/{code}")
    public Response active(@PathParam("code") String code) {
        try {
            Respuesta res = userService.activebyCode(code);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();
        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Path("/userIsAct/{user}/{pass}")
    public boolean isActive(@PathParam("user") String user, @PathParam("pass") String pass) {

        Respuesta res = userService.isActive(user, pass);
        if (!res.getEstado()) {
            return false;
        }
        return true;
    }
    
    @GET
    @Path("/userIsAdmin/{user}/{pass}")
    public boolean isAdmin(@PathParam("user") String user, @PathParam("pass") String pass) {

        Respuesta res = userService.isAdmin(user, pass);
        if (!res.getEstado()) {
            return false;
        }
        return true;
    }
    
    @GET
    @Path("/userIsTemp/{user}/{pass}")
    public boolean isTempPass(@PathParam("user") String user, @PathParam("pass") String pass) {

        Respuesta res = userService.isTempPas(user, pass);
        if (!res.getEstado()) {
            return false;
        }
        return true;
    }

//voy a cambiar la contraseña
    @POST
    @Path("/user/{Email}/{Password}")
    public Response ResetAccontPassword(@PathParam("Email") String Email, @PathParam("Password") String Password) {
        try {
            UsersDto userDto = new UsersDto();
            userDto.setUsEmail(Email);
            userDto.setUsPassword(Password);
            userDto.setUsRecover("N");
            Respuesta res = userService.updatebyEmail(userDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();

        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class
                    .getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el empleado").build();
        }
    }

    @POST
    @Path("/userTempPas/{Email}/{temPassword}")
    public Response ResetTemp(@PathParam("Email") String Email, @PathParam("temPassword") String temPassword) {
        try {
            UsersDto userDto = new UsersDto();
            userDto.setUsEmail(Email);
            userDto.setUsTemppassword(temPassword);
            userDto.setUsPassword(temPassword);
            userDto.setUsRecover("Y");
            Respuesta res = userService.updaterecovery(userDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(res.getResultado("")).build();

        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class
                    .getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el empleado").build();
        }
    }

    @GET
    @Path("/users")
    public Response getUsers() {
        try {
            Respuesta res = userService.getUsers();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<UsersDto>>((List< UsersDto>) res.getResultado("Users")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(ModuleUser.class
                    .getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los usuarios").build();
        }
    }
}
