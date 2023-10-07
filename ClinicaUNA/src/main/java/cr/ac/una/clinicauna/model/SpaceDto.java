/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.util.Date;

/**
 *
 * @author jomav
 */
public class SpaceDto {
    public Date seHour;
    public Integer seId;
    public AppointmentDto seAppointment;

    public SpaceDto() {
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

    public AppointmentDto getSeAppointment() {
        return seAppointment;
    }

    public void setSeAppointment(AppointmentDto seAppointment) {
        this.seAppointment = seAppointment;
    }
}
