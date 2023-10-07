/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;


import cr.ac.una.clinicaws.model.Disease;
import cr.ac.una.clinicaws.model.DiseaseDto;
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
 * @author dilan
 */
@Stateless
@LocalBean
public class DiseaseService {
    private static final Logger LOG = Logger.getLogger(DiseaseService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    
    
    public Respuesta getDisease(Long dsId) {
        try {
            Query qryusuario = em.createNamedQuery("Disease.findByDsId", Disease.class);
            qryusuario.setParameter("dsId", dsId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diseases", new DiseaseDto((Disease) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una enfermedad con el código ingresado.", "getDisease NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la enfermedad.", "getDisease NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la enfermedad.", "getDisease " + ex.getMessage());
        }
    }

    public Respuesta saveDisease(DiseaseDto diseaseDto) {
        try {
            Disease disease = new Disease();
            if (diseaseDto.getDsId()!= null && diseaseDto.getDsId()> 0) {
                disease = em.find(Disease.class, diseaseDto.getDsId());
                if (disease == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la enfermedad a modificar.", "guardarEnfermedad NoResultException");
                }
                disease.update(diseaseDto);
                disease = em.merge(disease);
            } else {
                disease = new Disease(diseaseDto);
                em.persist(disease);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diseases", new DiseaseDto(disease));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la enfermedad.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la enfermedad.", "guardarEnfermedad " + ex.getMessage());
        }
    }

    public Respuesta deleteDisease(Integer id) {
        try {
            Disease disease;
            if (id != null && id > 0) {
                disease = em.find(Disease.class, id);
                if (disease == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la enfermedad a eliminar.", "eliminarAgenda NoResultException");
                }
                em.remove(disease);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la enfermedad a eliminar.", "eliminarAgenda NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la agenda porque tiene relaciones con otros registros.", "eliminarAgenda " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar la agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la agenda.", "eliminarAgenda " + ex.getMessage());
        }
    }

    
    public Respuesta getDiseases() {
        try {
            Query qryUsers = em.createNamedQuery("Disease.findAll", Disease.class);
            List<Disease> diary = (List<Disease>) qryUsers.getResultList();
             List<DiseaseDto> ListDiseases= new ArrayList<>();
            for (Disease tipo : diary) {
                DiseaseDto diseaseDto = new DiseaseDto(tipo);
                ListDiseases.add(diseaseDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diseases", ListDiseases);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen enfermedades.", "getDiseases NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las enfermedades.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las enfermedades.", "getDiseases NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las enfermedades.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las enfermedades.", "getDiseases " + ex.getMessage());
        }
    }
    
     
}
