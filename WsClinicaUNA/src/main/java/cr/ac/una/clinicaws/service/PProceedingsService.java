/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.PProceedings;
import cr.ac.una.clinicaws.model.PProceedingsDto;
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
public class PProceedingsService {

    private static final Logger LOG = Logger.getLogger(PProceedingsService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getPProceeding(Integer usId) {
        try {
            Query qryPProceedings = em.createNamedQuery("PProceedings.findByPpId", PProceedings.class);
            qryPProceedings.setParameter("ppId", usId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PProceeding", new PProceedingsDto((PProceedings) qryPProceedings.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getPProceedings NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PProceedings.", "getPProceedings NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PProceedings.", "getPProceedings " + ex.getMessage());
        }
    }

    public Respuesta getProceeding(Integer usId) {
        try {
            Query qryPProceedings = em.createNamedQuery("PProceedings.findByProceedingId", PProceedings.class);
            qryPProceedings.setParameter("ProceedingId", usId);
            List<PProceedings> pProceedings = (List<PProceedings>) qryPProceedings.getResultList();
            List<PProceedingsDto> ListpProceedingsDto = new ArrayList<>();
            for (PProceedings tipo : pProceedings) {
                PProceedingsDto usersDto = new PProceedingsDto(tipo);
                ListpProceedingsDto.add(usersDto);
            }
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PProceeding", ListpProceedingsDto);
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getPProceedings NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PProceedings.", "getPProceedings NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el PProceedings.", "getPProceedings " + ex.getMessage());
        }
    }

    public Respuesta savePProceedings(PProceedingsDto pProceedingsDto) {
        try {
            PProceedings pProceedings = new PProceedings();
            if (pProceedingsDto.getPpId() != null && pProceedingsDto.getPpId() > 0) {
                pProceedings = em.find(PProceedings.class, pProceedingsDto.getPpId());
                if (pProceedings == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el PProceedings a modificar.", "guardarPProceedings NoResultException");
                }
                pProceedings.update(pProceedingsDto);
                pProceedings = em.merge(pProceedings);
            } else {
                pProceedings = new PProceedings(pProceedingsDto);
                em.persist(pProceedings);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PProceeding", new PProceedingsDto(pProceedings));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el PProceedings.", "guardarPProceedings " + ex.getMessage());
        }
    }

    public Respuesta deletePProceedings(Integer id) {
        try {
            PProceedings pProceedings;
            if (id != null && id > 0) {
                pProceedings = em.find(PProceedings.class, id);
                if (pProceedings == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el PProceedings a eliminar.", "eliminarPProceedings NoResultException");
                }
                em.remove(pProceedings);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el PProceedings a eliminar.", "eliminarPProceedings NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el PProceedings porque tiene relaciones con otros registros.", "eliminarPProceedings " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el PProceedings.", "eliminarPProceedings " + ex.getMessage());
        }
    }

    public Respuesta gePProceedings() {
        try {
            Query qryUsers = em.createNamedQuery("PProceedings.findAll", PProceedings.class);
            List<PProceedings> pProceedings = (List<PProceedings>) qryUsers.getResultList();
            List<PProceedingsDto> ListpProceedingsDto = new ArrayList<>();
            for (PProceedings tipo : pProceedings) {
                PProceedingsDto usersDto = new PProceedingsDto(tipo);
                ListpProceedingsDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "PProceeding", ListpProceedingsDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de PProceedings con el código ingresado.", "getTipoPProceedings NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de PProceedings.", "getTipoPProceedings NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de PProceedings.", "getTipoPProceedings " + ex.getMessage());
        }
    }
}
