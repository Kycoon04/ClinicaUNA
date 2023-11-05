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

    public ReportVacio() {
    }

    public ReportVacio(String rtState, Date rtDate) {
        this.rtState = rtState;
        this.rtDate = rtDate;
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
