/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.ParametersSql;
import cr.ac.una.clinicaws.model.ParametersSqlDto;
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
public class ParametersSqlService {
    private static final Logger LOG = Logger.getLogger(ParametersSqlService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getParameters(Integer psId) {
        try {
            Query qryParameters = em.createNamedQuery("ParametersSql.findByPsqlId", ParametersSqlService.class);
            qryParameters.setParameter("psqlId", psId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new ParametersSqlDto((ParametersSql) qryParameters.getSingleResult()));
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

    public Respuesta saveParametersSql(ParametersSqlDto parametersSqlDto) {
        try {
            ParametersSql parameters = new ParametersSql();
            if (parametersSqlDto.getPsqlId() != null && parametersSqlDto.getPsqlId() > 0) {
                parameters = em.find(ParametersSql.class, parametersSqlDto.getPsqlId());
                if (parameters == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a modificar.", "guardarParameters NoResultException");
                }
                parameters.update(parametersSqlDto);
                parameters = em.merge(parameters);
            } else {
                parameters = new ParametersSql(parametersSqlDto);
                em.persist(parameters);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new ParametersSqlDto(parameters));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Parameters.", "guardarParameters " + ex.getMessage());
        }
    }

    public Respuesta deleteParameters(Integer id) {
        try {
            ParametersSql parameters;
            if (id != null && id > 0) {
                parameters = em.find(ParametersSql.class, id);
                if (parameters == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a eliminar.", "eliminarParameters NoResultException");
                }
                em.remove(parameters);
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

    public Respuesta getParametersByFrequency(Integer id) {
        try {
            Query qryParameters = em.createNamedQuery("ParametersSql.findBySqlId", ParametersSql.class);
            qryParameters.setParameter("psId", id);
            List<ParametersSql> diary = (List<ParametersSql>) qryParameters.getResultList();
             List<ParametersSqlDto> ListDiaries = new ArrayList<>();
            for (ParametersSql tipo : diary) {
                ParametersSqlDto diaryDto = new ParametersSqlDto(tipo);
                ListDiaries.add(diaryDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", ListDiaries);
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
}
