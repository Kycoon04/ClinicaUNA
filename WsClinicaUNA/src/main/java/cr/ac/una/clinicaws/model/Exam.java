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
@Table(name = "CL_EXAM")
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findByEmId", query = "SELECT e FROM Exam e WHERE e.emId = :emId"),
    @NamedQuery(name = "Exam.findByEmName", query = "SELECT e FROM Exam e WHERE e.emName = :emName"),
    @NamedQuery(name = "Exam.findByEmDoctornote", query = "SELECT e FROM Exam e WHERE e.emDoctornote = :emDoctornote")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EM_ID")
    private Integer emId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EM_NAME")
    private String emName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "EM_DOCTORNOTE")
    private String emDoctornote;
    @JoinColumn(name = "EM_DOCTOR", referencedColumnName = "DR_ID")
    @ManyToOne(optional = false)
    private Doctor emDoctor;
    @JoinColumn(name = "EM_PROCEEDINGS", referencedColumnName = "PS_ID")
    @ManyToOne(optional = false)
    private Proceedings emProceedings;

    public Exam() {
    }

    public Exam(Integer emId) {
        this.emId = emId;
    }

    public Exam(Integer emId, String emName, String emDoctornote) {
        this.emId = emId;
        this.emName = emName;
        this.emDoctornote = emDoctornote;
    }

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmDoctornote() {
        return emDoctornote;
    }

    public void setEmDoctornote(String emDoctornote) {
        this.emDoctornote = emDoctornote;
    }

    public Doctor getEmDoctor() {
        return emDoctor;
    }

    public void setEmDoctor(Doctor emDoctor) {
        this.emDoctor = emDoctor;
    }

    public Proceedings getEmProceedings() {
        return emProceedings;
    }

    public void setEmProceedings(Proceedings emProceedings) {
        this.emProceedings = emProceedings;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emId != null ? emId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.emId == null && other.emId != null) || (this.emId != null && !this.emId.equals(other.emId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Exam[ emId=" + emId + " ]";
    }
    
}
