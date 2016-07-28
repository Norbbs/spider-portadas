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
@XmlRootElement(name = "TitleElement")
@XmlAccessorType(XmlAccessType.FIELD)
public class TitleElement {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "TitleElementLevel")
    protected String titleElementLevel;
    @XmlElement(name = "TitleText")
    protected String titleText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public TitleElement() {
        super();
    }

    public TitleElement(String titleElementLevel, String titleText) {
        super();
        this.titleElementLevel = titleElementLevel;
        this.titleText = titleText;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getTitleElementLevel() {
        return titleElementLevel;
    }

    public void setTitleElementLevel(String titleElementLevel) {
        this.titleElementLevel = titleElementLevel;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }
    //</editor-fold>
}
