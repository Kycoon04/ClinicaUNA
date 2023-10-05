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
public class DiseaseDto {
 
    private Integer dsId;

    private String dsName;

    private List<Familybackground> familybackgroundList;

    public DiseaseDto(Disease disease) {
        this.dsId = disease.getDsId();
        this.dsName = disease.getDsName();
        this.familybackgroundList = disease.getFamilybackgroundList();
        
    }

    public Integer getDsId() {
        return dsId;
    }

    public void setDsId(Integer dsId) {
        this.dsId = dsId;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public List<Familybackground> getFamilybackgroundList() {
        return familybackgroundList;
    }

    public void setFamilybackgroundList(List<Familybackground> familybackgroundList) {
        this.familybackgroundList = familybackgroundList;
    }
    
  
}
