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
public class ProceedingsDto {
     private Integer psId;

    private List<FProceedings> fProceedingsList;
    private List<PProceedings> pProceedingsList;
    private List<Exam> examList;

    public ProceedingsDto(Proceedings proceedings) {
        this.psId = proceedings.getPsId();
        this.fProceedingsList = proceedings.getFProceedingsList();
        this.pProceedingsList = proceedings.getPProceedingsList();
        this.examList = proceedings.getExamList();
        this.psPatient = proceedings.getPsPatient();
    }

    
    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public List<FProceedings> getfProceedingsList() {
        return fProceedingsList;
    }

    public void setfProceedingsList(List<FProceedings> fProceedingsList) {
        this.fProceedingsList = fProceedingsList;
    }

    public List<PProceedings> getpProceedingsList() {
        return pProceedingsList;
    }

    public void setpProceedingsList(List<PProceedings> pProceedingsList) {
        this.pProceedingsList = pProceedingsList;
    }

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public Patient getPsPatient() {
        return psPatient;
    }

    public void setPsPatient(Patient psPatient) {
        this.psPatient = psPatient;
    }
    
    
    
  
    private Patient psPatient;
}
