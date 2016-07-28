package com.norbs.spider.common.xml.catalogo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 *
 */
@XmlRootElement(name = "Addressee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Addressee {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "AddresseeName")
    protected String AddresseeName;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Addressee() {
        super();
    }
    
    public Addressee(String addresseeName) {
        super();
        AddresseeName = addresseeName;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getAddresseeName() {
        return AddresseeName;
    }
    
    public void setAddresseeName(String addresseeName) {
        AddresseeName = addresseeName;
    }
    //</editor-fold>
}