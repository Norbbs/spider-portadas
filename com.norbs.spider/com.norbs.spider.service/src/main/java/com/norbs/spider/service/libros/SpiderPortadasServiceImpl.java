package com.norbs.spider.service.libros;

import com.norbs.spider.common.classes.Constantes;
import com.norbs.spider.common.classes.EnlacePortada;
import com.norbs.spider.common.enums.EnumTipoURL;
import com.norbs.spider.service.base.BaseService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase que se encarga de descargar portadas a libros que no poseen portadas
 * @author Norbs norbbs@gmail.com +58-4143832967
 * https://ve.linkedin.com/in/norbbs
 */
@Service
@Transactional
public class SpiderPortadasServiceImpl extends BaseService implements SpiderPortadasService {
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private List<String> isbnsActualizar;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LibroService">
    /**
     * Descarga portadas para libros en un directorio por defecto (/portadas").
     */
    @Override
    public void iniciarDescargaPortadas() {
        List<EnlacePortada> enlacesPortadas = this.obtenerEnlacesSpider();
        isbnsActualizar = new ArrayList<>();
        
        /* Descargando portadas en un directorio especifico */
        enlacesPortadas.stream().forEach((enlacePortada) -> {
            this.descargarPortada(Constantes.DIRECTORIO_DESCARGA_PORTADAS, enlacePortada.getUrl(), enlacePortada.getIsbn());
        });
        
        /* Actualizando libros con las portadas que fueron descargadas */         
        isbnsActualizar.stream().forEach((isbn) -> {
            List<Object> parametros = new ArrayList<>();
            parametros.add(generarRutaLocalPortada(isbn));
            parametros.add(isbn);
            this.libroDAO.ejecutar("Libro.updateCover", parametros);
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos privados">
    /**
     * @return Devuelve una cadena con los enlaces de portadas de libros que
     * el spider debe buscar.
     */
    private List<EnlacePortada> obtenerEnlacesSpider() {
        
        List<EnlacePortada> listaEnlaces = new ArrayList<>();
        List<String> listaIsbns = this.libroDAO.obtenerIsbnsSinPortada();
        
        listaIsbns.stream().forEach((isbn) -> {
            EnlacePortada enlacePortada = new EnlacePortada();
            enlacePortada.setIsbn(isbn);
            enlacePortada.setUrl(generarUrlPortada(isbn));
            listaEnlaces.add(enlacePortada);
        });
        
        return listaEnlaces;
    }
    
    /**
     * Crea un url de una portada apartir de su isbn
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return Devuelve el url creado
     */
    private String generarUrlPortada(String isbn) {
        StringBuilder sb = new StringBuilder(Constantes.URL_REPOS_PORTADAS);
        sb.append(isbn.substring(12, 13));
        sb.append('/');
        sb.append(isbn);
        sb.append(Constantes.FORMATO_JPG);
        return sb.toString();
    }
    
    /**
     * Crea una ruta local donde se encuentra ubicada la portada descargada
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return Devuelve la ruta completa creada.
     */
    private String generarRutaLocalPortada(String isbn) {
        StringBuilder sb = new StringBuilder(Constantes.DIRECTORIO_DESCARGA_PORTADAS);
        sb.append(isbn);
        sb.append(Constantes.FORMATO_JPG);
        return sb.toString();
    }
    
    /**
     * Descarga una portada en el directorio indicado, primeramente comprueba si
     * la imagen existe, en caso de que no exista inicia una nueva búsqueda de
     * la misma.
     *
     * @param directorioDescarga ruta donde se descargará la imagen.
     * @param url enlace donde se encuentra una posible imagen.
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     */
    private void descargarPortada(String directorioDescarga, String url, String isbn) {
        
        Logger.getLogger(SpiderPortadasServiceImpl.class.getName()).log(Level.INFO, "URL = {0}", url);
        
        StringBuilder sb = new StringBuilder(directorioDescarga);
        sb.append(isbn);
        sb.append(Constantes.FORMATO_JPG);

        try {
            URL imagenUrl = new URL(url);

            if (esImagenValida(imagenUrl)) {
                ReadableByteChannel rbc = Channels.newChannel(imagenUrl.openStream());
                try (FileOutputStream outputStream = new FileOutputStream(sb.toString())) {
                    outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                }
                /* Se agrega el isbn a la lista para actualizar posteriormente */
                isbnsActualizar.add(isbn);
                Logger.getLogger(SpiderPortadasServiceImpl.class.getName()).log(Level.INFO, "Se descargo la portada = {0}", isbn);
            } else {
                iniciarNuevaBusquedaURLPortada(directorioDescarga, isbn);
            }
        } catch (MalformedURLException e) {
            iniciarNuevaBusquedaURLPortada(directorioDescarga, isbn);
        } catch (FileNotFoundException e) {
            iniciarNuevaBusquedaURLPortada(directorioDescarga, isbn);
        } catch (IOException e) {
            iniciarNuevaBusquedaURLPortada(directorioDescarga, isbn);
        }
    }
    
    /**
     * Verifica si una imagen ha sido descargada correctamente de la web a
     * través de la clase ImageIcon analizando sus propiedades "width" y
     * "height" con sus mínimos establecidos como aceptables.
     *
     * @param imagenUrl Objeto URL que contiene el enlace de la imagen en
     * internet.
     * @return true en caso de que las propiedades de la imagen sean mayor a las
     * propiedades establecidas y false en caso contrario.
     * @throws
     */
    private boolean esImagenValida(URL imagenUrl) {
        try {
            ImageIcon image = new ImageIcon(imagenUrl);
            return image.getIconWidth() >= Constantes.ANCHO_MINIMO_ACEPTABLE && image.getIconHeight() >= Constantes.ALTURA_MINIMA_ACEPTABLE;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Empieza una nueva búsqueda de una imagen que no fue encontrada en el
     * primer intento, construyendo un url iniciando con el formato FNAC.
     *
     * @param directorioDescarga directorio donde se descargará la imagen
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     */
    private void iniciarNuevaBusquedaURLPortada(String directorioDescarga, String isbn) {
        String url = buscarProximoURLPortada(EnumTipoURL.FNAC, isbn);

        if (url != null) {
            descargarPortada(directorioDescarga, url, isbn);
        }
    }

    /**
     * Se encarga de buscar un nuevo url en base al isbn para buscar una imagen
     * que no fue encontrada en un primer intento, irá pasando por distintos
     * formatos llamandose de forma recursiva hasta conseguir una imagen válida
     * o agotarse los intentos.
     *
     * @param proxTipoURL Tipo de URL a construir. Los tipos existentes son:
     * FNAC, CEGAL, LSF y CUSPIDE.
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return String del URL armado apartir del formato, en caso contrario
     * returna null.
     */
    private String buscarProximoURLPortada(EnumTipoURL proxTipoURL, String isbn) {
        
        if (isbn.length() < 13) {
            return null;
        }

        String url = null;

        switch (proxTipoURL) {
            case FNAC:
                url = construirURLFNAC(isbn);
                proxTipoURL = EnumTipoURL.CEGAL;
                break;

            case CEGAL:
                url = construirURLCEGAL(isbn);
                proxTipoURL = EnumTipoURL.LSF;
                break;

            case LSF:
                url = construirURLLSF(isbn);
                proxTipoURL = EnumTipoURL.CUSPIDE;
                break;

            case CUSPIDE:
                url = construirURLCUSPIDE(isbn);
                proxTipoURL = EnumTipoURL.CLOUD_FRONT;
                break;

            case CLOUD_FRONT:
                url = construirURLCLOUDFRONT(isbn);
                proxTipoURL = null;
                break;
        }

        try {

            if (esImagenValida(new URL(url))) {
                return url;
            }

            if (proxTipoURL == null) {
                return null;
            }

            return buscarProximoURLPortada(proxTipoURL, isbn);
        } catch (MalformedURLException e) {
            if (proxTipoURL == null) {
                return null;
            }

            return buscarProximoURLPortada(proxTipoURL, isbn);
        }
    }

    /**
     * Construye un URL apartir del formato FNAC Por ejemplo: La url se
     * construye usando primero los 3 ultimos díagitos del isbn al inverso y
     * luego el isbn completo, el isbn termina en 029 y que la url tiene /9/2/0
     * http://multimedia.fnac.com/multimedia/ES/images_produits/ES/ZoomPE/9/2/0/9788416237029.jpg
     *
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return String con el url construído.
     */
    private String construirURLFNAC(String isbn) {

        char c = '/';
        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.URL_FNAC);

        StringBuilder inverso = new StringBuilder();
        char[] ultDigitosIsbn = isbn.substring((isbn.length() - 3), isbn.length()).toCharArray();

        for (char d : ultDigitosIsbn) {
            inverso.append(d);
            inverso.append(c);
        }

        ultDigitosIsbn = inverso.reverse().toString().toCharArray();

        for (char d : ultDigitosIsbn) {
            sb.append(d);
        }

        sb.append(c);
        sb.append(isbn);
        sb.append(Constantes.FORMATO_JPG);

        return sb.toString();
    }

    /**
     * Construye un URL apartir del formato CEGAL Por ejemplo: La url se
     * construye usando parte del isbn, en este caso los 7 primeros dígitos
     * seguidos del caracter '/' y luego el isbn completo con extensión .gif
     * http://static.cegal.es/imagenes/marcadas/9788416/978841629043.gif
     *
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return String con el url construído.
     */
    private String construirURLCEGAL(String isbn) {

        char c = '/';
        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.URL_CEGAL);
        sb.append(isbn.substring(0, 7));
        sb.append(c);
        sb.append(isbn);
        sb.append(Constantes.FORMATO_GIF);
        return sb.toString();
    }

    /**
     * Construye un URL apartir del formato LSF Por ejemplo: La url se construye
     * usando un fragmento fijo seguigo del isbn que corresponda.
     * http://content.lsf.com.ar/getcover.ashx?ISBN=9789504922599&size=3&coverNumber=1
     *
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return String con el url construído.
     */
    private String construirURLLSF(String isbn) {

        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.URL_LSF);
        sb.append(isbn);
        sb.append("&size=3&coverNumber=1");
        return sb.toString();
    }

    /**
     * Construye un URL apartir del formato CUSPIDE Por ejemplo: La url se
     * construye usando un fragmento fijo seguigo del isbn que corresponda.
     * http://content.cuspide.com/getcover.ashx?ISBN=9789504946403&size=3&coverNumber=1&id_com=1113
     *
     * @param isbn International Standard Book Number, Identificador único de
     * cada imagen del libro.
     * @return String con el url construído.
     */
    private String construirURLCUSPIDE(String isbn) {

        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.URL_CUSPIDE);
        sb.append(isbn);
        sb.append("&size=3&coverNumber=1&id_com=1113");
        return sb.toString();
    }

    /**
     * Construye un URL apartir del formato CLOUDFRONT Por ejemplo: La url se
     * construye usando un fragmento fijo seguigo de los 4 primeros dígitos del
     * isbn seguidos del caracter '/' y luego los 4 próximos y finalmente el
     * isbn completo.
     * http://d39ttiideeq0ys.cloudfront.net/assets/images/book/large/9781/4447/9781444783056.jpg
     *
     * @param isbn International Standard Book Number, Identificador único de
     * cada libro.
     * @return String con el url construído.
     */
    private String construirURLCLOUDFRONT(String isbn) {

        char c = '/';
        StringBuilder sb = new StringBuilder();
        sb.append(Constantes.URL_CLOUD_FRONT);
        sb.append(isbn.substring(0, 4));
        sb.append(c);
        sb.append(isbn.substring(4, 8));
        sb.append(c);
        sb.append(isbn);
        sb.append(Constantes.FORMATO_JPG);
        return sb.toString();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Métodos públicos">
    /**
     * 
     * @return Cantidad de portadas que han sido descargadas.
     */
    public int getCantidadPortadasDescargadas() {
        return isbnsActualizar == null ? 0 : isbnsActualizar.size();
    }
    //</editor-fold>
}