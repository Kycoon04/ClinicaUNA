/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

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

/**
 *
 * @author dilan
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
    @NamedQuery(name = "Report.findByRtDoctorReason", query = "SELECT r FROM Report r WHERE r.rtDoctorReason = :rtDoctorReason")})
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
    private short rtPressure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_HEART_RATE")
    private short rtHeartRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_WEIGHT")
    private short rtWeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_HEIGHT")
    private short rtHeight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_TEMPERATURE")
    private short rtTemperature;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT_BODY_MASS")
    private short rtBodyMass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "RT_NOTES_NURSING")
    private String rtNotesNursing;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "RT_DOCTOR_REASON")
    private String rtDoctorReason;
    @JoinColumn(name = "RT_APPOINTMENT", referencedColumnName = "AT_ID")
    @ManyToOne(optional = false)
    private Appointment rtAppointment;

    public Report() {
    }

    public Report(Integer rtId) {
        this.rtId = rtId;
    }

    public Report(Integer rtId, short rtPressure, short rtHeartRate, short rtWeight, short rtHeight, short rtTemperature, short rtBodyMass, String rtNotesNursing, String rtDoctorReason) {
        this.rtId = rtId;
        this.rtPressure = rtPressure;
        this.rtHeartRate = rtHeartRate;
        this.rtWeight = rtWeight;
        this.rtHeight = rtHeight;
        this.rtTemperature = rtTemperature;
        this.rtBodyMass = rtBodyMass;
        this.rtNotesNursing = rtNotesNursing;
        this.rtDoctorReason = rtDoctorReason;
    }

    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
    }

    public short getRtPressure() {
        return rtPressure;
    }

    public void setRtPressure(short rtPressure) {
        this.rtPressure = rtPressure;
    }

    public short getRtHeartRate() {
        return rtHeartRate;
    }

    public void setRtHeartRate(short rtHeartRate) {
        this.rtHeartRate = rtHeartRate;
    }

    public short getRtWeight() {
        return rtWeight;
    }

    public void setRtWeight(short rtWeight) {
        this.rtWeight = rtWeight;
    }

    public short getRtHeight() {
        return rtHeight;
    }

    public void setRtHeight(short rtHeight) {
        this.rtHeight = rtHeight;
    }

    public short getRtTemperature() {
        return rtTemperature;
    }

    public void setRtTemperature(short rtTemperature) {
        this.rtTemperature = rtTemperature;
    }

    public short getRtBodyMass() {
        return rtBodyMass;
    }

    public void setRtBodyMass(short rtBodyMass) {
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

    public Appointment getRtAppointment() {
        return rtAppointment;
    }

    public void setRtAppointment(Appointment rtAppointment) {
        this.rtAppointment = rtAppointment;
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
