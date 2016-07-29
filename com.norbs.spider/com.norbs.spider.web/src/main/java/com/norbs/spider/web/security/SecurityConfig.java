package com.norbs.spider.web.security;

import com.norbs.spider.entity.usuarios.Usuario;
import com.norbs.spider.service.usuarios.UsuarioServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement(proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    /**
     * Configuracion de roles de usuario en memoria para pruebas.
     * @param auth
     * @throws Exception 
     */
    @Autowired
    public void configurarRolesUsuario(AuthenticationManagerBuilder auth) throws Exception {
        
        List<Usuario> resultado = this.usuarioServiceImpl.consultarUsuarios();
        if (resultado.isEmpty()) {
            Usuario usuario = this.usuarioServiceImpl.crearUsuarioPorDefecto();
            resultado.add(usuario);
        }

        for (Usuario usuario : resultado) {
            auth.inMemoryAuthentication()
                    .withUser(usuario.getNombre())
                    .password(usuario.getPassword())
                    .authorities("ROLE_USER");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('USER')")
                .and().formLogin().loginPage("/")
                .usernameParameter("username").passwordParameter("password")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
}
