/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dilan
 */
public class DiseaseDto {
     
    public SimpleIntegerProperty dsId;
    public SimpleStringProperty dsName;

    public DiseaseDto() {
        this.dsId = new SimpleIntegerProperty();
        this.dsName = new SimpleStringProperty();
    }

    public int getDsId() {
        return dsId.get();
    }

    public void setDsId(int dsId) {
        this.dsId.set(dsId);
    }

    public String getDsName() {
        return dsName.get();
    }

    public void setDsName(String dsName) {
        this.dsName.set(dsName);
    }
    
    
    
}
