/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.FProceedings;
import cr.ac.una.clinicaws.model.FProceedingsDto;
import cr.ac.una.clinicaws.model.PProceedings;
import cr.ac.una.clinicaws.model.PProceedingsDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.Stateless;
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
 * @author dilan
 */
@Stateless
@LocalBean
public class FProceedingsService {

    private static final Logger LOG = Logger.getLogger(DiseaseService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getFProceedings(Long fpId) {
        try {
            Query qryusuario = em.createNamedQuery("FProceedings.findByFpId", FProceedings.class);
            qryusuario.setParameter("fpId", fpId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "FProceedings", new FProceedingsDto((FProceedings) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un FProceedings con el código ingresado.", "getExam NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar  el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el FProceedings.", "getExam NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar  el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar  el FProceedings.", "getExam " + ex.getMessage());
        }
    }

    public Respuesta getProceeding(Integer usId) {
        try {
            Query qryPProceedings = em.createNamedQuery("FProceedings.findByProceedingId", PProceedings.class);
            qryPProceedings.setParameter("ProceedingId", usId);
            List<FProceedings> pProceedings = (List<FProceedings>) qryPProceedings.getResultList();
            List<FProceedingsDto> ListpProceedingsDto = new ArrayList<>();
            for (FProceedings tipo : pProceedings) {
                FProceedingsDto usersDto = new FProceedingsDto(tipo);
                ListpProceedingsDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "FProceeding", ListpProceedingsDto);
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
    
    public Respuesta saveFProceedings(FProceedingsDto fProceedingsdto) {
        try {
            FProceedings fProceedings = new FProceedings();
            if (fProceedingsdto.getFpId()!= null && fProceedingsdto.getFpId() > 0) {
                fProceedings = em.find(FProceedings.class, fProceedingsdto.getFpId());
                if (fProceedings == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el FProceedings a modificar.", "guardarExam NoResultException");
                }
                fProceedings.update(fProceedingsdto);
                fProceedings = em.merge(fProceedings);
            } else {
                fProceedings = new FProceedings(fProceedingsdto);
                em.persist(fProceedings);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "FProceedings", new FProceedingsDto(fProceedings));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar  el FProceedings.", "guardarExam " + ex.getMessage());
        }
    }

    public Respuesta deleteFProceedings(Integer id) {
        try {
            FProceedings fProceedings;
            if (id != null && id > 0) {
                fProceedings = em.find(FProceedings.class, id);
                if (fProceedings == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el FProceedings a eliminar.", "eliminarExamen NoResultException");
                }
                em.remove(fProceedings);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el FProceedings a eliminar.", "eliminarExamen NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el FProceedings porque tiene relaciones con otros registros.", "eliminarExamen " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el FProceedings.", "eliminarExamen " + ex.getMessage());
        }
    }

    public Respuesta getFProceedings() {
        try {
            Query qryUsers = em.createNamedQuery("FProceedings.findAll", FProceedings.class);
            List<FProceedings> fProceedings = (List<FProceedings>) qryUsers.getResultList();
            List<FProceedingsDto> listfProceedingsDto = new ArrayList<>();
            for (FProceedings tipo : fProceedings) {
                FProceedingsDto proceedingsDto = new FProceedingsDto(tipo);
                listfProceedingsDto.add(proceedingsDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "FProceedings", listfProceedingsDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen FProceedings.", "getExamen NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar Examenes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar FProceedings.", "getExamen NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar Examenes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar FProceedings.", "getExamen " + ex.getMessage());
        }
    }

}
