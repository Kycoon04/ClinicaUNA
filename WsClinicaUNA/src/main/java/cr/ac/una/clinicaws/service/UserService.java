/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicaws.service;

import cr.ac.una.clinicaws.model.Users;
import cr.ac.una.clinicaws.model.UsersDto;
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
import jakarta.persistence.TypedQuery;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dilan
 */
@Stateless
@LocalBean
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public Respuesta getUser(Integer usId) {
        try {
            Query qryusuario = em.createNamedQuery("Users.findByUsId", Users.class);
            qryusuario.setParameter("usId", usId);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Users", new UsersDto((Users) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getusuario NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getusuario NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getusuario " + ex.getMessage());
        }
    }
    
 public Respuesta getUserDoc(Integer drId) {
    try {
        Query qryusuario = em.createNamedQuery("Users.findUserByDoctorId", Users.class);
        qryusuario.setParameter("doctorId", drId);

        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Users", new UsersDto((Users) qryusuario.getSingleResult()));

    } catch (NoResultException ex) {//sin resultado
        return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getusuario NoResultException");
    } catch (NonUniqueResultException ex) {//mas de un resultado 
        LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getusuario NonUniqueResultException");
    } catch (Exception ex) {// codig de erro en el server 
        LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
        return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getusuario " + ex.getMessage());
    }
}


     public Respuesta getUserEmail(String usUsername) {
        try {
            Query qryusuario = em.createNamedQuery("Users.findByUsUsername", Users.class);
            qryusuario.setParameter("usUsername", usUsername);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "User", new UsersDto((Users) qryusuario.getSingleResult()));

        } catch (NoResultException ex) {//sin resultado
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un user con el código ingresado.", "getusuario NoResultException");
        } catch (NonUniqueResultException ex) {//mas de un resultado 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getusuario NonUniqueResultException");
        } catch (Exception ex) {// codig de erro en el server 
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getusuario " + ex.getMessage());
        }
    }
    public Respuesta validateUser(String usuario, String clave) {
        try {
            Query qryActividad = em.createNamedQuery("Users.findByUsuClave", Users.class);
            qryActividad.setParameter("usuario", usuario);
            qryActividad.setParameter("clave", clave);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Users", new UsersDto((Users) qryActividad.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }

    public Respuesta saveUser(UsersDto userDto) {
        try {
            Users user = new Users();
            if (userDto.getUsId() != null && userDto.getUsId() > 0) {
                user = em.find(Users.class, userDto.getUsId());
                if (user == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "guardarusuario NoResultException");
                }
                user.update(userDto);
                user = em.merge(user);
            } else {
                user = new Users(userDto);
                em.persist(user);
            }
        
            Email email= new Email(user.getUsEmail(), "Activacion", "http://localhost:8080/WsClinicaUNA/Activacion.html?Code="+user.getUsCode());
            email.envioDeCorreos(email);
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Users", new UsersDto(user));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarusuario " + ex.getMessage());
        }
    }

    public Respuesta deleteUser(Integer id) {
        try {
            Users usuario;
            if (id != null && id > 0) {
                usuario = em.find(Users.class, id);
                if (usuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a eliminar.", "eliminarusuario NoResultException");
                }
                em.remove(usuario);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el usuario porque tiene relaciones con otros registros.", "eliminarusuario " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

    public Respuesta activebyCode(String code) {
        Users users;
        try {
            if (code != null && !code.isEmpty()) {
                TypedQuery<Users> query = em.createNamedQuery("Users.findByUsCode", Users.class);
                query.setParameter("usCode", code);
                users = query.getSingleResult();
                users.setUsState("A");
                users.setUsCode("");
                users = em.merge(users);
                em.flush();
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

    public Respuesta isActive(String usuario, String clave) {
        Users users;
        try {
            if (usuario != null && !clave.isEmpty()) {
                TypedQuery<Users> query = em.createNamedQuery("Users.findByUsuClave", Users.class);
                query.setParameter("usuario", usuario);
                query.setParameter("clave", clave);
                users = query.getSingleResult();
                if (users.getUsState().equals("A")) {
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                }
                return new Respuesta(false, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

    
       public Respuesta isAdmin(String usuario, String clave) {
        Users users;
        try {
            if (usuario != null && !clave.isEmpty()) {
                TypedQuery<Users> query = em.createNamedQuery("Users.findByUsuClave", Users.class);
                query.setParameter("usuario", usuario);
                query.setParameter("clave", clave);
                users = query.getSingleResult();
                if (users.getUsType().equals("Administrator")) {
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                }
                return new Respuesta(false, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }
        
    public Respuesta isTempPas(String usuario, String clave) {
        Users users;
        try {
            if (usuario != null && !clave.isEmpty()) {
                TypedQuery<Users> query = em.createNamedQuery("Users.findByUsuClave", Users.class);
                query.setParameter("usuario", usuario);
                query.setParameter("clave", clave);
                users = query.getSingleResult();
                if (users.getUsRecover().equals("Y")) {
                    return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
                }
                return new Respuesta(false, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

   public Respuesta updatebyEmail(UsersDto usersDto) {
        Users user;
        try {
            if (usersDto.getUsEmail() != null || usersDto.getUsPassword() != null) {
                TypedQuery<Users> query = em.createNamedQuery("Users.findByUsEmail", Users.class);
                query.setParameter("usEmail", usersDto.getUsEmail());
                user = query.getSingleResult();
                user.setUsPassword(usersDto.getUsPassword());
                user.setUsRecover(usersDto.getUsRecover());
                user.setUsTemppassword("");
                user = em.merge(user);
                em.flush();
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }
    // user.setUsRecover(usersDto.getUsRecover());
    public Respuesta updaterecovery(UsersDto usersDto) {
        Users user;
        try {
            if (usersDto.getUsEmail() != null || usersDto.getUsTemppassword() != null) {
                TypedQuery<Users> query = em.createNamedQuery("Users.findByUsEmail", Users.class);
                query.setParameter("usEmail", usersDto.getUsEmail());
                user = query.getSingleResult();
                user.setUsTemppassword(usersDto.getUsTemppassword());
                user.setUsRecover(usersDto.getUsRecover());
                user.setUsPassword(usersDto.getUsTemppassword());
                

                 Email email= new Email(user.getUsEmail(), "Recuperacion de constraseña","");
                 email.envioCmbClave(usersDto.getUsTemppassword());
            
                user = em.merge(user);
                em.flush();
                return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarusuario NoResultException");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarusuario " + ex.getMessage());
        }
    }

    public Respuesta getUsers() {
        try {
            Query qryUsers = em.createNamedQuery("Users.findAll", Users.class);
            List<Users> users = (List<Users>) qryUsers.getResultList();
            List<UsersDto> ListUsersDto = new ArrayList<>();
            for (Users tipo : users) {
                UsersDto usersDto = new UsersDto(tipo);
                ListUsersDto.add(usersDto);
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Users", ListUsersDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un tipo de usuario con el código ingresado.", "getTipousuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tipo de usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de usuario.", "getTipousuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tipo de usuario.", "getTipousuario " + ex.getMessage());
        }
    }
    
    
  public Respuesta sendEmail(Email email) {
    try {
        if (email != null) {
            Email emeilD = new Email(email.getDestinationMail(), email.getAsunt(), email.getLink());
            email.envioDeCorreos(emeilD);
        }
        return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
    } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);
        return new Respuesta(false, CodigoRespuesta.CORRECTO, "", "");
    }
}

    
}
