package com.norbs.spider.common.xml.catalogo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@XmlRootElement(name = "TitleDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class TitleDetail {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "TitleType")
    protected String titleType;
    @XmlElement(name = "TitleElement")
    protected TitleElement titleElement;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public TitleDetail() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public TitleDetail(String titleType, TitleElement titleElement) {
        super();
        this.titleType = titleType;
        this.titleElement = titleElement;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getTitleType() {
        return titleType;
    }
    
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
    
    public TitleElement getTitleElement() {
        return titleElement;
    }
    
    public void setTitleElement(TitleElement titleElement) {
        this.titleElement = titleElement;
    }
    //</editor-fold>
}