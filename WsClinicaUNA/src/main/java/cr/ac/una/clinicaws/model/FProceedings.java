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
 * @author dilan
 */
@Entity
@Table(name = "CL_F_PROCEEDINGS")
@NamedQueries({
    @NamedQuery(name = "FProceedings.findAll", query = "SELECT f FROM FProceedings f"),
    @NamedQuery(name = "FProceedings.findByFpId", query = "SELECT f FROM FProceedings f WHERE f.fpId = :fpId")})
public class FProceedings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FP_ID")
    private Integer fpId;
    @JoinColumn(name = "FP_FAMILYBACK", referencedColumnName = "FB_ID")
    @ManyToOne(optional = false)
    private Familybackground fpFamilyback;
    @JoinColumn(name = "FP_PROCEEDINGS", referencedColumnName = "PS_ID")
    @ManyToOne(optional = false)
    private Proceedings fpProceedings;

    public FProceedings() {
    }

    public FProceedings(FProceedingsDto fProceedingsDto) {
        this.fpId = fProceedingsDto.getFpId();
        update(fProceedingsDto);
    }

    public void update(FProceedingsDto fProceedingsDto) {

        this.fpProceedings = fProceedingsDto.getFpProceedings();
        this.fpFamilyback = fProceedingsDto.getFpFamilyback();
        
    }

    public FProceedings(Integer fpId) {
        this.fpId = fpId;
    }

    public Integer getFpId() {
        return fpId;
    }

    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }

    public Familybackground getFpFamilyback() {
        return fpFamilyback;
    }

    public void setFpFamilyback(Familybackground fpFamilyback) {
        this.fpFamilyback = fpFamilyback;
    }

    public Proceedings getFpProceedings() {
        return fpProceedings;
    }

    public void setFpProceedings(Proceedings fpProceedings) {
        this.fpProceedings = fpProceedings;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fpId != null ? fpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FProceedings)) {
            return false;
        }
        FProceedings other = (FProceedings) object;
        if ((this.fpId == null && other.fpId != null) || (this.fpId != null && !this.fpId.equals(other.fpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.FProceedings[ fpId=" + fpId + " ]";
    }

}
