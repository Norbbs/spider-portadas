package com.norbs.spider.service.usuarios;

import com.norbs.spider.common.classes.SecurityUtil;
import com.norbs.spider.common.classes.StringUtil;
import com.norbs.spider.dao.usuarios.RolDAO;
import com.norbs.spider.dao.usuarios.UsuarioDAO;
import com.norbs.spider.entity.base.EntidadBase;
import com.norbs.spider.entity.usuarios.Rol;
import com.norbs.spider.entity.usuarios.Usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de manejar la lógica de negocio asociadas a la entidad Usuario.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private RolDAO rolDAO;

    /**
     * Ejcuta el login del usuario aplicando las reglas de negocio establecidas.
     * @param username Nombre de usuario
     * @param password Contraseña de usuario
     * @throws Exception Si existe una inconsistencia
     */
    @Override
    public void login(String username, String password) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre(username);
        usuario.setPassword(SecurityUtil.cifrarMD5(password));
        this.validar(usuario);
    }

    /**
     * Ejecuta la validación de la entidad Usuario
     * @param <T> Entidad derivada de la clase EntidadBase
     * @param entidad Entidad Usuario a evaluar
     * @throws Exception 
     */
    @Override
    public <T extends EntidadBase> void validar(T entidad) throws Exception {

        if (entidad == null) {
            throw new Exception("Datos incorrectos.");
        }

        Usuario usuario = (Usuario) entidad;

        if (StringUtil.esNulaOVacia(usuario.getNombre())) {
            throw new Exception("Debe ingresar un nombre de usuario.");
        }

        if (StringUtil.esNulaOVacia(usuario.getPassword())) {
            throw new Exception("Debe ingresar una contraseña.");
        }

        List<Object> parametros = new ArrayList<>();
        parametros.add(usuario.getNombre());
        parametros.add(usuario.getPassword());        
        Usuario resultado = null;

        try {
            resultado = (Usuario) this.usuarioDAO.consultarEntidad("Usuario.login", parametros);
        } catch (NoResultException ex) {
            throw new Exception("Nombre de usuario o contraseña incorrecta.");
        }
        
        this.validarRolesUsuario(resultado.getRoles());
    }
    
    /**
     * Valida que los roles de usuario sean los correctos para accesar a un sitio 
     * web determinado.
     * @param roles
     * @throws Exception 
     */
    private void validarRolesUsuario(List<Rol> roles) throws Exception {
        if (roles == null || roles.isEmpty()) {
            throw new Exception("Usted no posee autorización para acceder este sitio.");
        }
        
        for (Rol rol : roles) {
            
            if (rol.getDescripcion().equals("ROLE_USER")) {
                return;
            }
        }
        
        throw new Exception("Usted no posee las credenciales necesarias acceder a este sitio.");
    }

    /**
     * Método de prueba empleado para insertar un usuario por defecto en el
     * sistema.
     * @return El usuario creado con su id autogenerado.
     */
    @Override
    public Usuario crearUsuarioPorDefecto() {
        Usuario usuario = new Usuario();
        usuario.setNombre("norbs");
        usuario.setPassword(SecurityUtil.cifrarMD5("norbs"));
        this.usuarioDAO.insertar(usuario);
        this.asignarRolPorDefectoUsuario(usuario);
        return usuario;
    }
    
    /**
     * Método de prueba para asignar un rol por defecto al usuario por defecto del
     * sistema.
     * @param usuario 
     */
    private void asignarRolPorDefectoUsuario(Usuario usuario) {
        Rol rol = new Rol();
        rol.setDescripcion("ROLE_USER");
        rol.setUsuario(usuario);
        this.rolDAO.insertar(rol);

        usuario.setRoles(new ArrayList<>());
        usuario.getRoles().add(rol);
    }
    
    /**
     * Metodo que transforma una lista de entidades "Rol" en una lista de autorizaciones
     * de la clase GrantedAuthority empleadas para asignar autorizaciones al usuario.
     * @param roles Lista de roles del usuario.
     * @return Lista de autorizaciones a asignar al usuario.
     */
    public List<GrantedAuthority> getAutorizaciones(List<Rol> roles) {

        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        roles.stream().forEach((rol) -> {
            autorizaciones.add(new SimpleGrantedAuthority(rol.getDescripcion()));
        });
        return new ArrayList<>(autorizaciones);
    }

    /**
     * Ejecuta la consulta de los registros de la entidad Usuario que existen
     * en el sistema.
     * @return Lista de resultados.
     */
    public List<Usuario> consultarUsuarios() {
        return this.usuarioDAO.consultar("Usuario.findAll");
    }
}