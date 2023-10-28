/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;
/**
 *
 * @author dilan
 */
public class SpaceDto {

    private String seHour;
    private Integer seId;
    private Appointment seAppointment;

    public SpaceDto(Space space) {
        this.seHour = space.getSeHour();
        this.seId = space.getSeId();
        this.seAppointment = space.getSeAppointment();
    }

    public SpaceDto() {
    }

    public String getSeHour() {
        return seHour;
    }

    public void setSeHour(String seHour) {
        this.seHour = seHour;
    }

    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
    }

    public Appointment getSeAppointment() {
        return seAppointment;
    }

    public void setSeAppointment(Appointment seAppointment) {
        this.seAppointment = seAppointment;
    }
    
    
    
}
