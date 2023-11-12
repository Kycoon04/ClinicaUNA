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
@Table(name = "CL_SQL")
@NamedQueries({
    @NamedQuery(name = "Sql.findAll", query = "SELECT s FROM Sql s"),
    @NamedQuery(name = "Sql.findBySqlId", query = "SELECT s FROM Sql s WHERE s.sqlId = :sqlId"),
    @NamedQuery(name = "Sql.findByParamId", query = "SELECT s FROM Sql s WHERE s.sqlParam.psId = :psId"),
    @NamedQuery(name = "Sql.findBySqlQuery", query = "SELECT s FROM Sql s WHERE s.sqlQuery = :sqlQuery")})
public class Sql implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SQL_ID")
    private Integer sqlId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "SQL_QUERY")
    private String sqlQuery;
    @JoinColumn(name = "SQL_PARAM", referencedColumnName = "PS_ID")
    @ManyToOne(optional = false)
    private Parameters sqlParam;

    public Sql() {
    }

    public Sql(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public Sql(SqlDto sqlDto) {
        this.sqlId = sqlDto.getSqlId();
        update(sqlDto);
    }

    public void update(SqlDto sql) {
        this.sqlId = sql.getSqlId();
        this.sqlQuery = sql.getSqlQuery();
        this.sqlParam = sql.getSqlParam();
    }

    public Sql(Integer sqlId, String sqlQuery) {
        this.sqlId = sqlId;
        this.sqlQuery = sqlQuery;
    }

    public Integer getSqlId() {
        return sqlId;
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId = sqlId;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public Parameters getSqlParam() {
        return sqlParam;
    }

    public void setSqlParam(Parameters sqlParam) {
        this.sqlParam = sqlParam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sqlId != null ? sqlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sql)) {
            return false;
        }
        Sql other = (Sql) object;
        if ((this.sqlId == null && other.sqlId != null) || (this.sqlId != null && !this.sqlId.equals(other.sqlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Sql[ sqlId=" + sqlId + " ]";
    }

}
