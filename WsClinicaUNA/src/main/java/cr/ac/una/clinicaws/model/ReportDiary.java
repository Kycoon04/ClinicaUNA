package cr.ac.una.clinicaws.model;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ReportDiary {
    
    private Date   dyDate;
    private String atEmail;
    private String atState;
    private String atPatient;
    private String atUserregister;
    private String atCode;

    public ReportDiary() {
    }

    public ReportDiary(DiaryDto agenda, AppointmentDto cita) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(agenda.getDySpace().getSeHour(), timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(agenda.getDyDate(), localTime);
        this.dyDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        this.atEmail = cita.getAtEmail();
        this.atState = cita.getAtState();
        this.atPatient = cita.getAtPatient().getPtName() + " "+ cita.getAtPatient().getPtPlastname();
        this.atUserregister = cita.getAtUserregister().getUsName() + " "+ cita.getAtUserregister().getUsPlastname();
        this.atCode = cita.getAtCode();
    }
    public void asignarFechaDesdeLocalDate(LocalDate fechaLocal) {
        this.dyDate = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getDyDate() {
        return dyDate;
    }

    public void setDyDate(Date dyDate) {
        this.dyDate = dyDate;
    }

    public String getAtEmail() {
        return atEmail;
    }

    public void setAtEmail(String atEmail) {
        this.atEmail = atEmail;
    }

    public String getAtState() {
        return atState;
    }

    public void setAtState(String atState) {
        this.atState = atState;
    }

    public String getAtPatient() {
        return atPatient;
    }

    public void setAtPatient(String atPatient) {
        this.atPatient = atPatient;
    }

    public String getAtUserregister() {
        return atUserregister;
    }

    public void setAtUserregister(String atUserregister) {
        this.atUserregister = atUserregister;
    }

    public String getAtCode() {
        return atCode;
    }

    public void setAtCode(String atCode) {
        this.atCode = atCode;
    }
}
