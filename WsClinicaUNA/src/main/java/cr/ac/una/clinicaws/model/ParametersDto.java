/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;
import java.time.LocalDate;

/**
 *
 * @author jomav
 */
public class ParametersDto {
    private Integer psId;
    private String psName;
    private String psDescription;
    private String psTitule;
    private String psTime;
    private String psQuery;
    private LocalDate psDateInit;
    private LocalDate psDateSig;

    public ParametersDto(Parameters parameters) {
        this.psId = parameters.getPsId();
        this.psName = parameters.getPsName();
        this.psDescription = parameters.getPsDescription();
        this.psTitule = parameters.getPsTitule();
        this.psTime = parameters.getPsTime();
        this.psDateInit = parameters.getPsDateInit();
        this.psDateSig = parameters.getPsDateSig();
        this.psQuery = parameters.getPsQuery();
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

    public ParametersDto() {
    }

    public String getPsQuery() {
        return psQuery;
    }

    public void setPsQuery(String psQuery) {
        this.psQuery = psQuery;
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
    
    
    
}
