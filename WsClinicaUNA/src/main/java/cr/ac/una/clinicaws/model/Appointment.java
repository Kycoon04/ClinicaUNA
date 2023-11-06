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
import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author jomav
 */
@Entity
@Table(name = "CL_APPOINTMENT")
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByAtId", query = "SELECT a FROM Appointment a WHERE a.atId = :atId"),
    @NamedQuery(name = "Appointment.findByAtReason", query = "SELECT a FROM Appointment a WHERE a.atReason = :atReason"),
    @NamedQuery(name = "Appointment.findByAtTelephone", query = "SELECT a FROM Appointment a WHERE a.atTelephone = :atTelephone"),
    @NamedQuery(name = "Appointment.findByAtEmail", query = "SELECT a FROM Appointment a WHERE a.atEmail = :atEmail"),
    @NamedQuery(name = "Appointment.findByAtState", query = "SELECT a FROM Appointment a WHERE a.atState = :atState"),
    @NamedQuery(name = "Appointment.findByAtCode", query = "SELECT a FROM Appointment a WHERE a.atCode = :atCode"),
    @NamedQuery(name = "Appointment.findByPatientId", query = "SELECT a FROM Appointment a WHERE a.atPatient.ptId = :PatientId"),
    @NamedQuery(name = "Appointment.findByAtDateregister", query = "SELECT a FROM Appointment a WHERE a.atDateregister = :atDateregister")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AT_ID")
    private Integer atId;
    @Size(max = 200)
    @Column(name = "AT_REASON")
    private String atReason;
    @Column(name = "AT_TELEPHONE")
    private Long atTelephone;
    @Size(max = 30)
    @Column(name = "AT_EMAIL")
    private String atEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "AT_STATE")
    private String atState;
    @Size(max = 10)
    @Column(name = "AT_CODE")
    private String atCode;
    @Column(name = "AT_DATEREGISTER")
    private LocalDate atDateregister;
    @JoinColumn(name = "AT_PATIENT", referencedColumnName = "PT_ID")
    @ManyToOne(optional = false)
    private Patient atPatient;
    @JoinColumn(name = "AT_USERREGISTER", referencedColumnName = "US_ID")
    @ManyToOne(optional = false)
    private Users atUserregister;
    
    public Appointment() {
    }

    public Appointment(Integer atId) {
        this.atId = atId;
    }
     public Appointment(AppointmentDto appointmentDto) {
        this.atId = appointmentDto.getAtId();
        update(appointmentDto);
    }
    public void update(AppointmentDto appointmentDto) {
        this.atEmail = appointmentDto.getAtEmail();
        this.atPatient = appointmentDto.getAtPatient();
        this.atReason = appointmentDto.getAtReason();
        this.atState = appointmentDto.getAtState();
        this.atTelephone = appointmentDto.getAtTelephone();
        this.atUserregister = appointmentDto.getAtUserregister();
        this.atCode = appointmentDto.getAtCode();
        this.atDateregister = appointmentDto.getFechaRegistro();
    }
    public Appointment(Integer atId, String atState) {
        this.atId = atId;
        this.atState = atState;
    }

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }

    public String getAtReason() {
        return atReason;
    }

    public void setAtReason(String atReason) {
        this.atReason = atReason;
    }

    public Long getAtTelephone() {
        return atTelephone;
    }

    public void setAtTelephone(Long atTelephone) {
        this.atTelephone = atTelephone;
    }

    public String getAtEmail() {
        return atEmail;
    }

    public void setAtEmail(String atEmail) {
        this.atEmail = atEmail;
    }

    public String getAtState() {
        return atState;
    }

    public void setAtState(String atState) {
        this.atState = atState;
    }

    public String getAtCode() {
        return atCode;
    }

    public void setAtCode(String atCode) {
        this.atCode = atCode;
    }

    public LocalDate getAtDateregister() {
        return atDateregister;
    }

    public void setAtDateregister(LocalDate atDateregister) {
        this.atDateregister = atDateregister;
    }
    public Patient getAtPatient() {
        return atPatient;
    }

    public void setAtPatient(Patient atPatient) {
        this.atPatient = atPatient;
    }

    public Users getAtUserregister() {
        return atUserregister;
    }

    public void setAtUserregister(Users atUserregister) {
        this.atUserregister = atUserregister;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atId != null ? atId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.atId == null && other.atId != null) || (this.atId != null && !this.atId.equals(other.atId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Appointment[ atId=" + atId + " ]";
    }
    
}
