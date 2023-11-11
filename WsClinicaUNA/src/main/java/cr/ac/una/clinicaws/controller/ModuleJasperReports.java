package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.AppointmentDto;
import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.model.ExamDto;
import cr.ac.una.clinicaws.model.FProceedingsDto;
import cr.ac.una.clinicaws.model.HistoryDto;
import cr.ac.una.clinicaws.model.PProceedingsDto;
import cr.ac.una.clinicaws.model.ProceedingsDto;
import cr.ac.una.clinicaws.model.ReportDiary;
import cr.ac.una.clinicaws.model.ReportDto;
import cr.ac.una.clinicaws.model.ReportHistograma;
import cr.ac.una.clinicaws.model.ReportIMC;
import cr.ac.una.clinicaws.model.ReportVacio;
import cr.ac.una.clinicaws.service.AppointmentService;
import cr.ac.una.clinicaws.service.DiaryService;
import cr.ac.una.clinicaws.service.ExamService;
import cr.ac.una.clinicaws.service.FProceedingsService;
import cr.ac.una.clinicaws.service.HistoryService;
import cr.ac.una.clinicaws.service.PProceedingsService;
import cr.ac.una.clinicaws.service.ProceedingsService;
import cr.ac.una.clinicaws.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Path("/ModuleJasperReports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "JasperReports", description = "Operations on Diary")
//@Secure
public class ModuleJasperReports {

    @EJB
    DiaryService diaryService;
    @EJB
    AppointmentService appointmentService;
    @EJB
    ProceedingsService proceedingsService;
    @EJB
    PProceedingsService pProceedingsService;
    @EJB
    FProceedingsService fProceedingsService;
    @EJB
    ReportService reportService;
    @EJB
    HistoryService historyService;
    @EJB
    ExamService examService;
    @Context
    ServletContext context;

    @GET
    @Path("/ReportDiary/{date}/{dateTwo}/{Doctor}/{Language}")
    @Produces("application/pdf")
    public Response exportPdf(@PathParam("date") String date, @PathParam("dateTwo") String dateTwo, @PathParam("Doctor") Integer Doctor, @PathParam("Language") String language) throws JRException, FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDateTwo;
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        if (dateTwo.equals("N/A")) {
            parsedDateTwo = LocalDate.parse(date, formatter);
        } else {
            parsedDateTwo = LocalDate.parse(dateTwo, formatter);
        }

        List<DiaryDto> lista = (List<DiaryDto>) diaryService.getByDate(parsedDate, parsedDateTwo, Doctor).getResultado("Diaries");
        List<ReportDiary> reportesEmpleados = new ArrayList<>();

        if (lista.size() == 0) {
            reportesEmpleados.add(new ReportDiary());
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getDySpace().getSeAppointment().getAtState().equals("Cancelada")) {
                    reportesEmpleados.add(new ReportDiary(lista.get(i), new AppointmentDto(lista.get(i).getDySpace().getSeAppointment()), language));
                }
            }
        }
        byte[] pdfContent = exportToPdf(reportesEmpleados, lista.get(0).getDyDoctor().getDrUser().getUsName(), language);
        String contentDisposition = "attachment; filename=\"Reporte Agenda de " + lista.get(0).getDyDoctor().getDrUser().getUsName() + ".pdf\"";
        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    @GET
    @Path("/ReportPatient/{id}/{Language}")
    @Produces("application/pdf")
    public Response exportPdfPatient(@PathParam("id") Integer id, @PathParam("Language") String language) throws JRException, FileNotFoundException {
        ProceedingsDto expediente = (ProceedingsDto) proceedingsService.getProceedingsbyPatId(id).getResultado("Proceedings");
        List<PProceedingsDto> personales = (List<PProceedingsDto>) pProceedingsService.getProceeding(expediente.getPsId()).getResultado("PProceeding");
        List<FProceedingsDto> familiares = (List<FProceedingsDto>) fProceedingsService.getProceeding(expediente.getPsId()).getResultado("FProceeding");
        List<AppointmentDto> citas = (List<AppointmentDto>) appointmentService.getAppointmentsPatient(id).getResultado("Appointments");

        List<ReportDto> reportes = new ArrayList<>();
        List<ReportIMC> IMC = new ArrayList<>();
        List<ExamDto> examenes = (List<ExamDto>) examService.getExamsByPatientId(id.longValue()).getResultado("Exams");

        for (AppointmentDto p : citas) {
            if ((ReportDto) reportService.getReports(p.getAtId()).getResultado("Report") != null) {
                reportes.add((ReportDto) reportService.getReports(p.getAtId()).getResultado("Report"));
            }
        }
        for (ReportDto p : reportes) {
            IMC.add(new ReportIMC(p));
        }

        for (int i = 0; i < personales.size(); i++) {
            if (language.equals("Spanish")) {
                personales.get(i).setTipo("Tipo");
                personales.get(i).setDescripcion("Descripción");
            } else if (language.equals("English") || language.equals("Japanese")) {
                personales.get(i).setTipo("Type");
                personales.get(i).setDescripcion("Description");
            } else if (language.equals("French")) {
                personales.get(i).setTipo("taper");
                personales.get(i).setDescripcion("Description");
            }
        }
        for (int i = 0; i < examenes.size(); i++) {
            if (language.equals("Spanish")) {
                examenes.get(i).setDoctor("Doctor");
                examenes.get(i).setNombre("Nombre");
                examenes.get(i).setNotas("Notas");
            } else if (language.equals("English") || language.equals("Japanese")) {
                examenes.get(i).setDoctor("Doctor");
                examenes.get(i).setNombre("Name");
                examenes.get(i).setNotas("Notes");
            } else if (language.equals("French")) {
                examenes.get(i).setDoctor("Docteur");
                examenes.get(i).setNombre("Nom");
                examenes.get(i).setNotas("Notes");
            }
        }
        for (int i = 0; i < familiares.size(); i++) {
            if (language.equals("Spanish")) {
                familiares.get(i).setRelacion("Relación");
                familiares.get(i).setEnfermedad("Enfermedad");
            } else if (language.equals("English") || language.equals("Japanese")) {
                familiares.get(i).setRelacion("Relation");
                familiares.get(i).setEnfermedad("Disease");
            } else if (language.equals("French")) {
                familiares.get(i).setRelacion("Relation");
                familiares.get(i).setEnfermedad("Maladie");
            }
        }

        for (int i = 0; i < reportes.size(); i++) {
            if (language.equals("Spanish")) {
                reportes.get(i).setFecha("Fecha");
                reportes.get(i).setCodigo("Codigo");
                reportes.get(i).setPresion("Presion");
                reportes.get(i).setRitmo("Ritmo Cardiaco");
                reportes.get(i).setPeso("Peso");
                reportes.get(i).setAltura("Altura");
                reportes.get(i).setTemperatura("Temperatura");
                reportes.get(i).setMasa("Masa corporal");
                reportes.get(i).setNotas("Notas de enfermera");
                reportes.get(i).setRazon("Razón de la consulta");
                reportes.get(i).setPlan("Plan de anteción");
                reportes.get(i).setTratamiento("Tratamiento");
                reportes.get(i).setExamenFisico("Examen Fisico");
                reportes.get(i).setObservaciones("Observaciones");

            } else if (language.equals("English") || language.equals("Japanese")) {
                reportes.get(i).setFecha("Date");
                reportes.get(i).setCodigo("Code");
                reportes.get(i).setPresion("Pressure");
                reportes.get(i).setRitmo("Heart Rate");
                reportes.get(i).setPeso("Weight");
                reportes.get(i).setAltura("Height");
                reportes.get(i).setTemperatura("Temperature");
                reportes.get(i).setMasa("Body Mass");
                reportes.get(i).setNotas("Nurse's Notes");
                reportes.get(i).setRazon("Reason for Consultation");
                reportes.get(i).setPlan("Care Plan");
                reportes.get(i).setTratamiento("Treatment");
                reportes.get(i).setExamenFisico("Physical Examination");
                reportes.get(i).setObservaciones("Observations");
            } else if (language.equals("French")) {
                reportes.get(i).setFecha("Date");
                reportes.get(i).setCodigo("Code");
                reportes.get(i).setPresion("Pression");
                reportes.get(i).setRitmo("Rythme Cardiaque");
                reportes.get(i).setPeso("Poids");
                reportes.get(i).setAltura("Taille");
                reportes.get(i).setTemperatura("Température");
                reportes.get(i).setMasa("Masse corporelle");
                reportes.get(i).setNotas("Notes de l'infirmière");
                reportes.get(i).setRazon("Raison de la consultation");
                reportes.get(i).setPlan("Plan de soins");
                reportes.get(i).setTratamiento("Traitement");
                reportes.get(i).setExamenFisico("Examen Physique");
                reportes.get(i).setObservaciones("Observations");
            }
        }
        byte[] pdfContent = exportToPdfPatient(personales, familiares, reportes, examenes, IMC, language);
        String contentDisposition = "attachment; filename=\"Reporte del paciente " + citas.get(0).getAtPatient().getPtName() + ".pdf\"";
        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    @GET
    @Path("/ReportEspacios/{date}/{dateTwo}/{Doctor}/{Language}")
    @Produces("application/pdf")
    public Response exportPdfEspacios(@PathParam("date") String date, @PathParam("dateTwo") String dateTwo, @PathParam("Doctor") Integer Doctor, @PathParam("Language") String language) throws JRException, FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDateTwo;
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        if (dateTwo.equals("N/A")) {
            parsedDateTwo = LocalDate.parse(date, formatter);
        } else {
            parsedDateTwo = LocalDate.parse(dateTwo, formatter);
        }
        List<DiaryDto> lista = (List<DiaryDto>) diaryService.getByDate(parsedDate, parsedDateTwo, Doctor).getResultado("Diaries");
        List<HistoryDto> historial = (List<HistoryDto>) historyService.getHistoryByDoctor(Doctor).getResultado("historytime");
        List<ReportHistograma> Histograma = new ArrayList<>();
        List<ReportVacio> resultado = new ArrayList<>();
        int cantidadCancelada = 0;
        int cantidadSinAgendar = 0;
        for (LocalDate datefor = parsedDate; !datefor.isAfter(parsedDateTwo); datefor = datefor.plusDays(1)) {
            final LocalDate currentDate = datefor;
            List<DiaryDto> Aux = lista.stream().filter(x -> x.getDyDate().equals(currentDate)).collect(Collectors.toList());

            HistoryDto filteredList = historial.stream()
                    .filter(x -> (currentDate.isAfter(x.getHtDate()) || currentDate.isEqual(x.getHtDate()))
                    && (x.getHtDateFinal() == null || currentDate.isBefore(x.getHtDateFinal()) || currentDate.isEqual(x.getHtDateFinal())))
                    .findFirst()
                    .orElse(null);

            String horaInicio = filteredList.getHtIniworking();
            String horaFin = filteredList.getHtFinisworking();
            String[] partesInicio = horaInicio.split(":");
            String[] partesFin = horaFin.split(":");
            int horaInicioInt = Integer.parseInt(partesInicio[0]);
            int horaFinInt = Integer.parseInt(partesFin[0]);
            int contHora = horaInicioInt;
            String[] mint = new String[filteredList.getHtSpaces()];

            if (filteredList.getHtSpaces() == 2) {
                mint[0] = "00";
                mint[1] = "30";
            } else if (filteredList.getHtSpaces() == 3) {
                mint[0] = "00";
                mint[1] = "20";
                mint[2] = "40";
            } else {
                mint[0] = "00";
                mint[1] = "15";
                mint[2] = "30";
                mint[3] = "45";
            }

            for (int i = 0; i < (horaFinInt - horaInicioInt); i++) {

                for (int j = 0; j < filteredList.getHtSpaces(); j++) {
                    String horaString;
                    LocalDate localDate = currentDate;
                    if ((contHora + i) < 10) {
                        horaString = "0" + (contHora + i) + ":" + mint[j];
                    } else {
                        horaString = (contHora + i) + ":" + mint[j];
                    }
                    LocalTime timeFromString = LocalTime.parse(horaString);
                    boolean exists = Aux.stream()
                            .anyMatch(item -> {
                                LocalTime seHour = LocalTime.parse(item.getDySpace().getSeHour());
                                return seHour.equals(timeFromString);
                            });
                    if (!exists) {
                        LocalTime localTime = LocalTime.parse(horaString);
                        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                        Date dateDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                        resultado.add(new ReportVacio("Sin agendar", dateDate,language));
                        cantidadSinAgendar++;
                    }
                }
            }
            for (int i = 0; i < Aux.size(); i++) {
                if (Aux.get(i).getDySpace().getSeAppointment().getAtState().equals("Cancelada")) {
                    LocalDate localDate = Aux.get(i).getDyDate();
                    String horaString = Aux.get(i).getDySpace().getSeHour();
                    LocalTime localTime = LocalTime.parse(horaString);
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                    Date dateDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                    resultado.add(new ReportVacio("Cancelada", dateDate,language));
                    cantidadCancelada++;
                }
            }
            Histograma.add(new ReportHistograma("Sin agendar", datefor.format(formatter), cantidadSinAgendar));
            Histograma.add(new ReportHistograma("Cancelada", datefor.format(formatter), cantidadCancelada));
            cantidadCancelada = 0;
            cantidadSinAgendar = 0;
        }

        byte[] pdfContent = exportToPdfAppointment(resultado, Histograma, lista.get(0).getDyDoctor().getDrUser().getUsName(),language);
        String contentDisposition = "attachment; filename=\"Reporte del doctor " + lista.get(0).getDyDoctor().getDrUser().getUsName() + ".pdf\"";
        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    public byte[] exportToPdfAppointment(List<ReportVacio> list, List<ReportHistograma> listHistograma, String name, String Language) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReportAppointment(list, listHistograma, name,Language));
    }

    private JasperPrint getReportAppointment(List<ReportVacio> list, List<ReportHistograma> listHistograma, String name, String Language) throws FileNotFoundException, JRException {
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(list.toArray());
        JRBeanArrayDataSource dsHistograma = new JRBeanArrayDataSource(listHistograma.toArray());
        Map<String, Object> params = new HashMap<String, Object>();
        InputStream LogoClinic = context.getResourceAsStream("/LogoMedicalClinic.png");
        InputStream ImageBackground = context.getResourceAsStream("/LogoMedicalClinic.png");
        params.put("dsTable", ds);
        params.put("ds", dsHistograma);
        params.put("Doctor", name);
        
        if (Language.equals("Spanish")) {
            params.put("FechaTitulo", "Fecha de generación: ");
            params.put("TituloCentral", "REPORTE DE LA AGENDA DEL DOCTOR");
        } else if (Language.equals("English") || Language.equals("Japanese")) {
            params.put("FechaTitulo", "Generation Date: ");
            params.put("TituloCentral", "DOCTOR'S SCHEDULE REPORT");
        } else if (Language.equals("French")) {
            params.put("FechaTitulo", "Date de génération: ");
            params.put("TituloCentral", "RAPPORT DE L'AGENDA DU MÉDECIN");
        }
        
        params.put("LogoClinic", LogoClinic);
        params.put("Imagebackgroud", ImageBackground);
        InputStream reportStream = context.getResourceAsStream("/ReportAppointment.jrxml");
        JasperReport compiledReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint report = JasperFillManager.fillReport(compiledReport, params, new JREmptyDataSource());
        return report;
    }

    public byte[] exportToPdf(List<ReportDiary> list, String name, String Language) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list, name, Language));
    }

    private JasperPrint getReport(List<ReportDiary> list, String name, String Language) throws FileNotFoundException, JRException {
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(list.toArray());
        JRBeanArrayDataSource dsAgenda = new JRBeanArrayDataSource(list.toArray());
        Map<String, Object> params = new HashMap<String, Object>();
        InputStream LogoClinic = context.getResourceAsStream("/LogoMedicalClinic.png");
        InputStream ImageBackground = context.getResourceAsStream("/LogoMedicalClinic.png");
        params.put("ds", ds);
        params.put("Doctor", name);

        if (Language.equals("Spanish")) {
            params.put("FechaTitulo", "Fecha de generación: ");
            params.put("TituloCentral", "REPORTE DE LA AGENDA DEL DOCTOR");
        } else if (Language.equals("English") || Language.equals("Japanese")) {
            params.put("FechaTitulo", "Generation Date: ");
            params.put("TituloCentral", "DOCTOR'S SCHEDULE REPORT");
        } else if (Language.equals("French")) {
            params.put("FechaTitulo", "Date de génération: ");
            params.put("TituloCentral", "RAPPORT DE L'AGENDA DU MÉDECIN");
        }

        params.put("LogoClinic", LogoClinic);
        params.put("Imagebackgroud", ImageBackground);
        InputStream reportStream = context.getResourceAsStream("/ReporteAgendaMedico.jrxml");
        JasperReport compiledReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint report = JasperFillManager.fillReport(compiledReport, params, new JREmptyDataSource());
        return report;
    }

    public byte[] exportToPdfPatient(List<PProceedingsDto> listPP, List<FProceedingsDto> listFP, List<ReportDto> listReporte, List<ExamDto> listExam, List<ReportIMC> IMC, String language) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReportPatient(listPP, listFP, listReporte, listExam, IMC, language));
    }

    private JasperPrint getReportPatient(List<PProceedingsDto> listPP, List<FProceedingsDto> listFP, List<ReportDto> listReporte, List<ExamDto> listExam, List<ReportIMC> IMC, String language) throws FileNotFoundException, JRException {
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(listPP.toArray());
        JRBeanArrayDataSource dsFamily = new JRBeanArrayDataSource(listFP.toArray());
        JRBeanArrayDataSource dsExamen = new JRBeanArrayDataSource(listExam.toArray());
        JRBeanArrayDataSource dsAppointment = new JRBeanArrayDataSource(listReporte.toArray());
        JRBeanArrayDataSource dsHistogramasource = new JRBeanArrayDataSource(IMC.toArray());
        Map<String, Object> params = new HashMap<String, Object>();
        InputStream LogoClinic = context.getResourceAsStream("/LogoMedicalClinic.png");
        InputStream ImageBackground = context.getResourceAsStream("/LogoMedicalClinic.png");
        params.put("ds", ds);
        params.put("dsHistogramasource", dsHistogramasource);
        params.put("dsExamen", dsExamen);
        params.put("dsAppointment", dsAppointment);

        if (language.equals("Spanish")) {
            params.put("FechaTitulo", "Fecha de generación: ");
            params.put("TituloCentral", "REPORTE DEL EXPEDIENTE DEL PACIENTE");
            params.put("PacienteTitulo", "Paciente");
            params.put("AntPersonales", "Antecedentes Personales");
            params.put("AntFamiliares", "Antecedentes Familiares");
        } else if (language.equals("English") || language.equals("Japanese")) {
            params.put("FechaTitulo", "Generation Date: ");
            params.put("PacienteTitulo", "Patient");
            params.put("TituloCentral", "PATIENT RECORD REPORT");
            params.put("AntPersonales", "Personal History");
            params.put("AntFamiliares", "Family History");
        } else if (language.equals("French")) {
            params.put("FechaTitulo", "Date de génération: ");
            params.put("TituloCentral", "RAPPORT DU DOSSIER DU PATIENT");
            params.put("PacienteTitulo", "Patient");
            params.put("AntPersonales", "Antécédents Personnels");
            params.put("AntFamiliares", "Antécédents Familiaux");
        }

        params.put("Paciente", listReporte.get(0).getRtAppointment().getAtPatient().getPtName());
        params.put("dsFamily", dsFamily);
        params.put("LogoClinic", LogoClinic);
        params.put("Imagebackgroud", ImageBackground);
        InputStream reportStream = context.getResourceAsStream("/ReportPatient.jrxml");
        JasperReport compiledReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint report = JasperFillManager.fillReport(compiledReport, params, new JREmptyDataSource());
        return report;
    }
}
