package com.norbs.spider.entity.libros;

import com.norbs.spider.entity.base.EntidadBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Entity
@Table(name = "libro")
@NamedQueries({
    @NamedQuery(name = "Libro.updateCover", query = "UPDATE Libro l SET l.portada = :p1 WHERE l.isbn = :p2"),
    @NamedQuery(name = "Libro.delete", query = "DELETE FROM Libro")
})
public class Libro extends EntidadBase {

    private static final long serialVersionUID = 1L;

    //<editor-fold defaultstate="collapsed" desc="Atributos">    
    @Column(nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = true, length = 255)
    private String portada;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
//</editor-fold>
}