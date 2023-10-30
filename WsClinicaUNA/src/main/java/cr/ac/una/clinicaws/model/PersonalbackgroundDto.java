/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class PersonalbackgroundDto {

    private Integer pbId;
    private String pbType;
    private String pbContext;
     private int pbFilecode;

    public PersonalbackgroundDto() {
    }

    public PersonalbackgroundDto(Personalbackground personalbackground) {
        pbId = personalbackground.getPbId();
        pbType = personalbackground.getPbType();
        pbContext = personalbackground.getPbContext();
        pbFilecode = personalbackground.getPbFilecode();
    }

    public int getPbFilecode() {
        return pbFilecode;
    }

    public void setPbFilecode(int pbFilecode) {
        this.pbFilecode = pbFilecode;
    }

    public Integer getPbId() {
        return pbId;
    }

    public void setPbId(Integer pbId) {
        this.pbId = pbId;
    }

    public String getPbType() {
        return pbType;
    }

    public void setPbType(String pbType) {
        this.pbType = pbType;
    }

    public String getPbContext() {
        return pbContext;
    }

    public void setPbContext(String pbContext) {
        this.pbContext = pbContext;
    }

}
