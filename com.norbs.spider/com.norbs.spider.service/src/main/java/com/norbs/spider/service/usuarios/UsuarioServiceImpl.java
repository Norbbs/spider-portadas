package com.norbs.spider.service.usuarios;

import com.norbs.spider.common.classes.SecurityUtil;
import com.norbs.spider.common.classes.StringUtil;
import com.norbs.spider.dao.usuarios.RolDAO;
import com.norbs.spider.dao.usuarios.UsuarioDAO;
import com.norbs.spider.entity.base.EntidadBase;
import com.norbs.spider.entity.usuarios.Rol;
import com.norbs.spider.entity.usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
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

    @Override
    public void login(String username, String password) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre(username);
        usuario.setPassword(SecurityUtil.cifrarMD5(password));
        this.validar(usuario);
    }

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
        List<Usuario> resultado = this.usuarioDAO.consultar("Usuario.login", parametros);

        if (resultado.isEmpty()) {
            throw new Exception("Nombre de usuario o contraseña incorrecta.");
        }
    }

    @Override
    public Usuario crearUsuarioPorDefecto() {
        Usuario usuario = new Usuario();
        usuario.setNombre("norbs");
        usuario.setPassword(SecurityUtil.cifrarMD5("norbs"));        
        this.usuarioDAO.insertar(usuario);
        this.asignarRolPorDefectoUsuario(usuario);        
        return usuario;
    }

    public List<Usuario> consultarUsuarios() {
        return this.usuarioDAO.consultar("Usuario.findAll");
    }
    
    private void asignarRolPorDefectoUsuario(Usuario usuario) {
        Rol rol = new Rol();
        rol.setDescripcion("ROLE_USER");
        rol.setUsuario(usuario);
        this.rolDAO.insertar(rol);
        
        usuario.setRoles(new ArrayList<>());
        usuario.getRoles().add(rol);
    }
}