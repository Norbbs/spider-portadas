package com.norbs.spider.service.libros;

import com.norbs.spider.entity.base.EntidadBase;

/**
 * Interfaz que define métodos para trabajar la logica de negocio sobre la entidad
 * "Libro".
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
public interface LibroService {

    public void procesarCatalogosLibros() throws Exception;

    public <T extends EntidadBase> void validar(T entidad) throws Exception;

    public void eliminarLibros();
}