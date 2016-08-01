package com.norbs.spider.entity.base;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Super clase en la que se definen los atributos "comunes" que heredarán sus
 * clases hijas. Está anotada como @MappedSuperclass.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@MappedSuperclass
public class EntidadBase implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    public String toString() {
        return "com.norbs.spider.entity.base.EntidadBase[ id=" + id + " ]";
    }
    //</editor-fold>
}
