/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.service.DiaryService;
import cr.ac.una.clinicaws.util.Email;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TimerService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author jomav
 */
@Singleton
@Startup
public class AutomaticEmail {

    private static final Logger LOGGER = Logger.getLogger(AutomaticEmail.class.getName());

    @Resource
    TimerService timerService;

    @PostConstruct
    public void init() {
        LOGGER.info("ScheduledTask iniciado");
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Schedule(hour = "23", minute = "16", persistent = false)
    public void executeTask() {

        ModuleDiary diario = new ModuleDiary();
        diario.setDiaryService(new DiaryService());
        List<DiaryDto> lista = new ArrayList<>();
        
        Respuesta respuesta = diario.diaryService.getDiaries();

        lista = (List<DiaryDto>) respuesta.getResultado("Diaries");
        
        
        List<DiaryDto> filteredList = lista.stream()
                .filter(distinctByKey(x -> x.getDySpace().getSeAppointment().getAtId()))
                .collect(Collectors.toList());

        filteredList = filteredList.stream()
                .filter(x -> LocalDate.now().plusDays(1).equals(x.getDyDate()))
                .collect(Collectors.toList());

        for (DiaryDto p : filteredList) {
           Email email = new Email();
           email.setDestinationMail(p.getDySpace().getSeAppointment().getAtEmail());
           email.enviarRecordatorio(p.getDyDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), p.getDySpace().getSeAppointment().getAtPatient().getPtName());
           
        }
    }
}
