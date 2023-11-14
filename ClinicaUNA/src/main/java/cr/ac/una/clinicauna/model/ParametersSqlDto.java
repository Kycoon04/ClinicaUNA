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
public class ParametersSqlDto {

    private SimpleIntegerProperty psqlId;
    private SimpleStringProperty psqlValue;
    private SimpleStringProperty psqlType;
    private SimpleStringProperty psqlIdent;
    private ParametersDto psqlIdparam;

    public ParametersSqlDto() {
        this.psqlId = new SimpleIntegerProperty();
        this.psqlValue = new SimpleStringProperty();
        this.psqlType = new SimpleStringProperty();
        this.psqlIdent = new SimpleStringProperty();
        this.psqlIdparam = new ParametersDto();
    }

    public Integer getPsqlId() {
        return psqlId.get();
    }

    public void setPsqlId(Integer psqlId) {
        this.psqlId.set(psqlId);
    }

    public String getPsqlValue() {
        return psqlValue.get();
    }

    public void setPsqlValue(String psqlValue) {
        this.psqlValue.set(psqlValue);
    }

    public String getPsqlIdent() {
        return psqlIdent.get();
    }

    public void setPsqlIdent(String psqlValue) {
        this.psqlIdent.set(psqlValue);
    }

    public String getPsqlType() {
        return psqlType.get();
    }

    public void setPsqlType(String psqlType) {
        this.psqlType.set(psqlType);
    }

    public ParametersDto getPsqlIdparam() {
        return psqlIdparam;
    }

    public void setPsqlIdparam(ParametersDto psqlIdparam) {
        this.psqlIdparam = psqlIdparam;
    }
}
