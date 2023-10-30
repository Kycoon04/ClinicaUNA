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
public class PersonalbackgroundDto {


    public SimpleIntegerProperty pbId;
    public SimpleStringProperty pbType;
    public SimpleStringProperty pbContext;
    public SimpleIntegerProperty pbFilecode;

    public PersonalbackgroundDto() {
        pbId= new SimpleIntegerProperty();
        pbType= new SimpleStringProperty();
        pbContext= new SimpleStringProperty();
        pbFilecode= new SimpleIntegerProperty();
    }

    public int getPbFilecode() {
        return pbFilecode.get();
    }

    public void setPbFilecode(int pbFilecode) {
        this.pbFilecode.set(pbFilecode);
    }

    
    public Integer getPbId() {
        return pbId.get();
    }

    public void setPbId(Integer pbId) {
        this.pbId.set(pbId);
    }

    public String getPbType() {
        return pbType.get();
    }

    public void setPbType(String pbType) {
        this.pbType.set(pbType);
    }

    public String getPbContext() {
        return pbContext.get();
    }

    public void setPbContext(String pbContext) {
        this.pbContext.set(pbContext);
    }

    
}
