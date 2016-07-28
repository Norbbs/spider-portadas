/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.norbs.spider.common.xml.catalogo;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "ProductIdentifier")
    protected List<ProductIdentifier> productIdentifierList;
    @XmlElement(name = "DescriptiveDetail")
    protected DescriptiveDetail descriptiveDetail;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public List<ProductIdentifier> getProductIdentifierList() {
        return productIdentifierList;
    }

    public void setProductIdentifierList(List<ProductIdentifier> productIdentifierList) {
        this.productIdentifierList = productIdentifierList;
    }
    
    
    public DescriptiveDetail getDescriptiveDetail() {
        return descriptiveDetail;
    }
    
    public void setDescriptiveDetail(DescriptiveDetail descriptiveDetail) {
        this.descriptiveDetail = descriptiveDetail;
    }
    //</editor-fold>
}