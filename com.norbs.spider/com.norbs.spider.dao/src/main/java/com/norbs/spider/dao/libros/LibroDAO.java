package com.norbs.spider.dao.libros;

import com.norbs.spider.dao.base.BaseDAO;
import com.norbs.spider.entity.libros.Libro;
import java.util.List;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * Clase derivada de BaseDAO que se encarga de realizar operaciones con la
 * entidad Libro y pudiera realizar operaciones sobre tablas relacionadas o
 * dependientes de esta entidad para requerimientos futuros.
 *
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Repository
public class LibroDAO extends BaseDAO<Libro, Integer> {

    /**
     * Se encarga de realizar una consulta via JPQL sobre los registros de la
     * entidad Libro que no posean portada.
     *
     * @return Devuelve una lista con los isbn de libros que no poseen portada.
     */
    @Transactional
    public List<String> obtenerIsbnsSinPortada() {
        Query query = this.getEntityManager().createQuery("SELECT l.isbn FROM Libro l WHERE l.portada IS NULL");
        return query.getResultList();
    }
}