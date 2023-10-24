/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_DOCTOR")
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findByDrId", query = "SELECT d FROM Doctor d WHERE d.drId = :drId"),
    @NamedQuery(name = "Doctor.findByDrCode", query = "SELECT d FROM Doctor d WHERE d.drCode = :drCode"),
    @NamedQuery(name = "Doctor.findByDrLicense", query = "SELECT d FROM Doctor d WHERE d.drLicense = :drLicense"),
    @NamedQuery(name = "Doctor.findByDrFol", query = "SELECT d FROM Doctor d WHERE d.drFol = :drFol"),
    @NamedQuery(name = "Doctor.findByDrIniworking", query = "SELECT d FROM Doctor d WHERE d.drIniworking = :drIniworking"),
    @NamedQuery(name = "Doctor.findByDrFinisworking", query = "SELECT d FROM Doctor d WHERE d.drFinisworking = :drFinisworking"),
    @NamedQuery(name = "Doctor.findDoctorByUserId", query = "SELECT d FROM Doctor d WHERE d.drUser.usId = :userId"),
    @NamedQuery(name = "Doctor.findByDrBreak", query = "SELECT d FROM Doctor d WHERE d.drBreak = :drBreak"),
    @NamedQuery(name = "Doctor.findByDrSpaces", query = "SELECT d FROM Doctor d WHERE d.drSpaces = :drSpaces")})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DR_ID")
    private Integer drId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DR_CODE")
    private int drCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DR_LICENSE")
    private int drLicense;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DR_FOL")
    private int drFol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DR_INIWORKING")
    private String drIniworking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DR_FINISWORKING")
    private String drFinisworking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DR_BREAK")
    private String drBreak;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DR_SPACES")
    private short drSpaces;
    @JoinColumn(name = "DR_USER", referencedColumnName = "US_ID")
    @ManyToOne(optional = false)
    private Users drUser;

    public Doctor() {
    }

    public Doctor(Integer drId) {
        this.drId = drId;
    }

     public Doctor(DoctorDto doctorDto) {
       this.drId = doctorDto.getDrId();
        update(doctorDto);
    }
     
    public Doctor(Integer drId, int drCode, int drLicense, int drFol, String drIniworking, String drFinisworking, String drBreak, short drSpaces, Users drUser) {
        this.drId = drId;
        this.drCode = drCode;
        this.drLicense = drLicense;
        this.drFol = drFol;
        this.drIniworking = drIniworking;
        this.drFinisworking = drFinisworking;
        this.drBreak = drBreak;
        this.drSpaces = drSpaces;
        this.drUser = drUser;
    }
       public void update(DoctorDto doctorDto) {   
      
        this.drCode = doctorDto.getDrCode();
        this.drLicense = doctorDto.getDrLicense();
        this.drFol = doctorDto.getDrFol();
        this.drIniworking = doctorDto.getDrIniworking();
        this.drFinisworking = doctorDto.getDrFinisworking();
        this.drBreak = doctorDto.getDrBreak();
        this.drUser = doctorDto.getDrUser();
        this.drSpaces= doctorDto.getDrSpaces();
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

    public short getDrSpaces() {
        return drSpaces;
    }

    public void setDrSpaces(short drSpaces) {
        this.drSpaces = drSpaces;
    }

    public Users getDrUser() {
        return drUser;
    }

    public void setDrUser(Users drUser) {
        this.drUser = drUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drId != null ? drId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.drId == null && other.drId != null) || (this.drId != null && !this.drId.equals(other.drId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Doctor[ drId=" + drId + " ]";
    }
    
}
