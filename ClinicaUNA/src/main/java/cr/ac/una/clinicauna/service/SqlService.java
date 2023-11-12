/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.ExcelDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
public class SqlService {
    
        public Respuesta getExcel(ExcelDto excelDto) throws FileNotFoundException {
        try {
            Request request = new Request("ModuleSql/sql");
            request.post(excelDto);
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
    
}
