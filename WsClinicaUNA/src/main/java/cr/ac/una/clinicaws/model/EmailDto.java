/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author jomav
 */
public class EmailDto {
    
    private Integer elId;
    private String elEmail;
    private Sql elIdsql;

    public EmailDto(Emails email) {
        this.elId = email.getElId();
        this.elEmail = email.getElEmail();
        this.elIdsql = email.getElIdsql();
    }

    public EmailDto() {
    }

    public Integer getElId() {
        return elId;
    }

    public void setElId(Integer elId) {
        this.elId = elId;
    }

    public String getElEmail() {
        return elEmail;
    }

    public void setElEmail(String elEmail) {
        this.elEmail = elEmail;
    }

    public Sql getElIdsql() {
        return elIdsql;
    }

    public void setElIdsql(Sql elIdsql) {
        this.elIdsql = elIdsql;
    }
    
    
}
