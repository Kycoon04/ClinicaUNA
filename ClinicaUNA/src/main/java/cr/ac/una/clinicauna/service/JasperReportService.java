/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.HistoryDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class JasperReportService {

    public Respuesta getDiaryDoctor(Integer id, String Date, String DateTwo,String Language) throws FileNotFoundException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Doctor", id);
            parametros.put("date", Date);
            parametros.put("dateTwo", DateTwo);
            parametros.put("Language", Language);
            Request request = new Request("ModuleJasperReports/ReportDiary", "/{date}/{dateTwo}/{Doctor}/{Language}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            byte[] pdfContent = request.getPdfContent();

            if (pdfContent != null) {
                // Guarda el contenido del PDF en un archivo local
                try (FileOutputStream outputStream = new FileOutputStream("Reporte Agenda.pdf")) {
                    outputStream.write(pdfContent);
                }
                return new Respuesta(true, "PDF descargado con éxito", "");
            } else {
                // El servidor no respondió con un PDF válido
                return new Respuesta(false, "No se pudo obtener el contenido del PDF", "");
            }
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error obteniendo  agenda [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo  agenda.", "getAgenda " + ex.getMessage());
        }
    }

    public Respuesta getProceedings(Integer id,String Language) throws FileNotFoundException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            parametros.put("Language", Language);
            Request request = new Request("ModuleJasperReports/ReportPatient", "/{id}/{Language}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            byte[] pdfContent = request.getPdfContent();

            if (pdfContent != null) {
                // Guarda el contenido del PDF en un archivo local
                try (FileOutputStream outputStream = new FileOutputStream("Reporte Expediente.pdf")) {
                    outputStream.write(pdfContent);
                }
                return new Respuesta(true, "PDF descargado con éxito", "");
            } else {
                // El servidor no respondió con un PDF válido
                return new Respuesta(false, "No se pudo obtener el contenido del PDF", "");
            }
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error obteniendo  agenda [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo  agenda.", "getAgenda " + ex.getMessage());
        }
    }
    
        public Respuesta getNotDiaryDoctor(Integer id, String Date, String DateTwo,String Language) throws FileNotFoundException {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("Doctor", id);
            parametros.put("date", Date);
            parametros.put("dateTwo", DateTwo);
            parametros.put("Language", Language);
            Request request = new Request("ModuleJasperReports/ReportEspacios", "/{date}/{dateTwo}/{Doctor}/{Language}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            byte[] pdfContent = request.getPdfContent();

            if (pdfContent != null) {
                // Guarda el contenido del PDF en un archivo local
                try (FileOutputStream outputStream = new FileOutputStream("Reporte espacios.pdf")) {
                    outputStream.write(pdfContent);
                }
                return new Respuesta(true, "PDF descargado con éxito", "");
            } else {
                // El servidor no respondió con un PDF válido
                return new Respuesta(false, "No se pudo obtener el contenido del PDF", "");
            }
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error obteniendo  agenda [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo  agenda.", "getAgenda " + ex.getMessage());
        }
    }
    
}
