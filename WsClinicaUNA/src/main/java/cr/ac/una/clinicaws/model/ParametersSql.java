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
@Table(name = "CL_PARAMETERS_SQL")
@NamedQueries({
    @NamedQuery(name = "ParametersSql.findAll", query = "SELECT p FROM ParametersSql p"),
    @NamedQuery(name = "ParametersSql.findByPsqlId", query = "SELECT p FROM ParametersSql p WHERE p.psqlId = :psqlId"),
    @NamedQuery(name = "ParametersSql.findByPsqlValue", query = "SELECT p FROM ParametersSql p WHERE p.psqlValue = :psqlValue"),
    @NamedQuery(name = "ParametersSql.findByPsqlType", query = "SELECT p FROM ParametersSql p WHERE p.psqlType = :psqlType")})
public class ParametersSql implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PSQL_ID")
    private Integer psqlId;
    @Size(max = 30)
    @Column(name = "PSQL_VALUE")
    private String psqlValue;
    @Size(max = 30)
    @Column(name = "PSQL_TYPE")
    private String psqlType;
    @JoinColumn(name = "PSQL_IDPARAM", referencedColumnName = "PS_ID")
    @ManyToOne
    private Parameters psqlIdparam;

    public ParametersSql() {
    }

    public ParametersSql(Integer psqlId) {
        this.psqlId = psqlId;
    }

    public Integer getPsqlId() {
        return psqlId;
    }

    public void setPsqlId(Integer psqlId) {
        this.psqlId = psqlId;
    }

    public String getPsqlValue() {
        return psqlValue;
    }

    public void setPsqlValue(String psqlValue) {
        this.psqlValue = psqlValue;
    }

    public String getPsqlType() {
        return psqlType;
    }

    public void setPsqlType(String psqlType) {
        this.psqlType = psqlType;
    }

    public Parameters getPsqlIdparam() {
        return psqlIdparam;
    }

    public void setPsqlIdparam(Parameters psqlIdparam) {
        this.psqlIdparam = psqlIdparam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psqlId != null ? psqlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametersSql)) {
            return false;
        }
        ParametersSql other = (ParametersSql) object;
        if ((this.psqlId == null && other.psqlId != null) || (this.psqlId != null && !this.psqlId.equals(other.psqlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.ParametersSql[ psqlId=" + psqlId + " ]";
    }
    
}
