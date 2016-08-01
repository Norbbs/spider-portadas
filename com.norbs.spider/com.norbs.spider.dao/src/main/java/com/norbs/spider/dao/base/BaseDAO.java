package com.norbs.spider.dao.base;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Clase base para el manejo de persistencia en el sistema, implementa la interfaz
 * RepositorioDAO que contiene metodos para realizar operaciones en la base de 
 * datos.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 * @param <INSTANCE_CLASS> Clase que implementa la interfaz
 * @param <PRIMARY_KEY_CLASS> Clave primaria de la instancia
 */
@Repository
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
    
    /**
     * Ejecuta un namedQuery para consultar (SELECT) registros
     *
     * @param namedQuery nombre clave asignado a una instruccion JPQL a ejecutar
     * @return Lista de resultados
     */
    @Override
    public List<INSTANCE_CLASS> consultar(String namedQuery) {
        Query query = this.getEntityManager().createNamedQuery(namedQuery);
        return query.getResultList();
    }

    /**
     * Ejecuta un namedQuery para consultar (SELECT) registros con una lista
     * de parametros.
     *
     * @param namedQuery nombre clave asignado a una instruccion JPQL a ejecutar
     * @param parametros lista de parametros
     * @return Lista de resultados
     */
    @Override
    public List<INSTANCE_CLASS> consultar(String namedQuery, List<Object> parametros) {
        Query query = this.getEntityManager().createNamedQuery(namedQuery);
        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter("p"+i, parametro);
            i++;
        }        
        return query.getResultList();
    }
    
    /**
     * Ejecuta un namedQuery para consultar (SELECT) una entidad especifica en la
     * base de datos
     *
     * @param namedQuery nombre clave asignado a una instruccion JPQL a ejecutar
     * @param parametros lista de parametros
     * @return Entidad resultante
     */
    @Override
    public Object consultarEntidad(String namedQuery, List<Object> parametros) {
        Query query = this.getEntityManager().createNamedQuery(namedQuery);
        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter("p"+i, parametro);
            i++;
        }        
        return query.getSingleResult();
    }
    //</editor-fold>
}