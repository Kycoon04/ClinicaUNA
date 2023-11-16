/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.model.DiaryDto;
import cr.ac.una.clinicauna.model.ParametersDto;
import cr.ac.una.clinicauna.util.Request;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<ParametersDto> getParametersBySql(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleSql/sql", "/{id}", parametros);
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

    public List<ParametersDto> ParametersBySql(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleSql/sql", "/{id}", parametros);
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

}
