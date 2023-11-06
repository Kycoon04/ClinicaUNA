/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.HistoryDto;
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
 * @author jomav
 */
public class HistoryService {
    
    public Respuesta getHistoryId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleHistory/history", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            HistoryDto historyDto = (HistoryDto) request.readEntity(HistoryDto.class);
            return new Respuesta(true, "", "", "history", historyDto);
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error obteniendo  agenda [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo  agenda.", "getAgenda " + ex.getMessage());
        }
    }
    
    public Respuesta saveHistory(HistoryDto historyDto) {
        try {
            Request request = new Request("ModuleHistory/history");
            request.post(historyDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            HistoryDto historyDtos = (HistoryDto) request.readEntity(HistoryDto.class);
            return new Respuesta(true, "", "", "Diary", historyDtos);
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error guardando agenda.", ex);
            return new Respuesta(false, "Error guardando agenda.", "guardarAgenda" + ex.getMessage());
        }
    }
//

    public Respuesta deleteHistory(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleHistory/history", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error eliminando agenda.", ex);
            return new Respuesta(false, "Error eliminando agenda.", "eliminareagenda " + ex.getMessage());
        }
    }

    public List<HistoryDto> getHistorys() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleHistory/history", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<HistoryDto>) request.readEntity(new GenericType<List<HistoryDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public List<HistoryDto> getHistorysByDoctor(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleHistory/historys", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<HistoryDto>) request.readEntity(new GenericType<List<HistoryDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    public Respuesta getHistorysByDate(String date,Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("date", date);
            parametros.put("id", id);
            Request request = new Request("ModuleHistory/historysRange", "/{date}/{id}", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            HistoryDto historyDto = (HistoryDto) request.readEntity(HistoryDto.class);
            return new Respuesta(true, "", "", "history", historyDto);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
