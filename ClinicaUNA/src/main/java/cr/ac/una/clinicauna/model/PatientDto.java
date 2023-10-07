/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jomav
 */
public class PatientDto {

    public SimpleStringProperty ptName;
    public SimpleStringProperty ptPlastname;
    public SimpleStringProperty ptSlastname;
    public SimpleStringProperty ptIdentification;
    public SimpleStringProperty ptGender;
    public SimpleStringProperty ptEmail;
    public SimpleIntegerProperty ptId;
    public Date ptBirthdate;

    public PatientDto() {
        ptName = new SimpleStringProperty();
        ptPlastname = new SimpleStringProperty();
        ptSlastname = new SimpleStringProperty();
        ptIdentification = new SimpleStringProperty();
        ptGender = new SimpleStringProperty();
        ptEmail = new SimpleStringProperty();
        ptId = new SimpleIntegerProperty();
    }

    public String getPtName() {
        return ptName.get();
    }

    public void setPtName(String ptName) {
        this.ptName.set(ptName);
    }

    public String getPtPlastname() {
        return ptPlastname.get();
    }

    public void setPtPlastname(String ptPlastname) {
        this.ptPlastname.set(ptPlastname);
    }

    public String getPtSlastname() {
        return ptSlastname.get();
    }

    public void setPtSlastname(String ptSlastname) {
        this.ptSlastname.set(ptSlastname);
    }

    public String getPtIdentification() {
        return ptIdentification.get();
    }

    public void setPtIdentification(String ptIdentification) {
        this.ptIdentification.set(ptIdentification);
    }

    public String getPtGender() {
        return ptGender.get();
    }

    public void setPtGender(String ptGender) {
        this.ptGender.set(ptGender);
    }

    public String getPtEmail() {
        return ptEmail.get();
    }

    public void setPtEmail(String ptEmail) {
        this.ptEmail.set(ptEmail);
    }

    public int getPtId() {
        return ptId.get();
    }

    public void setPtId(int ptId) {
        this.ptId.set(ptId);
    }

    public Date getPtBirthdate() {
        return ptBirthdate;
    }

    public void setPtBirthdate(Date ptBirthdate) {
        this.ptBirthdate = ptBirthdate;
    }
}
