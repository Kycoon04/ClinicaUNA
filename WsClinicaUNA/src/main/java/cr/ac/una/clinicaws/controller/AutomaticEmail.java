/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.model.EmailDto;
import cr.ac.una.clinicaws.model.ExcelDto;
import cr.ac.una.clinicaws.model.ParametersDto;
import cr.ac.una.clinicaws.model.ParametersSqlDto;
import cr.ac.una.clinicaws.service.DiaryService;
import cr.ac.una.clinicaws.service.EmailService;
import cr.ac.una.clinicaws.service.GenericSql;
import cr.ac.una.clinicaws.service.ParametersService;
import cr.ac.una.clinicaws.service.ParametersSqlService;
import cr.ac.una.clinicaws.util.Email;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
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

    @EJB
    private DiaryService diaryService;
    @EJB
    ParametersService ParamService;
    @EJB
    EmailService EmailService;
    @EJB
    GenericSql ServiceSql;
    @EJB
    ParametersSqlService ParamSqlService;
    @PostConstruct
    public void init() {
        LOGGER.info("ScheduledTask iniciado");
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    @Schedule(hour = "20", minute = "27", persistent = false)
    public void executeTask() {

        List<DiaryDto> lista = new ArrayList<>();

        Respuesta respuesta = diaryService.getDiaries();

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
            email.enviarRecordatorio(p.getDyDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), p.getDySpace().getSeAppointment().getAtPatient().getPtName(), p.getDySpace().getSeAppointment().getAtUserregister().getUsLenguage());
        }
        
        respuesta = ParamService.getParametersByFrequency(LocalDate.now());
        List<ParametersDto> parametros = (List<ParametersDto>) respuesta.getResultado("Parameters");
        List<ExcelDto> excelsDto = new ArrayList<>();

        for (int i = 0; i < parametros.size(); i++) {
            respuesta = EmailService.getSqlBySql(parametros.get(i).getPsId());
            List<EmailDto> emailDto = (List<EmailDto>) respuesta.getResultado("Email");
            respuesta = ParamSqlService.getParametersByFrequency(parametros.get(i).getPsId());
            List<ParametersSqlDto> parametrosSql = (List<ParametersSqlDto>) respuesta.getResultado("Parameters");
            ExcelDto excelDto = new ExcelDto(parametros.get(i), emailDto,parametrosSql);
            excelsDto.add(excelDto);
        }
        for (ExcelDto p : excelsDto) {
            respuesta = ServiceSql.getSQL(p);
            p.getParametersDto().setPsDateInit(p.getParametersDto().getPsDateSig());
            p.getParametersDto().setPsDateSig(DatePlus(p.getParametersDto().getPsTime(),p.getParametersDto().getPsDateInit()));
            respuesta = ParamService.saveParameters(p.getParametersDto());
        }
        
    }
        public LocalDate DatePlus(String date,LocalDate dateUpdate) {

        switch (date) {
            case "Daily":
                return dateUpdate.plusDays(1);
            case "Weekly":
                return dateUpdate.plusWeeks(1);
            case "Monthly":
                return dateUpdate.plusMonths(1);
        }
        return null;
    }

}
