package com.norbs.spider.dao.base;

import java.util.List;

/**
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 * @param <INSTANCE_CLASS> Clase que implementa la interfaz
 * @param <PRIMARY_KEY_CLASS> Clave primaria de la instancia
 */
public interface RepositorioDAO<INSTANCE_CLASS, PRIMARY_KEY_CLASS> {
    
    public void insertar(INSTANCE_CLASS instance);
    
    public void ejecutar(String namedQuery);
    
    public void ejecutar(String namedQuery, List<Object> parametros);
    
    public List<INSTANCE_CLASS> consultar(String namedQuery);
    
    public List<INSTANCE_CLASS> consultar(String namedQuery, List<Object> parametros);
}