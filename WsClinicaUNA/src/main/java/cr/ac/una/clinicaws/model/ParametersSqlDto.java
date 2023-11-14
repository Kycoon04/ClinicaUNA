/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author jomav
 */
public class ParametersSqlDto {
    
    private Integer psqlId;
    private String psqlValue;
    private String psqlType;
    private Parameters psqlIdparam;
    private String psqlIdent;
    public ParametersSqlDto() {
    }

    public ParametersSqlDto(ParametersSql parametersSql) {
        this.psqlId = parametersSql.getPsqlId();
        this.psqlValue = parametersSql.getPsqlValue();
        this.psqlType = parametersSql.getPsqlType();
        this.psqlIdparam = parametersSql.getPsqlIdparam();
        this.psqlIdent = parametersSql.getPsqlIdent();
    }

    public String getPsqlIdent() {
        return psqlIdent;
    }

    public void setPsqlIdent(String psqlIdent) {
        this.psqlIdent = psqlIdent;
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
    
    
}
