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
import jakarta.validation.constraints.Size;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_FAMILYBACKGROUND")
@NamedQueries({
    @NamedQuery(name = "Familybackground.findAll", query = "SELECT f FROM Familybackground f"),
    @NamedQuery(name = "Familybackground.findByFbId", query = "SELECT f FROM Familybackground f WHERE f.fbId = :fbId"),
    @NamedQuery(name = "Familybackground.findByFbRelationship", query = "SELECT f FROM Familybackground f WHERE f.fbRelationship = :fbRelationship")})
public class Familybackground implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FB_ID")
    private Integer fbId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "FB_RELATIONSHIP")
    private String fbRelationship;
   /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "fpFamilyback")
    private List<FProceedings> fProceedingsList;*/
    @JoinColumn(name = "FB_DISEASE", referencedColumnName = "DS_ID")
    @ManyToOne(optional = false)
    private Disease fbDisease;

    public Familybackground() {
    }
    
     public Familybackground(FamilybackgroundDto familybackgroundDto) {
       this.fbId = familybackgroundDto.getFbId();
        update(familybackgroundDto);
    }
    public void update(FamilybackgroundDto familybackgroundDto) {   
      
        this.fbDisease = familybackgroundDto.getFbDisease();
        this.fbRelationship = familybackgroundDto.getFbRelationship();    
    }

    public Familybackground(Integer fbId) {
        this.fbId = fbId;
    }

    public Familybackground(Integer fbId, String fbRelationship) {
        this.fbId = fbId;
        this.fbRelationship = fbRelationship;
    }

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public String getFbRelationship() {
        return fbRelationship;
    }

    public void setFbRelationship(String fbRelationship) {
        this.fbRelationship = fbRelationship;
    }

  /*  public List<FProceedings> getFProceedingsList() {
        return fProceedingsList;
    }

    public void setFProceedingsList(List<FProceedings> fProceedingsList) {
        this.fProceedingsList = fProceedingsList;
    }*/

    public Disease getFbDisease() {
        return fbDisease;
    }

    public void setFbDisease(Disease fbDisease) {
        this.fbDisease = fbDisease;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fbId != null ? fbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familybackground)) {
            return false;
        }
        Familybackground other = (Familybackground) object;
        if ((this.fbId == null && other.fbId != null) || (this.fbId != null && !this.fbId.equals(other.fbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Familybackground[ fbId=" + fbId + " ]";
    }
    
}
