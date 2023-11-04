package cr.ac.una.clinicaws.model;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ReportDiary {

    private Date dyDate;
    private String ptEmail;
    private String atState;
    private String atPatient;
    private String atUserregister;
    private String atCode;

    public ReportDiary() {
        Calendar calendar = Calendar.getInstance();
        this.dyDate = calendar.getTime();
        this.ptEmail = "N/A";
        this.atState =  "N/A";
        this.atPatient =  "N/A";
        this.atUserregister = "N/A";
        this.atCode = "N/A";
    }

    public ReportDiary(DiaryDto agenda, AppointmentDto cita) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(agenda.getDySpace().getSeHour(), timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(agenda.getDyDate(), localTime);
        this.dyDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        this.ptEmail = cita.getAtEmail();
        this.atState = cita.getAtState();
        this.atPatient = cita.getAtPatient().getPtName() + " " + cita.getAtPatient().getPtPlastname();
        this.atUserregister = cita.getAtUserregister().getUsName() + " " + cita.getAtUserregister().getUsPlastname();
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

    public String getPtEmail() {
        return ptEmail;
    }

    public void setPtEmail(String atEmail) {
        this.ptEmail = atEmail;
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
