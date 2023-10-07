/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dilan
 */
public class UserDto {

    private  SimpleStringProperty usName;
    private  SimpleStringProperty usPlastname;
    private  SimpleStringProperty usSlastname;
    private  SimpleStringProperty usIdentification;
    private  ObjectProperty<String> usType;
    private  ObjectProperty<String> usLenguage;
    private  ObjectProperty<String> usState;
    private  SimpleStringProperty usUsername;
    private  SimpleStringProperty usPassword;
    private  SimpleStringProperty usEmail;
    private  ObjectProperty<String> usRecover;
    private  SimpleStringProperty usTemppassword;
    private  SimpleStringProperty usId;
    private  SimpleStringProperty token;
    private  SimpleStringProperty usCode;

    public UserDto() {
        this.usName = new SimpleStringProperty();
         this.usPlastname = new SimpleStringProperty();
        this.usSlastname = new SimpleStringProperty();
        this.usIdentification = new SimpleStringProperty();
        this.usType = new SimpleObjectProperty();
        this.usLenguage = new SimpleObjectProperty();
        this.usState = new SimpleObjectProperty();
        this.usUsername = new SimpleStringProperty();
        this.usPassword = new SimpleStringProperty();
        this.usEmail = new SimpleStringProperty();
        this.usRecover = new SimpleObjectProperty();
        this.usTemppassword = new SimpleStringProperty();
        this.usId = new SimpleStringProperty();
        this.token = new SimpleStringProperty();
        this.usCode = new SimpleStringProperty();
    }

    public String getUsName() {
        return usName.get();
    }

    public void setUsName(String usName) {
        this.usName.set(usName);
    }


    public String getUsSlastname() {
        return usSlastname.get();
    }

    public void setUsSlastname(String usSlastname) {
        this.usSlastname.set(usSlastname);
    }

    public String getUsIdentification() {
        return usIdentification.get();
    }

    public String getUsPlastname() {
        return usPlastname.get();
    }

    public void setUsPlastname(String usPlastname) {
        this.usPlastname.set(usPlastname); 
    }

    public void setUsIdentification(String usIdentification) {
        this.usIdentification.set(usIdentification);
    }

    public String getUsType() {
        return usType.get();
    }

    public void setUsType(String usType) {
        this.usType.set(usType);
    }

    public String getUsLenguage() {
        return usLenguage.get();
    }

    public void setUsLenguage(String usLenguage) {
        this.usLenguage.set(usLenguage);
    }

    public String getUsState() {
        return usState.get();
    }

    public void setUsState(String usState) {
        this.usState.set(usState);
    }

    public String getUsUsername() {
        return usUsername.get();
    }

    public void setUsUsername(String usUsername) {
        this.usUsername.set(usUsername);
    }

    public String getUsPassword() {
        return usPassword.get();
    }

    public void setUsPassword(String usPassword) {
        this.usPassword.set(usPassword);
    }

    public String getUsEmail() {
        return usEmail.get();
    }

    public void setUsEmail(String usEmail) {
        this.usEmail.set(usEmail);
    }

    public String getUsRecover() {
        return usRecover.get();
    }

    public void setUsRecover(String usRecover) {
        this.usRecover.set(usRecover);
    }

    public String getUsTemppassword() {
        return usTemppassword.get();
    }

    public void setUsTemppassword(String usTemppassword) {
        this.usTemppassword.set(usTemppassword);
    }

    public String getUsId() {
        return usId.get();
    }

    public void setUsId(String usId) {
        this.usId.set(usId);
    }

    public String getToken() {
        return token.get();
    }

    public void setToken(String token) {
        this.token.set(token);
    }

    public String getUsCode() {
        return usCode.get();
    }

    public void setUsCode(String usCode) {
        this.usCode.set(usCode);
    }

}
