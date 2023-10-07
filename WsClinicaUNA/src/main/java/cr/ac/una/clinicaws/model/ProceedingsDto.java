/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;
/**
 *
 * @author dilan
 */
public class ProceedingsDto {
    private Integer psId;
    private Patient psPatient;
    
    public ProceedingsDto(Proceedings proceedings) {
        this.psId = proceedings.getPsId();
        this.psPatient = proceedings.getPsPatient();
    }

    public ProceedingsDto() {
    }

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public Patient getPsPatient() {
        return psPatient;
    }

    public void setPsPatient(Patient psPatient) {
        this.psPatient = psPatient;
    }
}
