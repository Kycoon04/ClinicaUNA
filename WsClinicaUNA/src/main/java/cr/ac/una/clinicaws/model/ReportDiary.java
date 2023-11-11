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
    private String fechaTabla;
    private String codigoTabla;
    private String estadoTabla;
    private String usuarioTabla;
    private String pacienteTabla;
    private String emailTabla;

    public ReportDiary() {
        Calendar calendar = Calendar.getInstance();
        this.dyDate = calendar.getTime();
        this.ptEmail = "N/A";
        this.atState = "N/A";
        this.atPatient = "N/A";
        this.atUserregister = "N/A";
        this.atCode = "N/A";
    }

    public ReportDiary(DiaryDto agenda, AppointmentDto cita, String Language) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(agenda.getDySpace().getSeHour(), timeFormatter);
        LocalDateTime localDateTime = LocalDateTime.of(agenda.getDyDate(), localTime);
        this.dyDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        this.ptEmail = cita.getAtEmail();
        this.atState = cita.getAtState();
        this.atPatient = cita.getAtPatient().getPtName() + " " + cita.getAtPatient().getPtPlastname();
        this.atUserregister = cita.getAtUserregister().getUsName() + " " + cita.getAtUserregister().getUsPlastname();
        this.atCode = cita.getAtCode();
        if (Language.equals("Spanish")) {
            this.fechaTabla = "Fecha";
            this.codigoTabla = "Código de Cita";
            this.estadoTabla = "Estado";
            this.usuarioTabla = "Usuario Registrador";
            this.pacienteTabla = "Paciente";
            this.emailTabla = "Email";
        } else if (Language.equals("English")|| Language.equals("Japanese")) {
            this.fechaTabla = "Date";
            this.codigoTabla = "Appointment Code";
            this.estadoTabla = "Status";
            this.usuarioTabla = "Registering User";
            this.pacienteTabla = "Patient";
            this.emailTabla = "Email";
        } else if (Language.equals("French")) {
            this.fechaTabla = "Date";
            this.codigoTabla = "Code de Rendez-vous";
            this.estadoTabla = "État";
            this.usuarioTabla = "Utilisateur Enregistreur";
            this.pacienteTabla = "Patient";
            this.emailTabla = "Email";
        }
    }

    public String getCodigoTabla() {
        return codigoTabla;
    }

    public void setCodigoTabla(String codigoTabla) {
        this.codigoTabla = codigoTabla;
    }

    public String getEstadoTabla() {
        return estadoTabla;
    }

    public void setEstadoTabla(String estadoTabla) {
        this.estadoTabla = estadoTabla;
    }

    public String getUsuarioTabla() {
        return usuarioTabla;
    }

    public void setUsuarioTabla(String usuarioTabla) {
        this.usuarioTabla = usuarioTabla;
    }

    public String getPacienteTabla() {
        return pacienteTabla;
    }

    public void setPacienteTabla(String pacienteTabla) {
        this.pacienteTabla = pacienteTabla;
    }

    public String getEmailTabla() {
        return emailTabla;
    }

    public void setEmailTabla(String emailTabla) {
        this.emailTabla = emailTabla;
    }

    public String getFechaTabla() {
        return fechaTabla;
    }

    public void setFechaTabla(String FechaTabla) {
        this.fechaTabla = FechaTabla;
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
