package com.norbs.spider.web.controller;

import com.norbs.spider.service.libros.LibroServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Controller
public class CatalogoLibroController {
    
    @Autowired
    private LibroServiceImpl libroServiceImpl;
    
    @RequestMapping(value = "/catalogo", method = {RequestMethod.GET})
    public ModelAndView getVistaCatalogo() {
        ModelAndView mav = new ModelAndView("main");
        
        try {
            this.libroServiceImpl.procesarCatalogosLibros();
            mav.addObject("mensaje", "Catalogos cargados exitosamente.!");
        } catch (Exception ex) {
            Logger.getLogger(CatalogoLibroController.class.getName()).log(Level.SEVERE, null, ex);
            mav.addObject("error", ex.getMessage());
        }
        
        return mav;
    }
}