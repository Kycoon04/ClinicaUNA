/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

/**
 *
 * @author dilan
 */
public class PProceedingsDto {

    private Integer ppId;
    private Personalbackground ppPersonalback;
    private Proceedings ppProceedings;
    private String Tipo;
    private String Descripcion;
    
    public PProceedingsDto() {
    }

    public PProceedingsDto(PProceedings PProceedings) {
        ppId = PProceedings.getPpId();
        ppPersonalback = PProceedings.getPpPersonalback();
        ppProceedings = PProceedings.getPpProceedings();
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Integer getPpId() {
        return ppId;
    }

    public String getPbType() {
        return ppPersonalback.getPbType();
    }
    
    public String getPbContext() {
        return ppPersonalback.getPbContext();
    }
    public void setPpId(Integer ppId) {
        this.ppId = ppId;
    }

    public Personalbackground getPpPersonalback() {
        return ppPersonalback;
    }

    public void setPpPersonalback(Personalbackground ppPersonalback) {
        this.ppPersonalback = ppPersonalback;
    }

    public Proceedings getPpProceedings() {
        return ppProceedings;
    }

    public void setPpProceedings(Proceedings ppProceedings) {
        this.ppProceedings = ppProceedings;
    }

}
