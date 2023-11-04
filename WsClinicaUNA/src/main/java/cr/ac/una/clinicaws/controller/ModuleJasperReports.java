package cr.ac.una.clinicaws.controller;

import cr.ac.una.clinicaws.model.AppointmentDto;
import cr.ac.una.clinicaws.model.DiaryDto;
import cr.ac.una.clinicaws.model.ReportDiary;
import cr.ac.una.clinicaws.service.AppointmentService;
import cr.ac.una.clinicaws.service.DiaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    @Context
    ServletContext context;
private static final Logger LOGGER = Logger.getLogger(ModuleJasperReports.class.getName());

    @GET
    @Path("/export-pdf")
    @Produces("application/pdf")
    public Response exportPdf() throws JRException, FileNotFoundException {
        List<AppointmentDto> citastotales = (List<AppointmentDto>) appointmentService.getAppointments().getResultado("Appointments");
        List<DiaryDto> lista = (List<DiaryDto>) diaryService.getDiaries().getResultado("Diaries");
        List<ReportDiary> reportesEmpleados = new ArrayList<>();
        
        for (int i = 0; i < citastotales.size(); i++) {
            reportesEmpleados.add(new ReportDiary(lista.get(i), citastotales.get(i)));
           LOGGER.log(Level.INFO,reportesEmpleados.get(i).getAtPatient());
        }
        
        byte[] pdfContent = exportToPdf(reportesEmpleados);
        String contentDisposition = "attachment; filename=\"petsReport.pdf\"";

        return Response.ok(pdfContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }

    @GET
    @Path("/export-xls")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public Response exportXls() throws JRException, FileNotFoundException {
        List<AppointmentDto> citastotales = (List<AppointmentDto>) appointmentService.getAppointments().getResultado("Appointments");
        List<DiaryDto> lista = (List<DiaryDto>) diaryService.getDiaries().getResultado("Diaries");
        List<ReportDiary> reportesEmpleados = new ArrayList<>();
        for (int i = 0; i < citastotales.size(); i++) {
            reportesEmpleados.add(new ReportDiary(lista.get(i), citastotales.get(i)));
        }
        byte[] xlsContent = exportToXls(reportesEmpleados); // Implementar este método para exportar el XLS
        String contentDisposition = "attachment; filename=\"petsReport.xls\"";

        return Response.ok(xlsContent)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .build();
    }
    
    public byte[] exportToPdf(List<ReportDiary> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    public byte[] exportToXls(List<ReportDiary> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<ReportDiary> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("dsReportAgenda", new JRBeanCollectionDataSource(list));
        InputStream reportStream = context.getResourceAsStream("/ReporteAgendaMedico.jrxml");
        JasperReport compiledReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint report = JasperFillManager.fillReport(compiledReport, params, new JREmptyDataSource());
        return report;
    }
}
