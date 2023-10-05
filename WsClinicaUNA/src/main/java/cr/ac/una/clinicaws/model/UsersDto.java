/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.util.List;

/**
 *
 * @author dilan
 */
public class UsersDto {

    private String usName;
    private String usPlastname;
    private String usSlastname;
    private String usIdentification;
    private String usType;

    private String usLenguage;

    private String usState;

    private String usUsername;

    private String usPassword;

    private String usEmail;

    private String usRecover;

    private String usTemppassword;

    private Integer usId;

    private List<Doctor> doctorList;

    private List<Appointment> appointmentList;

    public UsersDto(Users user) {
        this.usName = user.getUsName();
        this.usPlastname = user.getUsPlastname();
        this.usSlastname = user.getUsSlastname();
        this.usIdentification = user.getUsIdentification();
        this.usType = user.getUsType();
        this.usLenguage = user.getUsLenguage();
        this.usState = user.getUsState();
        this.usUsername = user.getUsUsername();
        this.usPassword = user.getUsPassword();
        this.usEmail = user.getUsEmail();
        this.usRecover = user.getUsRecover();
        this.usTemppassword = user.getUsTemppassword();
        this.usId = user.getUsId();
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public String getUsPlastname() {
        return usPlastname;
    }

    public void setUsPlastname(String usPlastname) {
        this.usPlastname = usPlastname;
    }

    public String getUsSlastname() {
        return usSlastname;
    }

    public void setUsSlastname(String usSlastname) {
        this.usSlastname = usSlastname;
    }

    public String getUsIdentification() {
        return usIdentification;
    }

    public void setUsIdentification(String usIdentification) {
        this.usIdentification = usIdentification;
    }

    public String getUsType() {
        return usType;
    }

    public void setUsType(String usType) {
        this.usType = usType;
    }

    public String getUsLenguage() {
        return usLenguage;
    }

    public void setUsLenguage(String usLenguage) {
        this.usLenguage = usLenguage;
    }

    public String getUsState() {
        return usState;
    }

    public void setUsState(String usState) {
        this.usState = usState;
    }

    public String getUsUsername() {
        return usUsername;
    }

    public void setUsUsername(String usUsername) {
        this.usUsername = usUsername;
    }

    public String getUsPassword() {
        return usPassword;
    }

    public void setUsPassword(String usPassword) {
        this.usPassword = usPassword;
    }

    public String getUsEmail() {
        return usEmail;
    }

    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }

    public String getUsRecover() {
        return usRecover;
    }

    public void setUsRecover(String usRecover) {
        this.usRecover = usRecover;
    }

    public String getUsTemppassword() {
        return usTemppassword;
    }

    public void setUsTemppassword(String usTemppassword) {
        this.usTemppassword = usTemppassword;
    }

    public Integer getUsId() {
        return usId;
    }

    public void setUsId(Integer usId) {
        this.usId = usId;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

}
