package com.norbs.spider.common.classes;

/**
 * Clase que describe un enlace (url) asociado a un libro (isbn)
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
public class EnlacePortada {
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private String url;
    private String isbn;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public EnlacePortada() {
    }
    
    public EnlacePortada(String url, String isbn) {
        this.url = url;
        this.isbn = isbn;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    //</editor-fold>
}