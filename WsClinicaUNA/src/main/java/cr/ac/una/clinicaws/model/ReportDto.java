/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.time.Instant;
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
    private Date rtDate;
    private Proceedings rtProceedings;
    private String fecha;
    private String presion;
    private String codigo;
    private String ritmo;
    private String peso;
    private String altura;
    private String temperatura;
    private String masa;
    private String notas;
    private String razon;
    private String plan;
    private String tratamiento;
    private String examenFisico;
    private String observaciones;
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
        this.rtProceedings = report.getRtProceedings();
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
    
    public Date getRtDateDate() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse("12:23", timeFormatter);
        Instant instant = rtDate.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Proceedings getRtProceedings() {
        return rtProceedings;
    }

    public void setRtProceedings(Proceedings rtProceedings) {
        this.rtProceedings = rtProceedings;
    }

    public String getCodeAppointment() {
        return rtAppointment.getAtCode();
    }

    public Date getRtDate() {
        return rtDate;
    }

    public void setRtDate(Date rtDate) {
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
