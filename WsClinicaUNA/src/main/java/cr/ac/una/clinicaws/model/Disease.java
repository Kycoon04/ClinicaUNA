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
import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_DISEASE")
@NamedQueries({
    @NamedQuery(name = "Disease.findAll", query = "SELECT d FROM Disease d"),
    @NamedQuery(name = "Disease.findByDsId", query = "SELECT d FROM Disease d WHERE d.dsId = :dsId"),
    @NamedQuery(name = "Disease.findByDsName", query = "SELECT d FROM Disease d WHERE d.dsName = :dsName")})
public class Disease implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DS_ID")
    private Integer dsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DS_NAME")
    private String dsName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fbDisease")
    private List<Familybackground> familybackgroundList;

    public Disease() {
    }

    public Disease(Integer dsId) {
        this.dsId = dsId;
    }

    public Disease(Integer dsId, String dsName) {
        this.dsId = dsId;
        this.dsName = dsName;
    }

    public Integer getDsId() {
        return dsId;
    }

    public void setDsId(Integer dsId) {
        this.dsId = dsId;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public List<Familybackground> getFamilybackgroundList() {
        return familybackgroundList;
    }

    public void setFamilybackgroundList(List<Familybackground> familybackgroundList) {
        this.familybackgroundList = familybackgroundList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dsId != null ? dsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disease)) {
            return false;
        }
        Disease other = (Disease) object;
        if ((this.dsId == null && other.dsId != null) || (this.dsId != null && !this.dsId.equals(other.dsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Disease[ dsId=" + dsId + " ]";
    }
    
}
