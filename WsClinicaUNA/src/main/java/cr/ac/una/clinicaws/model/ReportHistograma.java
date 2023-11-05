/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;
/**
 *
 * @author jomav
 */
public class ReportHistograma {
    private String rtState;
    private String rtDate;
    private Integer rtCantidad;

    public ReportHistograma(String rtState, String rtDate, Integer rtCantidad) {
        this.rtState = rtState;
        this.rtDate = rtDate;
        this.rtCantidad = rtCantidad;
    }

    public String getRtState() {
        return rtState;
    }

    public void setRtState(String rtState) {
        this.rtState = rtState;
    }

    public String getRtDate() {
        return rtDate;
    }

    public void setRtDate(String rtDate) {
        this.rtDate = rtDate;
    }

    public Integer getRtCantidad() {
        return rtCantidad;
    }

    public void setRtCantidad(Integer rtCantidad) {
        this.rtCantidad = rtCantidad;
    }
    
    
}
