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
    private static final Logger LOG = Logger.getLogger(SqlService.class.getName());
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    
    public Respuesta getSql(Integer sqlId) {
        try {
            Query qrySql = em.createNamedQuery("Sql.findBySqlId", Sql.class);
            qrySql.setParameter("sqlId", sqlId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sql", new SqlDto((Sql) qrySql.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getSql NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Sql.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Sql.", "getSql NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Sql.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Sql.", "getSql " + ex.getMessage());
        }
    }

    public Respuesta saveSql(SqlDto sqlDto) {
        try {
            Sql sql = new Sql();
            if (sqlDto.getSqlId() != null && sqlDto.getSqlId() > 0) {
                sql = em.find(Sql.class, sqlDto.getSqlId() );
                if (sql == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Sql a modificar.", "guardarSql NoResultException");
                }
                sql.update(sqlDto);
                sql = em.merge(sql);
            } else {
                sql = new Sql(sqlDto);
                em.persist(sql);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sql", new SqlDto(sql));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Sql.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Sql.", "guardarSql " + ex.getMessage());
        }
    }

    public Respuesta deleteSql(Integer id) {
        try {
            Sql sql;
            if (id != null && id > 0) {
                sql = em.find(Sql.class, id);
                if (sql == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Sql a eliminar.", "eliminarSql NoResultException");
                }
                em.remove(sql);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Sql a eliminar.", "eliminarSql NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Sql porque tiene relaciones con otros registros.", "eliminarSql " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Sql.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Sql.", "eliminarSql " + ex.getMessage());
        }
    }
    
    public Respuesta getSqlByParam(Integer sqlParam) {
        try {
            Query qrySql = em.createNamedQuery("Sql.findByParamId", Sql.class);
            qrySql.setParameter("psId", sqlParam);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sql", new SqlDto((Sql) qrySql.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getSql NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Sql.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Sql.", "getSql NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Sql.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Sql.", "getSql " + ex.getMessage());
        }
    }
}
