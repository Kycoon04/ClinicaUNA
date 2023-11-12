/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;


/**
 *
 * @author jomav
 */
public class SqlDto {
    private Integer sqlId;
    private String sqlQuery;
    private Parameters sqlParam;

    public SqlDto(Sql sql) {
        this.sqlId = sql.getSqlId();
        this.sqlQuery = sql.getSqlQuery();
        this.sqlParam = sql.getSqlParam();
    }

    public SqlDto() {
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
    
    
    
}
