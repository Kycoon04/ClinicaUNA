/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author dilan
 */
public class SpaceDto {

    private Date seHour;
    private Integer seId;
    private List<Diary> diaryList;
    private Appointment seAppointment;

    public SpaceDto(Space space) {
        this.seHour = space.getSeHour();
        this.seId = space.getSeId();
        this.diaryList = space.getDiaryList();
        this.seAppointment = space.getSeAppointment();
    }

    public Date getSeHour() {
        return seHour;
    }

    public void setSeHour(Date seHour) {
        this.seHour = seHour;
    }

    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
    }

    public List<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }

    public Appointment getSeAppointment() {
        return seAppointment;
    }

    public void setSeAppointment(Appointment seAppointment) {
        this.seAppointment = seAppointment;
    }
    
    
    
}
