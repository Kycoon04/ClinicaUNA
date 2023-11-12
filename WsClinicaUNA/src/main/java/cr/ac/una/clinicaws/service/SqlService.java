/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Sql;
import cr.ac.una.clinicaws.model.SqlDto;
import cr.ac.una.clinicaws.util.CodigoRespuesta;
import cr.ac.una.clinicaws.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
@Stateless
@LocalBean
public class SqlService {
    private static final Logger LOG = Logger.getLogger(ParametersService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    public Respuesta getSql(Integer sqlId) {
        try {
            Query qryParameters = em.createNamedQuery("Sql.findBySqlId", Sql.class);
            qryParameters.setParameter("sqlId", sqlId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new SqlDto((Sql) qryParameters.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getParameters NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Parameters.", "getParameters NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Parameters.", "getParameters " + ex.getMessage());
        }
    }

    public Respuesta saveSql(SqlDto sqlDto) {
        try {
            Sql sql = new Sql();
            if (sqlDto.getSqlId() != null && sqlDto.getSqlId() > 0) {
                sql = em.find(Sql.class, sqlDto.getSqlId() );
                if (sql == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a modificar.", "guardarParameters NoResultException");
                }
                sql.update(sqlDto);
                sql = em.merge(sql);
            } else {
                sql = new Sql(sqlDto);
                em.persist(sql);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new SqlDto(sql));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Parameters.", "guardarParameters " + ex.getMessage());
        }
    }

    public Respuesta deleteSql(Integer id) {
        try {
            Sql sql;
            if (id != null && id > 0) {
                sql = em.find(Sql.class, id);
                if (sql == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Parameters a eliminar.", "eliminarParameters NoResultException");
                }
                em.remove(sql);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Parameters a eliminar.", "eliminarParameters NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Parameters porque tiene relaciones con otros registros.", "eliminarParameters " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Parameters.", "eliminarParameters " + ex.getMessage());
        }
    }
    
    public Respuesta getSqlByParam(Integer sqlParam) {
        try {
            Query qryParameters = em.createNamedQuery("Sql.findByParamId", Sql.class);
            qryParameters.setParameter("psId", sqlParam);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parameters", new SqlDto((Sql) qryParameters.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getParameters NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Parameters.", "getParameters NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parameters.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Parameters.", "getParameters " + ex.getMessage());
        }
    }
}
