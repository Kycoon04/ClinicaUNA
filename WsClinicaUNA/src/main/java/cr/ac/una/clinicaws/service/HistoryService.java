/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.HistoryDto;
import cr.ac.una.clinicaws.model.Historytime;
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
public class HistoryService {
    
    
    private static final Logger LOG = Logger.getLogger(DiaryService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    
    
    public Respuesta getHistory(Integer htId) {
        try {
            Query qryusuario = em.createNamedQuery("Historytime.findByHtId", Historytime.class);
            qryusuario.setParameter("htId", htId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "historytime", new HistoryDto((Historytime) qryusuario.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una historial con el código ingresado.", "getDiary NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la historial.", "getDiary NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la historial.", "getDiary " + ex.getMessage());
        }
    }
    public Respuesta getHistoryByDoctor(Integer htId) {
        try {
            Query qryusuario = em.createNamedQuery("Historytime.findByDoctorId", Historytime.class);
            qryusuario.setParameter("DoctorId", htId);
            List<Historytime> history = (List<Historytime>) qryusuario.getResultList();
            List<HistoryDto> Listhistorys = new ArrayList<>();
            for (Historytime tipo : history) {
                HistoryDto diaryDto = new HistoryDto(tipo);
                Listhistorys.add(diaryDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "historytime", Listhistorys);
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una historial con el código ingresado.", "getDiary NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la historial.", "getDiary NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la historial.", "getDiary " + ex.getMessage());
        }
    }
    public Respuesta getHistoryByDate(LocalDate date,Integer id) {
        try {
            Query qryusuario = em.createNamedQuery("Historytime.findByDateInRange", Historytime.class);
            qryusuario.setParameter("date", date);
            qryusuario.setParameter("DoctorId", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "historytime",  new HistoryDto((Historytime) qryusuario.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una historial con el código ingresado.", "getDiary NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la historial.", "getDiary NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la historial.", "getDiary " + ex.getMessage());
        }
    }
    public Respuesta saveHistory(HistoryDto historyDto) {
        try {
            Historytime historytime = new Historytime();
            if (historyDto.getHtId()!= null && historyDto.getHtId()> 0) {
                historytime = em.find(Historytime.class, historyDto.getHtId());
                if (historytime == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la historial a modificar.", "guardarhistorial NoResultException");
                }
                historytime.update(historyDto);
                historytime = em.merge(historytime);
            } else {
                historytime = new Historytime(historyDto);
                em.persist(historytime);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "historytime", new HistoryDto(historytime));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la historial.", "guardarhistorial " + ex.getMessage());
        }
    }

    public Respuesta deleteHistory(Integer id) {
        try {
            Historytime historytime;
            if (id != null && id > 0) {
                historytime = em.find(Historytime.class, id);
                if (historytime == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la historial a eliminar.", "eliminarhistorial NoResultException");
                }
                em.remove(historytime);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la historial a eliminar.", "eliminarhistorial NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la historial porque tiene relaciones con otros registros.", "eliminarhistorial " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar la historial.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la historial.", "eliminarhistorial " + ex.getMessage());
        }
    }

    
    public Respuesta getHistorys() {
        try {
            Query qryUsers = em.createNamedQuery("Historytime.findAll", Historytime.class);
            List<Historytime> history = (List<Historytime>) qryUsers.getResultList();
             List<HistoryDto> Listhistorys = new ArrayList<>();
            for (Historytime tipo : history) {
                HistoryDto diaryDto = new HistoryDto(tipo);
                Listhistorys.add(diaryDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "historytime", Listhistorys);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen historiales.", "gethistoriales NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las historiales.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las historiales.", "gethistoriales NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las historiales.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las historiales.", "gethistoriales " + ex.getMessage());
        }
    }
    
}
