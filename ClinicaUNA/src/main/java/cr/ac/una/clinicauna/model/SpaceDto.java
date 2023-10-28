/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;
/**
 *
 * @author jomav
 */
public class SpaceDto {

    public String seHour;
    public Integer seId;
    public AppointmentDto seAppointment;

    public SpaceDto() {
        this.seAppointment = new AppointmentDto();
    }
    
    public Integer getSeId() {
        return seId;
    }

    public void setSeId(Integer seId) {
        this.seId = seId;
    }

    public String getSeHour() {
        return seHour;
    }

    public void setSeHour(String seHour) {
        this.seHour = seHour;
    }

    public AppointmentDto getSeAppointment() {
        return seAppointment;
    }

    public void setSeAppointment(AppointmentDto seAppointment) {
        this.seAppointment = seAppointment;
    }
}
