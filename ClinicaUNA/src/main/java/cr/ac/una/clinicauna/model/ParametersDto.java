/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jomav
 */
public class ParametersDto {

    private SimpleIntegerProperty psId;
    private SimpleStringProperty psName;
    private SimpleStringProperty psDescription;
    private SimpleStringProperty psTitule;
    private SimpleStringProperty psTime;
    private SimpleStringProperty psQuery;
    private LocalDate psDateInit;
    private LocalDate psDateSig;
    public ParametersDto() {
        this.psId = new SimpleIntegerProperty();
        this.psName = new SimpleStringProperty();
        this.psDescription = new SimpleStringProperty();
        this.psTitule = new SimpleStringProperty();
        this.psTime = new SimpleStringProperty();
        this.psQuery = new SimpleStringProperty();
    }

    public Integer getPsId() {
        return psId.get();
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

    public void setPsId(Integer emId) {
        this.psId.set(emId);
    }
    public String getPsQuery() {
        return psQuery.get();
    }

    public void setPsQuery(String psName) {
        this.psQuery.set(psName);
    }
    public String getPsName() {
        return psName.get();
    }

    public void setPsName(String psName) {
        this.psName.set(psName);
    }

    public String getPsDescription() {
        return psDescription.get();
    }

    public void setPsDescription(String psDescription) {
        this.psDescription.set(psDescription);
    }

    public String getPsTitule() {
        return psTitule.get();
    }

    public void setPsTitule(String psTitule) {
        this.psTitule.set(psTitule);
    }

    public String getPsTime() {
        return psTime.get();
    }

    public void setPsTime(String psTime) {
        this.psTime.set(psTime);
    }

    
    
    
}
