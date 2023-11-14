/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jomav
 */
public class EmailDto {
    
    private SimpleIntegerProperty elId;
    private SimpleStringProperty elEmail;
    private ParametersDto elIdsql;

    public EmailDto() {
        this.elId = new SimpleIntegerProperty();
        this.elEmail = new SimpleStringProperty();
        this.elIdsql = new ParametersDto();
    }

    public Integer getElId() {
        return elId.get();
    }

    public void setElId(Integer elId) {
        this.elId.set(elId);
    }

    public String getElEmail() {
        return elEmail.get();
    }

    public void setElEmail(String elEmail) {
        this.elEmail.set(elEmail);
    }

    public ParametersDto getElIdsql() {
        return elIdsql;
    }

    public void setElIdsql(ParametersDto elIdsql) {
        this.elIdsql = elIdsql;
    }
    
    
}
