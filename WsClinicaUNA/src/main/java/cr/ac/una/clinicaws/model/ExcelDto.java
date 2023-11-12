/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.util.List;

/**
 *
 * @author jomav
 */
public class ExcelDto {
    private ParametersDto parametersDto;
    private SqlDto sqlDto;
    private List<EmailDto> emailDto;

    public ExcelDto() {
    }

    public ExcelDto(ParametersDto parametersDto, SqlDto sqlDto, List<EmailDto> emailDto) {
        this.parametersDto = parametersDto;
        this.sqlDto = sqlDto;
        this.emailDto = emailDto;
    }

    public ParametersDto getParametersDto() {
        return parametersDto;
    }

    public void setParametersDto(ParametersDto parametersDto) {
        this.parametersDto = parametersDto;
    }

    public SqlDto getSqlDto() {
        return sqlDto;
    }

    public void setSqlDto(SqlDto sqlDto) {
        this.sqlDto = sqlDto;
    }

    public List<EmailDto> getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(List<EmailDto> emailDto) {
        this.emailDto = emailDto;
    }
}
