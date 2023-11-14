/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.util.List;

/**
 *
 * @author jomav
 */
public class ExcelDto {
    private ParametersDto parametersDto;
    private List<EmailDto> emailDto;
    private List<ParametersSqlDto> parametersSqlDto;
    
    public ExcelDto(ParametersDto parametersDto, List<EmailDto> emailDto,List<ParametersSqlDto> sqlDto) {
        this.parametersDto = parametersDto;
        this.emailDto = emailDto;
        this.parametersSqlDto = sqlDto;
    }

    public List<ParametersSqlDto> getParametersSqlDto() {
        return parametersSqlDto;
    }

    public void setParametersSqlDto(List<ParametersSqlDto> parametersSqlDto) {
        this.parametersSqlDto = parametersSqlDto;
    }

    public ParametersDto getParametersDto() {
        return parametersDto;
    }

    public void setParametersDto(ParametersDto parametersDto) {
        this.parametersDto = parametersDto;
    }

    public List<EmailDto> getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(List<EmailDto> emailDto) {
        this.emailDto = emailDto;
    }
}
