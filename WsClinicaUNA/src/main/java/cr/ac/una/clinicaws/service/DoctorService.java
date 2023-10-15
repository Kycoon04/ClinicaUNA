/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Diary;
import cr.ac.una.clinicaws.model.Doctor;
import cr.ac.una.clinicaws.model.DoctorDto;
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
public class DoctorService {
    
    private static final Logger LOG = Logger.getLogger(DoctorService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    
    
    public Respuesta getDoctor(Long drId) {
        try {
            Query qryusuario = em.createNamedQuery("Doctor.findByDrId", Doctor.class);
            qryusuario.setParameter("drId", drId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Doctors", new DoctorDto((Doctor) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un Doctor con el código ingresado.", "getDoctor NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar un Doctor.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Doctor.", "getDoctor NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar un Doctor.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Doctor.", "getDoctor " + ex.getMessage());
        }
    }

    
    public Respuesta getDoctorByUser(Long drId) {
        try {
            Query qryusuario = em.createNamedQuery("Doctor.findDoctorByUserId", Doctor.class);
            qryusuario.setParameter("userId", drId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Doctors", new DoctorDto((Doctor) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un Doctor con el código ingresado.", "getDoctor NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar un Doctor.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Doctor.", "getDoctor NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar un Doctor.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Doctor.", "getDoctor " + ex.getMessage());
        }
    }

    public Respuesta saveDoctor(DoctorDto doctorDto) {
        try {
            Doctor doctor = new Doctor();
            if (doctorDto.getDrId()!= null && doctorDto.getDrId()> 0) {
                doctor = em.find(Doctor.class, doctorDto.getDrId());
                if (doctor == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró doctor a modificar.", "guardarDoctor NoResultException");
                }
                doctor.update(doctorDto);
                doctor = em.merge(doctor);
            } else {
                doctor = new Doctor(doctorDto);
                em.persist(doctor);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Doctors", new DoctorDto(doctor));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el doctor.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la Agenda.", "guardarDoctor " + ex.getMessage());
        }
    }

    public Respuesta deleteDoctor(Integer id) {
        try {
            Doctor doctor;
            if (id != null && id > 0) {
                doctor = em.find(Doctor.class, id);
                if (doctor == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el doctor a eliminar.", "eliminarDoctor NoResultException");
                }
                em.remove(doctor);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el doctor a eliminar.", "eliminarDoctor NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el doctor porque tiene relaciones con otros registros.", "eliminarDoctor " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar el doctor.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el doctor.", "eliminarAgenda " + ex.getMessage());
        }
    }

    
    public Respuesta getDoctors() {
        try {
            Query qryUsers = em.createNamedQuery("Doctor.findAll", Doctor.class);
            List<Doctor> doctor = (List<Doctor>) qryUsers.getResultList();
             List<DoctorDto> ListDoctor = new ArrayList<>();
            for (Doctor tipo : doctor) {
                DoctorDto doctorDto = new DoctorDto(tipo);
                ListDoctor.add(doctorDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Doctors", ListDoctor);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen Doctores.", "getDoctor NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las agendas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar Doctores.", "getDoctor NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las agendas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar Doctores.", "getDoctor " + ex.getMessage());
        }
    }
   
}
