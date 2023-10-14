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
public class DoctorDto {

    public SimpleIntegerProperty drId;
    public SimpleIntegerProperty drCode;
    public SimpleIntegerProperty drLicense;
    public SimpleIntegerProperty drFol;
    public SimpleStringProperty drIniworking;
    public SimpleStringProperty drFinisworking;
    public SimpleStringProperty drBreak;
    public UserDto drUser;

    public DoctorDto() {
        this.drId = new SimpleIntegerProperty();
        this.drCode = new SimpleIntegerProperty();
        this.drLicense = new SimpleIntegerProperty();
        this.drFol = new SimpleIntegerProperty();
        this.drIniworking = new SimpleStringProperty();
        this.drFinisworking = new SimpleStringProperty();
        this.drBreak = new SimpleStringProperty();
    }

    public int getDrId() {
        return drId.get();
    }

    public void setDrId(int drId) {
        this.drId.set(drId);
    }

    public int getDrCode() {
        return drCode.get();
    }

    public void setDrCode(int drCode) {
        this.drCode.set(drCode);
    }

    public int getDrLicense() {
        return drLicense.get();
    }

    public void setDrLicense(int drLicense) {
        this.drLicense.set(drLicense);
    }

    public int getDrFol() {
        return drFol.get();
    }

    public void setDrFol(int drFol) {
        this.drFol.set(drFol);
    }

    public String getDrIniworking() {
        return drIniworking.get();
    }

    public void setDrIniworking(String drIniworking) {
        this.drIniworking.set(drIniworking);
    }

    public String getDrFinisworking() {
        return drFinisworking.get();
    }

    public void setDrFinisworking(String drFinisworking) {
        this.drFinisworking.set(drFinisworking);
    }

    public String getDrBreak() {
        return drBreak.get();
    }

    public void setDrBreak(String drBreak) {
        this.drBreak.set(drBreak);
    }

    public UserDto getDrUser() {
        return drUser;
    }

    public void setDrUser(UserDto drUser) {
        this.drUser = drUser;
    }
    public String getDoctorName(){
        return drUser.getUsName();
    }
    public String getDoctorPsurname(){
        return drUser.getUsPlastname();
    }

}
