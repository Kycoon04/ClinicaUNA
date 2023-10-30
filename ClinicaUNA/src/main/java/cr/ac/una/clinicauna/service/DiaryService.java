/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.SpaceDto;
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
public class DiaryService {
     public Respuesta getDiaryId(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDiary/diary", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DiaryDto diaryDto = (DiaryDto) request.readEntity(DiaryDto.class);
            System.out.println(diaryDto.getDyDoctor().getDrId());
            return new Respuesta(true, "", "", "Diary",diaryDto);
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error obteniendo  agenda [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo  agenda.", "getAgenda " + ex.getMessage());
        }
    }
     
    public Respuesta emailDiary(DiaryDto diaryDtos) {
        try {
            Request request = new Request("ModuleDiary/diaryemail");
            request.post(diaryDtos);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error guardando agenda.", ex);
            return new Respuesta(false, "Error guardando agenda.", "guardarAgenda" + ex.getMessage());
        }
    }

    public Respuesta saveDiary(DiaryDto diaryDtos) {
        try {
            Request request = new Request("ModuleDiary/diary");
            request.post(diaryDtos);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            DiaryDto diaryDto = (DiaryDto) request.readEntity(DiaryDto.class);
            return new Respuesta(true, "", "", "Diary", diaryDto);
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error guardando agenda.", ex);
            return new Respuesta(false, "Error guardando agenda.", "guardarAgenda" + ex.getMessage());
        }
    }
//
    public Respuesta deleteDiary(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleDiary/diary", "/{id}", parametros);
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

    public List<DiaryDto> getDiary() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleDiary/diary", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<DiaryDto>) request.readEntity(new GenericType<List<DiaryDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
