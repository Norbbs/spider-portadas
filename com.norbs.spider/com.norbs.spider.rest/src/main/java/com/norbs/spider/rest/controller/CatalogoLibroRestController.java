package com.norbs.spider.rest.controller;

import com.norbs.spider.service.libros.LibroServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest webservice para cargar catálogos 
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@RestController
public class CatalogoLibroRestController {
    
    @Autowired
    private LibroServiceImpl libroServiceImpl;
    
    /**
     * Se invoca al Bean de negocio encargado de gestionar la carga de catalogos
     * de libros.
     * @return ResponseEntity que representa una respuesta HTTP con el resultado 
     * de la solicitud.
     */
    @RequestMapping(value = "/catalogo", method = RequestMethod.POST)
    public ResponseEntity<?> ejecutarSpider() {
        
        Map<String, Object> mapRespuesta = new HashMap<>();
        
        try {
            this.libroServiceImpl.procesarCatalogosLibros();
            mapRespuesta.put("success", true);
            mapRespuesta.put("mensaje", "Catálogos cargados exitosamente.!");
        } catch (Exception ex) {
            mapRespuesta.put("success", false);
            mapRespuesta.put("error", ex.getMessage());
        }

        return new ResponseEntity<>(mapRespuesta, HttpStatus.OK);
    }
}
