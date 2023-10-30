/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Familybackground;
import cr.ac.una.clinicaws.model.FamilybackgroundDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Logger;
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
public class FamilybackgroundService {
    private static final Logger LOG = Logger.getLogger(FamilybackgroundService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    
      
    public Respuesta getFamilybackground(Long fbId) {
        try {
            Query qryusuario = em.createNamedQuery("Familybackground.findByFbId", Familybackground.class);
            qryusuario.setParameter("fbId", fbId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Familybackground", new FamilybackgroundDto((Familybackground) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un antecedente heredo familiar código ingresado.", "getFamilybackground NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar  antecedentes heredo familiare.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar antecedentes heredo familiares.", "getFamilybackground NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar antecedentes heredo familiare.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar  antecedentes heredo familiare.", "getFamilybackground " + ex.getMessage());
        }
    }

      public Respuesta getFamilybackgroundCode(Long code) {
         try {
            Query qryusuario = em.createNamedQuery("Familybackground.findByFbFilecode", Familybackground.class);
            qryusuario.setParameter("fbFilecode", code);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Familybackground", new FamilybackgroundDto((Familybackground) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un antecedente heredo familiar código ingresado.", "getFamilybackground NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar  antecedentes heredo familiare.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar antecedentes heredo familiares.", "getFamilybackground NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar antecedentes heredo familiare.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar  antecedentes heredo familiare.", "getFamilybackground " + ex.getMessage());
        }
    }


    public Respuesta saveFamilybackground(FamilybackgroundDto familybackgrounddto) {
        try {
            Familybackground familybackground = new Familybackground();
            if (familybackgrounddto.getFbId()!= null && familybackgrounddto.getFbId()> 0) {
                familybackground = em.find(Familybackground.class, familybackgrounddto.getFbId());
                if (familybackground == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró  antecedentes heredo familiares. a modificar.", "Familybackground NoResultException");
                }
                familybackground.update(familybackgrounddto);
                familybackground = em.merge(familybackground);
            } else {
                familybackground = new Familybackground(familybackgrounddto);
                em.persist(familybackground);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Familybackground", new FamilybackgroundDto(familybackground));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar  antecedentes heredo familiares.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar   antecedentes heredo familiares.", "guardarFamilybackground " + ex.getMessage());
        }
    }

    public Respuesta deleteFamilybackground(Integer id) {
        try {
            Familybackground familybackground;
            if (id != null && id > 0) {
                familybackground = em.find(Familybackground.class, id);
                if (familybackground == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró  antecedentes heredo familiares a eliminar.", "eliminarFamilybackground NoResultException");
                }
                em.remove(familybackground);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar antecedentes heredo familiares a eliminar a eliminar.", "eliminarFamilybackground NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar antecedentes heredo familiares  porque tiene relaciones con otros registros.", "eliminarFamilybackground " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar antecedentes heredo familiares a eliminar.", "eliminarFamilybackground " + ex.getMessage());
        }
    }

    
    public Respuesta getFamilybackgrounds() {
        try {
            Query qryUsers = em.createNamedQuery("Familybackground.findAll", Familybackground.class);
            List<Familybackground> familybackground = (List<Familybackground>) qryUsers.getResultList();
             List<FamilybackgroundDto> ListExam = new ArrayList<>();
            for (Familybackground tipo : familybackground) {
                FamilybackgroundDto familybackgroundDto = new FamilybackgroundDto(tipo);
                ListExam.add(familybackgroundDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Familybackground", ListExam);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen antecedentes heredo familiares.", "getFamilybackground NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar antecedentes heredo familiares.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar antecedentes heredo familiares.", "getFamilybackground NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar antecedentes heredo familiares.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar antecedentes heredo familiares.", "getFamilybackground " + ex.getMessage());
        }
    }
    
    
    
}
