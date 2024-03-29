/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Dilan
 */
public class FamilybackgroundDto {
    public SimpleIntegerProperty fbId;
    public SimpleStringProperty fbRelationship;
    public DiseaseDto fbDisease;
    public SimpleIntegerProperty fbFilecode;

    public FamilybackgroundDto() {
        this.fbDisease= new DiseaseDto();
        this.fbId= new SimpleIntegerProperty();
        this.fbRelationship = new SimpleStringProperty();
        this.fbFilecode= new SimpleIntegerProperty();
    }

    public int getFbFilecode() {
        return fbFilecode.get();
    }

    public void setFbFilecode(int fbFilecode) {
        this.fbFilecode.set(fbFilecode);
    }

    public Integer getFbId() {
        return fbId.get();
    }

    public void setFbId(Integer fbId) {
        this.fbId.set(fbId);
    }

    public String getFbRelationship() {
        return fbRelationship.get();
    }

    public void setFbRelationship(String fbRelationship) {
        this.fbRelationship.set(fbRelationship);
    }

    public DiseaseDto getFbDisease() {
        return fbDisease;
    }

    
    public String getFbDiseaseName() {
        return fbDisease.getDsName();
    }
    
    public void setFbDisease(DiseaseDto fbDisease) {
        this.fbDisease = fbDisease;
    }
    
    
    
    
}
