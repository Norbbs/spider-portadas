package com.norbs.spider.web.controller;

import com.norbs.spider.service.libros.SpiderPortadasServiceImpl;
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
@RequestMapping(value = {"/admin/spider", "/dba/spider"})
public class SpiderController {

    //<editor-fold defaultstate="collapsed" desc="Autowired">
    @Autowired
    private SpiderPortadasServiceImpl spiderPortadasServiceImpl;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Controller">
    public ModelAndView getVistaDescargas() {
        this.spiderPortadasServiceImpl.iniciarDescargaPortadas();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("spider");
        mav.addObject("cantidadPortadasDescargadas", this.spiderPortadasServiceImpl.getCantidadPortadasDescargadas());
        mav.addObject("titulo", "Spider de portadas");
        return mav;
    }
    //</editor-fold>
}
