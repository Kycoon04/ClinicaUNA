/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jomav
 */
public class HistoryDto {
    
    private SimpleIntegerProperty htId;
    private LocalDate htDate;
    private LocalDate htDateFinal;
    private Short htSpaces;
    private SimpleStringProperty htIniworking;
    private SimpleStringProperty htFinisworking;
    private DoctorDto htDoctor;

    public HistoryDto() {
        this.htId = new SimpleIntegerProperty();
        this.htDoctor = new DoctorDto();
        this.htIniworking = new SimpleStringProperty();
        this.htFinisworking = new SimpleStringProperty();
    }
    
    public String getHtIniworking() {
        return htIniworking.get();
    }

    public void setHtIniworking(String usName) {
        this.htIniworking.set(usName);
    }
    public String getHtFinisworking() {
        return htFinisworking.get();
    }

    public void setHtFinisworking(String usName) {
        this.htFinisworking.set(usName);
    }
    public int getHtId() {
        return htId.get();
    }

    public void setHtId(int htId) {
        this.htId.set(htId);
    }

    public LocalDate getHtDate() {
        return htDate;
    }

    public void setHtDate(LocalDate htDate) {
        this.htDate = htDate;
    }

    public LocalDate getHtDateFinal() {
        return htDateFinal;
    }

    public void setHtDateFinal(LocalDate htDateFinal) {
        this.htDateFinal = htDateFinal;
    }

    public Short getHtSpaces() {
        return htSpaces;
    }

    public void setHtSpaces(Short htSpaces) {
        this.htSpaces = htSpaces;
    }

    public DoctorDto getHtDoctor() {
        return htDoctor;
    }

    public void setHtDoctor(DoctorDto htDoctor) {
        this.htDoctor = htDoctor;
    }
    
    
}
