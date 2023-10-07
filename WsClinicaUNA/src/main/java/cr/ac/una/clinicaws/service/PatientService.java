/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Patient;
import cr.ac.una.clinicaws.model.PatientDto;
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
public class PatientService {
    
    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getPatient(Integer ptId) {
        try {
            Query qryPatient = em.createNamedQuery("Patient.findByPtId", Patient.class);
            qryPatient.setParameter("ptId", ptId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Patient", new PatientDto((Patient) qryPatient.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getPatient NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Patient.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Patient.", "getPatient NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Patient.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Patient.", "getPatient " + ex.getMessage());
        }
    }

    public Respuesta savePatient(PatientDto patientDto) {
        try {
            Patient patient = new Patient();
            if (patientDto.getPtId() != null && patientDto.getPtId() > 0) {
                patient = em.find(Patient.class, patientDto.getPtId());
                if (patient == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Patient a modificar.", "guardarPatient NoResultException");
                }
                patient.update(patientDto);
                patient = em.merge(patient);
            } else {
                patient = new Patient(patientDto);
                em.persist(patient);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Patient", new PatientDto(patient));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Patient.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Patient.", "guardarPatient " + ex.getMessage());
        }
    }

    public Respuesta deletePatient(Integer id) {
        try {
            Patient patient;
            if (id != null && id > 0) {
                patient = em.find(Patient.class, id);
                if (patient == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Patient a eliminar.", "eliminarPatient NoResultException");
                }
                em.remove(patient);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Patient a eliminar.", "eliminarPatient NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Patient porque tiene relaciones con otros registros.", "eliminarPatient " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Patient.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Patient.", "eliminarPatient " + ex.getMessage());
        }
    }
    
    public Respuesta getPatient() {
        try {
            Query qryPatient = em.createNamedQuery("Patient.findAll", Patient.class);
            List<Patient> patient = (List<Patient>) qryPatient.getResultList();
             List<PatientDto> ListpatientDto = new ArrayList<>();
            for (Patient tipo : patient) {
                PatientDto usersDto = new PatientDto(tipo);
                ListpatientDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Patient", ListpatientDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de Patient con el código ingresado.", "getTipoPatient NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de Patient.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Patient.", "getTipoPatient NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Patient.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Patient.", "getTipoPatient " + ex.getMessage());
        }
    }
}
