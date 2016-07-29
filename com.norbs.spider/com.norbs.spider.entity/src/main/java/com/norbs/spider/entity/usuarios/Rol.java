/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.norbs.spider.entity.usuarios;

import com.norbs.spider.entity.base.EntidadBase;
import javax.persistence.*;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Entity
@Table(name = "rol")
public class Rol extends EntidadBase {
    private static final long serialVersionUID = 1L;
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false, length = 30)
    private String descripcion;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getters & Setters">
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //</editor-fold>
}
