package com.norbs.spider.web.controller;

import com.norbs.spider.service.usuarios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Controller
public class LoginController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView mav = new ModelAndView();
        
        try {
            this.usuarioServiceImpl.login(username, password);
            mav.setViewName("redirect:/");
        } catch (Exception ex) {
            mav.setViewName("login");
            mav.addObject("error", ex.getMessage());
        }
        
        return mav;
    }
    
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage() {
        ModelAndView mav = new ModelAndView("accessDenied");
        return mav;
    }
}
