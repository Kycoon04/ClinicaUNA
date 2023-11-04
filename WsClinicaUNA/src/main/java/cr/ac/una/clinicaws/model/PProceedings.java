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

/**
 *
 * @author jomav
 */
@Entity
@Table(name = "CL_P_PROCEEDINGS")
@NamedQueries({
    @NamedQuery(name = "PProceedings.findAll", query = "SELECT p FROM PProceedings p"),
    @NamedQuery(name = "PProceedings.findByProceedingId", query = "SELECT p FROM PProceedings p WHERE p.ppProceedings.psId = :ProceedingId"),
    @NamedQuery(name = "PProceedings.findByPpId", query = "SELECT p FROM PProceedings p WHERE p.ppId = :ppId")})
public class PProceedings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PP_ID")
    private Integer ppId;
    @NotNull
    @JoinColumn(name = "PP_PERSONALBACK", referencedColumnName = "PB_ID")
    @ManyToOne(optional = false)
    private Personalbackground ppPersonalback;
    @JoinColumn(name = "PP_PROCEEDINGS", referencedColumnName = "PS_ID")
    @ManyToOne(optional = false)
    private Proceedings ppProceedings;

    public PProceedings() {
    }

    public PProceedings(Integer ppId) {
        this.ppId = ppId;
    }

    public PProceedings(PProceedingsDto PProceedings) {
        this.ppId = PProceedings.getPpId();
        update(PProceedings);
    }
    public void update(PProceedingsDto PProceedings) {
        ppPersonalback = PProceedings.getPpPersonalback();
        ppProceedings = PProceedings.getPpProceedings();
    }

    public Integer getPpId() {
        return ppId;
    }

    public void setPpId(Integer ppId) {
        this.ppId = ppId;
    }

    public Personalbackground getPpPersonalback() {
        return ppPersonalback;
    }

    public void setPpPersonalback(Personalbackground ppPersonalback) {
        this.ppPersonalback = ppPersonalback;
    }

    public Proceedings getPpProceedings() {
        return ppProceedings;
    }

    public void setPpProceedings(Proceedings ppProceedings) {
        this.ppProceedings = ppProceedings;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ppId != null ? ppId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PProceedings)) {
            return false;
        }
        PProceedings other = (PProceedings) object;
        if ((this.ppId == null && other.ppId != null) || (this.ppId != null && !this.ppId.equals(other.ppId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.PProceedings[ ppId=" + ppId + " ]";
    }

}
