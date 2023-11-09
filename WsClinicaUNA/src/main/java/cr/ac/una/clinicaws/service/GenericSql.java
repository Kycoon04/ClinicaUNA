/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Respuesta getSQL(String consulta) {
        try {
            Query query = em.createNativeQuery(consulta);
            List<String> headers = extractSQL(consulta);

            for (String p : headers) {
                LOG.info("Encabezados encontrados: " + p);
            }

            List<Object[]> resultList = query.getResultList();
            List<Map<String, Object>> rows = new ArrayList<>();
            for (int j = 0; j < resultList.size(); j++) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    row.put(headers.get(i), resultList.get(j)[i]);
                }
                rows.add(row);
            }

            for (Map<String, Object> row : rows) {
                LOG.info(row.toString());
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "","Lista",rows);
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Error haciendo la consulta sql", "GenericSql NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error haciendo la consulta sql", "GenericSql NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el PProceedings.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Error haciendo la consulta sql", "GenericSql " + ex.getMessage());
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
