/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Personalbackground;
import cr.ac.una.clinicaws.model.PersonalbackgroundDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
public class PBackgroundService {

    private static final Logger LOG = Logger.getLogger(PBackgroundService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getPBackground(Integer pbId) {
        try {
            Query qryPBackground = em.createNamedQuery("Personalbackground.findByPbId", Personalbackground.class);
            qryPBackground.setParameter("pbId", pbId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PBackground", new PersonalbackgroundDto((Personalbackground) qryPBackground.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getPBackground NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PBackground.", "getPBackground NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PBackground.", "getPBackground " + ex.getMessage());
        }
    }

    public Respuesta getPBackgroundCode(Integer pbFilecode) {
        try {
            Query qryPBackground = em.createNamedQuery("Personalbackground.findByPbFilecode", Personalbackground.class);
            qryPBackground.setParameter("pbFilecode", pbFilecode);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PBackground", new PersonalbackgroundDto((Personalbackground) qryPBackground.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getPBackground NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PBackground.", "getPBackground NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PBackground.", "getPBackground " + ex.getMessage());
        }
    }

    public Respuesta savePBackground(PersonalbackgroundDto personalbackgroundDto) {
        try {
            Personalbackground personalbackground = new Personalbackground();
            if (personalbackgroundDto.getPbId() != null && personalbackgroundDto.getPbId() > 0) {
                personalbackground = em.find(Personalbackground.class, personalbackgroundDto.getPbId());
                if (personalbackground == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el PBackground a modificar.", "guardarPBackground NoResultException");
                }
                personalbackground.update(personalbackgroundDto);
                personalbackground = em.merge(personalbackground);
            } else {
                personalbackground = new Personalbackground(personalbackgroundDto);
                em.persist(personalbackground);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PBackground", new PersonalbackgroundDto(personalbackground));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el PBackground.", "guardarPBackground " + ex.getMessage());
        }
    }

    public Respuesta deletePBackground(Integer id) {
        try {
            Personalbackground PBackground;
            if (id != null && id > 0) {
                PBackground = em.find(Personalbackground.class, id);
                if (PBackground == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el PBackground a eliminar.", "eliminarPBackground NoResultException");
                }
                em.remove(PBackground);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el PBackground a eliminar.", "eliminarPBackground NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el PBackground porque tiene relaciones con otros registros.", "eliminarPBackground " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el PBackground.", "eliminarPBackground " + ex.getMessage());
        }
    }

    public Respuesta getPBackground() {
        try {
            Query qryUsers = em.createNamedQuery("Personalbackground.findAll", Personalbackground.class);
            List<Personalbackground> personalbackground = (List<Personalbackground>) qryUsers.getResultList();
            List<PersonalbackgroundDto> ListpersonalbackgroundDto = new ArrayList<>();
            for (Personalbackground tipo : personalbackground) {
                PersonalbackgroundDto usersDto = new PersonalbackgroundDto(tipo);
                ListpersonalbackgroundDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PBackground", ListpersonalbackgroundDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de PBackground con el código ingresado.", "getTipoPBackground NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de PBackground.", "getTipoPBackground NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PBackground.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de PBackground.", "getTipoPBackground " + ex.getMessage());
        }
    }



}
