/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author dilan
 */
public class ProceedingsDto {
     private SimpleIntegerProperty psId;
     private PatientDto psPatient;

    public ProceedingsDto() {
        this.psId = new SimpleIntegerProperty();
        this.psPatient = new PatientDto();
    }

    public int getPsId() {
        return psId.get();
    }

    public void setPsId(int psId) {
        this.psId.set(psId);
    }

    public PatientDto getPsPatient() {
        return psPatient;
    }

    public void setPsPatient(PatientDto psPatient) {
        this.psPatient = psPatient;
    } 
}
