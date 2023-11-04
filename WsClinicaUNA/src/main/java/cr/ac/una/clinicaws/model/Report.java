/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 *
 * @author jomav
 */
@Entity
@Table(name = "CL_REPORT")
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByRtId", query = "SELECT r FROM Report r WHERE r.rtId = :rtId"),
    @NamedQuery(name = "Report.findByRtPressure", query = "SELECT r FROM Report r WHERE r.rtPressure = :rtPressure"),
    @NamedQuery(name = "Report.findByRtHeartRate", query = "SELECT r FROM Report r WHERE r.rtHeartRate = :rtHeartRate"),
    @NamedQuery(name = "Report.findByRtWeight", query = "SELECT r FROM Report r WHERE r.rtWeight = :rtWeight"),
    @NamedQuery(name = "Report.findByRtHeight", query = "SELECT r FROM Report r WHERE r.rtHeight = :rtHeight"),
    @NamedQuery(name = "Report.findByRtTemperature", query = "SELECT r FROM Report r WHERE r.rtTemperature = :rtTemperature"),
    @NamedQuery(name = "Report.findByRtBodyMass", query = "SELECT r FROM Report r WHERE r.rtBodyMass = :rtBodyMass"),
    @NamedQuery(name = "Report.findByRtNotesNursing", query = "SELECT r FROM Report r WHERE r.rtNotesNursing = :rtNotesNursing"),
    @NamedQuery(name = "Report.findByRtDoctorReason", query = "SELECT r FROM Report r WHERE r.rtDoctorReason = :rtDoctorReason"),
    @NamedQuery(name = "Report.findByRtFisicExamen", query = "SELECT r FROM Report r WHERE r.rtFisicExamen = :rtFisicExamen"),
    @NamedQuery(name = "Report.findByRtTreatmentExamen", query = "SELECT r FROM Report r WHERE r.rtTreatmentExamen = :rtTreatmentExamen"),
    @NamedQuery(name = "Report.findByRtCarePlan", query = "SELECT r FROM Report r WHERE r.rtCarePlan = :rtCarePlan"),
    @NamedQuery(name = "Report.findByRtObservations", query = "SELECT r FROM Report r WHERE r.rtObservations = :rtObservations"),
    @NamedQuery(name = "Report.findByAppointmentId", query = "SELECT r FROM Report r WHERE r.rtAppointment.atId = :AppointmentId"),
    @NamedQuery(name = "Report.findByRtDate", query = "SELECT r FROM Report r WHERE r.rtDate = :rtDate")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_ID")
    private Integer rtId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_PRESSURE")
    private double rtPressure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_HEART_RATE")
    private double rtHeartRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_WEIGHT")
    private double rtWeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_HEIGHT")
    private double rtHeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_TEMPERATURE")
    private double rtTemperature;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_BODY_MASS")
    private double rtBodyMass;
    @Size(max = 200)
    @Column(name = "RT_NOTES_NURSING")
    private String rtNotesNursing;
    @Size(max = 300)
    @Column(name = "RT_DOCTOR_REASON")
    private String rtDoctorReason;
    @Size(max = 300)
    @Column(name = "RT_FISIC_EXAMEN")
    private String rtFisicExamen;
    @Size(max = 300)
    @Column(name = "RT_TREATMENT_EXAMEN")
    private String rtTreatmentExamen;
    @Size(max = 300)
    @Column(name = "RT_CARE_PLAN")
    private String rtCarePlan;
    @Size(max = 300)
    @Column(name = "RT_OBSERVATIONS")
    private String rtObservations;
    @Column(name = "RT_DATE")
    private LocalDate rtDate;
    @JoinColumn(name = "RT_APPOINTMENT", referencedColumnName = "AT_ID")
    @ManyToOne(optional = false)
    private Appointment rtAppointment;

    public Report() {
    }

    public Report(Integer rtId) {
        this.rtId = rtId;
    }
    public Report(ReportDto reportDto) {
        this.rtId = reportDto.getRtId();
        update(reportDto);
    }
    
    public void update(ReportDto report) {
        this.rtPressure = report.getRtPressure();
        this.rtHeartRate = report.getRtHeartRate();
        this.rtWeight = report.getRtWeight();
        this.rtHeight = report.getRtHeight();
        this.rtTemperature = report.getRtTemperature();
        this.rtBodyMass = report.getRtBodyMass();
        this.rtNotesNursing = report.getRtNotesNursing();
        this.rtDoctorReason = report.getRtDoctorReason();
        this.rtAppointment = report.getRtAppointment();
        this.rtFisicExamen = report.getRtFisicExamen();
        this.rtTreatmentExamen = report.getRtTreatmentExamen();
        this.rtCarePlan = report.getRtCarePlan();
        this.rtObservations = report.getRtObservations();
        this.rtDate = report.getRtDate();
    }
    
    public Appointment getRtAppointment() {
        return rtAppointment;
    }

    public void setRtAppointment(Appointment rtAppointment) {
        this.rtAppointment = rtAppointment;
    }

    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }

    public double getRtPressure() {
        return rtPressure;
    }

    public void setRtPressure(double rtPressure) {
        this.rtPressure = rtPressure;
    }

    public double getRtHeartRate() {
        return rtHeartRate;
    }

    public void setRtHeartRate(double rtHeartRate) {
        this.rtHeartRate = rtHeartRate;
    }

    public double getRtWeight() {
        return rtWeight;
    }

    public void setRtWeight(double rtWeight) {
        this.rtWeight = rtWeight;
    }

    public double getRtHeight() {
        return rtHeight;
    }

    public void setRtHeight(double rtHeight) {
        this.rtHeight = rtHeight;
    }

    public double getRtTemperature() {
        return rtTemperature;
    }

    public void setRtTemperature(double rtTemperature) {
        this.rtTemperature = rtTemperature;
    }

    public double getRtBodyMass() {
        return rtBodyMass;
    }

    public void setRtBodyMass(double rtBodyMass) {
        this.rtBodyMass = rtBodyMass;
    }

    public String getRtNotesNursing() {
        return rtNotesNursing;
    }

    public void setRtNotesNursing(String rtNotesNursing) {
        this.rtNotesNursing = rtNotesNursing;
    }

    public String getRtDoctorReason() {
        return rtDoctorReason;
    }

    public void setRtDoctorReason(String rtDoctorReason) {
        this.rtDoctorReason = rtDoctorReason;
    }

    public String getRtFisicExamen() {
        return rtFisicExamen;
    }

    public void setRtFisicExamen(String rtFisicExamen) {
        this.rtFisicExamen = rtFisicExamen;
    }

    public String getRtTreatmentExamen() {
        return rtTreatmentExamen;
    }

    public void setRtTreatmentExamen(String rtTreatmentExamen) {
        this.rtTreatmentExamen = rtTreatmentExamen;
    }

    public String getRtCarePlan() {
        return rtCarePlan;
    }

    public void setRtCarePlan(String rtCarePlan) {
        this.rtCarePlan = rtCarePlan;
    }

    public String getRtObservations() {
        return rtObservations;
    }

    public void setRtObservations(String rtObservations) {
        this.rtObservations = rtObservations;
    }

    public LocalDate getRtDate() {
        return rtDate;
    }

    public void setRtDate(LocalDate rtDate) {
        this.rtDate = rtDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rtId != null ? rtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.rtId == null && other.rtId != null) || (this.rtId != null && !this.rtId.equals(other.rtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Report[ rtId=" + rtId + " ]";
    }

}
