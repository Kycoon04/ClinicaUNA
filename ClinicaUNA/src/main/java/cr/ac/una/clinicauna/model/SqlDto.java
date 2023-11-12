/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jomav
 */
public class SqlDto {

    private SimpleIntegerProperty sqlId;
    private SimpleStringProperty sqlQuery;
    private ParametersDto sqlParam;

    public SqlDto() {
        this.sqlId = new SimpleIntegerProperty();
        this.sqlQuery = new SimpleStringProperty();
        this.sqlParam = new ParametersDto();
    }

    public Integer getSqlId() {
        return sqlId.get();
    }

    public void setSqlId(Integer sqlId) {
        this.sqlId.set(sqlId);
    }

    public String getSqlQuery() {
        return sqlQuery.get();
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery.set(sqlQuery);
    }

    public ParametersDto getSqlParam() {
        return sqlParam;
    }

    public void setSqlParam(ParametersDto sqlParam) {
        this.sqlParam = sqlParam;
    }

}
