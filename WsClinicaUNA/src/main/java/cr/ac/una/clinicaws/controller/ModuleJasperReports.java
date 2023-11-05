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
    @Path("/ReportDiary/{date}/{dateTwo}/{Doctor}")
    @Produces("application/pdf")
    public Response exportPdf(@PathParam("date") String date, @PathParam("dateTwo") String dateTwo, @PathParam("Doctor") Integer Doctor) throws JRException, FileNotFoundException {
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
                reportesEmpleados.add(new ReportDiary(lista.get(i), new AppointmentDto(lista.get(i).getDySpace().getSeAppointment())));
            }
        }
        byte[] pdfContent = exportToPdf(reportesEmpleados, lista.get(0).getDyDoctor().getDrUser().getUsName());
        String contentDisposition = "attachment; filename=\"Reporte Agenda de " + lista.get(0).getDyDoctor().getDrUser().getUsName() + ".pdf\"";
        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    @GET
    @Path("/ReportPatient/{id}")
    @Produces("application/pdf")
    public Response exportPdfPatient(@PathParam("id") Integer id) throws JRException, FileNotFoundException {
        ProceedingsDto expediente = (ProceedingsDto) proceedingsService.getProceedingsbyPatId(id).getResultado("Proceedings");
        List<PProceedingsDto> personales = (List<PProceedingsDto>) pProceedingsService.getProceeding(expediente.getPsId()).getResultado("PProceeding");
        List<FProceedingsDto> familiares = (List<FProceedingsDto>) fProceedingsService.getProceeding(expediente.getPsId()).getResultado("FProceeding");
        List<AppointmentDto> citas = (List<AppointmentDto>) appointmentService.getAppointmentsPatient(id).getResultado("Appointments");
        List<ReportDto> reportes = new ArrayList<>();
        List<ReportIMC> IMC = new ArrayList<>();
        for (AppointmentDto p : citas) {
            if ((ReportDto) reportService.getReports(p.getAtId()).getResultado("Report") != null) {
                reportes.add((ReportDto) reportService.getReports(p.getAtId()).getResultado("Report"));
            }
        }
        for (ReportDto p : reportes) {
            IMC.add(new ReportIMC(p));
        }
        List<ExamDto> examenes = (List<ExamDto>) examService.getExamsByPatientId(id.longValue()).getResultado("Exams");
        byte[] pdfContent = exportToPdfPatient(personales, familiares, reportes, examenes, IMC);
        String contentDisposition = "attachment; filename=\"Reporte del paciente " + citas.get(0).getAtPatient().getPtName() + ".pdf\"";
        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    @GET
    @Path("/ReportEspacios/{date}/{dateTwo}/{Doctor}")
    @Produces("application/pdf")
    public Response exportPdfEspacios(@PathParam("date") String date, @PathParam("dateTwo") String dateTwo, @PathParam("Doctor") Integer Doctor) throws JRException, FileNotFoundException {
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
        int cantidadAusente = 0;

        for (LocalDate datefor = parsedDate; !datefor.isAfter(parsedDateTwo); datefor = datefor.plusDays(1)) {
            final LocalDate currentDate = datefor;
            List<DiaryDto> Aux = lista.stream().filter(x -> x.getDyDate().equals(currentDate)).collect(Collectors.toList());
            HistoryDto filteredList = historial.stream()
                    .filter(x -> (currentDate.isAfter(x.getHtDate()) || currentDate.isEqual(x.getHtDate()))
                    && (currentDate.isBefore(x.getHtDateFinal()) || currentDate.isEqual(x.getHtDateFinal()))).findFirst().get();

            for (int i = 0; i < Aux.size(); i++) {
                if (Aux.get(i).getDySpace().getSeAppointment().getAtState().equals("Cancelada")) {
                    LocalDate localDate = Aux.get(i).getDyDate();
                    String horaString = Aux.get(i).getDySpace().getSeHour();
                    LocalTime localTime = LocalTime.parse(horaString);
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                    Date dateDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                    resultado.add(new ReportVacio("Cancelada", dateDate));
                    cantidadCancelada++;
                }
                if (Aux.get(i).getDySpace().getSeAppointment().getAtState().equals("Ausente")) {
                    LocalDate localDate = Aux.get(i).getDyDate();
                    String horaString = Aux.get(i).getDySpace().getSeHour();
                    LocalTime localTime = LocalTime.parse(horaString);
                    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                    Date dateDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                    resultado.add(new ReportVacio("Ausente", dateDate));
                    cantidadAusente++;
                }
            }
            
            Histograma.add(new ReportHistograma("Cancelada", datefor.format(formatter), cantidadCancelada));
            Histograma.add(new ReportHistograma("Ausente", datefor.format(formatter), cantidadAusente));
            cantidadCancelada = 0;
            cantidadAusente = 0;
        }

        byte[] pdfContent = exportToPdfAppointment(resultado, Histograma);
        String contentDisposition = "attachment; filename=\"Reporte del doctor " + lista.get(0).getDyDoctor().getDrUser().getUsName() + ".pdf\"";
        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    public byte[] exportToPdfAppointment(List<ReportVacio> list, List<ReportHistograma> listHistograma) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReportAppointment(list, listHistograma));
    }

    private JasperPrint getReportAppointment(List<ReportVacio> list, List<ReportHistograma> listHistograma) throws FileNotFoundException, JRException {
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(list.toArray());
        JRBeanArrayDataSource dsHistograma = new JRBeanArrayDataSource(listHistograma.toArray());
        Map<String, Object> params = new HashMap<String, Object>();
        InputStream LogoClinic = context.getResourceAsStream("/LogoMedicalClinic.png");
        InputStream ImageBackground = context.getResourceAsStream("/LogoMedicalClinic.png");
        params.put("dsTable", ds);
        params.put("ds", dsHistograma);
        params.put("LogoClinic", LogoClinic);
        params.put("Imagebackgroud", ImageBackground);
        InputStream reportStream = context.getResourceAsStream("/ReportAppointment.jrxml");
        JasperReport compiledReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint report = JasperFillManager.fillReport(compiledReport, params, new JREmptyDataSource());
        return report;
    }

    public byte[] exportToPdf(List<ReportDiary> list, String name) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list, name));
    }

    private JasperPrint getReport(List<ReportDiary> list, String name) throws FileNotFoundException, JRException {
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(list.toArray());
        Map<String, Object> params = new HashMap<String, Object>();
        InputStream LogoClinic = context.getResourceAsStream("/LogoMedicalClinic.png");
        InputStream ImageBackground = context.getResourceAsStream("/LogoMedicalClinic.png");
        params.put("ds", ds);
        params.put("Doctor", name);
        params.put("LogoClinic", LogoClinic);
        params.put("Imagebackgroud", ImageBackground);
        InputStream reportStream = context.getResourceAsStream("/ReporteAgendaMedico.jrxml");
        JasperReport compiledReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint report = JasperFillManager.fillReport(compiledReport, params, new JREmptyDataSource());
        return report;
    }

    public byte[] exportToPdfPatient(List<PProceedingsDto> listPP, List<FProceedingsDto> listFP, List<ReportDto> listReporte, List<ExamDto> listExam, List<ReportIMC> IMC) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReportPatient(listPP, listFP, listReporte, listExam, IMC));
    }

    private JasperPrint getReportPatient(List<PProceedingsDto> listPP, List<FProceedingsDto> listFP, List<ReportDto> listReporte, List<ExamDto> listExam, List<ReportIMC> IMC) throws FileNotFoundException, JRException {
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
