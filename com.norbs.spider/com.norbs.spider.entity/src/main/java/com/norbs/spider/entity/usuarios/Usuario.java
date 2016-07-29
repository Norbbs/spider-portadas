package com.norbs.spider.entity.usuarios;

import com.norbs.spider.entity.base.EntidadBase;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.nombre = :p1 AND u.password = :p2"),
    @NamedQuery(name = "Usuario.delete", query = "DELETE FROM Usuario")
})
public class Usuario extends EntidadBase {
    private static final long serialVersionUID = 1L;
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = true, length = 255)
    private String password;
    
    @OneToMany(mappedBy = "usuario")
    private List<Rol> roles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    //</editor-fold>
}