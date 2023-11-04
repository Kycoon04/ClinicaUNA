/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author dilan
 */
public class ReportDto {

    private Integer rtId;
    private double rtPressure;
    private double rtHeartRate;
    private double rtWeight;
    private double rtHeight;
    private double rtTemperature;
    private double rtBodyMass;
    private String rtNotesNursing;
    private String rtDoctorReason;
    private Appointment rtAppointment;
    private String rtFisicExamen;
    private String rtTreatmentExamen;
    private String rtCarePlan;
    private String rtObservations;
    private LocalDate rtDate;

    public ReportDto() {

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
        this.rtFisicExamen = report.getRtFisicExamen();
        this.rtTreatmentExamen = report.getRtTreatmentExamen();
        this.rtCarePlan = report.getRtCarePlan();
        this.rtObservations = report.getRtObservations();
        this.rtDate = report.getRtDate();
    }

    public Date getRtDateDate() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime localDateTime = LocalDateTime.of(rtDate, null);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public String getCodeAppointment(){
    return rtAppointment.getAtCode();
    }
    
    public LocalDate getRtDate() {
        return rtDate;
    }

    public void setRtDate(LocalDate rtDate) {
        this.rtDate = rtDate;
    }

    public String getRtFisicExamen() {
        return rtFisicExamen;
    }

    public void setRtFisicExamen(String rtFisicExamen) {
        this.rtFisicExamen = rtFisicExamen;
    }

    public String getRtTreatmentExamen() {
        return rtTreatmentExamen;
    }

    public void setRtTreatmentExamen(String rtTreatmentExamen) {
        this.rtTreatmentExamen = rtTreatmentExamen;
    }

    public String getRtCarePlan() {
        return rtCarePlan;
    }

    public void setRtCarePlan(String rtCarePlan) {
        this.rtCarePlan = rtCarePlan;
    }

    public String getRtObservations() {
        return rtObservations;
    }

    public void setRtObservations(String rtObservations) {
        this.rtObservations = rtObservations;
    }

    public Integer getRtId() {
        return rtId;
    }

    public void setRtId(Integer rtId) {
        this.rtId = rtId;
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
