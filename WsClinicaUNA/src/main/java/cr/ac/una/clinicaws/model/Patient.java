/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_PATIENT")
@NamedQueries({
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p"),
    @NamedQuery(name = "Patient.findByPtName", query = "SELECT p FROM Patient p WHERE p.ptName = :ptName"),
    @NamedQuery(name = "Patient.findByPtPlastname", query = "SELECT p FROM Patient p WHERE p.ptPlastname = :ptPlastname"),
    @NamedQuery(name = "Patient.findByPtSlastname", query = "SELECT p FROM Patient p WHERE p.ptSlastname = :ptSlastname"),
    @NamedQuery(name = "Patient.findByPtIdentification", query = "SELECT p FROM Patient p WHERE p.ptIdentification = :ptIdentification"),
    @NamedQuery(name = "Patient.findByPtGender", query = "SELECT p FROM Patient p WHERE p.ptGender = :ptGender"),
    @NamedQuery(name = "Patient.findByPtEmail", query = "SELECT p FROM Patient p WHERE p.ptEmail = :ptEmail"),
    @NamedQuery(name = "Patient.findByPtBirthdate", query = "SELECT p FROM Patient p WHERE p.ptBirthdate = :ptBirthdate"),
    @NamedQuery(name = "Patient.findByPtId", query = "SELECT p FROM Patient p WHERE p.ptId = :ptId")})
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PT_NAME")
    private String ptName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PT_PLASTNAME")
    private String ptPlastname;
    @Size(max = 30)
    @Column(name = "PT_SLASTNAME")
    private String ptSlastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PT_IDENTIFICATION")
    private String ptIdentification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PT_GENDER")
    private String ptGender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PT_EMAIL")
    private String ptEmail;
    @Column(name = "PT_BIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptBirthdate;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PT_ID")
    private Integer ptId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atPatient")
    private List<Appointment> appointmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "psPatient")
    private List<Proceedings> proceedingsList;

    public Patient() {
    }

    public Patient(Integer ptId) {
        this.ptId = ptId;
    }

    public Patient(Integer ptId, String ptName, String ptPlastname, String ptIdentification, String ptGender, String ptEmail) {
        this.ptId = ptId;
        this.ptName = ptName;
        this.ptPlastname = ptPlastname;
        this.ptIdentification = ptIdentification;
        this.ptGender = ptGender;
        this.ptEmail = ptEmail;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getPtPlastname() {
        return ptPlastname;
    }

    public void setPtPlastname(String ptPlastname) {
        this.ptPlastname = ptPlastname;
    }

    public String getPtSlastname() {
        return ptSlastname;
    }

    public void setPtSlastname(String ptSlastname) {
        this.ptSlastname = ptSlastname;
    }

    public String getPtIdentification() {
        return ptIdentification;
    }

    public void setPtIdentification(String ptIdentification) {
        this.ptIdentification = ptIdentification;
    }

    public String getPtGender() {
        return ptGender;
    }

    public void setPtGender(String ptGender) {
        this.ptGender = ptGender;
    }

    public String getPtEmail() {
        return ptEmail;
    }

    public void setPtEmail(String ptEmail) {
        this.ptEmail = ptEmail;
    }

    public Date getPtBirthdate() {
        return ptBirthdate;
    }

    public void setPtBirthdate(Date ptBirthdate) {
        this.ptBirthdate = ptBirthdate;
    }

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Proceedings> getProceedingsList() {
        return proceedingsList;
    }

    public void setProceedingsList(List<Proceedings> proceedingsList) {
        this.proceedingsList = proceedingsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptId != null ? ptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.ptId == null && other.ptId != null) || (this.ptId != null && !this.ptId.equals(other.ptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Patient[ ptId=" + ptId + " ]";
    }
    
}
