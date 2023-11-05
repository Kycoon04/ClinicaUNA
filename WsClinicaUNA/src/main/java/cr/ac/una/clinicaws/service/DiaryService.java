/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;


import cr.ac.una.clinicaws.model.Diary;
import cr.ac.una.clinicaws.model.DiaryDto;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilan
 */
@Stateless
@LocalBean
public class DiaryService {

    private static final Logger LOG = Logger.getLogger(DiaryService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    
    
    public Respuesta getDiary(Long dyId) {
        try {
            Query qryusuario = em.createNamedQuery("Diary.findByDyId", Diary.class);
            qryusuario.setParameter("dyId", dyId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diaries", new DiaryDto((Diary) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una agenda con el código ingresado.", "getDiary NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la agenda.", "getDiary NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la agenda.", "getDiary " + ex.getMessage());
        }
    }

    public Respuesta getByDate(LocalDate dia , LocalDate fin,Integer id) {
        try {
            Query qryUsers = em.createNamedQuery("Diary.findByDateRange", Diary.class);
            qryUsers.setParameter("startDate", dia);
            qryUsers.setParameter("endDate", fin);
            qryUsers.setParameter("DoctorId", id);
            List<Diary> diary = (List<Diary>) qryUsers.getResultList();
            List<DiaryDto> ListDiaries = new ArrayList<>();
            for (Diary tipo : diary) {
                DiaryDto diaryDto = new DiaryDto(tipo);
                ListDiaries.add(diaryDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diaries", ListDiaries);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen agendas.", "getAgendas NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las agendas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las agendas.", "getAgendas NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las agendas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las agendas.", "getAgendas " + ex.getMessage());
        }
    }

    public Respuesta saveDiary(DiaryDto diaryDto) {
        try {
            Diary diary = new Diary();
            if (diaryDto.getDyId()!= null && diaryDto.getDyId()> 0) {
                diary = em.find(Diary.class, diaryDto.getDyId());
                if (diary == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la agenda a modificar.", "guardarAgenda NoResultException");
                }
                diary.update(diaryDto);
                diary = em.merge(diary);
            } else {
                diary = new Diary(diaryDto);
                em.persist(diary);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diaries", new DiaryDto(diary));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la Agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la Agenda.", "guardarAgenda " + ex.getMessage());
        }
    }

    public Respuesta deleteDiary(Integer id) {
        try {
            Diary diary;
            if (id != null && id > 0) {
                diary = em.find(Diary.class, id);
                if (diary == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la agenda a eliminar.", "eliminarAgenda NoResultException");
                }
                em.remove(diary);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la agenda a eliminar.", "eliminarAgenda NoResultException");
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

    
    public Respuesta getDiaries() {
        try {
            Query qryUsers = em.createNamedQuery("Diary.findAll", Diary.class);
            List<Diary> diary = (List<Diary>) qryUsers.getResultList();
             List<DiaryDto> ListDiaries = new ArrayList<>();
            for (Diary tipo : diary) {
                DiaryDto diaryDto = new DiaryDto(tipo);
                ListDiaries.add(diaryDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Diaries", ListDiaries);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen agendas.", "getAgendas NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las agendas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las agendas.", "getAgendas NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las agendas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las agendas.", "getAgendas " + ex.getMessage());
        }
    }
    

}
