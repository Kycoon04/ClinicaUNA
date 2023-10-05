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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_SPACE")
@NamedQueries({
    @NamedQuery(name = "Space.findAll", query = "SELECT s FROM Space s"),
    @NamedQuery(name = "Space.findBySeHour", query = "SELECT s FROM Space s WHERE s.seHour = :seHour"),
    @NamedQuery(name = "Space.findBySeId", query = "SELECT s FROM Space s WHERE s.seId = :seId")})
public class Space implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SE_HOUR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seHour;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SE_ID")
    private Integer seId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dySpace")
    private List<Diary> diaryList;
    @JoinColumn(name = "SE_APPOINTMENT", referencedColumnName = "AT_ID")
    @ManyToOne(optional = false)
    private Appointment seAppointment;

    public Space() {
    }

    public Space(Integer seId) {
        this.seId = seId;
    }

    public Space(Integer seId, Date seHour) {
        this.seId = seId;
        this.seHour = seHour;
    }

    public Date getSeHour() {
        return seHour;
    }

    public void setSeHour(Date seHour) {
        this.seHour = seHour;
    }

    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
    }

    public List<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }

    public Appointment getSeAppointment() {
        return seAppointment;
    }

    public void setSeAppointment(Appointment seAppointment) {
        this.seAppointment = seAppointment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seId != null ? seId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Space)) {
            return false;
        }
        Space other = (Space) object;
        if ((this.seId == null && other.seId != null) || (this.seId != null && !this.seId.equals(other.seId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Space[ seId=" + seId + " ]";
    }
    
}
