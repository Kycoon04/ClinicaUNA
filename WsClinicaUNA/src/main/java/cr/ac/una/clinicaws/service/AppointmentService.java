/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Appointment;
import cr.ac.una.clinicaws.model.AppointmentDto;
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
public class AppointmentService {
    private static final Logger LOG = Logger.getLogger(AppointmentService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getAppointment(Long atId) {
        try {
            Query qryusuario = em.createNamedQuery("Appointment.findByAtId", Appointment.class);
            qryusuario.setParameter("atId", atId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Appointments", new AppointmentDto((Appointment) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una cita con el código ingresado.", "getAppointment NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la cita.", "getAppointment NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la cita.", "getAppointment " + ex.getMessage());
        }
    }

    public Respuesta getAppointmentsPatient(Integer atId) {
        try {
            Query qryUsers = em.createNamedQuery("Appointment.findByPatientId", Appointment.class);
            qryUsers.setParameter("PatientId", atId);
            List<Appointment> appointments = (List<Appointment>) qryUsers.getResultList();
             List<AppointmentDto> ListAppointments = new ArrayList<>();
            for (Appointment tipo : appointments) {
                AppointmentDto appointmentDto = new AppointmentDto(tipo);
                ListAppointments.add(appointmentDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Appointments", ListAppointments);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen citas.", "getCitas NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las citas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las citas.", "getCitas NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la citas.", "getCitas " + ex.getMessage());
        }
    }

    public Respuesta getAppointmentsDate(LocalDate date) {
        try {
            Query qryUsers = em.createNamedQuery("Appointment.findByAtDateregister", Appointment.class);
            qryUsers.setParameter("atDateregister", date);
            List<Appointment> appointments = (List<Appointment>) qryUsers.getResultList();
             List<AppointmentDto> ListAppointments = new ArrayList<>();
            for (Appointment tipo : appointments) {
                AppointmentDto appointmentDto = new AppointmentDto(tipo);
                ListAppointments.add(appointmentDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Appointments", ListAppointments);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen citas.", "getCitas NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las citas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las citas.", "getCitas NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la citas.", "getCitas " + ex.getMessage());
        }
    }
    
    public Respuesta saveAppointments(AppointmentDto appointmentDto) {
        try {
            Appointment appointment = new Appointment();
            if (appointmentDto.getAtId()!= null && appointmentDto.getAtId()> 0) {
                appointment = em.find(Appointment.class, appointmentDto.getAtId());
                if (appointment == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la cita a modificar.", "guardarCita NoResultException");
                }
                appointment.update(appointmentDto);
                appointment = em.merge(appointment);
            } else {
                appointment = new Appointment(appointmentDto);
                em.persist(appointment);
            }
            em.flush();
            Query qryusuario = em.createNamedQuery("Appointment.findByAtCode", Appointment.class);
            qryusuario.setParameter("atCode", appointment.getAtCode());
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Appointments", new AppointmentDto((Appointment) qryusuario.getSingleResult()));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la cita.", "guardarCita " + ex.getMessage());
        }
    }

    public Respuesta deleteAppointment(Integer id) {
        try {
            Appointment appointment;
            if (id != null && id > 0) {
                appointment = em.find(Appointment.class, id);
                if (appointment == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la cita a eliminar.", "eliminarCita NoResultException");
                }
                em.remove(appointment);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la cita a eliminar.", "eliminarCita NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el usuario porque tiene relaciones con otros registros.", "eliminarCita " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al eliminar la cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la cita.", "eliminarCita " + ex.getMessage());
        }
    }

    
    public Respuesta getAppointments() {
        try {
            Query qryUsers = em.createNamedQuery("Appointment.findAll", Appointment.class);
            List<Appointment> appointments = (List<Appointment>) qryUsers.getResultList();
             List<AppointmentDto> ListAppointments = new ArrayList<>();
            for (Appointment tipo : appointments) {
                AppointmentDto appointmentDto = new AppointmentDto(tipo);
                ListAppointments.add(appointmentDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Appointments", ListAppointments);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen citas.", "getCitas NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las citas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las citas.", "getCitas NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la citas.", "getCitas " + ex.getMessage());
        }
    }
}
