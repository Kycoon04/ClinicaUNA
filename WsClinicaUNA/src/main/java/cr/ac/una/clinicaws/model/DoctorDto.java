/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class DoctorDto {

    private Integer drId;
    private int drCode;
    private int drLicense;
    private int drFol;
    private String drIniworking;
    private String drFinisworking;
    private String drBreak;
    private Users drUser;
    private short drSpaces;

    public DoctorDto(Doctor doctor) {
        this.drId = doctor.getDrId();
        this.drCode = doctor.getDrCode();
        this.drLicense = doctor.getDrLicense();
        this.drFol = doctor.getDrFol();
        this.drIniworking = doctor.getDrIniworking();
        this.drFinisworking = doctor.getDrFinisworking();
        this.drBreak = doctor.getDrBreak();
        this.drUser = doctor.getDrUser();
        this.drSpaces= doctor.getDrSpaces();
    }

    public short getDrSpaces() {
        return drSpaces;
    }

    public void setDrSpaces(short drSpaces) {
        this.drSpaces = drSpaces;
    }

    public DoctorDto() {
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public int getDrCode() {
        return drCode;
    }

    public void setDrCode(int drCode) {
        this.drCode = drCode;
    }

    public int getDrLicense() {
        return drLicense;
    }

    public void setDrLicense(int drLicense) {
        this.drLicense = drLicense;
    }

    public int getDrFol() {
        return drFol;
    }

    public void setDrFol(int drFol) {
        this.drFol = drFol;
    }

    public String getDrIniworking() {
        return drIniworking;
    }

    public void setDrIniworking(String drIniworking) {
        this.drIniworking = drIniworking;
    }

    public String getDrFinisworking() {
        return drFinisworking;
    }

    public void setDrFinisworking(String drFinisworking) {
        this.drFinisworking = drFinisworking;
    }

    public String getDrBreak() {
        return drBreak;
    }

    public void setDrBreak(String drBreak) {
        this.drBreak = drBreak;
    }

    public Users getDrUser() {
        return drUser;
    }

    public void setDrUser(Users drUser) {
        this.drUser = drUser;
    }
    
    
    
}
