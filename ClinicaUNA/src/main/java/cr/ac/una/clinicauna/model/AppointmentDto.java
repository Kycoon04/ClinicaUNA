/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jomav
 */
public class AppointmentDto {

    public SimpleIntegerProperty atId;
    public SimpleStringProperty atReason;
    public SimpleLongProperty atTelephone;
    public SimpleStringProperty atEmail;
    public SimpleStringProperty atState;
    public PatientDto atPatient;
    public UserDto atUserregister;

    public AppointmentDto() {
        this.atId = new SimpleIntegerProperty();
        this.atReason = new SimpleStringProperty();
        this.atTelephone = new SimpleLongProperty();
        this.atEmail = new SimpleStringProperty();
        this.atState = new SimpleStringProperty();
        this.atUserregister = new UserDto();
    }

    public int getAtId() {
        return atId.get();
    }

    public void setAtId(int atId) {
        this.atId.set(atId);
    }

    public String getAtReason() {
        return atReason.get();
    }

    public void setAtReason(String atReason) {
        this.atReason.set(atReason);
    }

    public Long getAtTelephone() {
        return atTelephone.get();
    }

    public void setAtTelephone(Long atTelephone) {
        this.atTelephone.set(atTelephone);
    }

    public String getAtEmail() {
        return atEmail.get();
    }

    public void setAtEmail(String atEmail) {
        this.atEmail.set(atEmail);
    }

    public String getAtState() {
        return atState.get();
    }

    public void setAtState(String atState) {
        this.atState.set(atState);
    }

    public PatientDto getAtPatient() {
        return atPatient;
    }

    public void setAtPatient(PatientDto atPatient) {
        this.atPatient = atPatient;
    }

    public UserDto getAtUserregister() {
        return atUserregister;
    }

    public void setAtUserregister(UserDto atUserregister) {
        this.atUserregister = atUserregister;
    }
}
