/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Users;
import cr.ac.una.clinicaws.model.UsersDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilan
 */
@Stateless
@LocalBean
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getEmpleado(Long usId) {
        try {
            Query qryEmpleado = em.createNamedQuery("Users.findByUsId", Users.class);
            qryEmpleado.setParameter("usId", usId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Users", new UsersDto((Users) qryEmpleado.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getEmpleado NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado " + ex.getMessage());
        }
    }
/*
        public Respuesta getUserByName(String usName) {
        try {
            Query qryActividad = em.createNamedQuery("Users.findByUsName", Users.class);
            qryActividad.setParameter("usName", usName);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "User", new UsersDto((Users) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "There is no user with the credentials entered..", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, " error occurred while querying the user.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "An error occurred while querying the user.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, " error occurred while querying the user.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "An error occurred while querying the user.", "validarUsuario " + ex.getMessage());
        }
    }*/
}
