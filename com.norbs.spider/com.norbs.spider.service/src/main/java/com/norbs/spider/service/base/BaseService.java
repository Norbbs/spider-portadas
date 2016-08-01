package com.norbs.spider.service.base;

import com.norbs.spider.dao.libros.LibroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase Base en la que se definen los beans DAO empleados por sus clases hijas
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Service
public abstract class BaseService {
    
    //<editor-fold defaultstate="collapsed" desc="Beans">
    @Autowired
    protected LibroDAO libroDAO;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setLibroDAO(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }
    //</editor-fold>
}