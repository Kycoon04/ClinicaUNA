/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.EmailDto;
import cr.ac.una.clinicaws.model.Emails;
import cr.ac.una.clinicaws.model.Space;
import cr.ac.una.clinicaws.model.SpaceDto;
import cr.ac.una.clinicaws.model.Sql;
import cr.ac.una.clinicaws.model.SqlDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
@Stateless
@LocalBean
public class EmailService {
     private static final Logger LOG = Logger.getLogger(ParametersService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    public Respuesta getEmail(Integer sqlId) {
        try {
            Query qryParameters = em.createNamedQuery("Email.findByElId", Emails.class);
            qryParameters.setParameter("elId", sqlId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new EmailDto((Emails) qryParameters.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getParameters NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Parameters.", "getParameters NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Parameters.", "getParameters " + ex.getMessage());
        }
    }

    public Respuesta saveEmail(EmailDto emailDto) {
        try {
            Emails emails = new Emails();
            if (emailDto.getElId() != null && emailDto.getElId() > 0) {
                emails = em.find(Emails.class, emailDto.getElId() );
                if (emails == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a modificar.", "guardarParameters NoResultException");
                }
                emails.update(emailDto);
                emails = em.merge(emails);
            } else {
                emails = new Emails(emailDto);
                em.persist(emails);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new EmailDto(emails));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Parameters.", "guardarParameters " + ex.getMessage());
        }
    }

    public Respuesta deleteEmail(Integer id) {
        try {
            Emails emails;
            if (id != null && id > 0) {
                emails = em.find(Emails.class, id);
                if (emails == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a eliminar.", "eliminarParameters NoResultException");
                }
                em.remove(emails);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Parameters a eliminar.", "eliminarParameters NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Parameters porque tiene relaciones con otros registros.", "eliminarParameters " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Parameters.", "eliminarParameters " + ex.getMessage());
        }
    }
    
    public Respuesta getSqlBySql(Integer sqlParam) {
        try {
            Query qrySpace = em.createNamedQuery("Emails.findBySqlId", Emails.class);
            qrySpace.setParameter("sqlId", sqlParam);
            List<Emails> email = (List<Emails>) qrySpace.getResultList();
            List<EmailDto> ListemailDto = new ArrayList<>();
            for (Emails tipo : email) {
                EmailDto usersDto = new EmailDto(tipo);
                ListemailDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Email", ListemailDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de Space con el código ingresado.", "getTipoSpace NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Space.", "getTipoSpace NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Space.", "getTipoSpace " + ex.getMessage());
        }
    }
}
