package com.norbs.spider.common.xml.catalogo;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Esta clase representa un catalogo de libros siguiendo el formato estandar
 * ONIX cada atributo de la clase esta asociado a una etiqueta XML que contiene
 * el documento.
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 *
 */
@XmlRootElement(name = "ONIXMessage", namespace = "http://ns.editeur.org/onix/3.0/reference")
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoLibroXML implements Serializable {

    private static final long serialVersionUID = -8920716769400125612L;

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "Header")
    protected Header header;
    @XmlElement(name = "Product")
    protected List<Product> productList;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public CatalogoLibroXML() {
        super();
    }
    
    public CatalogoLibroXML(Header header) {
        super();
        this.header = header;
    }
    
    public CatalogoLibroXML(Header header, List<Product> producList) {
        super();
        this.header = header;
        this.productList = producList;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Header getHeader() {
        return header;
    }
    
    public void setHeader(Header header) {
        this.header = header;
    }
    
    public List<Product> getProductList() {
        return productList;
    }
    
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    //</editor-fold>
}