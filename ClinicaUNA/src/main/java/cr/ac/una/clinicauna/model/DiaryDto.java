/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author dilan
 */
public class DiaryDto {
    
    
    public SimpleIntegerProperty dyId;
    public LocalDate dyDate;
    public DoctorDto dyDoctor;
    public SpaceDto dySpace;

    public DiaryDto() {
        this.dyId = new SimpleIntegerProperty();
        
        this.dyDoctor = new DoctorDto();
        this.dySpace = new SpaceDto();
    }

    
    public int getDyId() {
        return dyId.get();
    }

    public void setDyId(int dyId) {
        this.dyId.set(dyId);
    }

    public LocalDate getDyDate() {
        return dyDate;
    }

    public void setDyDate(LocalDate dyDate) {
        this.dyDate = dyDate;
    }

    public DoctorDto getDyDoctor() {
        return dyDoctor;
    }

    public void setDyDoctor(DoctorDto dyDoctor) {
        this.dyDoctor = dyDoctor;
    }

    public SpaceDto getDySpace() {
        return dySpace;
    }

    public void setDySpace(SpaceDto dySpace) {
        this.dySpace = dySpace;
    }
    
    
    
    
    
}
