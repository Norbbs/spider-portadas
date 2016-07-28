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
@XmlRootElement(name = "Header")
@XmlAccessorType(XmlAccessType.FIELD)
public class Header {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @XmlElement(name = "Sender")
    protected Sender sender;
    @XmlElement(name = "Addressee")
    protected Addressee addressee;
    @XmlElement(name = "SentDateTime")
    protected String sentDateTime;
    @XmlElement(name = "DefaultLanguageOfText")
    protected String defaultLanguageOfText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Header() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Header(Sender sender, Addressee addressee) {
        super();
        this.sender = sender;
        this.addressee = addressee;
    }
    
    public Header(Sender sender, Addressee addressee, String sentDateTime,
            String defaultLanguageOfText) {
        super();
        this.sender = sender;
        this.addressee = addressee;
        this.sentDateTime = sentDateTime;
        this.defaultLanguageOfText = defaultLanguageOfText;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Sender getSender() {
        return sender;
    }
    
    public void setSender(Sender sender) {
        this.sender = sender;
    }
    
    public Addressee getAddressee() {
        return addressee;
    }
    
    public void setAddressee(Addressee addressee) {
        this.addressee = addressee;
    }
    
    public String getSentDateTime() {
        return sentDateTime;
    }
    
    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
    }
    
    public String getDefaultLanguageOfText() {
        return defaultLanguageOfText;
    }
    
    public void setDefaultLanguageOfText(String defaultLanguageOfText) {
        this.defaultLanguageOfText = defaultLanguageOfText;
    }
    //</editor-fold>
}
