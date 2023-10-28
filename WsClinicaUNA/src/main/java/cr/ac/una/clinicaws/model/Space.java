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
 * @author jomav
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
    @Size(min = 1, max = 10)
    @Column(name = "SE_HOUR")
    private String seHour;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SE_ID")
    private Integer seId;
    @JoinColumn(name = "SE_APPOINTMENT", referencedColumnName = "AT_ID")
    @ManyToOne(optional = false)
    private Appointment seAppointment;
    
    public Space() {
    }

    public Space(SpaceDto spaceDto) {
        this.seId = spaceDto.getSeId();
        update(spaceDto);
    }

    public void update(SpaceDto space) {
        this.seHour = space.getSeHour();
        this.seAppointment = space.getSeAppointment();
    }
    public Space(Integer seId) {
        this.seId = seId;
    }

    public Space(Integer seId, String seHour) {
        this.seId = seId;
        this.seHour = seHour;
    }

    public String getSeHour() {
        return seHour;
    }
    public Appointment getSeAppointment() {
        return seAppointment;
    }

    public void setSeAppointment(Appointment seAppointment) {
        this.seAppointment = seAppointment;
    }
    public void setSeHour(String seHour) {
        this.seHour = seHour;
    }

    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
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
