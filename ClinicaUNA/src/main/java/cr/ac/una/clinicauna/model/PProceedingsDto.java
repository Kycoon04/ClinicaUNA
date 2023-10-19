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
public class PProceedingsDto {
    public SimpleIntegerProperty ppId;
    public PersonalbackgroundDto ppPersonalback;
    public ProceedingsDto ppProceedings;

    public PProceedingsDto() {
        this.ppId= new SimpleIntegerProperty();
        this.ppPersonalback= new PersonalbackgroundDto();
        this.ppProceedings= new ProceedingsDto();
    }

    public Integer getPpId() {
        return ppId.get();
    }

    public void setPpId(Integer ppId) {
        this.ppId.set(ppId);
    }

    public PersonalbackgroundDto getPpPersonalback() {
        return ppPersonalback;
    }

    public void setPpPersonalback(PersonalbackgroundDto ppPersonalback) {
        this.ppPersonalback = ppPersonalback;
    }

    public ProceedingsDto getPpProceedings() {
        return ppProceedings;
    }

    public void setPpProceedings(ProceedingsDto ppProceedings) {
        this.ppProceedings = ppProceedings;
    }
    
    
    
}
