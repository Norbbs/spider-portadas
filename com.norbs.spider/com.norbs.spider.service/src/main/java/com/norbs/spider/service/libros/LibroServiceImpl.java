package com.norbs.spider.service.libros;

import com.norbs.spider.service.base.BaseService;
import com.norbs.spider.common.classes.Constantes;
import com.norbs.spider.common.classes.StringUtil;
import com.norbs.spider.common.xml.catalogo.CatalogoLibroXML;
import com.norbs.spider.entity.base.EntidadBase;
import com.norbs.spider.entity.libros.Libro;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import org.springframework.stereotype.Service;

/**
 * Clase que se encarga de gestionar el inicio del sistema con la lectura
 * de un catalogo de libros en la base de datos.
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Service
public class LibroServiceImpl extends BaseService implements LibroService {

    //<editor-fold defaultstate="collapsed" desc="LibroService">
    /**
     * Metodo que permite cargar el catalogo de libros en la base de datos
     * partiendo de una ruta por defecto.
     * @throws java.lang.Exception
     */
    @Override
    public void procesarCatalogosLibros() throws Exception {

        try {
            Path rutaCatalogo = Paths.get(Constantes.RUTA_CATALOGO_LIBROS);
            DirectoryStream<Path> directorioCatalogos = Files.newDirectoryStream(rutaCatalogo);

            Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.INFO, "Revisando cada catalogo en el directorio dado");
            for (Path catalogo : directorioCatalogos) {

                Path rutaCatalogoLectura = Paths.get(this.construirRutaFinalCatalogo(catalogo.getFileName().toString()));
                InputStream is = Files.newInputStream(rutaCatalogoLectura);
                CatalogoLibroXML catalogoLibroXML = (CatalogoLibroXML) JAXBContext.newInstance(CatalogoLibroXML.class)
                        .createUnmarshaller()
                        .unmarshal(is);

                Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.INFO, "Validando catalogo... {0}", catalogo.getFileName().toString());
                this.validarCatalogo(catalogoLibroXML, catalogo.getFileName().toString());

                Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.INFO, "Cargando catalogo... {0}", catalogo.getFileName().toString());
                this.cargarCatalogo(catalogoLibroXML);
            }

        } catch (Exception ex) {
            Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.SEVERE, ex.getMessage());
            throw ex;
        }
    }

    @Override
    public <T extends EntidadBase> void validar(T entidad) throws Exception {

        if (entidad == null) {
            throw new Exception("Datos incorrectos.");
        }

        Libro libro = (Libro) entidad;

        if (StringUtil.esNulaOVacia(libro.getIsbn())) {
            throw new Exception("ERROR: Cada libro en el catalogo debe contener un isbn");
        }

        if (StringUtil.esNulaOVacia(libro.getTitulo())) {
            throw new Exception("ERROR: Cada libro en el catalogo debe contener un titulo");
        }
    }
    
    @Override
    public void eliminarLibros() {
        this.libroDAO.ejecutar("Libro.delete");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    private String construirRutaFinalCatalogo(String nombreArchivo) {
        StringBuilder sb = new StringBuilder(Constantes.RUTA_CATALOGO_LIBROS);
        sb.append('/');
        sb.append(nombreArchivo);
        return sb.toString();
    }

    private void validarCatalogo(CatalogoLibroXML catalogoLibroXML, String nombreCatalogo) throws Exception {

        if (catalogoLibroXML == null) {
            throw new Exception("El catalogo " + nombreCatalogo + " contiene datos incorrectos");
        }

        if (catalogoLibroXML.getProductList() == null || catalogoLibroXML.getProductList().isEmpty()) {
            throw new Exception("El catalogo " + nombreCatalogo + " no contiene libros para procesar.");
        }
    }

    private void cargarCatalogo(CatalogoLibroXML catalogoLibroXML) {

        catalogoLibroXML.getProductList().stream().forEach((producto) -> {

            /* 1. Cargando entidad libro */
            Libro libro = new Libro();

            /* 2. Asignando isbn */
            producto.getProductIdentifierList().forEach((i) -> {
                if (i.getProductIDType().equals("15")) {
                    libro.setIsbn(i.getiDValue());
                }
            });
            Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.INFO, "isbn asignado: {0}", libro.getIsbn());

            /* 3. Asignando titulo */
            Optional<String> variable = Optional.ofNullable(producto.getDescriptiveDetail().getTitleDetail().getTitleElement().getTitleText());
            libro.setTitulo(variable.orElse(""));
            Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.INFO, "titulo asignado: {0}", libro.getTitulo());

            /* 4. Validando libro y procesandolo*/
            try {
                this.validar(libro);
                this.libroDAO.insertar(libro);
            } catch (Exception ex) {
                Logger.getLogger(LibroServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    //</editor-fold>
}