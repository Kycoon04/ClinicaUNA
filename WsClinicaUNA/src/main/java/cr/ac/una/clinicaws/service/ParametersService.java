/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Diary;
import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.model.Parameters;
import cr.ac.una.clinicaws.model.ParametersDto;
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
import java.time.LocalDate;
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
public class ParametersService {

    private static final Logger LOG = Logger.getLogger(ParametersService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getParameters(Integer psId) {
        try {
            Query qryParameters = em.createNamedQuery("Parameters.findByPsId", Parameters.class);
            qryParameters.setParameter("psId", psId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new ParametersDto((Parameters) qryParameters.getSingleResult()));
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

    public Respuesta saveParameters(ParametersDto parametersDto) {
        try {
            Parameters parameters = new Parameters();
            if (parametersDto.getPsId() != null && parametersDto.getPsId() > 0) {
                parameters = em.find(Parameters.class, parametersDto.getPsId());
                if (parameters == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a modificar.", "guardarParameters NoResultException");
                }
                parameters.update(parametersDto);
                parameters = em.merge(parameters);
            } else {
                parameters = new Parameters(parametersDto);
                em.persist(parameters);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new ParametersDto(parameters));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Parameters.", "guardarParameters " + ex.getMessage());
        }
    }

    public Respuesta deleteParameters(Integer id) {
        try {
            Parameters parameters;
            if (id != null && id > 0) {
                parameters = em.find(Parameters.class, id);
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

    public Respuesta getParametersByTitule(String psName) {
        try {
            Query qryParameters = em.createNamedQuery("Parameters.findByPsTitule", Parameters.class);
            qryParameters.setParameter("psTitule", psName);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new ParametersDto((Parameters) qryParameters.getSingleResult()));
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

    public Respuesta getParametersByFrequency(LocalDate date) {
        try {
            Query qryParameters = em.createNamedQuery("Parameters.findByPsDateInit", Parameters.class);
            qryParameters.setParameter("psDateInit", date);
            List<Parameters> diary = (List<Parameters>) qryParameters.getResultList();
             List<ParametersDto> ListDiaries = new ArrayList<>();
            for (Parameters tipo : diary) {
                ParametersDto diaryDto = new ParametersDto(tipo);
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
        public Respuesta getParameter() {
        try {
            Query qryParameters = em.createNamedQuery("Parameters.findAll", Parameters.class);
            List<Parameters> diary = (List<Parameters>) qryParameters.getResultList();
             List<ParametersDto> ListDiaries = new ArrayList<>();
            for (Parameters tipo : diary) {
                ParametersDto diaryDto = new ParametersDto(tipo);
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
