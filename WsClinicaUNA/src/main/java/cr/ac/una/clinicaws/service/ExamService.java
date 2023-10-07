/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Exam;
import cr.ac.una.clinicaws.model.ExamDto;
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
public class ExamService {
 
    private static final Logger LOG = Logger.getLogger(ExamService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    
    
    public Respuesta getExam(Long emId) {
        try {
            Query qryusuario = em.createNamedQuery("Exam.findByEmId", Exam.class);
            qryusuario.setParameter("emId", emId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Exam", new ExamDto((Exam) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un examen con el código ingresado.", "getExam NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar  el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el examen.", "getExam NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar  el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar  el examen.", "getExam " + ex.getMessage());
        }
    }



    public Respuesta saveExam(ExamDto examDto) {
        try {
            Exam exam = new Exam();
            if (examDto.getEmId()!= null && examDto.getEmId()> 0) {
                exam = em.find(Exam.class, examDto.getEmId());
                if (exam == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el examen a modificar.", "guardarExam NoResultException");
                }
                exam.update(examDto);
                exam = em.merge(exam);
            } else {
                exam = new Exam(examDto);
                em.persist(exam);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Exam", new ExamDto(exam));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar  el examen.", "guardarExam " + ex.getMessage());
        }
    }

    public Respuesta deleteExam(Integer id) {
        try {
            Exam exam;
            if (id != null && id > 0) {
                exam = em.find(Exam.class, id);
                if (exam == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el examen a eliminar.", "eliminarExamen NoResultException");
                }
                em.remove(exam);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el examen a eliminar.", "eliminarExamen NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el examen porque tiene relaciones con otros registros.", "eliminarExamen " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el examen.", "eliminarExamen " + ex.getMessage());
        }
    }

    
    public Respuesta getExam() {
        try {
            Query qryUsers = em.createNamedQuery("Exam.findAll", Exam.class);
            List<Exam> exam = (List<Exam>) qryUsers.getResultList();
             List<ExamDto> ListExam = new ArrayList<>();
            for (Exam tipo : exam) {
                ExamDto examDto = new ExamDto(tipo);
                ListExam.add(examDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Exam", ListExam);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Examenes.", "getExamen NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar Examenes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar Examenes.", "getExamen NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar Examenes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar Examenes.", "getExamen " + ex.getMessage());
        }
    }
    

}
