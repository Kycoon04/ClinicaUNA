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
import java.time.LocalDate;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_DIARY")
@NamedQueries({
    @NamedQuery(name = "Diary.findAll", query = "SELECT d FROM Diary d"),
    @NamedQuery(name = "Diary.findByDyId", query = "SELECT d FROM Diary d WHERE d.dyId = :dyId"),
    @NamedQuery(name = "Diary.findByDyDate", query = "SELECT d FROM Diary d WHERE d.dyDate = :dyDate")})
    @NamedQuery(name = "Diary.findByDateRange", query = "SELECT d FROM Diary d WHERE d.dyDate BETWEEN :startDate AND :endDate")
public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DY_ID")
    private Integer dyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DY_DATE")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate dyDate;
    @JoinColumn(name = "DY_DOCTOR", referencedColumnName = "DR_ID")
    @ManyToOne(optional = false)
    private Doctor dyDoctor;
    @JoinColumn(name = "DY_SPACE", referencedColumnName = "SE_ID")
    @ManyToOne(optional = false)
    private Space dySpace;

    public Diary() {
    }

    public Diary(Integer dyId) {
        this.dyId = dyId;
    }
      public Diary(DiaryDto diaryDto) {
        this.dyId = diaryDto.getDyId();
        update(diaryDto);
    }
    public void update(DiaryDto diaryDto) {
        this.dyDate = diaryDto.getDyDate();
        this.dyDoctor = diaryDto.getDyDoctor();
        this.dySpace = diaryDto.getDySpace();
   
    }

    public Diary(Integer dyId, LocalDate dyDate) {
        this.dyId = dyId;
        this.dyDate = dyDate;
    }

    public Integer getDyId() {
        return dyId;
    }

    public void setDyId(Integer dyId) {
        this.dyId = dyId;
    }

    public LocalDate getDyDate() {
        return dyDate;
    }

    public void setDyDate(LocalDate dyDate) {
        this.dyDate = dyDate;
    }

    public Doctor getDyDoctor() {
        return dyDoctor;
    }

    public void setDyDoctor(Doctor dyDoctor) {
        this.dyDoctor = dyDoctor;
    }

    public Space getDySpace() {
        return dySpace;
    }

    public void setDySpace(Space dySpace) {
        this.dySpace = dySpace;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dyId != null ? dyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diary)) {
            return false;
        }
        Diary other = (Diary) object;
        if ((this.dyId == null && other.dyId != null) || (this.dyId != null && !this.dyId.equals(other.dyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Diary[ dyId=" + dyId + " ]";
    }
    
}
