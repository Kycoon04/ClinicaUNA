/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class ExamDto {
    

    private Integer emId;
   
    private String emName;

    private String emDoctornote;

    private Doctor emDoctor;

    private Proceedings emProceedings;

    public ExamDto(Exam exam) {
        this.emId = exam.getEmId();
        this.emName = exam.getEmName();
        this.emDoctornote = exam.getEmDoctornote();
        this.emDoctor = exam.getEmDoctor();
        this.emProceedings = exam.getEmProceedings();
    }

    public ExamDto() {
    }

    
    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmDoctornote() {
        return emDoctornote;
    }

    public void setEmDoctornote(String emDoctornote) {
        this.emDoctornote = emDoctornote;
    }

    public Doctor getEmDoctor() {
        return emDoctor;
    }

    public void setEmDoctor(Doctor emDoctor) {
        this.emDoctor = emDoctor;
    }

    public Proceedings getEmProceedings() {
        return emProceedings;
    }

    public void setEmProceedings(Proceedings emProceedings) {
        this.emProceedings = emProceedings;
    }
    
    
    
    
}
