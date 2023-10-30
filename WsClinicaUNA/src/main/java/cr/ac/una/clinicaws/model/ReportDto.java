/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class ReportDto {
    
    private Integer rtId;
    private short rtPressure;
    private short rtHeartRate;
    private short rtWeight;
    private short rtHeight;
    private short rtTemperature;
    private short rtBodyMass;
    private String rtNotesNursing;
    private String rtDoctorReason;
    private Appointment rtAppointment;

    public ReportDto(){
        
    }
    public ReportDto(Report report) {
        this.rtId = report.getRtId();
        this.rtPressure = report.getRtPressure();
        this.rtHeartRate = report.getRtHeartRate();
        this.rtWeight = report.getRtWeight();
        this.rtHeight = report.getRtHeight();
        this.rtTemperature = report.getRtTemperature();
        this.rtBodyMass = report.getRtBodyMass();
        this.rtNotesNursing = report.getRtNotesNursing();
        this.rtDoctorReason = report.getRtDoctorReason();
        this.rtAppointment = report.getRtAppointment();
    }
   
    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
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
        return rtNotesNursing;
    }

    public void setRtNotesNursing(String rtNotesNursing) {
        this.rtNotesNursing = rtNotesNursing;
    }

    public String getRtDoctorReason() {
        return rtDoctorReason;
    }

    public void setRtDoctorReason(String rtDoctorReason) {
        this.rtDoctorReason = rtDoctorReason;
    }

    public Appointment getRtAppointment() {
        return rtAppointment;
    }

    public void setRtAppointment(Appointment rtAppointment) {
        this.rtAppointment = rtAppointment;
    }
    
    
    
    
    
}
