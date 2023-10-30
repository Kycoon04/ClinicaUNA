/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_PERSONALBACKGROUND")
@NamedQueries({
    @NamedQuery(name = "Personalbackground.findAll", query = "SELECT p FROM Personalbackground p"),
    @NamedQuery(name = "Personalbackground.findByPbId", query = "SELECT p FROM Personalbackground p WHERE p.pbId = :pbId"),
    @NamedQuery(name = "Personalbackground.findByPbType", query = "SELECT p FROM Personalbackground p WHERE p.pbType = :pbType"),
    @NamedQuery(name = "Personalbackground.findByPbContext", query = "SELECT p FROM Personalbackground p WHERE p.pbContext = :pbContext"),
    @NamedQuery(name = "Personalbackground.findByPbFilecode", query = "SELECT p FROM Personalbackground p WHERE p.pbFilecode = :pbFilecode")})
public class Personalbackground implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PB_ID")
    private Integer pbId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PB_TYPE")
    private String pbType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PB_CONTEXT")
    private String pbContext;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PB_FILECODE")
    private int pbFilecode;

    public Personalbackground() {
    }

    public Personalbackground(Integer pbId) {
        this.pbId = pbId;
    }

    public Personalbackground(Integer pbId, String pbType, String pbContext, int pbFilecode) {
        this.pbId = pbId;
        this.pbType = pbType;
        this.pbContext = pbContext;
        this.pbFilecode = pbFilecode;
    }

    public Personalbackground(PersonalbackgroundDto personalbackground) {
        this.pbId = personalbackground.getPbId();
        update(personalbackground);
    }

    public void update(PersonalbackgroundDto personalbackground) {
        pbType = personalbackground.getPbType();
        pbContext = personalbackground.getPbContext();
        pbFilecode= personalbackground.getPbFilecode();
    }

    public Integer getPbId() {
        return pbId;
    }

    public void setPbId(Integer pbId) {
        this.pbId = pbId;
    }

    public String getPbType() {
        return pbType;
    }

    public void setPbType(String pbType) {
        this.pbType = pbType;
    }

    public String getPbContext() {
        return pbContext;
    }

    public void setPbContext(String pbContext) {
        this.pbContext = pbContext;
    }

    public int getPbFilecode() {
        return pbFilecode;
    }

    public void setPbFilecode(int pbFilecode) {
        this.pbFilecode = pbFilecode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pbId != null ? pbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personalbackground)) {
            return false;
        }
        Personalbackground other = (Personalbackground) object;
        if ((this.pbId == null && other.pbId != null) || (this.pbId != null && !this.pbId.equals(other.pbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Personalbackground[ pbId=" + pbId + " ]";
    }

}
