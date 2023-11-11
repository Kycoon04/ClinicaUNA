/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.model;

import java.util.Date;

/**
 *
 * @author jomav
 */
public class ReportVacio {

    private String rtState;
    private Date rtDate;
    private String fecha;
    private String estado;

    public ReportVacio() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ReportVacio(String rtState, Date rtDate, String language) {
        this.rtState = rtState;
        this.rtDate = rtDate;

        if (language.equals("Spanish")) {
            fecha = "Fecha";
            estado = "Estado";
        } else if (language.equals("English") || language.equals("Japanese")) {
            fecha = "Date";
            estado = "State";
        } else if (language.equals("French")) {
            fecha = "Date";
            estado = "État";
        }

    }

    public String getRtState() {
        return rtState;
    }

    public void setRtState(String rtState) {
        this.rtState = rtState;
    }

    public Date getRtDate() {
        return rtDate;
    }

    public void setRtDate(Date rtDate) {
        this.rtDate = rtDate;
    }

}
