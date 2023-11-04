/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class FProceedingsDto {
    
    private Integer fpId;
 
    private Familybackground fpFamilyback;
    
    private Proceedings fpProceedings;

    public FProceedingsDto() {
    }

    public FProceedingsDto(FProceedings fProceedings) {
        this.fpId = fProceedings.getFpId();
        this.fpFamilyback = fProceedings.getFpFamilyback();
        this.fpProceedings = fProceedings.getFpProceedings();
    }

    public Integer getFpId() {
        return fpId;
    }
    public String getFbRelationship() {
        return fpFamilyback.getFbRelationship();
    }
    public String getFbDiseaseString() {
        return fpFamilyback.getFbDiseaseString();
    }
    public void setFpId(Integer fpId) {
        this.fpId = fpId;
    }

    public Familybackground getFpFamilyback() {
        return fpFamilyback;
    }

    public void setFpFamilyback(Familybackground fpFamilyback) {
        this.fpFamilyback = fpFamilyback;
    }

    public Proceedings getFpProceedings() {
        return fpProceedings;
    }

    public void setFpProceedings(Proceedings fpProceedings) {
        this.fpProceedings = fpProceedings;
    }
    
    
    
}
