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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_PROCEEDINGS")
@NamedQueries({
    @NamedQuery(name = "Proceedings.findAll", query = "SELECT p FROM Proceedings p"),
    @NamedQuery(name = "Proceedings.findByPsId", query = "SELECT p FROM Proceedings p WHERE p.psId = :psId")})
public class Proceedings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PS_ID")
    private Integer psId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fpProceedings")
    private List<FProceedings> fProceedingsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ppProceedings")
    private List<PProceedings> pProceedingsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emProceedings")
    private List<Exam> examList;
    @JoinColumn(name = "PS_PATIENT", referencedColumnName = "PT_ID")
    @ManyToOne(optional = false)
    private Patient psPatient;

    public Proceedings() {
    }

    public Proceedings(Integer psId) {
        this.psId = psId;
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public List<FProceedings> getFProceedingsList() {
        return fProceedingsList;
    }

    public void setFProceedingsList(List<FProceedings> fProceedingsList) {
        this.fProceedingsList = fProceedingsList;
    }

    public List<PProceedings> getPProceedingsList() {
        return pProceedingsList;
    }

    public void setPProceedingsList(List<PProceedings> pProceedingsList) {
        this.pProceedingsList = pProceedingsList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public Patient getPsPatient() {
        return psPatient;
    }

    public void setPsPatient(Patient psPatient) {
        this.psPatient = psPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psId != null ? psId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proceedings)) {
            return false;
        }
        Proceedings other = (Proceedings) object;
        if ((this.psId == null && other.psId != null) || (this.psId != null && !this.psId.equals(other.psId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Proceedings[ psId=" + psId + " ]";
    }
    
}
