/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author dilan
 */
public class DiaryDto {
    
    
    private SimpleIntegerProperty dyId;
    private Date dyDate;
    private DoctorDto dyDoctor;
    private SpaceDto dySpace;

    public int getDyId() {
        return dyId.get();
    }

    public void setDyId(int dyId) {
        this.dyId.set(dyId);
    }

    public Date getDyDate() {
        return dyDate;
    }

    public void setDyDate(Date dyDate) {
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
