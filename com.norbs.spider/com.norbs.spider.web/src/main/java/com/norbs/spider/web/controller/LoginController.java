package com.norbs.spider.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Controller
public class LoginController {
    
    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", getUserName());
        mav.addObject("rol", "usuario");
        mav.addObject("titulo", "Usuario");
        return mav;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(ModelMap model) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", getUserName());
        mav.addObject("rol", "Admin");
        mav.addObject("titulo", "Admin");
        return mav;
    }
    
    @RequestMapping(value = "/dba", method = RequestMethod.GET)
    public ModelAndView dbaPage(ModelMap model) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", getUserName());
        mav.addObject("rol", "dba");
        mav.addObject("titulo", "DBA");
        return mav;
    }
    
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage(ModelMap model) {
        ModelAndView mav = new ModelAndView("accessDenied");
        mav.addObject("user", getUserName());
        return mav;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
 
    private String getUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}