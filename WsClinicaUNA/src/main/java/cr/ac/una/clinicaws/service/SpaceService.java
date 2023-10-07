package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Space;
import cr.ac.una.clinicaws.model.SpaceDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jomav
 */
@Stateless
@LocalBean
public class SpaceService {

    private static final Logger LOG = Logger.getLogger(SpaceService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getSpace(Integer seId) {
        try {
            Query qrySpace = em.createNamedQuery("Space.findBySeId", Space.class);
            qrySpace.setParameter("seId", seId);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Space", new SpaceDto((Space) qrySpace.getSingleResult()));
        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getSpace NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Space.", "getSpace NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el Space.", "getSpace " + ex.getMessage());
        }
    }

    public Respuesta saveSpace(SpaceDto spaceDto) {
        try {
            Space space = new Space();
            if (spaceDto.getSeId() != null && spaceDto.getSeId() > 0) {
                space = em.find(Space.class, spaceDto.getSeId());
                if (space == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Space a modificar.", "guardarSpace NoResultException");
                }
                space.update(spaceDto);
                space = em.merge(space);
            } else {
                space = new Space(spaceDto);
                em.persist(space);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Space", new SpaceDto(space));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el Space.", "guardarSpace " + ex.getMessage());
        }
    }

    public Respuesta deleteSpace(Integer id) {
        try {
            Space space;
            if (id != null && id > 0) {
                space = em.find(Space.class, id);
                if (space == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el Space a eliminar.", "eliminarSpace NoResultException");
                }
                em.remove(space);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el Space a eliminar.", "eliminarSpace NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el Space porque tiene relaciones con otros registros.", "eliminarSpace " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el Space.", "eliminarSpace " + ex.getMessage());
        }
    }

    public Respuesta getSpace() {
        try {
            Query qrySpace = em.createNamedQuery("Space.findAll", Space.class);
            List<Space> space = (List<Space>) qrySpace.getResultList();
            List<SpaceDto> ListSpaceDto = new ArrayList<>();
            for (Space tipo : space) {
                SpaceDto usersDto = new SpaceDto(tipo);
                ListSpaceDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Space", ListSpaceDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de Space con el código ingresado.", "getTipoSpace NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Space.", "getTipoSpace NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Space.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de Space.", "getTipoSpace " + ex.getMessage());
        }
    }
}
