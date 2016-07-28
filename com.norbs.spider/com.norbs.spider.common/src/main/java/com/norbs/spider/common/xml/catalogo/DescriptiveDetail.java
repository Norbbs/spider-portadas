/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@XmlRootElement(name = "DescriptiveDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class DescriptiveDetail {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "TitleDetail")
    protected TitleDetail titleDetail;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public TitleDetail getTitleDetail() {
        return titleDetail;
    }
    
    public void setTitleDetail(TitleDetail titleDetail) {
        this.titleDetail = titleDetail;
    }
    //</editor-fold>
}