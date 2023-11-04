/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class FamilybackgroundDto {

    private Integer fbId;

    private String fbRelationship;
    private Disease fbDisease;
    private int fbFilecode;

    public FamilybackgroundDto() {
    }

    public FamilybackgroundDto(Familybackground familybackground) {
        this.fbId = familybackground.getFbId();
        this.fbDisease = familybackground.getFbDisease();
        this.fbRelationship = familybackground.getFbRelationship();
        this.fbFilecode = familybackground.getFbFilecode();
    }

    public String getFbDiseaseString() {
        return fbDisease.getDsName();
    }

    public int getFbFilecode() {
        return fbFilecode;
    }

    public void setFbFilecode(int fbFilecode) {
        this.fbFilecode = fbFilecode;
    }

    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    public String getFbRelationship() {
        return fbRelationship;
    }

    public void setFbRelationship(String fbRelationship) {
        this.fbRelationship = fbRelationship;
    }

    public Disease getFbDisease() {
        return fbDisease;
    }

    public void setFbDisease(Disease fbDisease) {
        this.fbDisease = fbDisease;
    }

}
