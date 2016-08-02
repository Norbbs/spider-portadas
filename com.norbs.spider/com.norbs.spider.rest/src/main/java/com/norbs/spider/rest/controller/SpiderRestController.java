package com.norbs.spider.rest.controller;

import com.norbs.spider.service.libros.SpiderPortadasServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest webservice para descargar portadas de libros en la web
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@RestController
public class SpiderRestController {
    
    @Autowired
    private SpiderPortadasServiceImpl spiderPortadasServiceImpl;
    
    /**
     * Se invoca al Bean de negocio encargado de descargar las potadas de libros
     * en la web.
     * @return ResponseEntity que representa una respuesta HTTP con el resultado 
     * de la solicitud.
     */
    @RequestMapping(value = "/spider", method = RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public ResponseEntity<?> ejecutarSpider() {
        
        Map<String, Object> mapRespuesta = new HashMap<>();
        
        try {
            this.spiderPortadasServiceImpl.iniciarDescargaPortadas();
            mapRespuesta.put("success", true);
            mapRespuesta.put("mensaje", "Se han descargado " + this.spiderPortadasServiceImpl.getCantidadPortadasDescargadas() + " portadas de forma exitosa");
        } catch (Exception ex) {
            mapRespuesta.put("success", false);
            mapRespuesta.put("error", ex.getMessage());
        }

        return new ResponseEntity<>(mapRespuesta, HttpStatus.OK);
    }
}
