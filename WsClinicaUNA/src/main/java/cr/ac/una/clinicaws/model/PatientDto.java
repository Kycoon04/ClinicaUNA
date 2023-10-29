package cr.ac.una.clinicaws.model;

import java.util.Date;

/**
 *
 * @author dilan
 */
public class PatientDto {

    private String ptName;
    private String ptPlastname;
    private String ptSlastname;
    private String ptIdentification;
    private String ptGender;
    private String ptEmail;
    private Date ptBirthdate;
    private Integer ptId;
    private String ptTelephone;
    public PatientDto() {
    }

    public PatientDto(Patient patient) {
        ptName = patient.getPtName();
        ptPlastname = patient.getPtPlastname();
        ptSlastname = patient.getPtSlastname();
        ptIdentification = patient.getPtIdentification();
        ptGender = patient.getPtGender();
        ptEmail = patient.getPtEmail();
        ptBirthdate = patient.getPtBirthdate();
        ptId = patient.getPtId();
        ptTelephone = patient.getPtTelephone();
    }
    
    public String getPtName() {
        return ptName;
    }

    public String getPtTelephone() {
        return ptTelephone;
    }

    public void setPtTelephone(String ptTelephone) {
        this.ptTelephone = ptTelephone;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getPtPlastname() {
        return ptPlastname;
    }

    public void setPtPlastname(String ptPlastname) {
        this.ptPlastname = ptPlastname;
    }

    public String getPtSlastname() {
        return ptSlastname;
    }

    public void setPtSlastname(String ptSlastname) {
        this.ptSlastname = ptSlastname;
    }

    public String getPtIdentification() {
        return ptIdentification;
    }

    public void setPtIdentification(String ptIdentification) {
        this.ptIdentification = ptIdentification;
    }

    public String getPtGender() {
        return ptGender;
    }

    public void setPtGender(String ptGender) {
        this.ptGender = ptGender;
    }

    public String getPtEmail() {
        return ptEmail;
    }

    public void setPtEmail(String ptEmail) {
        this.ptEmail = ptEmail;
    }

    public Date getPtBirthdate() {
        return ptBirthdate;
    }

    public void setPtBirthdate(Date ptBirthdate) {
        this.ptBirthdate = ptBirthdate;
    }

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

}
