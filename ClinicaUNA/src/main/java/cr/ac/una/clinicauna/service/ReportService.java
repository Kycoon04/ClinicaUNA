/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.ExamDto;
import cr.ac.una.clinicauna.model.ReportDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilan
 */
public class ReportService {
    
     public Respuesta getReportById(Integer id) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleReport/report", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ReportDto reportDto = (ReportDto) request.readEntity(ReportDto.class);
            return new Respuesta(true, "", "", "Report", reportDto);
        } catch (Exception ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, "Error obteniendo el reporte [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el reporte.", "getreport " + ex.getMessage());
        }
    }

    public Respuesta saveReport(ReportDto reportsDto) {
        try {
            Request request = new Request("ModuleReport/report");
            request.post(reportsDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ReportDto reportDto = (ReportDto) request.readEntity(ReportDto.class);
            return new Respuesta(true, "", "", "Report", reportDto);
        } catch (Exception ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, "Error guardando el Reporte.", ex);
            return new Respuesta(false, "Error guardando el Reporte.", "guardarReporte" + ex.getMessage());
        }
    }

    public Respuesta deleteReport(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleReport/report", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, "Error eliminando el Reporte.", ex);
            return new Respuesta(false, "Error eliminando el Reporte.", "eliminarReporte " + ex.getMessage());
        }
    }

    public List<ReportDto> getReports() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleReport/report", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<ReportDto>) request.readEntity(new GenericType<List<ReportDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    
}
