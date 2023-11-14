/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.EmailDto;
import cr.ac.una.clinicaws.model.ExcelDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Email;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author jomav
 */
@Stateless
@LocalBean
public class GenericSql {

    private static final Logger LOG = Logger.getLogger(GenericSql.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta VerificarSQL(ExcelDto excelDto){
        try {
            Query query = em.createNativeQuery(excelDto.getParametersDto().getPsQuery());
            List<String> headers = extractSQL(excelDto.getParametersDto().getPsQuery());
            List<Object[]> resultList = query.getResultList();
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Error haciendo la consulta sql", "GenericSql NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el SQL.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error haciendo la consulta sql", "GenericSql NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el SQL.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error haciendo la consulta sql", "GenericSql " + ex.getMessage());
        }
    }
    
    public Respuesta getSQL(ExcelDto excelDto) {
        try {
            Query query = em.createNativeQuery(excelDto.getParametersDto().getPsQuery());
            List<String> headers = extractSQL(excelDto.getParametersDto().getPsQuery());

            List<Object[]> resultList = query.getResultList();
            List<Map<String, Object>> rows = new ArrayList<>();
            for (Object result : resultList) {
                Map<String, Object> row = new HashMap<>();
                if (result instanceof Object[]) { // Result is an array of values
                    Object[] rowArray = (Object[]) result;
                    for (int i = 0; i < headers.size(); i++) {
                        row.put(headers.get(i), rowArray[i]);
                    }
                } else { // Result is a single value
                    row.put(headers.get(0), result);
                }
                rows.add(row);
            }
            GenerarExcel(excelDto, resultList, headers);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Lista", rows);
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Error haciendo la consulta sql", "GenericSql NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el SQL.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error haciendo la consulta sql", "GenericSql NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el SQL.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error haciendo la consulta sql", "GenericSql " + ex.getMessage());
        }
    }

    private void GenerarExcel(ExcelDto excelDto, List<Object[]> resultList, List<String> headersDB) {
        final int cont = 0;
        String nombreArchivo = "ExcelSql.xls";
        String hoja = "ConsultaSql";
        Workbook libro = new HSSFWorkbook();
        Sheet hoja1 = libro.createSheet(hoja);
        List<String> header = new ArrayList<>();
        for (String row : headersDB) {
            header.add(row);
        }
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);

        CellStyle headerCellStyle = libro.createCellStyle();
        Font headerFont = libro.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row headerRowTitule = hoja1.createRow(0);
        Cell cell = headerRowTitule.createCell(header.size() / 2);
        cell.setCellValue(excelDto.getParametersDto().getPsTitule());
        cell.setCellStyle(headerCellStyle);
        Row headerRow = hoja1.createRow(1);

        for (int i = 0; i < header.size(); i++) {
            Cell cellTitule = headerRowTitule.createCell(i);;
            cell = headerRow.createCell(i);
            cell.setCellValue(header.get(i));
            cell.setCellStyle(headerCellStyle);
            cellTitule.setCellStyle(headerCellStyle);
        }

        cell = headerRowTitule.createCell(header.size() / 2);
        cell.setCellValue(excelDto.getParametersDto().getPsTitule());
        cell.setCellStyle(headerCellStyle);

        //-------------------RESULTADO DE LA CONSULTA---------------------------
        Row headerRowData = hoja1.createRow(2);
        int j = 2;
        for (int k = 0; k < resultList.size(); k++) {
            for (int i = 0; i < header.size(); i++) {
                Cell cellData = headerRowData.createCell(i);
                if (resultList.get(k) instanceof Object[] && resultList.get(k)[i] != null) {
                    cellData.setCellValue(resultList.get(k)[i].toString());
                } else if (resultList.get(k) != null) {
                    Object objeto= resultList.get(k);
                     cellData.setCellValue(objeto.toString());
                } else {
                    cellData.setCellValue("N/A");
                }
            }
            j++;
            headerRowData = hoja1.createRow(j);
        }

        for (int k = 0; k < header.size(); k++) {
            hoja1.autoSizeColumn(k);
        }
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
            libro.write(fileOut);
            File archivoAdjunto = new File(nombreArchivo);
            Email email = new Email();

            List<String> correos = new ArrayList<>();
            for (EmailDto p : excelDto.getEmailDto()) {
                correos.add(p.getElEmail());
            }

            email.enviarExcel(excelDto, archivoAdjunto, correos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> extractSQL(String sql) {
        List<String> headers = new ArrayList<>();
        String lowercaseSql = sql.toLowerCase();
        if (lowercaseSql.startsWith("select") && lowercaseSql.contains("from")) {
            int start = lowercaseSql.indexOf("select") + 6;
            int end = lowercaseSql.indexOf("from");
            String columnPart = sql.substring(start, end).trim();
            String[] columns = columnPart.split(",");
            for (String column : columns) {
                column = column.trim().replaceAll(".*\\.().", "");
                int asIndex = column.toLowerCase().indexOf(" as ");
                if (asIndex != -1) {
                    column = column.substring(asIndex + 4).trim();
                }
                headers.add(column);
            }
        }
        return headers;
    }
}
