/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.util.Date;

/**
 *
 * @author dilan
 */
public class DiaryDto {

    private Integer dyId;
 
    private Date dyDate;

    private Doctor dyDoctor;

    private Space dySpace;

    public DiaryDto(Diary diary) {
        this.dyId = diary.getDyId();
        this.dyDate = diary.getDyDate();
        this.dyDoctor = diary.getDyDoctor();
        this.dySpace = diary.getDySpace();
    }
    
    public Integer getDyId() {
        return dyId;
    }

    public void setDyId(Integer dyId) {
        this.dyId = dyId;
    }

    public Date getDyDate() {
        return dyDate;
    }

    public void setDyDate(Date dyDate) {
        this.dyDate = dyDate;
    }

    public Doctor getDyDoctor() {
        return dyDoctor;
    }

    public void setDyDoctor(Doctor dyDoctor) {
        this.dyDoctor = dyDoctor;
    }

    public Space getDySpace() {
        return dySpace;
    }

    public void setDySpace(Space dySpace) {
        this.dySpace = dySpace;
    }
    
    
    
    
    
}
