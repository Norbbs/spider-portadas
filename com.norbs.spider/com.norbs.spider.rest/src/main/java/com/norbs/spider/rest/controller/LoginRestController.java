package com.norbs.spider.rest.controller;

import com.norbs.spider.service.usuarios.UsuarioServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Webservice empleado para logear usuario.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@RestController
public class LoginRestController {
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    
    /**
     * Se invoca al Bean de negocio encargado de autenticar al usuario.
     * @param username Nombre de usuario introducido en un formulario.
     * @param password Contraseña de usuario introducida en un formulario.
     * @return ResponseEntity que representa una respuesta HTTP con el resultado 
     * de la solicitud.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.OK)
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        
        Map<String, Object> mapRespuesta = new HashMap<>();
        
        try {
            this.usuarioServiceImpl.login(username, password);
            mapRespuesta.put("success", true);
        } catch (Exception ex) {
            mapRespuesta.put("success", false);
            mapRespuesta.put("error", ex.getMessage());
        }

        return new ResponseEntity<>(mapRespuesta, HttpStatus.OK);
    }
}