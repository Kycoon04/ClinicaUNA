/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author dilan
 */
public class AppointmentDto {
  
    
    private Integer atId;
    private String atReason;
    private Long atTelephone;
    private String atEmail;
    private String atState;
    private Patient atPatient;
    private Users atUserregister;
    private String atCode;
    private LocalDate atDateregister;
    
    public AppointmentDto(Appointment appointment) {
        this.atId = appointment.getAtId();
        this.atReason = appointment.getAtReason();
        this.atTelephone = appointment.getAtTelephone();
        this.atEmail = appointment.getAtEmail();
        this.atState = appointment.getAtState();
        this.atPatient = appointment.getAtPatient();
        this.atUserregister = appointment.getAtUserregister();
        this.atCode = appointment.getAtCode();
        this.atDateregister = appointment.getAtDateregister();
    }

    public LocalDate getAtDateregister() {
        return atDateregister;
    }

    public void setAtDateregister(LocalDate FechaRegistro) {
        this.atDateregister = FechaRegistro;
    }

    public String getAtCode() {
        return atCode;
    }

    public void setAtCode(String atCode) {
        this.atCode = atCode;
    }

    public AppointmentDto() {
    }

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }

    public String getAtReason() {
        return atReason;
    }

    public void setAtReason(String atReason) {
        this.atReason = atReason;
    }

    public Long getAtTelephone() {
        return atTelephone;
    }

    public void setAtTelephone(Long atTelephone) {
        this.atTelephone = atTelephone;
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
/*
    public List<Space> getSpaceList() {
        return spaceList;
    }

    public void setSpaceList(List<Space> spaceList) {
        this.spaceList = spaceList;
    }
*/
    public Patient getAtPatient() {
        return atPatient;
    }

    public void setAtPatient(Patient atPatient) {
        this.atPatient = atPatient;
    }

    public Users getAtUserregister() {
        return atUserregister;
    }

    public void setAtUserregister(Users atUserregister) {
        this.atUserregister = atUserregister;
    }
/*
    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }
    
    
    */
    
    
}
