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
public class ReportIMC {
    private double rtBodyMass;
    private Date rtDate;

    public ReportIMC() {
    }
    public ReportIMC(ReportDto report) {
        rtBodyMass = report.getRtBodyMass();
        rtDate = report.getRtDate();
    }
    public double getRtBodyMass() {
        return rtBodyMass;
    }

    public void setRtBodyMass(double rtBodyMass) {
        this.rtBodyMass = rtBodyMass;
    }

    public Date getRtDate() {
        return rtDate;
    }

    public void setRtDate(Date rtDate) {
        this.rtDate = rtDate;
    }
    
    
}
