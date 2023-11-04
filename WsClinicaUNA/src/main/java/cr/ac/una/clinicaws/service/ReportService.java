/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Report;
import cr.ac.una.clinicaws.model.ReportDto;
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
public class ReportService {
    
    private static final Logger LOG = Logger.getLogger(ReportService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getReport(Integer rtId) {
        try {
            Query qryReport = em.createNamedQuery("Report.findByRtId", Report.class);
            qryReport.setParameter("rtId", rtId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Report", new ReportDto((Report) qryReport.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getReport NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Report.", "getReport NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Report.", "getReport " + ex.getMessage());
        }
    }

    public Respuesta saveReport(ReportDto reportDto) {
        try {
            Report report = new Report();
            if (reportDto.getRtId() != null && reportDto.getRtId() > 0) {
                report = em.find(Report.class, reportDto.getRtId());
                if (report == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Report a modificar.", "guardarReport NoResultException");
                }
                report.update(reportDto);
                report = em.merge(report);
            } else {
                report = new Report(reportDto);
                em.persist(report);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Report", new ReportDto(report));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Report.", "guardarReport " + ex.getMessage());
        }
    }

    public Respuesta deleteReport(Integer id) {
        try {
            Report report;
            if (id != null && id > 0) {
                report = em.find(Report.class, id);
                if (report == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Report a eliminar.", "eliminarReport NoResultException");
                }
                em.remove(report);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Report a eliminar.", "eliminarReport NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Report porque tiene relaciones con otros registros.", "eliminarReport " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Report.", "eliminarReport " + ex.getMessage());
        }
    }
    
    public Respuesta getReports() {
        try {
            Query qryReport = em.createNamedQuery("Report.findAll", Report.class);
            List<Report> reports = (List<Report>) qryReport.getResultList();
             List<ReportDto> ListreportsDto = new ArrayList<>();
            for (Report tipo : reports) {
                ReportDto reportDto = new ReportDto(tipo);
                ListreportsDto.add(reportDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Report", ListreportsDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de Report con el código ingresado.", "getTipoReport NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Report.", "getTipoReport NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Report.", "getTipoReport " + ex.getMessage());
        }
    }
    
        public Respuesta getReportAppoint(Integer rtId) {
        try {
            Query qryReport = em.createQuery("SELECT r FROM Report r WHERE r.rtAppointment.atId = :rtId", Report.class);
            qryReport.setParameter("rtId", rtId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Report", new ReportDto((Report) qryReport.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getReportAppoint NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Report.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Report.", "getReportAppoint NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Report Extraido.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Report.", "getReportAppoint " + ex.getMessage());
        }
    }
}
