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
    private short rtPressure;
    private short rtHeartRate;
    private short rtWeight;
    private short rtHeight;
    private short rtTemperature;
    private short rtBodyMass;
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
    
    public void setRtId(Integer rtId) {
        this.rtId.set(rtId);
    }
    
    public short getRtPressure() {
        return rtPressure;
    }
    
    public void setRtPressure(short rtPressure) {
        this.rtPressure = rtPressure;
    }
    
    public short getRtHeartRate() {
        return rtHeartRate;
    }
    
    public void setRtHeartRate(short rtHeartRate) {
        this.rtHeartRate = rtHeartRate;
    }
    
    public short getRtWeight() {
        return rtWeight;
    }
    
    public void setRtWeight(short rtWeight) {
        this.rtWeight = rtWeight;
    }
    
    public short getRtHeight() {
        return rtHeight;
    }
    
    public void setRtHeight(short rtHeight) {
        this.rtHeight = rtHeight;
    }
    
    public short getRtTemperature() {
        return rtTemperature;
    }
    
    public void setRtTemperature(short rtTemperature) {
        this.rtTemperature = rtTemperature;
    }
    
    public short getRtBodyMass() {
        return rtBodyMass;
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
