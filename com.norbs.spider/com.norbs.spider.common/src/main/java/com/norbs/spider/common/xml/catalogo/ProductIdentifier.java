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
@XmlRootElement(name = "ProductIdentifier")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductIdentifier {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "ProductIDType")
    protected String productIDType;
    @XmlElement(name = "IDValue")
    protected String iDValue;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public ProductIdentifier() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ProductIdentifier(String productIDType, String iDValue) {
        super();
        this.productIDType = productIDType;
        this.iDValue = iDValue;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getProductIDType() {
        return productIDType;
    }

    public void setProductIDType(String productIDType) {
        this.productIDType = productIDType;
    }

    public String getiDValue() {
        return iDValue;
    }

    public void setiDValue(String iDValue) {
        this.iDValue = iDValue;
    }
    //</editor-fold>
}