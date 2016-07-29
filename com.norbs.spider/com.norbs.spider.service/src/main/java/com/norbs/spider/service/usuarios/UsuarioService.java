package com.norbs.spider.service.usuarios;

import com.norbs.spider.entity.base.EntidadBase;
import com.norbs.spider.entity.usuarios.Usuario;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
public interface UsuarioService {
    
    public void login(String username, String password) throws Exception ;
    
    public <T extends EntidadBase> void validar(T entidad) throws Exception;
    
    public Usuario crearUsuarioPorDefecto();
}