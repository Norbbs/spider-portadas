package com.norbs.spider.web.controller;

import com.norbs.spider.service.libros.LibroServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Controller
@RequestMapping(value = {"/admin/catalogo"})
public class CatalogoLibroController {
    
    @Autowired
    private LibroServiceImpl libroServiceImpl;
    
    public ModelAndView getVistaCatalogo() {
        String info;
        try {
            this.libroServiceImpl.procesarCatalogosLibros();
            info = "Catalogos cargados exitosamente.!";
        } catch (Exception ex) {
            Logger.getLogger(CatalogoLibroController.class.getName()).log(Level.SEVERE, null, ex);
            info = ex.getMessage();
        }
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("catalogo");
        mav.addObject("info", info);
        mav.addObject("titulo", "Catalogos de libros");
        return mav;
    }
}