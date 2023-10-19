/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Dilan
 */
public class FProceedingsDto {
    public SimpleIntegerProperty fpId;
    public FamilybackgroundDto fpFamilyback;
    public ProceedingsDto fpProceedings;

    public FProceedingsDto() {
        this.fpId= new SimpleIntegerProperty();
        this.fpFamilyback= new FamilybackgroundDto();
        this.fpProceedings= new ProceedingsDto();
        
    }

    public Integer getFpId() {
        return fpId.get();
    }

    public void setFpId(Integer fpId) {
        this.fpId.set(fpId);
    }

    public FamilybackgroundDto getFpFamilyback() {
        return fpFamilyback;
    }

    public void setFpFamilyback(FamilybackgroundDto fpFamilyback) {
        this.fpFamilyback = fpFamilyback;
    }

    public ProceedingsDto getFpProceedings() {
        return fpProceedings;
    }

    public void setFpProceedings(ProceedingsDto fpProceedings) {
        this.fpProceedings = fpProceedings;
    }
    
    
    
}
