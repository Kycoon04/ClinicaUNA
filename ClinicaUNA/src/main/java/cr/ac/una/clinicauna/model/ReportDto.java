/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dilan
 */
public class ReportDto {
    
    private SimpleIntegerProperty rtId;
    private double rtPressure;
    private double rtHeartRate;
    private double rtWeight;
    private double rtHeight;
    private double rtTemperature;
    private double rtBodyMass;
    private SimpleStringProperty rtNotesNursing;
    private SimpleStringProperty rtDoctorReason;
    private AppointmentDto rtAppointment;
    
    public ReportDto() {
        this.rtId = new SimpleIntegerProperty();
        this.rtNotesNursing = new SimpleStringProperty();
        rtDoctorReason = new SimpleStringProperty();
        rtAppointment = new AppointmentDto();     
    }
    
    public Integer getRtId() {
        return rtId.get();
    }

    public double getRtPressure() {
        return rtPressure;
    }

    public void setRtPressure(double rtPressure) {
        this.rtPressure = rtPressure;
    }

    public double getRtHeartRate() {
        return rtHeartRate;
    }

    public void setRtHeartRate(double rtHeartRate) {
        this.rtHeartRate = rtHeartRate;
    }

    public double getRtWeight() {
        return rtWeight;
    }

    public void setRtWeight(double rtWeight) {
        this.rtWeight = rtWeight;
    }

    public double getRtHeight() {
        return rtHeight;
    }

    public void setRtHeight(double rtHeight) {
        this.rtHeight = rtHeight;
    }

    public double getRtTemperature() {
        return rtTemperature;
    }

    public void setRtTemperature(double rtTemperature) {
        this.rtTemperature = rtTemperature;
    }

    public double getRtBodyMass() {
        return rtBodyMass;
    }

    public void setRtBodyMass(double rtBodyMass) {
        this.rtBodyMass = rtBodyMass;
    }
    
    
    
    public void setRtBodyMass(short rtBodyMass) {
        this.rtBodyMass = rtBodyMass;
    }
    
    public String getRtNotesNursing() {
        return rtNotesNursing.get();
    }
    
    public void setRtNotesNursing(String rtNotesNursing) {
        this.rtNotesNursing.set(rtNotesNursing);
    }
    
    public String getRtDoctorReason() {
        return rtDoctorReason.get();
    }
    
    public void setRtDoctorReason(String rtDoctorReason) {
        this.rtDoctorReason.set(rtDoctorReason);
    }
    
    public AppointmentDto getRtAppointment() {
        return rtAppointment;
    }
    
    public void setRtAppointment(AppointmentDto rtAppointment) {
        this.rtAppointment = rtAppointment;
    }
    
}
