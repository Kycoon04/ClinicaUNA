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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jomav
 */
@Entity
@Table(name = "CL_PARAMETERS")
@NamedQueries({
    @NamedQuery(name = "Parameters.findAll", query = "SELECT p FROM Parameters p"),
    @NamedQuery(name = "Parameters.findByPsId", query = "SELECT p FROM Parameters p WHERE p.psId = :psId"),
    @NamedQuery(name = "Parameters.findByPsName", query = "SELECT p FROM Parameters p WHERE p.psName = :psName"),
    @NamedQuery(name = "Parameters.findByPsDescription", query = "SELECT p FROM Parameters p WHERE p.psDescription = :psDescription"),
    @NamedQuery(name = "Parameters.findByPsTitule", query = "SELECT p FROM Parameters p WHERE p.psTitule = :psTitule"),
    @NamedQuery(name = "Parameters.findByPsTime", query = "SELECT p FROM Parameters p WHERE p.psTime = :psTime"),
    @NamedQuery(name = "Parameters.findByPsQuery", query = "SELECT p FROM Parameters p WHERE p.psQuery = :psQuery"),
    @NamedQuery(name = "Parameters.findByPsDateInit", query = "SELECT p FROM Parameters p WHERE p.psDateInit = :psDateInit"),
    @NamedQuery(name = "Parameters.findByPsDateSig", query = "SELECT p FROM Parameters p WHERE p.psDateSig = :psDateSig")})
public class Parameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PS_ID")
    private Integer psId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PS_NAME")
    private String psName;
    @Size(max = 100)
    @Column(name = "PS_DESCRIPTION")
    private String psDescription;
    @Size(max = 30)
    @Column(name = "PS_TITULE")
    private String psTitule;
    @Size(max = 30)
    @Column(name = "PS_TIME")
    private String psTime;
    @Size(max = 1000)
    @Column(name = "PS_QUERY")
    private String psQuery;
    @Column(name = "PS_DATE_INIT")
    private LocalDate psDateInit;
    @Column(name = "PS_DATE_SIG")
    private LocalDate psDateSig;

    public Parameters() {
    }
    public Parameters(ParametersDto parametersDto) {
        this.psId = parametersDto.getPsId();
        update(parametersDto);
        
    }
    public void update(ParametersDto parameters) {
        this.psId = parameters.getPsId();
        this.psName = parameters.getPsName();
        this.psDescription = parameters.getPsDescription();
        this.psTitule = parameters.getPsTitule();
        this.psTime = parameters.getPsTime();      
        this.psDateInit = parameters.getPsDateInit();
        this.psDateSig = parameters.getPsDateSig();
        this.psQuery = parameters.getPsQuery();
    }
    public Parameters(Integer psId) {
        this.psId = psId;
    }

    public Parameters(Integer psId, String psName) {
        this.psId = psId;
        this.psName = psName;
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getPsDescription() {
        return psDescription;
    }

    public void setPsDescription(String psDescription) {
        this.psDescription = psDescription;
    }

    public String getPsTitule() {
        return psTitule;
    }

    public void setPsTitule(String psTitule) {
        this.psTitule = psTitule;
    }

    public String getPsTime() {
        return psTime;
    }

    public void setPsTime(String psTime) {
        this.psTime = psTime;
    }

    public String getPsQuery() {
        return psQuery;
    }

    public void setPsQuery(String psQuery) {
        this.psQuery = psQuery;
    }

    public LocalDate getPsDateInit() {
        return psDateInit;
    }

    public void setPsDateInit(LocalDate psDateInit) {
        this.psDateInit = psDateInit;
    }

    public LocalDate getPsDateSig() {
        return psDateSig;
    }

    public void setPsDateSig(LocalDate psDateSig) {
        this.psDateSig = psDateSig;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (psId != null ? psId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameters)) {
            return false;
        }
        Parameters other = (Parameters) object;
        if ((this.psId == null && other.psId != null) || (this.psId != null && !this.psId.equals(other.psId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Parameters[ psId=" + psId + " ]";
    }
    
}
