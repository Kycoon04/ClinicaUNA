/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.EmailDto;
import cr.ac.una.clinicauna.model.ParametersDto;
import cr.ac.una.clinicauna.model.ParametersSqlDto;
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
public class ParametersService {

    public List<ParametersDto> getParameters() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleSql/sql", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<ParametersDto>) request.readEntity(new GenericType<List<ParametersDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<ParametersSqlDto> getParametersBySql(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleSql/sql", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<ParametersSqlDto>) request.readEntity(new GenericType<List<ParametersSqlDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    public List<EmailDto> getEmail(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleSql/sqlEmail", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<EmailDto>) request.readEntity(new GenericType<List<EmailDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public Respuesta saveParameters(ParametersDto parametersDto) {
        try {
            Request request = new Request("ModuleSql/Parameter");
            request.post(parametersDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ParametersDto parameters = (ParametersDto) request.readEntity(ParametersDto.class);
            return new Respuesta(true, "", "", "Parameters", parameters);
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error guardando agenda.", ex);
            return new Respuesta(false, "Error guardando agenda.", "guardarAgenda" + ex.getMessage());
        }
    }

    public Respuesta saveParametersSql(ParametersSqlDto parametersDto) {
        try {
            Request request = new Request("ModuleSql/ParameterSql");
            request.post(parametersDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ParametersSqlDto parameters = (ParametersSqlDto) request.readEntity(ParametersSqlDto.class);
            return new Respuesta(true, "", "", "Parameters", parameters);
        } catch (Exception ex) {
            Logger.getLogger(DiaryService.class.getName()).log(Level.SEVERE, "Error guardando agenda.", ex);
            return new Respuesta(false, "Error guardando agenda.", "guardarAgenda" + ex.getMessage());
        }
    }

  

}
