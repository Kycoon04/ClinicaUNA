/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jomav
 */
@Entity
@Table(name = "CL_EMAIL")
@NamedQueries({
    @NamedQuery(name = "Emails.findAll", query = "SELECT e FROM Emails e"),
    @NamedQuery(name = "Emails.findByElId", query = "SELECT e FROM Emails e WHERE e.elId = :elId"),
    @NamedQuery(name = "Emails.findBySqlId", query = "SELECT e FROM Emails e WHERE e.elIdsql.psId = :psId"),
    @NamedQuery(name = "Emails.findByElEmail", query = "SELECT e FROM Emails e WHERE e.elEmail = :elEmail")})
public class Emails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EL_ID")
    private Integer elId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "EL_EMAIL")
    private String elEmail;
    @JoinColumn(name = "EL_IDSQL", referencedColumnName = "PS_ID")
    @ManyToOne(optional = false)
    private Parameters elIdsql;

    public Emails() {
    }
    public Emails(EmailDto emailDto) {
        this.elId = emailDto.getElId();
        update(emailDto);
    }

    public void update(EmailDto email) {
        this.elId = email.getElId();
        this.elEmail = email.getElEmail();
        this.elIdsql = email.getElIdsql();
    }
    public Emails(Integer elId) {
        this.elId = elId;
    }

    public Emails(Integer elId, String elEmail) {
        this.elId = elId;
        this.elEmail = elEmail;
    }

    public Integer getElId() {
        return elId;
    }

    public void setElId(Integer elId) {
        this.elId = elId;
    }

    public String getElEmail() {
        return elEmail;
    }

    public void setElEmail(String elEmail) {
        this.elEmail = elEmail;
    }

    public Parameters getElIdsql() {
        return elIdsql;
    }

    public void setElIdsql(Parameters elIdsql) {
        this.elIdsql = elIdsql;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elId != null ? elId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emails)) {
            return false;
        }
        Emails other = (Emails) object;
        if ((this.elId == null && other.elId != null) || (this.elId != null && !this.elId.equals(other.elId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Emails[ elId=" + elId + " ]";
    }
    
}
