/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author dilan
 */
@Entity
@Table(name = "CL_USERS")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsName", query = "SELECT u FROM Users u WHERE u.usName = :usName"),
    @NamedQuery(name = "Users.findByUsPlastname", query = "SELECT u FROM Users u WHERE u.usPlastname = :usPlastname"),
    @NamedQuery(name = "Users.findByUsSlastname", query = "SELECT u FROM Users u WHERE u.usSlastname = :usSlastname"),
    @NamedQuery(name = "Users.findByUsIdentification", query = "SELECT u FROM Users u WHERE u.usIdentification = :usIdentification"),
    @NamedQuery(name = "Users.findByUsType", query = "SELECT u FROM Users u WHERE u.usType = :usType"),
    @NamedQuery(name = "Users.findByUsLenguage", query = "SELECT u FROM Users u WHERE u.usLenguage = :usLenguage"),
    @NamedQuery(name = "Users.findByUsState", query = "SELECT u FROM Users u WHERE u.usState = :usState"),
    @NamedQuery(name = "Users.findByUsUsername", query = "SELECT u FROM Users u WHERE u.usUsername = :usUsername"),
    @NamedQuery(name = "Users.findByUsPassword", query = "SELECT u FROM Users u WHERE u.usPassword = :usPassword"),
    @NamedQuery(name = "Users.findByUsEmail", query = "SELECT u FROM Users u WHERE u.usEmail = :usEmail"),
    @NamedQuery(name = "Users.findByUsRecover", query = "SELECT u FROM Users u WHERE u.usRecover = :usRecover"),
    @NamedQuery(name = "Users.findByUsTemppassword", query = "SELECT u FROM Users u WHERE u.usTemppassword = :usTemppassword"),
    @NamedQuery(name = "Users.findByUsId", query = "SELECT u FROM Users u WHERE u.usId = :usId")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "US_NAME")
    private String usName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "US_PLASTNAME")
    private String usPlastname;
    @Size(max = 30)
    @Column(name = "US_SLASTNAME")
    private String usSlastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "US_IDENTIFICATION")
    private String usIdentification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "US_TYPE")
    private String usType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "US_LENGUAGE")
    private String usLenguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "US_STATE")
    private String usState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "US_USERNAME")
    private String usUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "US_PASSWORD")
    private String usPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "US_EMAIL")
    private String usEmail;
    @Size(max = 1)
    @Column(name = "US_RECOVER")
    private String usRecover;
    @Size(max = 15)
    @Column(name = "US_TEMPPASSWORD")
    private String usTemppassword;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "US_ID")
    private Integer usId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drUser")
    private List<Doctor> doctorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atUserregister")
    private List<Appointment> appointmentList;

    public Users() {
    }

    public Users(Integer usId) {
        this.usId = usId;
    }

    public Users(Integer usId, String usName, String usPlastname, String usIdentification, String usType, String usLenguage, String usState, String usUsername, String usPassword, String usEmail) {
        this.usId = usId;
        this.usName = usName;
        this.usPlastname = usPlastname;
        this.usIdentification = usIdentification;
        this.usType = usType;
        this.usLenguage = usLenguage;
        this.usState = usState;
        this.usUsername = usUsername;
        this.usPassword = usPassword;
        this.usEmail = usEmail;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usId != null ? usId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usId == null && other.usId != null) || (this.usId != null && !this.usId.equals(other.usId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.clinicaws.model.Users[ usId=" + usId + " ]";
    }
    
}
