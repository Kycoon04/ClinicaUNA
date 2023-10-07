/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Proceedings;
import cr.ac.una.clinicaws.model.ProceedingsDto;
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
public class ProceedingsService {

    private static final Logger LOG = Logger.getLogger(ProceedingsService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getProceedings(Integer psId) {
        try {
            Query qryProceedings = em.createNamedQuery("Proceedings.findByPsId", Proceedings.class);
            qryProceedings.setParameter("psId", psId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Proceedings", new ProceedingsDto((Proceedings) qryProceedings.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getProceedings NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Proceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Proceedings.", "getProceedings NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Proceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Proceedings.", "getProceedings " + ex.getMessage());
        }
    }
    public Respuesta saveProceedings(ProceedingsDto proceedingsDto) {
        try {
            Proceedings proceedings = new Proceedings();
            if (proceedingsDto.getPsId() != null && proceedingsDto.getPsId() > 0) {
                proceedings = em.find(Proceedings.class, proceedingsDto.getPsId());
                if (proceedings == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Proceedings a modificar.", "guardarProceedings NoResultException");
                }
                proceedings.update(proceedingsDto);
                proceedings = em.merge(proceedings);
            } else {
                proceedings = new Proceedings(proceedingsDto);
                em.persist(proceedings);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Proceedings", new ProceedingsDto(proceedings));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Proceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Proceedings.", "guardarProceedings " + ex.getMessage());
        }
    }

    public Respuesta deleteProceedings(Integer id) {
        try {
            Proceedings proceedings;
            if (id != null && id > 0) {
                proceedings = em.find(Proceedings.class, id);
                if (proceedings == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Proceedings a eliminar.", "eliminarProceedings NoResultException");
                }
                em.remove(proceedings);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Proceedings a eliminar.", "eliminarProceedings NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Proceedings porque tiene relaciones con otros registros.", "eliminarProceedings " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Proceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Proceedings.", "eliminarProceedings " + ex.getMessage());
        }
    }
    public Respuesta getProceedings() {
        try {
            Query qryUsers = em.createNamedQuery("Proceedings.findAll", Proceedings.class);
            List<Proceedings> proceedings = (List<Proceedings>) qryUsers.getResultList();
             List<ProceedingsDto> ListproceedingsDto = new ArrayList<>();
            for (Proceedings tipo : proceedings) {
                ProceedingsDto usersDto = new ProceedingsDto(tipo);
                ListproceedingsDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Proceedings", ListproceedingsDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de Proceedings con el código ingresado.", "getTipoProceedings NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de Proceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Proceedings.", "getTipoProceedings NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Proceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Proceedings.", "getTipoProceedings " + ex.getMessage());
        }
    }
}
