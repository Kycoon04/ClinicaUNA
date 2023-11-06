/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.AppointmentDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
public class AppointmentService {

    public Respuesta getAppointmentId(Integer id) {
        try {

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleAppointment/appointment", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            AppointmentDto appointmentDto = (AppointmentDto) request.readEntity(AppointmentDto.class);
            return new Respuesta(true, "", "", "Appointments", appointmentDto);
        } catch (Exception ex) {
            Logger.getLogger(AppointmentService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta saveAppointment(AppointmentDto appointmentDto) {
        try {
            Request request = new Request("ModuleAppointment/appointment");
            request.post(appointmentDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            AppointmentDto userDtoEn = (AppointmentDto) request.readEntity(AppointmentDto.class);
            return new Respuesta(true, "", "", "Appointments", userDtoEn);
        } catch (Exception ex) {
            Logger.getLogger(AppointmentService.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            return new Respuesta(false, "Error guardando el usuario.", "guardarusuario" + ex.getMessage());
        }
    }

    public Respuesta deleteAppointment(Integer id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ModuleAppointment/appointment", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(AppointmentService.class.getName()).log(Level.SEVERE, "Error eliminando el usuario.", ex);
            return new Respuesta(false, "Error eliminando el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

    public List<AppointmentDto> getAppointments() {
        try {
            Map<String, Object> parametros = new HashMap<>();
            Request request = new Request("ModuleAppointment/appointment", "", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<AppointmentDto>) request.readEntity(new GenericType<List<AppointmentDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public List<AppointmentDto> getAppointmentsByDate(String date) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("date", date);
            Request request = new Request("ModuleAppointment/appointmentsbyDate", "/{date}", parametros);
            request.get();
            if (request.isError()) {
                System.out.println(request.getError());
                return null;
            }
            return (List<AppointmentDto>) request.readEntity(new GenericType<List<AppointmentDto>>() {
            });
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
