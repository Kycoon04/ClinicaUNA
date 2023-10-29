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
public class ExamDto {
    public SimpleIntegerProperty emId;
    public SimpleStringProperty emName;
    public SimpleStringProperty emDoctornote;
    public DoctorDto emDoctor;
    public ProceedingsDto emProceedings;

    public ExamDto() {
        this.emDoctor= new DoctorDto();
        this.emDoctornote= new SimpleStringProperty();
        this.emId= new SimpleIntegerProperty();
        this.emName= new SimpleStringProperty();
        this.emProceedings= new ProceedingsDto();
    }


    public Integer getEmId() {
        return emId.get();
    }

    public void setEmId(Integer emId) {
        this.emId.set(emId);
    }

    public String getEmName() {
        return emName.get();
    }

    public void setEmName(String emName) {
        this.emName.set(emName);
    }

    public String getEmDoctornote() {
        return emDoctornote.get();
    }

    public void setEmDoctornote(String emDoctornote) {
        this.emDoctornote.set(emDoctornote);
    }

    public DoctorDto getEmDoctor() {
        return emDoctor;
    }

    public String getEmDoctorName() {
        return emDoctor.getDoctorName();
    }
       
    public void setEmDoctor(DoctorDto emDoctor) {
        this.emDoctor = emDoctor;
    }

    public ProceedingsDto getEmProceedings() {
        return emProceedings;
    }

    public void setEmProceedings(ProceedingsDto emProceedings) {
        this.emProceedings = emProceedings;
    }
    
    
}
