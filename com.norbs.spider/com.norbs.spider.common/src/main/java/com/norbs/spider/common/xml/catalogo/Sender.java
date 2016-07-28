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
@XmlRootElement(name = "Sender")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sender {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "SenderName")
    protected String SenderName;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getSenderName() {
        return SenderName;
    }
    
    public void setSenderName(String senderName) {
        SenderName = senderName;
    }
    //</editor-fold>
}