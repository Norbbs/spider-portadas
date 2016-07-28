package com.norbs.spider.dao.base;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * Clase base para el manejo de persistencia en el sistema.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 * @param <INSTANCE_CLASS> Clase que implementa la interfaz
 * @param <PRIMARY_KEY_CLASS> Clave primaria de la instancia
 */
public class BaseDAO<INSTANCE_CLASS, PRIMARY_KEY_CLASS> implements RepositorioDAO<INSTANCE_CLASS, PRIMARY_KEY_CLASS> {

    //<editor-fold defaultstate="collapsed" desc="EntityManager">
    @PersistenceContext(unitName = "UnidadPersistencia")
    private EntityManager entityManager;
    
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="RepositorioDAO">
    /**
     * Inserta una nueva entidad en la base de datos.
     *
     * @param instancia instancia de la entidad libro a insertar
     */
    @Override
    public  void insertar(INSTANCE_CLASS instancia) {
        this.getEntityManager().persist(instancia);
    }

    /**
     * Ejecuta un namedQuery para actualizar o remover registros
     *
     * @param namedQuery nombre clave asignado a una instruccion JPQL a ejecutar
     */
    @Transactional
    @Override
    public void ejecutar(String namedQuery) {
        Query query = this.getEntityManager().createNamedQuery(namedQuery);
        query.executeUpdate();
    }
    
    /**
     * Ejecuta un namedQuery para actualizar o remover registros con una lista
     * de parametros.
     *
     * @param namedQuery nombre clave asignado a una instruccion JPQL a ejecutar
     * @param parametros lista de parametros
     */
    @Transactional
    @Override
    public void ejecutar(String namedQuery, List<Object> parametros) {
        Query query = this.getEntityManager().createNamedQuery(namedQuery);
        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter("p"+i, parametro);
            i++;
        }        
        query.executeUpdate();
    }
    
    @Override
    public List<INSTANCE_CLASS> consultar(String namedQuery) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<INSTANCE_CLASS> consultar(String namedQuery, List<Object> parametros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //</editor-fold>
}