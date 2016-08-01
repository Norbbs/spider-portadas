package com.norbs.spider.rest.web.controller;

import com.norbs.spider.service.libros.SpiderPortadasServiceImpl;
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
public class SpiderController {

    @Autowired
    private SpiderPortadasServiceImpl spiderPortadasServiceImpl;

    @RequestMapping(value = "/spider", method = {RequestMethod.GET})
    public ModelAndView getVistaDescargas() {
        ModelAndView mav = new ModelAndView("main");
        
        try {
            this.spiderPortadasServiceImpl.iniciarDescargaPortadas();
            mav.addObject("mensaje", "Se descargaron " + this.spiderPortadasServiceImpl.getCantidadPortadasDescargadas() + " portadas de forma exitosa.");
        } catch (Exception ex) {
            Logger.getLogger(CatalogoLibroController.class.getName()).log(Level.SEVERE, null, ex);
            mav.addObject("error", ex.getMessage());
        }
        mav.setViewName("main");
        return mav;
    }
}