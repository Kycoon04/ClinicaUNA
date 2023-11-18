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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jomav
 */
@Entity
@Table(name = "CL_HISTORYTIME")
@NamedQueries({
    @NamedQuery(name = "Historytime.findAll", query = "SELECT h FROM Historytime h"),
    @NamedQuery(name = "Historytime.findByHtId", query = "SELECT h FROM Historytime h WHERE h.htId = :htId"),
    @NamedQuery(name = "Historytime.findByHtDate", query = "SELECT h FROM Historytime h WHERE h.htDate = :htDate"),
    @NamedQuery(name = "Historytime.findByHtSpaces", query = "SELECT h FROM Historytime h WHERE h.htSpaces = :htSpaces"),
    @NamedQuery(name = "Historytime.findByHtIniworking", query = "SELECT h FROM Historytime h WHERE h.htIniworking = :htIniworking"),
    @NamedQuery(name = "Historytime.findByHtFinisworking", query = "SELECT h FROM Historytime h WHERE h.htFinisworking = :htFinisworking"),
    @NamedQuery(name = "Historytime.findByDoctorId", query = "SELECT h FROM Historytime h WHERE h.htDoctor.drId = :DoctorId"),
    @NamedQuery(name = "Historytime.findByDateInRange",
    query = "SELECT h FROM Historytime h WHERE :date >= h.htDate AND (h.htDateFinal IS NULL OR :date < h.htDateFinal) AND h.htDoctor.drId = :DoctorId"),
    @NamedQuery(name = "Historytime.findByHtDateFinal", query = "SELECT h FROM Historytime h WHERE h.htDateFinal = :htDateFinal")})
public class Historytime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "HT_ID")
    private Integer htId;
    @Column(name = "HT_DATE")
    private LocalDate htDate;
    @Column(name = "HT_SPACES")
    private Short htSpaces;
    @Size(max = 10)
    @Column(name = "HT_INIWORKING")
    private String htIniworking;
    @Size(max = 10)
    @Column(name = "HT_FINISWORKING")
    private String htFinisworking;
    @Column(name = "HT_DATE_FINAL")
    private LocalDate htDateFinal;
    @JoinColumn(name = "HT_DOCTOR", referencedColumnName = "DR_ID")
    @ManyToOne
    private Doctor htDoctor;
    
    public Historytime() {
    }
    public Historytime(HistoryDto historyDto) {
        this.htId = historyDto.getHtId();
        update(historyDto);
    }

    public void update(HistoryDto historyDto) {
        this.htId = historyDto.getHtId();
        this.htDate = historyDto.getHtDate();
        this.htSpaces = historyDto.getHtSpaces();
        this.htIniworking = historyDto.getHtIniworking();
        this.htFinisworking = historyDto.getHtFinisworking();
        this.htDoctor = historyDto.getHtDoctor();
        this.htDateFinal = historyDto.getHtDateFinal();
    }
    public Historytime(Integer htId) {
        this.htId = htId;
    }

    public Integer getHtId() {
        return htId;
    }

    public void setHtId(Integer htId) {
        this.htId = htId;
    }

    public LocalDate getHtDate() {
        return htDate;
    }

    public void setHtDate(LocalDate htDate) {
        this.htDate = htDate;
    }

    public Short getHtSpaces() {
        return htSpaces;
    }

    public void setHtSpaces(Short htSpaces) {
        this.htSpaces = htSpaces;
    }

    public String getHtIniworking() {
        return htIniworking;
    }

    public void setHtIniworking(String htIniworking) {
        this.htIniworking = htIniworking;
    }

    public String getHtFinisworking() {
        return htFinisworking;
    }

    public void setHtFinisworking(String htFinisworking) {
        this.htFinisworking = htFinisworking;
    }

    public LocalDate getHtDateFinal() {
        return htDateFinal;
    }

    public void setHtDateFinal(LocalDate htDateFinal) {
        this.htDateFinal = htDateFinal;
    }
    public Doctor getHtDoctor() {
        return htDoctor;
    }

    public void setHtDoctor(Doctor htDoctor) {
        this.htDoctor = htDoctor;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (htId != null ? htId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historytime)) {
            return false;
        }
        Historytime other = (Historytime) object;
        if ((this.htId == null && other.htId != null) || (this.htId != null && !this.htId.equals(other.htId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Historytime[ htId=" + htId + " ]";
    }
    
}
