package com.norbs.spider.service.usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * Clase empleada en la capa logica para ejecutar operaciones sobre el manejo de
 * seguridad y control de acceso a los usuarios.
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Service
public class UsuarioService extends SimpleUrlAuthenticationSuccessHandler {
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAuthenticationTargetUrlRequestHandler">
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
 
    @Override
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
    
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String urlFinal = obtenerURL(authentication);

        if (response.isCommitted()) {
            System.out.println("Error al redireccionar");
            return;
        }

        redirectStrategy.sendRedirect(request, response, urlFinal);
    }

    /* Verifica los roles del usuario para asignar la url que corresponda.
     * @return 
     */
    protected String obtenerURL(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        authorities.stream().forEach((a) -> {
            roles.add(a.getAuthority());
        });
        
        String url;

        if (isDba(roles)) {
            url = "/db";
        } else if (isAdmin(roles)) {
            url = "/admin";
        } else if (isUser(roles)) {
            url = "/home";
        } else {
            url = "/accessDenied";
        }

        return url;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">    
    private boolean isUser(List<String> roles) {
        return roles.contains("ROLE_USER");
    }
 
    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }
 
    private boolean isDba(List<String> roles) {
        return roles.contains("ROLE_DBA");
    }
    //</editor-fold>
}